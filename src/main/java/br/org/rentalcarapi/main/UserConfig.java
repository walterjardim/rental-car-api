package br.org.rentalcarapi.main;

import br.org.rentalcarapi.application.usecases.impl.DeleteUserInteractor;
import br.org.rentalcarapi.application.usecases.impl.UpdateUserInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.application.usecases.impl.CreateUserInteractor;
import br.org.rentalcarapi.application.usecases.impl.ListUsersInteractor;
import br.org.rentalcarapi.infra.dto.UserDTOMapper;
import br.org.rentalcarapi.infra.gateways.CarEntityMapper;
import br.org.rentalcarapi.infra.gateways.UserEntityMapper;
import br.org.rentalcarapi.infra.gateways.UserRepositoryGateway;
import br.org.rentalcarapi.infra.persistence.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    CreateUserInteractor createUserInteractor(UserGateway userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new UserRepositoryGateway(userRepository, userEntityMapper, bCryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserEntityMapper userEntityMapper(CarEntityMapper carEntityMapper) {
        return new UserEntityMapper(carEntityMapper);
    }

    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }

    @Bean
    CarEntityMapper carEntityMapper() {
        return new CarEntityMapper();
    }

    @Bean
    ListUsersInteractor listUsersInteractor(UserGateway userGateway) {
        return new ListUsersInteractor(userGateway);
    }

    @Bean
    DeleteUserInteractor deleteUserInteractor(UserGateway userGateway) {
        return new DeleteUserInteractor(userGateway);
    }

    @Bean
    UpdateUserInteractor updateUserInteractor(UserGateway userGateway) { return new UpdateUserInteractor(userGateway); }
}
