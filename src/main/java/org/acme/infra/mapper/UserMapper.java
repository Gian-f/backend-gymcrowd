package org.acme.infra.mapper;

import org.acme.domain.dto.request.CreateUserRequest;
import org.acme.domain.dto.request.UpdateUserRequest;
import org.acme.domain.dto.response.CreateUserResponse;
import org.acme.domain.dto.response.UpdateUserResponse;
import org.acme.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta")
public interface UserMapper {

    User toEntity(CreateUserRequest dto);

    CreateUserResponse toCreateDto(User entity);

    UpdateUserResponse toUpdateDto(User entity);

    User updateEntityFromDto(UpdateUserRequest dto, @MappingTarget User entity);

    User createEntityFromDto(CreateUserRequest dto, @MappingTarget User entity);
}