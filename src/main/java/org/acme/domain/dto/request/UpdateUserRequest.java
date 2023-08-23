package org.acme.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.BooleanFlag;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @Size(min = 6)
    @NotNull
    private String username;
    @Email
    @NotBlank
    @NotNull
    private String email;

    @BooleanFlag
    private Boolean status;

    @Email
    @NotBlank
    @NotNull
    private String password;

}
