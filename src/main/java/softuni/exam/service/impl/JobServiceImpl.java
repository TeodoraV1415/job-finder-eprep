package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.JobImportDTO;
import softuni.exam.models.dto.JobImportRootDTO;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;
import softuni.exam.util.XmlParser;

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
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private static final String JOBS_FILE_PATH = "src/main/resources/files/xml/jobs.xml";

    private final JobRepository jobRepository;

    private final CompanyRepository companyRepository;

    private final XmlParser xmlParser;

    private final Validator validator;

    private final ModelMapper modelMapper;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository, XmlParser xmlParser, Validator validator, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files.readString(Path.of(JOBS_FILE_PATH));
    }

    @Override
    public String importJobs() throws IOException, JAXBException {
        JobImportRootDTO jobImportRootDTO = this.xmlParser.fromFile(JOBS_FILE_PATH, JobImportRootDTO.class);

        List<JobImportDTO> jobDTOs = jobImportRootDTO.getJobs();

        List<String> result = new ArrayList<>();

        for (JobImportDTO jobDTO : jobDTOs) {
            Set<ConstraintViolation<JobImportDTO>> errors = this.validator.validate(jobDTO);
            if (errors.isEmpty()){
                Job mappedJob = this.modelMapper.map(jobDTO, Job.class);
                Optional<Company> companyById = this.companyRepository.findById(jobDTO.getCompany());
                mappedJob.setCompany(companyById.get());
                this.jobRepository.save(mappedJob);
                result.add(String.format("Successfully imported job %s", jobDTO.getTitle()));
            } else {
                result.add("Invalid job");
            }
        }

        return String.join("\n", result);
    }

    @Override
    public String getBestJobs() {
        List<Job> bestJobs = jobRepository.findBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc(5000.00, 30.00);

        return bestJobs.stream()
                .map(job -> String.format("Job title: %s\n-Salary: %.2f$\n --Hours a week: %.2fh.\n",
                        job.getTitle(), job.getSalary(), job.getHoursAWeek()))
                .collect(Collectors.joining("\n"));
    }
}
