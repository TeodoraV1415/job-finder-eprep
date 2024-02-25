package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {

    private static final String COUNTRIES_FILE_PATH = "src/main/resources/files/json/countries.json";

    private final CountryRepository countryRepository;

    private final Gson gson;

    private final Validator validator;

    private final ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, Validator validator, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFileContent() throws IOException {
        return Files.readString(Path.of(COUNTRIES_FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {
        String countriesJson = this.readCountriesFileContent();

        CountryImportDTO [] countryImportDTOs = this.gson.fromJson(countriesJson, CountryImportDTO[].class);

        List<String> result = new ArrayList<>();

        for (CountryImportDTO countryImportDTO : countryImportDTOs) {
            Set<ConstraintViolation<CountryImportDTO>> errors = this.validator.validate(countryImportDTO);

            if (errors.isEmpty()){
                Country mappedCountry = this.modelMapper.map(countryImportDTO, Country.class);
                this.countryRepository.save(mappedCountry);
                result.add(String.format("Successfully imported country %s - %s", countryImportDTO.getName(), countryImportDTO.getCountryCode()));
            } else {
                result.add("Invalid country");
            }
        }
        return String.join("\n", result);
    }

}
