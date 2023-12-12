package com.example.springbootmongodbassessment.Service;


import com.example.springbootmongodbassessment.Model.Employee;
import com.example.springbootmongodbassessment.Repository.EmployeeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    public String getEmployee(){
        List<Employee> getEmp=employeeRepository.findAll();
        return
                "{"+
                        "\"message\":"+"Succesfully Retrieved Movies\",\n"+
                        "\"data\":"+getEmp+",\n"+
                        "}";
    }
    public String saveEmployee(Employee employee){

        Employee savedEmp=employeeRepository.save(employee);
        return
                "{"+
                        "\"message\":"+"Succesfully Created User\",\n"+
                        "\"data\":"+savedEmp+",\n"+
                        "}";
    }

    public void removeAllEmployee(){
        employeeRepository.deleteAll();
    }

    public long findCountEmployee(){
        return employeeRepository.findAll().size();
    }
    public void removeEmployeeById(String id) {
        ObjectId id1 = new ObjectId(id);

        Optional<Employee> tempLearners = employeeRepository.findById(id1);
        if (tempLearners.isEmpty()) {
            throw new RuntimeException("Employee id " + id + "doesn't exist");
        }
        employeeRepository.deleteById(id1);
    }
    public Employee updateEmployee(Employee employee, String id) {

        ObjectId objectId = new ObjectId(id);

        // Use findById to retrieve the entity by its ID
        Optional<Employee> temp = employeeRepository.findById(objectId);
       // var temp = employeeRepository.findById(id);
        if (temp.isEmpty()) {

            throw new RuntimeException("Employee id doesn't exist");

        }
        var upCourse = temp.get();
        if (employee.getName() != null) {
            upCourse.setName(employee.getName());
        }
        if (employee.getJob_role() != null) {
            upCourse.setJob_role(employee.getJob_role());
        }
        if (employee.getSalary() != 0) {
            upCourse.setSalary(employee.getSalary());
        }
        return employeeRepository.save(upCourse);
    }
    //Get an Movies by id-------------------------------------
 /*   public Movie getMovie(ObjectId id){
        Optional<Movie> optionalMovie=movieRepository.findById(id);
        return optionalMovie.orElseGet(optionalMovie::get);
    }
    //List Users



    //remove Learners
    public void removeLearnersById( int id){
        Optional<Movie> tempLearners=movieRepository.findById(id);
        if(tempLearners.isEmpty()){
            throw new RuntimeException("Learners id"+ id +"doesn't exist");
        }
        movieRepository.deleteById(id);
    }*/

}

