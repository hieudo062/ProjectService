package com.unikom.projectservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "team_size")
    private Long teamSize;

    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "time_start")
    private LocalDate timeStart;

    @Column(name = "time_finish")
    private LocalDate timeFinish;

    @ManyToOne
    private ProjectStatus projectStatus;

}
