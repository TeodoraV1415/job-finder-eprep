package softuni.exam.models.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyImportDTO {

    @XmlElement(name = "companyName")
    @Size(min = 2, max = 40)
    private String name;

    @XmlElement(name = "dateEstablished")
    private String dateEstablished;

    @XmlElement(name = "website")
    @Size(min = 2, max = 30)
    private String website;

    @XmlElement(name = "countryId")
    private Long country;

    public CompanyImportDTO() {
    }

    public String getName() {
        return name;
    }

    public CompanyImportDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDateEstablished() {
        return dateEstablished;
    }

    public CompanyImportDTO setDateEstablished(String dateEstablished) {
        this.dateEstablished = dateEstablished;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CompanyImportDTO setWebsite(String website) {
        this.website = website;
        return this;
    }

    public Long getCountry() {
        return country;
    }

    public CompanyImportDTO setCountry(Long country) {
        this.country = country;
        return this;
    }
}
