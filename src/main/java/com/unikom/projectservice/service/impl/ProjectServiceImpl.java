package com.unikom.projectservice.service.impl;

import com.unikom.projectservice.IProjectService;
import com.unikom.projectservice.controller.partner.IPartnerController;
import com.unikom.projectservice.dto.PartnerDTO;
import com.unikom.projectservice.dto.ProjectDTO;
import com.unikom.projectservice.dto.request.Search;
import com.unikom.projectservice.model.Project;
import com.unikom.projectservice.repository.IProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private IPartnerController partnerController;

    private IProjectRepository projectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "Project does not exist"));
        projectRepository.delete(project);
    }

    @Override
    public ProjectDTO findById(Long id) {
        ProjectDTO projectDTO = projectRepository.findById(id).map(ProjectDTO::new).orElseThrow(() -> new ResourceNotFoundException("Project", "Project does not exist"));
        projectDTO.setPartnerDTO(findPartnerById(projectDTO.getPartner()));
        return projectDTO;
    }

    @Override
    public Boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    @Override
    public List<ProjectDTO> search(Search search, Pageable pageable) {

        List<PartnerDTO> partnerDTOs = partnerController.findAll();
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("select p ");
        hql.append("from Project p ");
        hql.append("where 1=1 ");
        if (search.getCode() != null) {
            hql.append("and p.code like :code ");
            params.put("code", "%" + search.getCode() + "%");
        }
        if (search.getName() != null) {
            hql.append("and p.name like :name");
            params.put("name", "%" + search.getName() + "%");
        }
        Query query = entityManager.createQuery(hql.toString())
                .setMaxResults(pageable.getPageSize())
                .setFirstResult((int) pageable.getOffset());;
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        List<Project> projects = query.getResultList();
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project project: projects) {
            projectDTOs.add(new ProjectDTO(project));
        }
//        List<ProjectDTO> projectDTOsz = query.getResultList().stream().map(ProjectDTO::new);

        for (ProjectDTO project : projectDTOs) {
            for (PartnerDTO partner : partnerDTOs) {
                if (project.getPartner() == partner.getId()) {
                    project.setPartnerDTO(partner);
                }
            }
        }

        return projectDTOs;
    }

    @Override
    public long count(Search search) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("select p ");
        hql.append("from Project p ");
        hql.append("where 1=1 ");
        if (search.getCode() != null) {
            hql.append("and p.code like :code ");
            params.put("code", "%" + search.getCode() + "%");
        }
        if (search.getName() != null) {
            hql.append("and p.name like :name");
            params.put("name", "%" + search.getName() + "%");
        }
        Query query = entityManager.createQuery(hql.toString());
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        return (long) query.getSingleResult();
    }

    public PartnerDTO findPartnerById(Long id) {
        return partnerController.findById(id);
    }

    public List<PartnerDTO> findAllPartner() {
        return partnerController.findAll();
    }

}
