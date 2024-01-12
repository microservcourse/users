package ru.gk.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String middleName;
    @NotBlank
    private String gender;
    @NotBlank
    private Date dateOfBirth;
    private String city;
    private String avatarUrl;
    private String aboutMe;
    private String nickname;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;

}
