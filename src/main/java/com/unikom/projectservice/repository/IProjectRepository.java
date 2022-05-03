package com.unikom.projectservice.repository;

import com.unikom.projectservice.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p from Project p where p.code like %:code% and p.name like %:name%")
    Page<Project> searchProject(String code, String name, Pageable pageable);

    boolean existsById(Long id);

}
