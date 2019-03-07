package com.sathish.springboot.restapi.client;

import org.springframework.web.client.RestTemplate;

import com.sathish.springboot.restapi.dto.Employee;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 * @author Sathish
 *
 */
public class EmployeeRestClient {

	public static final String SERVER_URI = "http://localhost:8080";

	public static void main(String args[]) {
		System.out.println("*****Before Get Default Employee details*****");
		getDefaultEmployee();
		System.out.println("*****Before create Employee *****");
		createEmployee(1, "Sathish");
		System.out.println("*****Before get Employee details *****");
		getEmployee();
		System.out.println("*****Before Update Employee details *****");
		updateEmployee(1, "Sathish123");
		System.out.println("***** Before get all employee details *****");
		getAllEmployee();
	}

	private static void getAllEmployee() {
		// we can use RestClient, HTTPClient or RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		// we can't get List<Employee> because JSON converter doesn't know the type of
		// object in the list and hence convert it to default JSON object type
		// LinkedHashMap
		List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI + "/rest/emps", List.class);
		System.out.println(emps.size());
		for (LinkedHashMap map : emps) {
			System.out.println(
					"ID=" + map.get("id") + ",Name=" + map.get("name") + ",CreatedDate=" + map.get("createdDate"));
			;
		}
	}

	/**
	 * 
	 * @param empId
	 * @param name
	 */
	public static void createEmployee(int empId, String name) {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = new Employee();
		emp.setId(empId);
		emp.setName(name);
		Employee response = restTemplate.postForObject(SERVER_URI + "/rest/emp/create", emp, Employee.class);
		printEmpData(response);
	}

	/**
	 * 
	 * @param empId
	 * @param name
	 */
	public static void updateEmployee(int empId, String name) {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = new Employee();
		emp.setId(empId);
		emp.setName(name);
		Employee response = restTemplate.postForObject(SERVER_URI + "/rest/emp/create", emp, Employee.class);
		printEmpData(response);
	}

	public static void getEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = restTemplate.getForObject(SERVER_URI + "/rest/emp/1", Employee.class);
		printEmpData(emp);
	}

	public static void getDefaultEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = restTemplate.getForObject(SERVER_URI + "/rest/emp/default", Employee.class);
		printEmpData(emp);
	}

	/**
	 * 
	 * @param emp
	 */
	public static void printEmpData(Employee emp) {
		System.out.println("ID=" + emp.getId() + ",Name=" + emp.getName() + ",CreatedDate=" + emp.getCreatedDate());
	}
}
