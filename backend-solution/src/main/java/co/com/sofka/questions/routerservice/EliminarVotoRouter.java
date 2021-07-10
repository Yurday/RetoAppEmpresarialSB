package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.usecaseservice.UseCaseEliminarVoto;
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
public class EliminarVotoRouter {

    @Bean
    public RouterFunction<ServerResponse> deleteVote(UseCaseEliminarVoto useCaseEliminarVoto) {
        return route( PUT("/eliminarvoto/{answerid}/{userid}").and(accept(MediaType.APPLICATION_JSON)),
                request-> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(useCaseEliminarVoto.borrarVoto(request.pathVariable("answerid"), request.pathVariable("userid")), AnswerDTO.class)
        );
    }
}
