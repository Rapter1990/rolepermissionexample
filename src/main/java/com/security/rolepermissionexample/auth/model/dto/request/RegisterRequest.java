package com.security.rolepermissionexample.auth.model.dto.request;

import com.security.rolepermissionexample.auth.model.Permission;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

/**
 * Represents a register request as {@link RegisterRequest} with user details
 * including email, password, first name, last name,
 * phone number, roles, and permissions.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @Email(message = "Please enter valid e-mail address")
    @Size(min = 7, message = "Minimum e-mail length is 7 characters.")
    private String email;

    @Size(min = 8)
    private String password;

    @NotBlank(message = "First name can't be blank.")
    private String firstName;

    @NotBlank(message = "Last name can't be blank.")
    private String lastName;

    @NotBlank(message = "Phone number can't be blank.")
    @Size(min = 11, max = 20)
    private String phoneNumber;

    @NotEmpty(message = "Role name can't be empty.")
    private List<String> role;

    @NotEmpty(message = "Permission can't be empty.")
    private List<String> permissions;

}
