package com.unikom.projectservice.dto.response;

import com.unikom.projectservice.dto.PartnerDTO;
import lombok.Data;

import java.util.List;

@Data
public class PartnerListResponse {

    private int status;
    private List<PartnerDTO> data;

    public PartnerListResponse(int status, List<PartnerDTO> data) {
        this.status = status;
        this.data = data;
    }

}
