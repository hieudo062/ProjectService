package com.unikom.projectservice.controller.partner;

import com.unikom.projectservice.dto.PartnerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "partner-service")
public interface IPartnerController {

    @GetMapping("/partner/test")
    List<PartnerDTO> findAll();

    @GetMapping("/partner/test/findById/{id}")
    PartnerDTO findById(@PathVariable long id);

}
