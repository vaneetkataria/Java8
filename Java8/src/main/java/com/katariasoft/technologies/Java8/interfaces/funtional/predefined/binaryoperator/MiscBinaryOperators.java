package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.binaryoperator;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.UnaryOperator;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

public class MiscBinaryOperators {

	private IntBinaryOperator rectangleAreaCalculator = rectangleAreaCalculatorImpl();
	private IntBinaryOperator rectangleParameterCalculator = rectangleParameterCalculatorImpl();
	private BinaryOperator<Employee> betterEmployeeSelectorFunction = betterEmployeeSelectorFunctionImpl();
	private UnaryOperator<Employee> salaryIncementorFunction = salaryIncementorFunctionImpl();
	private BiFunction<Employee, Employee, Employee> betterEmployeeSalaryIncementorFunction = betterEmployeeSalaryIncementorFunctionImpl();
	private BiFunction<Employee, Employee, Employee> betterEmployeeSalaryDesignationIncementorFunction = betterEmployeeSalaryDesignationIncementorFunctionImpl();

	private IntBinaryOperator rectangleAreaCalculatorImpl() {
		return (l, b) -> l * b;
	}

	private IntBinaryOperator rectangleParameterCalculatorImpl() {
		return (l, b) -> 2 * (l + b);
	}

	public IntBinaryOperator getRectangleAreaCalculator() {
		return rectangleAreaCalculator;
	}

	public IntBinaryOperator getRectangleParameterCalculator() {
		return rectangleParameterCalculator;
	}

	public BinaryOperator<Employee> getBetterEmployeeSelectorFunction() {
		return betterEmployeeSelectorFunction;
	}

	public UnaryOperator<Employee> getSalaryIncementorFunction() {
		return salaryIncementorFunction;
	}

	public BiFunction<Employee, Employee, Employee> getBetterEmployeeSalaryIncementorFunction() {
		return betterEmployeeSalaryIncementorFunction;
	}

	public BiFunction<Employee, Employee, Employee> getBetterEmployeeSalaryDesignationIncementorFunction() {
		return betterEmployeeSalaryDesignationIncementorFunction;
	}

	private BinaryOperator<Employee> betterEmployeeSelectorFunctionImpl() {
		return (e1, e2) -> {
			Objects.requireNonNull(e1, "Employee 1 cannot be null.");
			Objects.requireNonNull(e2, "Employee 2 cannot be null.");
			return (e1.getAge() < e2.getAge() && e1.getSalary() > e2.getSalary()) ? e1 : e2;
		};
	}

	private UnaryOperator<Employee> salaryIncementorFunctionImpl() {
		return e -> {
			Objects.requireNonNull(e, "Employee cannot be null");
			e.setSalary(e.getSalary() + 10000);
			return e;
		};
	}

	private BiFunction<Employee, Employee, Employee> betterEmployeeSalaryIncementorFunctionImpl() {
		return betterEmployeeSelectorFunction.andThen(salaryIncementorFunction);
	}

	private BiFunction<Employee, Employee, Employee> betterEmployeeSalaryDesignationIncementorFunctionImpl() {
		UnaryOperator<Employee> designationIncrementorFunction = e -> {
			Objects.requireNonNull(e, "Employee cannnot be empty");
			e.setDesignation("Manager");
			return e;
		};

		// return
		// betterEmployeeSelectorFunction.andThen(salaryIncementorFunction).andThen(designationIncrementorFunction);
		return betterEmployeeSalaryIncementorFunction.andThen(designationIncrementorFunction);
	}

	public static void main(String args[]) {
		MiscBinaryOperators miscBinaryOperators = new MiscBinaryOperators();
		System.out.println("Rectangle Area for length 5 and width 5 is :"
				+ miscBinaryOperators.getRectangleAreaCalculator().applyAsInt(5, 5));
		System.out.println("Rectangle parameter for length 5 and width 5 is :"
				+ miscBinaryOperators.getRectangleParameterCalculator().applyAsInt(5, 5));

		System.out.println(miscBinaryOperators.getBetterEmployeeSelectorFunction().apply(EmployeeList.get().get(0),
				EmployeeList.get().get(1)));
		System.out.println(miscBinaryOperators.getBetterEmployeeSalaryIncementorFunction()
				.apply(EmployeeList.get().get(0), EmployeeList.get().get(1)));

		System.out.println(miscBinaryOperators.getBetterEmployeeSalaryDesignationIncementorFunction()
				.apply(EmployeeList.get().get(0), EmployeeList.get().get(1)));
	}

}
