package pl.ppyrczak.busschedulesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ppyrczak.busschedulesystem.security.UserRole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
}
