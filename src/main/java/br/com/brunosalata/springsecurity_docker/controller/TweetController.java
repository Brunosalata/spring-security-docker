package br.com.brunosalata.springsecurity_docker.controller;

import br.com.brunosalata.springsecurity_docker.controller.dto.CreateTweetDTO;
import br.com.brunosalata.springsecurity_docker.controller.dto.TweetItemDTO;
import br.com.brunosalata.springsecurity_docker.controller.dto.TweetResponseDTO;
import br.com.brunosalata.springsecurity_docker.entities.Role;
import br.com.brunosalata.springsecurity_docker.entities.Tweet;
import br.com.brunosalata.springsecurity_docker.repository.TweetRepository;
import br.com.brunosalata.springsecurity_docker.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

/**
 * @author Bruno Salata Lima
 * github.com/Brunosalata
 */
@RestController
public class TweetController {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetController(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/feed")
    public ResponseEntity<TweetResponseDTO> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        var tweets = tweetRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimestamp"))
                .map(tweet -> new TweetItemDTO(
                        tweet.getTweetId(),
                        tweet.getContent(),
                        tweet.getUser().getUsername())
                );

        return ResponseEntity.ok(new TweetResponseDTO(
                tweets.getContent(), page, pageSize, tweets.getTotalPages(), tweets.getTotalElements()));
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDTO tweetDTO, JwtAuthenticationToken token) {

        var user = userRepository.findById(String.valueOf(UUID.fromString(token.getName())));
        System.out.println(user);
        var tweet = new Tweet();
        tweet.setUser(user.get());
        tweet.setContent(tweetDTO.content());
        tweetRepository.save(tweet);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId, JwtAuthenticationToken token){

        var user = userRepository.findById(String.valueOf(token.getName()));
        var tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        var isAdmin = user.get().getRoles()
                .stream().anyMatch(role -> role.getRole().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if(isAdmin || tweet.getUser().getId().equals(String.valueOf(token.getName()))){
            tweetRepository.deleteById(tweetId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok().build();
    }
}
