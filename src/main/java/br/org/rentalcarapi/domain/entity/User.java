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

    public User() {}

    public User(String firstName, String lastName, String email, Date birthday, String login, String password,
            String phone, List<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.cars = cars;
    }

    public User(Long id, String firstName, String lastName, String email, Date birthday, String login, String password,
            String phone, List<Car> cars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.cars = cars;
    }
    
    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public Date getBirthday() {
        return birthday;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone() {
        return phone;
    }
    public List<Car> getCars() {
        return cars;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    

}
