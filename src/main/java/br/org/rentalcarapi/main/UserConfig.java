package br.org.rentalcarapi.main;

import br.org.rentalcarapi.application.usecases.DeleteUserInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.application.usecases.CreateUserInteractor;
import br.org.rentalcarapi.application.usecases.ListUsersInteractor;
import br.org.rentalcarapi.infra.dto.UserDTOMapper;
import br.org.rentalcarapi.infra.gateways.CarEntityMapper;
import br.org.rentalcarapi.infra.gateways.UserEntityMapper;
import br.org.rentalcarapi.infra.gateways.UserRepositoryGateway;
import br.org.rentalcarapi.infra.persistence.repository.UserRepository;

@Configuration
public class UserConfig {

    @Bean
    CreateUserInteractor createUserInteractor(UserGateway userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
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

}
