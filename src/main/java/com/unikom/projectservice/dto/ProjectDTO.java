package com.unikom.projectservice.dto;

import com.unikom.projectservice.model.Project;
import com.unikom.projectservice.model.ProjectStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProjectDTO {
    private Long id;

    private String code;

    private String name;

    private Long teamSize;

    private String namePartner;

    private LocalDate timeStart;

    private LocalDate timeFinish;

    private ProjectStatus projectStatus;

    public ProjectDTO(Project source) {
        BeanUtils.copyProperties(source,this);
    }
}
