package br.org.rentalcarapi.infra.persistence.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import br.org.rentalcarapi.domain.entity.Car;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table("USERS")
public class UserEntity {
    
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private String login;
    private String password;
    private String phone;
    private List<Car> cars;

}
