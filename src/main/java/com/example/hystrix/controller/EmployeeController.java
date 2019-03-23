package com.example.hystrix.controller;

import com.example.hystrix.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/getAllEmployee")
    @HystrixCommand(fallbackMethod = "fallToCallback")
    public List<Employee> getAllEmployee(){
        List<Employee> allEmp = restTemplate.getForObject("http://emp-service/getAllEmployee/", List.class);
        return allEmp;

    }

    public List<Employee> fallToCallback(){
        List<Employee> allEmp = new ArrayList<>();
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("Phakphoom Chainapaporn");
        emp.setPosition("Java programmer");
        allEmp.add(emp);
        return allEmp;

    }

}
