package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Person;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.PersonService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private static final String PEOPLE_FILE_PATH = "src/main/resources/files/json/people.json";

    private final PersonRepository personRepository;

    private final CountryRepository countryRepository;

    private final Gson gson;

    private final Validator validator;

    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, CountryRepository countryRepository, Gson gson, Validator validator, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return Files.readString(Path.of(PEOPLE_FILE_PATH));
    }

    @Override
    public String importPeople() throws IOException, JAXBException {
        String peopleJson = this.readPeopleFromFile();

        PersonImportDTO[] personImportDTOs = this.gson.fromJson(peopleJson, PersonImportDTO[].class);

        List<String> result = new ArrayList<>();

        for (PersonImportDTO personImportDTO : personImportDTOs) {
            Set<ConstraintViolation<PersonImportDTO>> errors = this.validator.validate(personImportDTO);

            if (errors.isEmpty()){
                Optional<Person> byEmail = this.personRepository.findByEmail(personImportDTO.getEmail());
                Optional<Person> byFirstName = this.personRepository.findByFirstName(personImportDTO.getFirstName());
                Optional<Person> byPhone = this.personRepository.findByPhone(personImportDTO.getPhone());

                if (byEmail.isPresent() || byPhone.isPresent() || byFirstName.isPresent()){
                    result.add("Invalid person");
                } else {
                    Optional<Country> byId = this.countryRepository.findById(personImportDTO.getCountry());
                    Person mappedPerson = this.modelMapper.map(personImportDTO, Person.class);
                    mappedPerson.setCountry(byId.get());
                    this.personRepository.save(mappedPerson);
                    result.add(String.format("Successfully imported person %s %s", personImportDTO.getFirstName(), personImportDTO.getLastName()));
                }
            } else {
                result.add("Invalid person");
            }

        }

        return String.join("\n", result);
    }
}
