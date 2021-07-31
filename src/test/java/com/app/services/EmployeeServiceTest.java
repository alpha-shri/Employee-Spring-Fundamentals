package com.app.services;

import com.app.dao.EmployeeRepository;
import com.app.models.Employee;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {


    @Autowired
    EmployeeService service;

    @MockBean
    EmployeeRepository dao;

    @BeforeEach
    void setUp() {
        Employee employee = Employee.builder()
                                    .name("Shrijeet")
                                    .salary(2000).build();

        Mockito.when(dao.findByName("Shrijeet"))
                .thenReturn(Optional.of(employee));

    }


    @Test
    @DisplayName("Get data based on valid employee name")

//    @Disabled --> We can disable a particular test-case while running the whole test-suite.

    public void whenValidDeptName_thenDeptShouldFound(){
        String employeeName = "Shrijeet";

        Employee byName = service.findByName(employeeName);

        assertEquals(employeeName, byName.getName());

    }



}