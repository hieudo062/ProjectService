package com.unikom.projectservice.controller.partner;

import com.unikom.projectservice.dto.PartnerDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/partnerRest")
public class PartnerController {
    @Autowired
    private IPartnerController partnerResController;

    @GetMapping("/findById/{id}")
    public PartnerDTO findById(@PathVariable long id) {
        return partnerResController.findById(id).getData();
    }

    @GetMapping("/findAll")
    public List<PartnerDTO> findAll() {
        return partnerResController.findAll().getData();
    }
}
