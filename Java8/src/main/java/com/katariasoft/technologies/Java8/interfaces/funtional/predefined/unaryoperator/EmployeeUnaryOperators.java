package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.unaryoperator;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.employee.EmployeeFilterWithPredicate;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

/*
 * In this class lambdas are created while class loading just once . 
 * Implementation is written in separate private methods .
 * Functions are like private instance variable of this class which are functional interfaces taking implementation 
 * with some lambda expression defined in a method . 
 * Corresponding getters will provide this function . 
 *
 * Benefits : All function users of this class will have a single object initialised once not again and again.
 * */

public class EmployeeUnaryOperators {

	private EmployeeFilterWithPredicate employeeFilterWithPredicate = new EmployeeFilterWithPredicate();
	private UnaryOperator<List<Employee>> employeeSalaryIncrementor = employeeSalaryIncrementorImpl();
	private UnaryOperator<List<Employee>> employeeDesignationIncrementor = employeeDesignationIncrementorImpl();
	private UnaryOperator<List<Employee>> employeeDesignationBandIncrementor = employeeDesignationBandIncrementorImpl();
	private Function<List<Employee>, List<Employee>> employeeSalaryDesignationIncrementor = employeeSalaryDesignationIncrementorImp();
	private Function<List<Employee>, List<Employee>> employeeSalaryDesignationAndBandIncrementor = employeeSalaryDesignationAndBandIncrementorImp();

	private UnaryOperator<List<Employee>> employeeSalaryIncrementorImpl() {
		return l -> {
			Objects.requireNonNull(l, "Employee List cannot be null or empty.");
			double incrementedSalary = 5000.0d;
			for (Employee e : l)
				e.setSalary(e.getSalary() + incrementedSalary);
			return l;
		};
	}

	private UnaryOperator<List<Employee>> employeeDesignationIncrementorImpl() {
		return l -> {
			for (Employee e : l)
				if (employeeFilterWithPredicate.getHavingDesignationTechLeadPredicate().test(e))
					e.setDesignation("Manager");
			return l;
		};
	}

	private UnaryOperator<List<Employee>> employeeDesignationBandIncrementorImpl() {
		return l -> {
			for (Employee e : l)
				if (e.getDesignationBand().equals("1"))
					e.setDesignationBand("2");
			return l;

		};
	}

	private Function<List<Employee>, List<Employee>> employeeSalaryDesignationIncrementorImp() {
		return employeeSalaryIncrementor.compose(employeeDesignationIncrementor);
	}

	private Function<List<Employee>, List<Employee>> employeeSalaryDesignationAndBandIncrementorImp() {
		return employeeSalaryDesignationIncrementor.compose(employeeDesignationBandIncrementor);
	}

	public UnaryOperator<List<Employee>> getEmployeeSalaryIncrmentorFunction() {
		return employeeSalaryIncrementor;
	}

	public UnaryOperator<List<Employee>> getEployeeDesignationIncrementorFunction() {
		return employeeDesignationIncrementor;
	}

	public Function<List<Employee>, List<Employee>> getEmployeeDesignationBandIncrementorFunction() {
		return employeeDesignationBandIncrementor;
	}

	public Function<List<Employee>, List<Employee>> getEmployeeSalaryDesignationIncrementorFunction() {
		return employeeSalaryDesignationIncrementor;
	}

	public Function<List<Employee>, List<Employee>> getemployeeSalaryDesignationAndBandIncrementorFunction() {
		return employeeSalaryDesignationAndBandIncrementor;
	}

	public static void main(String args[]) {
		EmployeeUnaryOperators miscUnaryOperators = new EmployeeUnaryOperators();
		System.out.println("Employee List with old salary: " + EmployeeList.get());
		System.out.println("Employee List with new salary:"
				+ miscUnaryOperators.getEmployeeSalaryIncrmentorFunction().apply(EmployeeList.get()));
		System.out.println("##########");

		System.out.println("Employee List with old designations: " + EmployeeList.get());
		System.out.println("Employee List with new designations:"
				+ miscUnaryOperators.getEployeeDesignationIncrementorFunction().apply(EmployeeList.get()));

		System.out.println("##########");

		System.out.println("Employee List with old designation bands : " + EmployeeList.get());
		System.out.println("Employee List with new designation bands :"
				+ miscUnaryOperators.getEmployeeDesignationBandIncrementorFunction().apply(EmployeeList.get()));

		System.out.println("##########");

		System.out.println("Employee List with old designations: " + EmployeeList.get());
		System.out.println("Employee List with new salary and  designations:"
				+ miscUnaryOperators.getEmployeeSalaryDesignationIncrementorFunction().apply(EmployeeList.get()));

		System.out.println("##########");

		System.out.println("Employee List with old designations salary and bands : " + EmployeeList.get());
		System.out.println("Employee List with new salary and  designations and bands :" + miscUnaryOperators
				.getemployeeSalaryDesignationAndBandIncrementorFunction().apply(EmployeeList.get()));
	}

}
