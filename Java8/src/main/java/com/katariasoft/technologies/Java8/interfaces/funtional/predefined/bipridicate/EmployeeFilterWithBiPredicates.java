package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.bipridicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

public class EmployeeFilterWithBiPredicates {

	private BiPredicate<Employee, Character> nameStartsWith = (e, c) -> Objects.nonNull(e)
			&& Objects.nonNull(e.getName()) && c == e.getName().charAt(0);
	private BiPredicate<Employee, String> havingDesignation = (e, d) -> Objects.nonNull(e)
			&& Objects.nonNull(e.getDesignation()) && d.equalsIgnoreCase(e.getDesignation());
	private BiPredicate<Employee, Double> havingSalaryGreterThan = (e, s) -> Objects.nonNull(e)
			&& Objects.nonNull(e.getSalary()) && e.getSalary() > s;
	private BiPredicate<Employee, Double> havingSalaryLessThan = (e, s) -> Objects.nonNull(e)
			&& Objects.nonNull(e.getSalary()) && e.getSalary() < s;
	private BiPredicate<Employee, String> havingLocation = (e, l) -> Objects.nonNull(e)
			&& Objects.nonNull(e.getLocation()) && e.getLocation().equalsIgnoreCase(l);
	private BiPredicate<Employee, String> withSex = (e, s) -> Objects.nonNull(e) && Objects.nonNull(e.getSex())
			&& e.getSex().equalsIgnoreCase(s);
	private BiPredicate<Employee, String> havingMaritalStatus = (e, m) -> Objects.nonNull(e)
			&& Objects.nonNull(e.getMaritalStatus()) && e.getMaritalStatus().equalsIgnoreCase(m);

	public List<Employee> filterNameStartingWith(List<Employee> employees, char c) {
		Objects.requireNonNull(employees);
		Objects.requireNonNull(c);
		List<Employee> filteredList = new ArrayList<>();
		for (Employee employee : employees)
			if (nameStartsWith.test(employee, c))
				filteredList.add(employee);
		return filteredList;

	}

	public List<Employee> filterHavingDesignation(List<Employee> employees, String designation) {
		Objects.requireNonNull(employees);
		Objects.requireNonNull(designation);
		List<Employee> filteredList = new ArrayList<>();
		for (Employee employee : employees)
			if (havingDesignation.test(employee, designation))
				filteredList.add(employee);
		return filteredList;
	}

	public List<Employee> filterHavingSalaryGreaterThan(List<Employee> employees, double salary) {
		Objects.requireNonNull(employees);
		Objects.requireNonNull(salary);
		List<Employee> filteredList = new ArrayList<>();
		for (Employee employee : employees)
			if (havingSalaryGreterThan.test(employee, salary))
				filteredList.add(employee);
		return filteredList;

	}

	public List<Employee> filterHavingSalaryLessThan(List<Employee> employees, double salary) {
		Objects.requireNonNull(employees);
		Objects.requireNonNull(salary);
		List<Employee> filteredList = new ArrayList<>();
		for (Employee employee : employees)
			if (havingSalaryLessThan.test(employee, salary))
				filteredList.add(employee);
		return filteredList;
	}

	public List<Employee> filterWithLocation(List<Employee> employees, String location) {
		Objects.requireNonNull(employees);
		Objects.requireNonNull(location);
		List<Employee> filteredList = new ArrayList<>();
		for (Employee employee : employees)
			if (havingLocation.test(employee, location))
				filteredList.add(employee);
		return filteredList;
	}

	public List<Employee> filterOnSex(List<Employee> employees, String sex) {
		Objects.requireNonNull(employees);
		Objects.requireNonNull(sex);
		List<Employee> filteredList = new ArrayList<>();
		for (Employee employee : employees)
			if (withSex.test(employee, sex))
				filteredList.add(employee);
		return filteredList;
	}

	public List<Employee> filterOnMaritalStatus(List<Employee> employees, String maritalStatus) {
		Objects.requireNonNull(employees);
		Objects.requireNonNull(maritalStatus);
		List<Employee> filteredList = new ArrayList<>();
		for (Employee employee : employees)
			if (havingMaritalStatus.test(employee, maritalStatus))
				filteredList.add(employee);
		return filteredList;
	}

	public static void main(String args[]) {
		char startingChar = 'v';
		String designation = "techlead";
		double salary = 25000;
		String location = "ambala";
		String sex = "female";
		String maritalStatus = "married";

		EmployeeFilterWithBiPredicates filter = new EmployeeFilterWithBiPredicates();
		System.out.println("Starting with charcter " + startingChar);
		System.out.println(filter.filterNameStartingWith(EmployeeList.get(), startingChar));
		System.out.println("Having designation : " + designation);
		System.out.println(filter.filterHavingDesignation(EmployeeList.get(), designation));
		System.out.println("Having salary greater than : " + salary);
		System.out.println(filter.filterHavingSalaryGreaterThan(EmployeeList.get(), salary));
		System.out.println("Having salary less than : " + salary);
		System.out.println(filter.filterHavingSalaryLessThan(EmployeeList.get(), salary));
		System.out.println("Having location : " + location);
		System.out.println(filter.filterWithLocation(EmployeeList.get(), location));
		System.out.println("Having sex : " + sex);
		System.out.println(filter.filterOnSex(EmployeeList.get(), sex));
		System.out.println("Having marital status : " + maritalStatus);
		System.out.println(filter.filterOnMaritalStatus(EmployeeList.get(), maritalStatus));

	}

}
