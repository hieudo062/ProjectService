package com.unikom.projectservice.dto.response;

import com.unikom.projectservice.dto.PartnerDTO;
import lombok.Data;

@Data
public class PartnerResponse {

    private int status;
    private PartnerDTO data;

    public PartnerResponse(int status, PartnerDTO data) {
        this.status = status;
        this.data = data;
    }

}
