package authservice.clients;

import authservice.models.UserCredentials;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Status;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import reactor.core.publisher.Mono;

@Client(id = "userservice")
@Secured(SecurityRule.IS_ANONYMOUS)
public interface UserServiceClient {

    @Post("/api/user/verify")
    Mono<HttpResponse<Boolean>> verifyCredentials(@Body UserCredentials credentials);
    
}
