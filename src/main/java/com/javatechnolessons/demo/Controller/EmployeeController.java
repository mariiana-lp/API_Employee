package com.javatechnolessons.demo.Controller;

import com.javatechnolessons.demo.Services.EmployeeService;
import com.javatechnolessons.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public ArrayList<Employee> getEmplyee(){
        return employeeService.getEmployee();
    }

    @GetMapping(path = "/{id}")
    public Optional<Employee> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.employeeService.getById(id);
    }

    @PostMapping()
    public Employee saveEmployee(@RequestBody Employee employee){
        return this.employeeService.saveEmployee(employee);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById (@PathVariable("id") Long id){
        boolean ok = this.employeeService.deleteEmployee(id);
        if(ok){
            return "Se elimino el usuario con id " + id;
        }else {
            return "No se pudo eliminar el usuario con id" + id;
        }
    }

    @PutMapping("/employee/update/{id}")
    public ResponseEntity<Employee> updateById(@PathVariable("id") long id, @RequestBody Employee employee){
        return this.employeeService.updateById(id, employee);
    }



}
