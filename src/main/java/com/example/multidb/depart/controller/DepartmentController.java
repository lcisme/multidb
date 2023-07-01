package com.example.multidb.depart.controller;

import com.example.multidb.depart.entity.Department;
import com.example.multidb.depart.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository repository;

    @PostMapping
    public void createDerpartment(@RequestBody Department department){
        repository.save(department);
    }

    @GetMapping
    public List<Department> getAllCustomer(){
        return repository.findAll();
    }
}
