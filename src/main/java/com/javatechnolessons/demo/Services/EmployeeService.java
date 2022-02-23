package com.javatechnolessons.demo.Services;

import com.javatechnolessons.demo.model.Employee;
import com.javatechnolessons.demo.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

//( Crear, Eliminar, Actualizar, Obtener todos y Obtener por ID). 30%
@Service
public class EmployeeService {
    @Autowired
    IEmployeeJpaRepository employeeJpaRepository;

    //obtener todos
    public ArrayList<Employee> getEmployee(){
        return (ArrayList<Employee>) employeeJpaRepository.findAll();
    }

    //Obtener por id
    public Optional<Employee> getById(long id){
        return employeeJpaRepository.findById(id);
    }

    //Guardar Empleado
    public Employee saveEmployee(Employee usuario){
        return employeeJpaRepository.save(usuario);
    }

    //Eliminar empleado por id
    public boolean deleteEmployee(Long id){
        try {
            employeeJpaRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    //
    public ResponseEntity<Employee> updateById(long id,Employee employee){
        try{
            Employee _e = employeeJpaRepository.findById(id).get();
            _e.setFirstName(employee.getFirstName());
            _e.setLastName(employee.getLastName());
            _e.setRole(employee.getRole());
            _e.setProjects(employee.getProjects());
            _e.setEmployeeid(employee.getEmployeeid());
            return new ResponseEntity<>(employeeJpaRepository.save(_e), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
