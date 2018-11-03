package com.katariasoft.technologies.Java8.references.method;

import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.UnaryOperator;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

public class MiscMethodReferences {

	public boolean isEvenNumber(int i) {
		return i % 2 == 0;
	}

	public static boolean isOddNumber(int i) {
		return !(i % 2 == 0);
	}

	public static Employee incrementEmployeeSalary(Employee e) {
		Objects.requireNonNull(e);
		e.setSalary(e.getSalary() + 10000);
		return e;
	}

	public static void main(String args[]) {
		/*
		 * IntPredicate isOddNumberPredicate = i -> i % 2 != 0;
		 * System.out.println("5 is an 0dd number :" + isOddNumberPredicate.test(5));
		 * System.out.println("6 is an 0dd number :" + isOddNumberPredicate.test(6));
		 */

		IntPredicate isOddNumberPredivcate = MiscMethodReferences::isOddNumber;
		System.out.println("5 is an 0dd number :" + isOddNumberPredivcate.test(5));
		System.out.println("6 is an 0dd number :" + isOddNumberPredivcate.test(6));

		IntPredicate isEvenNumberPredicate = new MiscMethodReferences()::isEvenNumber;
		System.out.println("5 is an even number :" + isEvenNumberPredicate.test(5));
		System.out.println("6 is an even number :" + isEvenNumberPredicate.test(6));

		UnaryOperator<Employee> salaryIncementorLambda = e -> {
			Objects.requireNonNull(e);
			e.setSalary(e.getSalary() + 5000);
			return e;
		};
		UnaryOperator<Employee> salaryIncementorMethodRefrence = MiscMethodReferences::incrementEmployeeSalary;
		System.out.println(salaryIncementorLambda.apply(EmployeeList.get().get(0)));
		System.out.println(salaryIncementorMethodRefrence.apply(EmployeeList.get().get(0)));

	}

}
