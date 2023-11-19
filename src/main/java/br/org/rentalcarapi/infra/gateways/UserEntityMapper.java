package br.org.rentalcarapi.infra.gateways;

import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.infra.persistence.entity.UserEntity;

public class UserEntityMapper {
    private final CarEntityMapper carEntityMapper;

    public UserEntityMapper(CarEntityMapper carEntityMapper) {
        this.carEntityMapper = carEntityMapper;
    }

    UserEntity toEntity(User userDomain) {
        return UserEntity
            .builder()
            .birthday(userDomain.getBirthday())
            .email(userDomain.getEmail())
            .firstName(userDomain.getFirstName())
            .lastName(userDomain.getLastName())
            .login(userDomain.getLogin())
            .password(userDomain.getPassword())
            .phone(userDomain.getPhone())
            .cars(this.carEntityMapper.toEntityList(userDomain.getCars()))
            .build();
    }

    User toDomainObject(UserEntity userEntity) {
        return new User(
            userEntity.getFirstName(),
            userEntity.getLastName(),
            userEntity.getEmail(),
            userEntity.getBirthday(),
            userEntity.getLogin(),
            userEntity.getPassword(),
            userEntity.getPhone(),
            this.carEntityMapper.toDomainObjectList(userEntity.getCars()));
    }
}
