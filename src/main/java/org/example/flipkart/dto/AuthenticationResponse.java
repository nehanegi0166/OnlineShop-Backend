package org.example.flipkart.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
