package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.usecaseservice.UseCaseAddAnswer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AddAnswerRouter {

    @Bean
    public RouterFunction<ServerResponse> addAnswer(UseCaseAddAnswer useCaseAddAnswer) {
        return route(POST("/añadirrespuesta").and(accept(MediaType.APPLICATION_JSON)),//uso json
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(answerDto -> useCaseAddAnswer.createAnswer(answerDto)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)//tipo respuesta texto plano o json
                                        .bodyValue(result))
                        )
        );
    }
}
