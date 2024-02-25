package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double salary;

    @Column(name = "hoursaweek", nullable = false)
    private Double hoursAWeek;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private Company company;

    public Job() {
    }

    public long getId() {
        return id;
    }

    public Job setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Job setTitle(String title) {
        this.title = title;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public Job setSalary(Double salary) {
        this.salary = salary;
        return this;
    }

    public Double getHoursAWeek() {
        return hoursAWeek;
    }

    public Job setHoursAWeek(Double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Job setDescription(String description) {
        this.description = description;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Job setCompany(Company company) {
        this.company = company;
        return this;
    }
}
