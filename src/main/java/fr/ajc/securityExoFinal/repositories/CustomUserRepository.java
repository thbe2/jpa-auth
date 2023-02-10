package fr.ajc.securityExoFinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.ajc.securityExoFinal.models.CustomUser;

@Repository
public interface CustomUserRepository extends JpaRepository <CustomUser, Long>{

	Optional<CustomUser> findByUsername(String username);
}
