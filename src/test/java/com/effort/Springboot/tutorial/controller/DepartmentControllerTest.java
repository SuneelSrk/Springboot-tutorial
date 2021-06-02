package com.effort.Springboot.tutorial.controller;

import com.effort.Springboot.tutorial.entity.Department;
import com.effort.Springboot.tutorial.error.DepartmentNotFoundException;
import com.effort.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp(){
        department = Department.builder()
                .departmentName("CSE")
                .departmentAddress("Bangalore")
                .departmentCode("CSE-06")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
         Department inputDepartment = Department.builder()
                .departmentName("CSE")
                .departmentAddress("Bangalore")
                .departmentCode("CSE-06")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"departmentName\":\"CSE\",\n" +
                "    \"departmentAddress\":\"BANGALORE\",\n" +
                "    \"departmentCode\":\"CSE-06\"\n" +
                "}"))
                .andExpect(status().isOk());



    }

    @Test
    void getDepartmentById() throws Exception {
      Mockito.when(departmentService.getDepartmentById(1L))
              .thenReturn(department);

      mockMvc.perform(get("/departments/1")
      .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.departmentName")
              .value(department.getDepartmentName()));

    }
}