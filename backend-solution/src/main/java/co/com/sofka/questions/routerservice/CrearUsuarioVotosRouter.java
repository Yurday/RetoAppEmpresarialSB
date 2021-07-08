package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.usecaseservice.UseCaseCrearUserVote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CrearUsuarioVotosRouter {

    @Bean
    public RouterFunction<ServerResponse> crearUsuarioVotos(UseCaseCrearUserVote useCaseCrearUserVote) {
        return route(POST("/crearusuariovotos").and(accept(MediaType.APPLICATION_JSON)),//uso json
                request -> request.bodyToMono(UserVoteDTO.class)
                        .flatMap(userVoteDTO -> useCaseCrearUserVote.insertarUserVote(userVoteDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)//tipo respuesta texto plano o json
                                        .bodyValue(result))
                        )
        );
    }

}
