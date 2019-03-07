package com.sathish.springboot.restapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RestController
@Api(value = "some company", description = "Employee description") // to display on swagger2 UI
public class EmployeeController {

	// Map to store employees, ideally it should come from database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

	@Autowired
	private EmployeeService employeeService;

	// swaggger URL http://localhost:8080/swagger-ui.html
	// URL http://localhost:8080/rest/emp/default
	@ApiOperation(value = "View default employee") // to display on swagger2 UI
	@RequestMapping(value = "/rest/emp/default", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Employee getDefaultEmployee() {
		Employee emp = new Employee();
		empData.put(9999, emp);
		emp = employeeService.getDefaultEmployeeService();
		return emp;
	}

	// URL http://localhost:8080/rest/emp/9999
	@ApiOperation(value = "get employee by ID")
	@RequestMapping(value = "/rest/emp/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
		System.out.println("Start getEmployee. ID=" + empId);

		return empData.get(empId);
	}

	// post data by request body
	// URL http://localhost:8080//rest/emp/create
	// request {"id":1,"name":"Sathish
	// Kariyanna","createdDate":"2018-06-24T20:25:58.096+0000"}
	@ApiOperation(value = "create employee by request body")
	@RequestMapping(value = "/rest/emp/create", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
		System.out.println("Start createEmployee.");
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}

	// post data by Path Variable
	// URL http://localhost:8080/rest/emp/createbypathvariable/2/sathish
	@ApiOperation(value = "create employee by path variables")
	@RequestMapping(value = "/rest/emp/createbypathvariable/{id}/{name}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Employee createEmployeeByParam(@PathVariable("id") int empId,
			@PathVariable("name") String empName) {
		System.out.println("Start createEmployee by param.");
		Employee emp = new Employee();
		emp.setId(empId);
		emp.setName(empName);
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}

	// post data by Query Parameter
	// URL http://localhost:8080/rest/emp/createbyqueryparam?id=3&name=Kariyanna
	@ApiOperation(value = "create employee by request parameters")
	@RequestMapping(value = "/rest/emp/createbyqueryparam", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Employee createEmployeeByQueryParam(@RequestParam("id") int empId,
			@RequestParam("name") String empName) {
		// in RequestParam we can specify required field
		// @RequestParam(value="param2", required=false) String param2)
		System.out.println("Start createEmployee by param.");
		Employee emp = new Employee();
		emp.setId(empId);
		emp.setName(empName);
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}

	// URL http://localhost:8080/rest/emps
	@ApiOperation(value = "get all employees")
	@RequestMapping(value = "/rest/emps", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Employee> getAllEmployees() {
		System.out.println("Start getAllEmployees.");
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIdKeys = empData.keySet();
		for (Integer i : empIdKeys) {
			emps.add(empData.get(i));
		}
		return emps;
	}

	// URL http://localhost:8080//rest/emp/update
	// request
	// {"id":1,"name":"Sathish","createdDate":"2018-06-25T20:25:58.096+0000"}
	@ApiOperation(value = "update employee by using request body")
	@RequestMapping(value = "/rest/emp/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody Employee updateEmployee(@RequestBody Employee emp) {
		System.out.println("Start updateEmployee.");
		// in real world we need to update the resource
		empData.put(emp.getId(), emp);
		return emp;
	}

	// URL http://localhost:8080//rest/emp/delete/1
	@ApiOperation(value = "delete employee by ID")
	@RequestMapping(value = "/rest/emp/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
		System.out.println("Start delete Employee.");
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
}
