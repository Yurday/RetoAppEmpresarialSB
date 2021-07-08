package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecasecrud.UseCaseActualizarQuestion;
import co.com.sofka.questions.usecaseservice.UseCasePlusVote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PlusVoteRouter {

    @Bean
    public RouterFunction<ServerResponse> plusvote(UseCasePlusVote useCasePlusVote) {
        return route( PUT("/sumarvoto/{idanswer}/{iduser}").and(accept(MediaType.APPLICATION_JSON)),
                request-> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(useCasePlusVote.plusPosition(request.pathVariable("idanswer"),request.pathVariable("iduser")), AnswerDTO.class)
        );
    }
}
