package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.supplier;

import java.util.List;
import java.util.function.Supplier;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

public class LuckyEmployeeOfTheMonthFunctionProvider {

	private List<Employee> employees = EmployeeList.get();

	public Supplier<Employee> luckyEmployeeOfTheMonthProvider() {
		return () -> employees.get((int) (Math.random() * employees.size()));
	}

	public static void main(String args[]) {
		LuckyEmployeeOfTheMonthFunctionProvider luckyEmployeeOfTheMonth = new LuckyEmployeeOfTheMonthFunctionProvider();

		for (byte b = 1; b <= 12; b++)
			System.out.println("Lucky employee of the month" + b + " is: "
					+ luckyEmployeeOfTheMonth.luckyEmployeeOfTheMonthProvider().get());

	}

}
