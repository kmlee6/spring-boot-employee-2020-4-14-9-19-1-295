package com.thoughtworks.springbootemployee.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private Integer companyId;

    public void updateEmployee(Employee updatedEmployee){
        if(updatedEmployee.getName()!=null){
            setName(updatedEmployee.getName());
        }
        if(updatedEmployee.getAge()!=null){
            setAge(updatedEmployee.getAge());
        }
        if(updatedEmployee.getGender()!=null){
            setGender(updatedEmployee.getGender());
        }
        if(updatedEmployee.getSalary()!=null){
            setSalary(updatedEmployee.getSalary());
        }
        if(updatedEmployee.getCompanyId()!=null){
            setCompanyId(updatedEmployee.getCompanyId());
        }
    }
}
