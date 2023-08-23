package org.acme.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "Informe o nome")
    @NotNull
    private String username;

    @NotBlank(message = "Informe o email válido")
    @Email
    @NotNull
    private String email;

    @BooleanFlag
    private Boolean status;

    @NotBlank(message = "Informe o cpf válido")
    @NotNull
    private String cpf;

    @NotBlank(message = "Informe a senha")
    @Size(min = 8, max = 15)
    @NotNull
    private String password;

}
