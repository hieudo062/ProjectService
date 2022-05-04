package com.unikom.projectservice.controller.partner;

import com.unikom.projectservice.dto.response.PartnerListResponse;
import com.unikom.projectservice.dto.response.PartnerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "partner-service")
public interface IPartnerController {

    @GetMapping("/partner/findAll")
    PartnerListResponse findAll();

    @GetMapping("/partner/{id}")
    PartnerResponse findById(@PathVariable long id);

}
