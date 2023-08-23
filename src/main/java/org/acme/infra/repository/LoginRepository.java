package org.acme.infra.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.model.User;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class LoginRepository implements PanacheRepository<User> {
    public Optional<User> findByName(String username) {
        return find("name", username).firstResultOptional();
    }

    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public List<User> findAllByStatus() {
        return listAll();
    }
}
