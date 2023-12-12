package com.example.springbootmongodbassessment.Controller;

import com.example.springbootmongodbassessment.Model.Employee;
import com.example.springbootmongodbassessment.Service.EmployeeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @PostMapping("/add")
    public  String saveMovie(@RequestBody Employee employee){
        return  employeeService.saveEmployee(employee);
    }
    @GetMapping("/get-employee")
    public String getEmployee(){
        return  employeeService.getEmployee();
    }

    //-----------delete by ID----------------------------

    @GetMapping("/del/{id}")
    public void removeEmployeeById(@PathVariable String id) {
        employeeService.removeEmployeeById(id);
    }

    //-----------delete employee---------------------------
    @GetMapping("/delAll")
    public  void removeAll(){
        employeeService.removeAllEmployee();
    }

    //------------------UPDATE EMPLOYEE------------------------
    @PostMapping("update/{id}")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable String id) {

        return employeeService.updateEmployee(employee,id);
    }


}
