package com.hopair.repository;

import com.hopair.entity.User;
import com.hopair.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByAuth0Sub(String auth0Sub);
    List<User> findByAgencyId(Long agencyId);
    List<User> findByAgencyIdAndRole(Long agencyId, Role role);
}
