package br.com.brunosalata.springsecurity_docker.repository;

import br.com.brunosalata.springsecurity_docker.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Salata Lima
 * github.com/Brunosalata
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
