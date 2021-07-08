package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.usecaseservice.UseCaseCrearUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CrearUsuarioRouter {

    @Bean
    public RouterFunction<ServerResponse> crearUsuario(UseCaseCrearUser useCaseCrearUser) {
        return route(POST("/crearusuario").and(accept(MediaType.APPLICATION_JSON)),//uso json
                request -> request.bodyToMono(UserDTO.class)
                        .flatMap(userDTO -> useCaseCrearUser.insertaruser(userDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)//tipo respuesta texto plano o json
                                        .bodyValue(result))
                        )
        );
    }

}
