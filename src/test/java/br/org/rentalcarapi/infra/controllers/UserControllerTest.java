package br.org.rentalcarapi.infra.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import br.org.rentalcarapi.application.usecases.impl.DeleteUserInteractor;
import br.org.rentalcarapi.application.usecases.impl.UpdateUserInteractor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.org.rentalcarapi.application.usecases.impl.CreateUserInteractor;
import br.org.rentalcarapi.application.usecases.impl.ListUsersInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.infra.dto.UserRequestDTO;
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

    @MockBean
    private DeleteUserInteractor deleteUserInteractor;

    @MockBean
    private UpdateUserInteractor updateUserInteractor;

    @Test
    void testCreateUserWithSuccess() throws Exception {
        UserRequestDTO body = new UserRequestDTO(null, "First", "Last", "email@test.com",
            new Date(), "login", "1234", "81988887777", new ArrayList<>());
        
        when(this.createUserInteractor.createUser(Mockito.any())).thenReturn(new User());
        
        this.mockMvc.perform(
            post("/api/users").
            contentType(MediaType.APPLICATION_JSON).
            content(this.objectMapper.writeValueAsString(body))
        ).andExpect(status().isOk());
    }

    @Test
    void testFindAllUsersWihSuccess() throws Exception {
        when(this.listUsersInteractor.findAll()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(
            get("/api/users")
        ).andExpect(status().isOk());
    }
    
    @Test
    void testFindUserByIdWihSuccess() throws Exception {
        when(this.listUsersInteractor.findById(Mockito.anyLong())).thenReturn(new User());
        this.mockMvc.perform(
            get("/api/users/1")
        ).andExpect(status().isOk());
    }

    @Test
    void testDeleteUserByIdWihSuccess() throws Exception {
        Mockito.doNothing().when(this.deleteUserInteractor).deleteUser(Mockito.anyLong());
        this.mockMvc.perform(
                delete("/api/users/1")
        ).andExpect(status().isOk());
    }

    @Test
    void testUpdateUserWithSuccess() throws Exception {
        UserRequestDTO body = new UserRequestDTO(1L, "First", "Last", "email@test.com",
                new Date(), "login", "1234", "81988887777", new ArrayList<>());

        when(this.updateUserInteractor.updateUser(Mockito.any())).thenReturn(new User());

        this.mockMvc.perform(
                put("/api/users/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(this.objectMapper.writeValueAsString(body))
        ).andExpect(status().isOk());
    }
}
