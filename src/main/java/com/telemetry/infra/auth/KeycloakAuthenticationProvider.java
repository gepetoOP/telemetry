package com.telemetry.infra.auth;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.micronaut.security.oauth2.configuration.OauthConfiguration;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Singleton
public class KeycloakAuthenticationProvider implements AuthenticationProvider {

    private final OauthConfiguration oauthConfiguration;

    public KeycloakAuthenticationProvider(OauthConfiguration oauthConfiguration) {
        this.oauthConfiguration = oauthConfiguration;
    }

    @Override
    public @NonNull Publisher<AuthenticationResponse> authenticate(Object httpRequest, AuthenticationRequest authenticationRequest) {
        return Mono.<AuthenticationResponse>create(emitter -> {
            // Lógica de autenticação personalizada, se necessário
            // Normalmente o JWT já é validado automaticamente
            emitter.success(AuthenticationResponse.success(
                    (String) authenticationRequest.getIdentity(),
                    Collections.emptyList()
            ));
        });
    }
}