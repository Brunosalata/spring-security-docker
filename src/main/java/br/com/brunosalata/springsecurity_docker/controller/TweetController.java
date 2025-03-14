package br.com.brunosalata.springsecurity_docker.controller;

import br.com.brunosalata.springsecurity_docker.controller.dto.CreateTweetDTO;
import br.com.brunosalata.springsecurity_docker.entities.Tweet;
import br.com.brunosalata.springsecurity_docker.repository.TweetRepository;
import br.com.brunosalata.springsecurity_docker.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
