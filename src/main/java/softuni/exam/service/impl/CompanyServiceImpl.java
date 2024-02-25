package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CompanyImportDTO;
import softuni.exam.models.dto.CompanyImportRootDTO;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final String COMPANIES_FILE_PATH = "src/main/resources/files/xml/companies.xml";

    private final CompanyRepository companyRepository;

    private final CountryRepository countryRepository;

    private final XmlParser xmlParser;

    private final Validator validator;

    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CountryRepository countryRepository, XmlParser xmlParser, Validator validator, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files.readString(Path.of(COMPANIES_FILE_PATH));
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {
        CompanyImportRootDTO companyImportRootDTO = this.xmlParser.fromFile(COMPANIES_FILE_PATH, CompanyImportRootDTO.class);

        List<CompanyImportDTO> companyDTOs = companyImportRootDTO.getCompanies();

        List<String> result = new ArrayList<>();

        for (CompanyImportDTO companyDTO : companyDTOs) {
            Set<ConstraintViolation<CompanyImportDTO>> errors = this.validator.validate(companyDTO);

            if (errors.isEmpty()){
                Optional<Company> byName = this.companyRepository.findByName(companyDTO.getName());
                if (byName.isPresent()){
                    result.add("Invalid company");
                } else {
                    Company mappedCompany = this.modelMapper.map(companyDTO, Company.class);
                    Optional<Country> byId = this.countryRepository.findById(companyDTO.getCountry());
                    LocalDate dateEstablished = LocalDate.parse(companyDTO.getDateEstablished(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    mappedCompany.setCountry(byId.get());
                    mappedCompany.setDateEstablished(dateEstablished);
                    companyRepository.save(mappedCompany);
                    result.add(String.format("Successfully imported company %s - %d", companyDTO.getName(), companyDTO.getCountry()));
                }
            } else {
                result.add("Invalid company");
            }
        }

        return String.join("\n", result);
    }
}
