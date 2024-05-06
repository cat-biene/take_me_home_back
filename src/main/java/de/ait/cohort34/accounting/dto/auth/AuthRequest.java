package de.ait.cohort34.accounting.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {

    private String managerName;
    private String password;
}
