package com.unikom.projectservice.controller.partner;

import com.unikom.projectservice.dto.PartnerDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private IPartnerController partnerController;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity(partnerController.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public List<PartnerDTO> findAll() {
        return partnerController.findAll();
    }

}
