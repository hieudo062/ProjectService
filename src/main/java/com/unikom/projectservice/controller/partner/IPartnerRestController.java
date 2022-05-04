package com.unikom.projectservice.controller.partner;

import com.unikom.projectservice.dto.PartnerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "partner-service")
public interface IPartnerRestController {

    @GetMapping("/partner")
    ResponseEntity<?> findAll();

    @GetMapping("/partner/{id}")
    ResponseEntity<?> findById(@PathVariable long id);

}
