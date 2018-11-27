package com.katariasoft.technologies.Java8.util.employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import com.katariasoft.technologies.Java8.beans.Employee;

public class EmployeeList {

	private static Supplier<List<Employee>> employeeListProvider = employeeListProviderImpl();
	private static Supplier<List<Employee>> nullIncludingEmployeeListProvider = nullIncludingEmployeeListProviderImpl();
	private static IntFunction<List<Employee>> largeEmployeeListFunction = largeEmployeeListFunctionIml();
	private static BiFunction<List<Employee>, Integer, List<Employee>> largeEmployeeListConvertor = largeEmployeeListConvertorImpl();
	private static UnaryOperator<List<Employee>> nullInserter = l -> {

		Objects.requireNonNull(l);
		l.add(null);
		l.add(null);
		l.add(null);
		l.add(null);
		l.add(null);
		return l;
	};

	private static BiFunction<List<Employee>, Integer, List<Employee>> largeEmployeeListConvertorImpl() {
		return (l, n) -> {
			List<Employee> largeList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				largeList.addAll(l);
			}
			return largeList;
		};
	}

	private static Supplier<List<Employee>> employeeListProviderImpl() {
		return () -> fetchEmployees();

	}

	private static Supplier<List<Employee>> nullIncludingEmployeeListProviderImpl() {
		return () -> nullInserter.apply(get());
	}

	public static List<Employee> getLargeEmployeeListOf(int numEmployees) {
		return largeEmployeeListFunction.apply((int) (numEmployees / 5));
	}

	public static Set<Employee> getLargeEmployeeSetOf(int numEmployees, Supplier<? extends Set<Employee>> setSupplier) {
		Set<Employee> employees = setSupplier.get();
		Map<String, Employee> map = fetchEmployees().stream().collect(Collectors.toMap(Employee::getName, e -> e));
		for (int i = 0; i < (int) numEmployees / 5; i++) {
			Employee vaneet = map.get("vaneet");
			vaneet.setName(vaneet.getName() + i);
			employees.add(vaneet);
			Employee pratapi = map.get("pratapi");
			pratapi.setName(pratapi.getName() + i);
			employees.add(pratapi);
			Employee deepak = map.get("deepak");
			deepak.setName(deepak.getName() + i);
			employees.add(deepak);
			Employee dheeraj = map.get("dheeraj");
			dheeraj.setName(dheeraj.getName() + i);
			employees.add(dheeraj);
			Employee franka = map.get("franka");
			franka.setName(franka.getName() + i);
			employees.add(franka);
		}
		return employees;

	}

	public static List<Employee> getLargeEmployeeLinkedListOf(int numEmployees) {
		List<Employee> list = new LinkedList<>();
		list.addAll(largeEmployeeListFunction.apply((int) (numEmployees / 5)));
		return list;
	}

	public static List<Employee> get() {
		return employeeListProvider.get();
	}

	public static List<Employee> getIncludingNulls() {
		return nullIncludingEmployeeListProvider.get();
	}

	private static IntFunction<List<Employee>> largeEmployeeListFunctionIml() {
		return n -> largeEmployeeListConvertor.apply(fetchEmployees(), n);
	}

	private static List<Employee> fetchEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("vaneet", (byte) 28, "TechLead", 715000, "ambala", "1", "Married", "male",
				8742910244L, new Employee.Address(6, 1, "1", "1", "1")));
		employees.add(new Employee("pratapi", (byte) 28, "sse", 615000, "up", "2", "unMarried", "male", 8742910241L,
				new Employee.Address(2, 2, "2", "2", "2")));
		employees.add(new Employee("deepak", (byte) 27, "sse", 65000, "up", "1", "unMarried", "male", 8742910242L,
				new Employee.Address(3, 3, "3", "3", "3")));
		employees.add(new Employee("dheeraj", (byte) 26, "product", 7000, "mp", "1", "unMarried", "male", 8742910243L,
				new Employee.Address(4, 4, "4", "4", "4")));
		employees.add(new Employee("franka", (byte) 28, "qa", 87000, "faridabad", "1", "Married", "male", 8742910244L,
				new Employee.Address(6, 1, "1", "1", "1")));
		return employees;
	}

	public static void main(String args[]) {
		Set<Employee> hashSet = getLargeEmployeeSetOf(5000, HashSet::new);
		Set<Employee> treeSet = getLargeEmployeeSetOf(5000, TreeSet::new);
		System.out.println("done");
	}

}
