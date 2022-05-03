package com.unikom.projectservice.dto.response;

import com.unikom.projectservice.dto.ProjectDTO;
import lombok.Data;

@Data
public class SuccessResponse {
    private int status;
    private ProjectDTO data;

    public SuccessResponse(int status, ProjectDTO data) {
        this.status = status;
        this.data = data;
    }
}
