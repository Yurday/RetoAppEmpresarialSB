package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.usecaseservice.UseCasePlusVote;
import co.com.sofka.questions.usecaseservice.UseCaseRestVote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RestVoteRouter {

    @Bean
    public RouterFunction<ServerResponse> restVote(UseCaseRestVote useCaseRestVote) {
        return route( PUT("/restarvoto/{answerid}/{userid}").and(accept(MediaType.APPLICATION_JSON)),
                request-> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(useCaseRestVote.restPosition(request.pathVariable("answerid"),request.pathVariable("userid")), AnswerDTO.class)
        );
    }
}
