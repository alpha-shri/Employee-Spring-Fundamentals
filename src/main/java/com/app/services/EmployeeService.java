package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.app.dao.EmployeeRepository;
import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyInputException;
import com.app.models.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository dao;
	
	public List<Employee> findAllService(){
		return dao.findAll();
	}

	public Employee findByName(String name){
		Optional<Employee> optional = dao.findByName(name);

		if(!optional.isPresent()) throw new BusinessException("603", "No employee found");

		return optional.get();

	}

	public Employee saveService(Employee employee) {
		
// NEVER PUT VALIDATION INSIDE TRY-BLOCK AS it will get overridden by the catch Exception block
	// try block should only contains dao calls

/* 			
 * 					============ 	BIG MISTAKE 	================
		
		try {
			if(employee.getName().isEmpty() || employee.getName().length() == 0) {
				throw new BusinessException("601", "Please send proper name, it's blank");
			}
			Employee savedEmp = dao.save(employee);
			return savedEmp;
			
		}
*/
		if(employee.getName().isEmpty() || employee.getName().length() == 0) {
//			throw new BusinessException("601", "Name field is blank");
			throw new EmptyInputException("601", "Name field is blank");
		}
		
		try {
			 return dao.save(employee);

		}catch (IllegalArgumentException ile) {
			throw new BusinessException("602", "Given employee is Null"+ile.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603", "Oops! Something went wrong in saveService Method: "+e.getMessage());
		}
		
	}

	public Employee findByIdService(Long id) {
		System.out.print(id);

		Optional<Employee> optional = dao.findById(id);

		if(!optional.isPresent()){
			throw new BusinessException("602", "Employee not found");
		}

		return optional.get();

	}

	public String deleteByIdService(Long id) {
		
		String status = "FAILED";
		
		try {
			dao.deleteById(id);
			status = "SUCCESS";
			return status;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return status;
	}

}
