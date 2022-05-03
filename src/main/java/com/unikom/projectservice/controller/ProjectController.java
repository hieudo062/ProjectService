package com.unikom.projectservice.controller;

import com.unikom.projectservice.IProjectService;
import com.unikom.projectservice.dto.ProjectDTO;
import com.unikom.projectservice.dto.request.Search;
import com.unikom.projectservice.dto.response.FailedResponse;
import com.unikom.projectservice.dto.response.Message;
import com.unikom.projectservice.dto.response.SuccessResponse;
import com.unikom.projectservice.model.Project;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/projects")
public class ProjectController {

    private IProjectService projectService;

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project projectRequest) {
        Project project = projectService.save(projectRequest);
        ProjectDTO projectDTOResponse = modelMapper.map(project, ProjectDTO.class);
        return new ResponseEntity<>(new SuccessResponse(1, projectDTOResponse), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        if (projectService.existsById(id)) {
            ProjectDTO projectDTO = projectService.findById(id);
            return new ResponseEntity<>(new SuccessResponse(1, projectDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FailedResponse(0, "404", "Project does not exist"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Long id, @RequestBody Project projectRequest) {
        if (projectService.existsById(id)) {
            projectRequest.setId(id);
            Project p = projectService.save(projectRequest);
            ProjectDTO projectDTOResponse = modelMapper.map(p, ProjectDTO.class);
            return new ResponseEntity<>(new SuccessResponse(1, projectDTOResponse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FailedResponse(0, "404", "Project does not exist"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        if (projectService.existsById(id)) {
            projectService.deleteById(id);
            return new ResponseEntity<>(new Message(1, "Deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FailedResponse(0, "404", "Project does not exist"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> search(@RequestParam(required = false) String code,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = true, defaultValue = "1") int page,
                                                   @RequestParam(required = true, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Search s = new Search(code, name);

        return new ResponseEntity<>(projectService.search(s, pageable), HttpStatus.OK);
    }


}
