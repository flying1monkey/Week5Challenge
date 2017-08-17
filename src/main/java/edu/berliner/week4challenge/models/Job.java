package edu.berliner.week4challenge.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String company;
    private String title;
    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;
    private String firstDuty;
    private String secondDuty;

}
