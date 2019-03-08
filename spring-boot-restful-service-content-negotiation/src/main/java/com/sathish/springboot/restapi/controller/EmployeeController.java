package com.sathish.springboot.restapi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.sathish.springboot.restapi.dto.Employee;
import com.sathish.springboot.restapi.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Handles requests for the Employee service.
 */
/**
 * 
 * @author Sathish
 *
 */
/*
 * Need to add jackson-dataformat-xml dependency in pom.xml. Add produces,
 * consumes with multiple media types with comma separated.
 */
@RestController
@Api(value = "abc company", description = "Employee description") // to display on swagger2 UI
public class EmployeeController {

	// Map to store employees, ideally it should come from database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

	@Autowired
	private EmployeeService employeeService;

	// swaggger URL http://localhost:8080/swagger-ui.html
	// post data by request body
	// URL http://localhost:8080//rest/emp/create
	// request {"id":1,"name":"Sathish
	// Kariyanna","createdDate":"2018-06-24T20:25:58.096+0000"}
	/*
	 * <Employee> <id>1</id> <name>Sathish</name>
	 * <createdDate>2019-03-08T05:27:58.543+0000</createdDate> </Employee>
	 */
	@ApiOperation(value = "create employee by request body")
	@RequestMapping(value = "/rest/emp/create", method = RequestMethod.POST, produces = { "application/xml",
			"application/json" }, consumes = { "application/xml", "application/json" })
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
		System.out.println("Start createEmployee.");
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}

	// URL http://localhost:8080/rest/emp/default
	@ApiOperation(value = "View default employee") // to display on swagger2 UI
	@RequestMapping(value = "/rest/emp/default", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public @ResponseBody Employee getDefaultEmployee() {
		Employee emp = new Employee();
		empData.put(9999, emp);
		emp = employeeService.getDefaultEmployeeService();
		return emp;
	}

	// URL http://localhost:8080/rest/emp/9999
	@ApiOperation(value = "get employee by ID")
	@RequestMapping(value = "/rest/emp/{id}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
		System.out.println("Start getEmployee. ID=" + empId);

		return empData.get(empId);
	}

}
