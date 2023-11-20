package br.org.rentalcarapi.infra.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.rentalcarapi.infra.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    
}
