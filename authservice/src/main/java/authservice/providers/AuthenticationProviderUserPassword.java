package authservice.providers;


import authservice.clients.UserServiceClient;
import authservice.models.UserCredentials;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestExecutorAuthenticationProvider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Singleton
@Slf4j
public class AuthenticationProviderUserPassword<B> implements HttpRequestExecutorAuthenticationProvider<B> {
    @Inject
    private UserServiceClient client;

    @Override
    public @NonNull AuthenticationResponse authenticate(@Nullable HttpRequest<B> requestContext,
            @NonNull AuthenticationRequest<String, String> authRequest) {        
        UserCredentials credentials = new UserCredentials(authRequest.getIdentity(), authRequest.getSecret());

        Mono<Boolean> areValidCredentials = client.verifyCredentials(credentials);        

        return areValidCredentials.block()
                ? AuthenticationResponse.success(authRequest.getIdentity())
                : AuthenticationResponse.failure();
    }

}