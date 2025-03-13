package br.com.brunosalata.springsecurity_docker.controller.dto;

/**
 * @author Bruno Salata Lima
 * github.com/Brunosalata
 */
public record LoginResponseDTO(String accessToken, Long expiresIn) {
}
