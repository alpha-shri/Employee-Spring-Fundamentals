package com.app.dao;

import com.app.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository dao;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Employee employee = Employee.builder()
                                    .id(4L).name("LT Infotech").salary(56000)
                                    .build();

        entityManager.persist(employee);
    }

    @Test
    public void whenFindById_thenReturnEmployee(){

        Employee employee = dao.findById(4L).get();

        assertEquals(employee.getName(), "LT Infotech");
    }

}