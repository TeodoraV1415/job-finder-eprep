package softuni.exam.models.entity;

import softuni.exam.models.enums.StatusType;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", unique = true, nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone", unique = true, nullable = true)
    private String phone;

    @Column(name = "status_type")
    @Enumerated(EnumType.ORDINAL)
    private StatusType statusType;

    @ManyToOne
    private Country country;

    public Person() {
    }

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Person setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public Person setStatusType(StatusType statusType) {
        this.statusType = statusType;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public Person setCountry(Country country) {
        this.country = country;
        return this;
    }
}
