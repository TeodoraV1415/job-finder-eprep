package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryImportDTO {

    @Size(min = 2, max = 30)
    private String name;

    @Size(min = 2, max = 19)
    private String countryCode;

    @Size(min = 2, max = 19)
    private String currency;

    public CountryImportDTO() {
    }

    public String getName() {
        return name;
    }

    public CountryImportDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public CountryImportDTO setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public CountryImportDTO setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
