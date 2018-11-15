package com.katariasoft.technologies.Java8.util.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.katariasoft.technologies.Java8.beans.Employee;

public class EmployeeList {

	private static Supplier<List<Employee>> employeeListProvider = employeeListProviderImpl();
	private static Supplier<List<Employee>> nullIncludingEmployeeListProvider = nullIncludingEmployeeListProviderImpl();
	private static UnaryOperator<List<Employee>> nullInserter = l -> {
		Objects.requireNonNull(l);
		l.add(null);
		l.add(null);
		l.add(null);
		l.add(null);
		l.add(null);
		return l;
	};

	private static Supplier<List<Employee>> employeeListProviderImpl() {
		return () -> fetchEmployees();

	}

	private static Supplier<List<Employee>> nullIncludingEmployeeListProviderImpl() {
		return () -> nullInserter.apply(get());
	}

	public static List<Employee> get() {
		return employeeListProvider.get();
	}

	public static List<Employee> getIncludingNulls() {
		return nullIncludingEmployeeListProvider.get();
	}

	private static List<Employee> fetchEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("vaneet", (byte) 28, "TechLead", 75000, "ambala", "1", "unMarried", "male",
				8742910244L, new Employee.Address(6, 1, "1", "1", "1")));
		employees.add(new Employee("Pratapi", (byte) 28, "TechLead", 65000, "up", "1", "unMarried", "male", 8742910241L,
				new Employee.Address(2, 2, "2", "2", "2")));
		employees.add(new Employee("Deepak", (byte) 27, "TechLead", 65000, "up", "1", "unMarried", "female", 8742910242L,
				new Employee.Address(3, 3, "3", "3", "3")));
		employees.add(new Employee("DheeraD", (byte) 26, "TechLead", 75000, "up", "1", "unMarried", "female", 8742910243L,
				new Employee.Address(4, 4, "4", "4", "4")));
		employees.add(new Employee("vaneeta", (byte) 28, "qa", 87000, "up", "1", "UnMarried", "male", 8742910244L,
				new Employee.Address(6, 1, "1", "1", "1")));
		return employees;
	}

}
