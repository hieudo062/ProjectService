package com.unikom.projectservice.controller.partner;

import com.unikom.projectservice.dto.PartnerDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/partnerRest")
public class PartnerRestController {
    @Autowired
    private IPartnerRestController partnerResController;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return partnerResController.findById(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return partnerResController.findAll();
    }
}
