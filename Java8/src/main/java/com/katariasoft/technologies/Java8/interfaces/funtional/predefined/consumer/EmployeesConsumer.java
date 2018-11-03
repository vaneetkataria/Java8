package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.consumer;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

public class EmployeesConsumer {

	public Consumer<List<Employee>> sendSmsToEmployees() {
		return l -> {
			Objects.requireNonNull(l, "Employee list cannot be null or empty");
			for (Employee e : l) {
				System.out.println("Sms sent to employee :" + e.getName());
			}
		};
	}

	public Consumer<List<Employee>> sendEmailToEmployees() {
		return l -> {
			Objects.requireNonNull(l, "Employee list cannot be null or empty");
			for (Employee e : l) {
				System.out.println("Email sent to employee :" + e.getName());
			}
		};
	}

	public Consumer<List<Employee>> sendSmsEmailToUser() {
		return sendSmsToEmployees().andThen(sendEmailToEmployees());
	}

	public static void main(String args[]) {
		EmployeesConsumer consumer = new EmployeesConsumer();
		consumer.sendSmsToEmployees().accept(EmployeeList.get());
		consumer.sendEmailToEmployees().accept(EmployeeList.get());
		consumer.sendSmsEmailToUser().accept(EmployeeList.get());
	}

}
