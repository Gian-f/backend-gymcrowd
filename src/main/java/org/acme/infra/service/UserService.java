package org.acme.infra.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.dto.request.CreateUserRequest;
import org.acme.domain.dto.request.UpdateUserRequest;
import org.acme.domain.dto.response.CreateUserResponse;
import org.acme.domain.dto.response.UpdateUserResponse;
import org.acme.domain.model.User;
import org.acme.infra.mapper.UserMapper;
import org.acme.infra.repository.LoginRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    LoginRepository repository;

    @Inject
    UserMapper mapper;

    public List<CreateUserResponse> listAll() {
        List<User> users = repository.findAllByStatus();
        return users.stream().map(mapper::toCreateDto).collect(Collectors.toList());
    }

    public CreateUserResponse findById(Long id) {
        User user = repository.findById(id);
        return mapper.toCreateDto(user);
    }

    public Optional<User> findByName(String username) {
        return repository.findByName(username);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public CreateUserResponse create(CreateUserRequest createUserRequest) {
        User userEntity = mapper.toEntity(createUserRequest);
        repository.persist(userEntity);
        return mapper.toCreateDto(userEntity);
    }

    public UpdateUserResponse update(Long id, UpdateUserRequest updateUserRequest) {
        User user = repository.findById(id);
        user = mapper.updateEntityFromDto(updateUserRequest, user);
        repository.persist(user);
        return mapper.toUpdateDto(user);
    }
}
