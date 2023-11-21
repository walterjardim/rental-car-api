package br.org.rentalcarapi.infra.gateways;

import java.util.List;
import java.util.Optional;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.infra.persistence.entity.UserEntity;
import br.org.rentalcarapi.infra.persistence.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRepositoryGateway(UserRepository userRepository,
                                 UserEntityMapper userEntityMapper,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User createUser(User userDomainObject) {
        UserEntity userEntity = this.userEntityMapper.toEntity(userDomainObject);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userDomainObject.getPassword()));
        return this.saveUser(userEntity);
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

    @Override
    public List<User> findAll() {
        Iterable<UserEntity> entities = this.userRepository.findAll();
        return this.userEntityMapper.toDomainObjectList(entities);
    }

    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        return user.map(this.userEntityMapper::toDomainObject).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> userToDelete = this.userRepository.findById(id);
        userToDelete.ifPresent(this.userRepository::delete);
    }

    @Override
    public User updateUser(User userDomainObject) {
        UserEntity userEntity = this.userEntityMapper.toEntity(userDomainObject);
        userEntity.setId(userDomainObject.getId());
        return this.saveUser(userEntity);
    }

    private User saveUser(UserEntity userEntity) {
        UserEntity savedUser = this.userRepository.save(userEntity);
        return this.userEntityMapper.toDomainObject(savedUser);
    }
}
