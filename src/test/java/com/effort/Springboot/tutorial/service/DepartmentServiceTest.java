package com.effort.Springboot.tutorial.service;

import com.effort.Springboot.tutorial.entity.Department;
import com.effort.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;
    @BeforeEach
    void setUp() {
        Department department = Department.builder().
                departmentName("IT")
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .departmentId(1L)
                .build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("it"))
                .thenReturn(department);

    }
    @Test
    @DisplayName("get data based on the valid department name")
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "IT";
        Department found = departmentService.getDepartmentByName(departmentName);
//        assertNull(found);
        assertNotEquals(departmentName,found.getDepartmentName());
    }
}