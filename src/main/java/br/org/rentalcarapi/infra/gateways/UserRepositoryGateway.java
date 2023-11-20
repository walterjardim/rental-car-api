package br.org.rentalcarapi.infra.gateways;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.infra.persistence.entity.UserEntity;
import br.org.rentalcarapi.infra.persistence.repository.UserRepository;

public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUser(User userDomainObject) {
        UserEntity userEntity = this.userEntityMapper.toEntity(userDomainObject);
        UserEntity savedUser = this.userRepository.save(userEntity);
        return this.userEntityMapper.toDomainObject(savedUser);
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        return userEntity != null ? this.userEntityMapper.toDomainObject(userEntity) : null;
    }

    @Override
    public User getUserByLogin(String login) {
        UserEntity userEntity = this.userRepository.findByLogin(login);
        return userEntity != null ? this.userEntityMapper.toDomainObject(userEntity) : null;
    }
    
}
