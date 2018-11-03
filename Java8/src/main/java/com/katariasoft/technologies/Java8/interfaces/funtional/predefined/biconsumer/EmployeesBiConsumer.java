package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.biconsumer;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

public class EmployeesBiConsumer {

	public BiConsumer<List<Employee>, String> employeeListAndSmsConsumer() {
		return (l, s) -> {
			Objects.requireNonNull(l, "Employee list cannot be null or empty");
			Objects.requireNonNull(s, "Sms Text cannot be null or empty");
			if (s.isEmpty())
				throw new IllegalArgumentException("Sms text must not be blank");
			for (Employee e : l) {
				System.out.println("Sms text :" + s + " sent to employee :" + e.getName());
			}
		};
	}

	public BiConsumer<List<Employee>, String> employeeListAndEmailConsumer() {
		return (l, e) -> {
			Objects.requireNonNull(l, "Employee list cannot be null or empty");
			Objects.requireNonNull(e, "Email Text cannot be null or empty");
			if (e.isEmpty())
				throw new IllegalArgumentException("Email text must not be blank");
			for (Employee emp : l) {
				System.out.println("Email text :" + e + " sent to employee :" + emp.getName());
			}
		};
	}

	public BiConsumer<List<Employee>, String> employeeTextSenderToMobileAndEmail() {
		return employeeListAndSmsConsumer().andThen(employeeListAndEmailConsumer());
	}

	public static void main(String args[]) {

		EmployeesBiConsumer employeesBiConsumer = new EmployeesBiConsumer();
		employeesBiConsumer.employeeListAndSmsConsumer().accept(EmployeeList.get(), "Hello Hi are you");
		employeesBiConsumer.employeeListAndEmailConsumer().accept(EmployeeList.get(), "Hello Hi are you");
		employeesBiConsumer.employeeTextSenderToMobileAndEmail().accept(EmployeeList.get(), "Hello Hi are you");

	}

}
