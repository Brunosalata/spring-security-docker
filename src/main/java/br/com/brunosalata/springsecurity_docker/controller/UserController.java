package br.com.brunosalata.springsecurity_docker.controller;

import br.com.brunosalata.springsecurity_docker.controller.dto.UserRequestDTO;
import br.com.brunosalata.springsecurity_docker.entities.Role;
import br.com.brunosalata.springsecurity_docker.entities.User;
import br.com.brunosalata.springsecurity_docker.repository.RoleRepository;
import br.com.brunosalata.springsecurity_docker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

/**
 * @author Bruno Salata Lima
 * github.com/Brunosalata
 */
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDTO userDTO){

        var basicRole = roleRepository.findByRole(Role.Values.BASIC.name());

        var userFromDb = userRepository.findByUsername(userDTO.username());
        if(userFromDb.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var newUser = new User();
        newUser.setUsername(userDTO.username());
        newUser.setPassword(passwordEncoder.encode(userDTO.password()));
        newUser.setRoles(Set.of(basicRole));

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")        // Possible for @EnableMethodSecurity annotation
    public ResponseEntity<List<User>> findAll() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
