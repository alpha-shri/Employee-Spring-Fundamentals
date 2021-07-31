package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.exceptions.BusinessException;
import com.app.exceptions.ControllerException;
import com.app.models.Employee;
import com.app.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/")
	public String homePage() {
		LOGGER.info("Inside homepage method in EmployeeController class.");
		return "Hello World";
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		System.out.println("Employee: " + employee);

		try {

			Employee savedEmp = service.saveService(employee);
			return new ResponseEntity<Employee>(savedEmp, HttpStatus.OK);

		} catch (BusinessException be) {
			ControllerException ce = new ControllerException(be.getErrorCode(), be.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("611", "Oops! Something went wrong in Controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/findall")
	public ResponseEntity<List<Employee>> fetchAll() {
		List<Employee> employeeList = service.findAllService();

		return employeeList != null
				? new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<Employee> fetchById(@PathVariable Long id) {
		Employee employee = service.findByIdService(id);

		return employee != null ? new ResponseEntity<Employee>(employee, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		System.out.println("Employee: " + employee);
		Employee savedEmp = service.saveService(employee);

		return savedEmp != null ? new ResponseEntity<Employee>(savedEmp, HttpStatus.CREATED)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping("/removebyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		System.out.println("deleteById = " + id);

		String status = service.deleteByIdService(id);

		return status.equals("SUCCESS")
				? new ResponseEntity<String>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
