package de.ait.cohort34.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String fullName;
    private String avatar;
    private String login;
    private String email;
    private String website;
    private String phone;
    private String telegram;
    private String role;
}
