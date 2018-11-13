package com.katariasoft.technologies.Java8.util.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.katariasoft.technologies.Java8.beans.Employee;

public class EmployeeList {

	public static Supplier<List<Employee>> employeeListProvider = employeeListProviderImpl();

	private static Supplier<List<Employee>> employeeListProviderImpl() {
		return () -> {
			List<Employee> employees = new ArrayList<>();
			employees.add(new Employee("Vaneet", (byte) 28, "TechLead", 75000, "Ambala", "3", "Married", "Male",
					8742910240L));
			employees
					.add(new Employee("Pratapi", (byte) 29, "sse", 65000, "up", "2", "unMarried", "Male", 8742910241L));
			employees.add(new Employee("Deepak", (byte) 26, "se", 55000, "up", "1", "unMarried", "Male", 8742910242L));
			employees.add(
					new Employee("DheeraD", (byte) 25, "product", 45000, "up", "1", "unMarried", "Male", 8742910243L));
			employees.add(
					new Employee("Franka", (byte) 28, "qa", 35000, "faridabad", "1", "Married", "female", 8742910244L));
			return employees;
		};
	}

	public static List<Employee> get() {
		return employeeListProvider.get();
	}

}
