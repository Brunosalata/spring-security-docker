package br.com.brunosalata.springsecurity_docker.repository;

import br.com.brunosalata.springsecurity_docker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Bruno Salata Lima
 * github.com/Brunosalata
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
