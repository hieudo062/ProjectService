package com.unikom.projectservice;

import com.unikom.projectservice.dto.ProjectDTO;
import com.unikom.projectservice.dto.request.Search;
import com.unikom.projectservice.model.Project;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProjectService {
    Project save(Project project);
    void deleteById(Long id);
    ProjectDTO findById(Long id);
    Boolean existsById(Long id);
    List<ProjectDTO> search(Search search, Pageable pageable);
    long count(Search search);
}
