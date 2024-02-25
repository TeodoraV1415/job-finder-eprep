package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobImportRootDTO {

    @XmlElement(name = "job")
    private List<JobImportDTO> jobs;

    public JobImportRootDTO() {
    }

    public List<JobImportDTO> getJobs() {
        return jobs;
    }

    public JobImportRootDTO setJobs(List<JobImportDTO> jobs) {
        this.jobs = jobs;
        return this;
    }
}
