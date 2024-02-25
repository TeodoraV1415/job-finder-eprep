package softuni.exam.models.dto;

import softuni.exam.models.enums.StatusType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class PersonImportDTO {

    @Email
    private String email;

    @Size(min = 2, max = 30)
    private String firstName;

    @Size(min = 2, max = 30)
    private String lastName;

    @Size(min = 2, max = 13)
    private String phone;

    private StatusType statusType;

    private Long country;

    public PersonImportDTO() {
    }

    public String getEmail() {
        return email;
    }

    public PersonImportDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonImportDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonImportDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PersonImportDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public PersonImportDTO setStatusType(StatusType statusType) {
        this.statusType = statusType;
        return this;
    }

    public Long getCountry() {
        return country;
    }

    public PersonImportDTO setCountry(Long country) {
        this.country = country;
        return this;
    }
}
