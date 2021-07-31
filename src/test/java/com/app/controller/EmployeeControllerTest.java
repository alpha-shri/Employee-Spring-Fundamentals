package com.app.controller;

import com.app.models.Employee;
import com.app.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService service;

    private Employee outputEmployee;

    @BeforeEach
    void setUp(){
        outputEmployee = Employee.builder()
                           .id(2L).name("Adam").salary(2000)
                           .build();
    }

    @Test
    void saveEmployee() throws Exception {
        Employee inputEmployee = Employee.builder()
                                    .id(2L).name("Adam").salary(2000)
                                    .build();

        Mockito.when(service.saveService(inputEmployee))
                .thenReturn(outputEmployee);

        mockMvc.perform(MockMvcRequestBuilders.post("/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "\t\"id\": 2L,\n" +
                                    "\t\"name\": \"Adam\",\n" +
                                    "\t\"salary\": 2000\n" +
                                    "}"))
                            .andExpect(MockMvcResultMatchers.status().isOk());



    }

    @Test
    void fetchByName() {
    }
}