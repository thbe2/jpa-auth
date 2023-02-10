package fr.ajc.securityExoFinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.ajc.securityExoFinal.models.CustomRole;

@Repository
public interface CustomRoleRepository extends JpaRepository <CustomRole, Long> {

}
