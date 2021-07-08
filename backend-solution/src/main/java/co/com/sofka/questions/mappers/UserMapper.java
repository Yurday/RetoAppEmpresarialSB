package co.com.sofka.questions.mappers;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper {

    public Function<UserDTO, User> fromUserDtoToUser(String id){
        return updateUser->{
            var user=new User();
            user.setId(id);
            user.setVoto(updateUser.getVoto());
            return user;
        };

    }

    public Function<User,UserDTO> fromUserToUserDTO(){
        return entity->
                new UserDTO(
                        entity.getId(),
                        entity.getVoto()
                );

    }
}
