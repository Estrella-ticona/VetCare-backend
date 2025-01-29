package com.estrellaticona.vetcare.iam.infrastructure.tokens.jwt;

import com.estrellaticona.vetcare.iam.application.internal.outboundservices.tokens.TokenService;

import jakarta.servlet.http.HttpServletRequest;

public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest token);
}