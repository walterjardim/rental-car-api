package br.org.rentalcarapi.infra.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.org.rentalcarapi.application.usecases.CreateUserInteractor;
import br.org.rentalcarapi.application.usecases.ListUsersInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.infra.dto.CreateUserRequest;
import jakarta.ws.rs.core.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserInteractor createUserInteractor;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ListUsersInteractor listUsersInteractor;

    @Test
    void testCreateUserWithSuccess() throws Exception {
        CreateUserRequest body = new CreateUserRequest("First", "Last", "email@test.com",
            new Date(), "login", "1234", "81988887777", new ArrayList<>());
        
        when(this.createUserInteractor.createUser(Mockito.any())).thenReturn(new User());
        
        this.mockMvc.perform(
            post("/users").
            contentType(MediaType.APPLICATION_JSON).
            content(this.objectMapper.writeValueAsString(body))
        ).andExpect(status().isOk());
    }

    @Test
    void testFindAllUsersWihSuccess() throws Exception {
        when(this.listUsersInteractor.findAll()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(
            get("/users")
        ).andExpect(status().isOk());
    }
    
    @Test
    void testFindUserByIdWihSuccess() throws Exception {
        when(this.listUsersInteractor.findById(Mockito.anyLong())).thenReturn(new User());
        this.mockMvc.perform(
            get("/users/1")
        ).andExpect(status().isOk());
    }
}
