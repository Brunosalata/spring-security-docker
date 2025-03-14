package br.com.brunosalata.springsecurity_docker.controller.dto;

import java.util.List;

/**
 * @author Bruno Salata Lima
 * github.com/Brunosalata
 */
public record TweetResponseDTO(
        List<TweetItemDTO> tweetList,
        int page,
        int pageSize,
        int totalPages,
        long totalElements) {
}
