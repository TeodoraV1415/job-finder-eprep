package softuni.exam.models.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JobImportDTO {

    @XmlElement(name = "jobTitle")
    @Size(min = 2, max = 40)
    private String title;

    @XmlElement(name = "hoursAWeek")
    @DecimalMin(value = "10.0")
    private Double hoursAWeek;

    @XmlElement(name = "salary")
    @DecimalMin(value = "300.0")
    private Double salary;

    @XmlElement(name = "description")
    @Size(min = 5)
    private String description;

    @XmlElement(name = "companyId")
    private Long company;

    public JobImportDTO() {
    }

    public String getTitle() {
        return title;
    }

    public JobImportDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Double getHoursAWeek() {
        return hoursAWeek;
    }

    public JobImportDTO setHoursAWeek(Double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public JobImportDTO setSalary(Double salary) {
        this.salary = salary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobImportDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCompany() {
        return company;
    }

    public JobImportDTO setCompany(Long company) {
        this.company = company;
        return this;
    }
}
