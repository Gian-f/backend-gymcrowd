package org.acme.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserResponse {
    Long id;
    String username;
    String email;
    String cpf;
    String password;
    Date createdAt;
    Date modifiedAt;
}
