package com.katariasoft.technologies.Java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.interfaces.funtional.predefined.consumer.EmployeesConsumer;
import com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.employee.EmployeeFilterWithPredicate;
import com.katariasoft.technologies.Java8.interfaces.funtional.predefined.unaryoperator.EmployeeUnaryOperators;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;
import com.katariasoft.technologies.Java8.util.employee.EmployeeListSorter;

public class MiscStreamProcessor {

	private EmployeeUnaryOperators employeeUnaryOperators = new EmployeeUnaryOperators();
	private EmployeeFilterWithPredicate employeeFilterWithPredicate = new EmployeeFilterWithPredicate();
	private EmployeesConsumer employeesConsumer = new EmployeesConsumer();
	private EmployeeListSorter employeeListSorter = new EmployeeListSorter();

	public static interface MiscStreamProcessorCases {
		String PRINT_ALL_NAMES = "PRINT_ALL_NAMES";
		String SAY_HELLO_TO_ALL = "SAY_HELLO_TO_ALL";
		String INCRIMENT_EMPLOYEE_SALARY = "INCRIMENT_EMPLOYEE_SALARY";
		String INCRIMENT_EMPLOYEE_SALARY_FOR_PARTICULAR_DESIGNATION = "INCRIMENT_EMPLOYEE_SALARY_FOR_PARTICULAR_DESIGNATION";
		String PRINT_ALL_DESIGNATION_BANDS = "PRINT_ALL_DESIGNATION_BANDS";
		String PRINT_ALL_SALARIES = "PRINT_ALL_SALARIES";
		String SEND_SMS_TO_PROMOTION_ELIGIBLE_EMPLOYEES = "SEND_SMS_TO_PROMOTION_ELIGIBLE_EMPLOYEES";
		String printDistinctDesignations = "printDistinctDesignations";
		String printDistinctSalaries = "printDistintSalaries";
		String printEmployeesSortedByRelevance = "printEmployeesSortedByRelevance";

	}

	public static void main(String args[]) {
		List<String> teamMembers = Arrays.asList("Vaneet", "Pratapi", "Deepak", "Dheeraj", "Franka");
		// List<String> teamMembers = new ArrayList<>();
		MiscStreamProcessor processor = new MiscStreamProcessor();
		String testCase = MiscStreamProcessorCases.printEmployeesSortedByRelevance;
		// Execute test case.
		switch (testCase) {
		case MiscStreamProcessorCases.PRINT_ALL_NAMES:
			processor.printAll(teamMembers);
			break;
		case MiscStreamProcessorCases.SAY_HELLO_TO_ALL:
			processor.sayHelloToAll(teamMembers);
			break;
		case MiscStreamProcessorCases.INCRIMENT_EMPLOYEE_SALARY:
			processor.incrementEmployeeSalary(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.INCRIMENT_EMPLOYEE_SALARY_FOR_PARTICULAR_DESIGNATION:
			processor.incrementEmployeeSalaryWithParticularDesignationTechLead(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.PRINT_ALL_DESIGNATION_BANDS:
			processor.printAllDesignationBands(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.PRINT_ALL_SALARIES:
			processor.printAllSalaries(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.SEND_SMS_TO_PROMOTION_ELIGIBLE_EMPLOYEES:
			processor.sendSmsToPromotionEligibleEmployees(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.printDistinctDesignations:
			processor.printDistinctDesignations(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.printDistinctSalaries:
			processor.printDistinctSalaries(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.printEmployeesSortedByRelevance:
			processor.printEmployeesSortedByRelevance(EmployeeList.get());
			break;
		default:
			break;
		}

	}

	public void printAll(List<String> values) {
		Objects.requireNonNull(values);
		values.stream().forEach(System.out::println);
	}

	public void sayHelloToAll(List<String> values) {
		Objects.requireNonNull(values);
		values.stream().forEach(e -> System.out.println("Hello " + e));
	}

	public List<Employee> incrementEmployeeSalary(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		System.out.println("Employees with old salary are :" + employees);
		List<Employee> incrementedSalaryList = employees.stream()
				.map(employeeUnaryOperators.getSingleEmployeeSalaryIncrementor()).collect(Collectors.toList());
		System.out.println("Employees with new salary are :" + incrementedSalaryList);
		return incrementedSalaryList;
	}

	public List<Employee> incrementEmployeeSalaryWithParticularDesignationTechLead(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		System.out.println("Employees with old salary are :" + employees);
		List<Employee> incrementedSalaryList = employees.stream()
				.filter(employeeFilterWithPredicate.getHavingDesignationTechLeadPredicate())
				.map(employeeUnaryOperators.getSingleEmployeeSalaryIncrementor()).collect(Collectors.toList());
		System.out.println("Filtered Employees with new salary are :" + incrementedSalaryList);
		return incrementedSalaryList;
	}

	public void printAllDesignationBands(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		employees.stream().mapToInt(e -> Integer.valueOf(e.getDesignationBand()).intValue())
				.forEach(System.out::println);
	}

	public void printAllSalaries(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		employees.stream().mapToDouble(e -> e.getSalary()).forEach(System.out::println);
	}

	public void sendSmsToPromotionEligibleEmployees(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		employees.stream().filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.mapToLong(e -> e.getPhoneNumber()).forEach(employeesConsumer.getHolidayAnnouncementSender());
	}

	public void printDistinctDesignations(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		employees.stream().map(e -> {
			Objects.requireNonNull(e);
			return e.getDesignation();
		}).distinct().forEach(System.out::println);
	}

	public void printDistinctSalaries(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		employees.stream().mapToDouble(e -> e.getSalary()).distinct().forEach(System.out::println);
	}

	public void printEmployeesSortedByRelevance(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		System.out.println("Employee List without sorting : " + employees);
		employees.stream().sorted(employeeListSorter.getRelevanceSorter()).forEach(System.out::println);
	}

}
