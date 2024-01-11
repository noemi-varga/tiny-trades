package com.tinytrades.tinytradesbackend.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record AuthenticationResponse (Long id, String username, String email, String jwt) {

}