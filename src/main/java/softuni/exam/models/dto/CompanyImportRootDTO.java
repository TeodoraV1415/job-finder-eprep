package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyImportRootDTO {

    @XmlElement(name = "company")
    private List<CompanyImportDTO> companies;

    public CompanyImportRootDTO() {
    }

    public List<CompanyImportDTO> getCompanies() {
        return companies;
    }

    public CompanyImportRootDTO setCompanies(List<CompanyImportDTO> companies) {
        this.companies = companies;
        return this;
    }
}
