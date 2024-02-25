package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String website;

    @Column(name = "date_established", nullable = false)
    private LocalDate dateEstablished;

    @OneToMany
    private List<Job> jobs;

    @ManyToOne
    private Country country;

    public Company() {
    }

    public long getId() {
        return id;
    }

    public Company setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public Company setWebsite(String website) {
        this.website = website;
        return this;
    }

    public LocalDate getDateEstablished() {
        return dateEstablished;
    }

    public Company setDateEstablished(LocalDate dateEstablished) {
        this.dateEstablished = dateEstablished;
        return this;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Company setJobs(List<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public Company setCountry(Country country) {
        this.country = country;
        return this;
    }
}
