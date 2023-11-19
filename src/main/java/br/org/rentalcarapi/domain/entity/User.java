package br.org.rentalcarapi.domain.entity;

import java.util.Date;
import java.util.List;

public class User {

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
