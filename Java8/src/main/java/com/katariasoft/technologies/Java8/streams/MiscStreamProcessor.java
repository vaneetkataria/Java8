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

public class MiscStreamProcessor {

	private EmployeeUnaryOperators employeeUnaryOperators = new EmployeeUnaryOperators();
	private EmployeeFilterWithPredicate employeeFilterWithPredicate = new EmployeeFilterWithPredicate();
	private EmployeesConsumer employeesConsumer = new EmployeesConsumer();
	private EmployeeListSorterUsingComparing employeeListSorterCmp = new EmployeeListSorterUsingComparing();

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
		String sendSmsEmailToEmployeesSkipFirstTwo = "sendSmsEmailToEmployeesSkipFirstTwo";
		String minMaxCountOfSortedByRelevanceEmployees = "minMaxCountOfSortedByRelevanceEmployees";
		String anyMatchAllMatchNoneMatch = "anyMatchAllMatchNoneMatch";
		String findFirstFindAny = "findFirstFindAny";

	}

	public static void main(String args[]) {
		List<String> teamMembers = Arrays.asList("Vaneet", "Pratapi", "Deepak", "Dheeraj", "Franka");
		// List<String> teamMembers = new ArrayList<>();
		MiscStreamProcessor processor = new MiscStreamProcessor();
		String testCase = MiscStreamProcessorCases.findFirstFindAny;
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
		case MiscStreamProcessorCases.sendSmsEmailToEmployeesSkipFirstTwo:
			processor.sendSmsEmailToEmployees(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.minMaxCountOfSortedByRelevanceEmployees:
			processor.minMaxCountOfSortedByRelevanceEmployees(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.anyMatchAllMatchNoneMatch:
			processor.anyMatchAllMatchNoneMatch(EmployeeList.get());
			break;
		case MiscStreamProcessorCases.findFirstFindAny:
			processor.findFirstFindAny(EmployeeList.get());
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
				.peek(e -> System.out.println("Next employee in the pipeline is:" + e.getName()))
				.filter(employeeFilterWithPredicate.getHavingDesignationTechLeadPredicate())
				.peek(e -> System.out
						.println("Next filtered promotion eligible employee in the pipeline is:" + e.getName()))
				.map(employeeUnaryOperators.getSingleEmployeeSalaryIncrementor())
				.peek(e -> System.out.println(
						"Next filtered promotion eligible employee salary incrimented in the pipeline is:" + e))
				.collect(Collectors.toList());
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
		employees.stream().peek(e -> System.out.println("Next employee in the pipeline is:" + e.getName()))
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.peek(e -> System.out.println(e.getName() + "is promotion eligible")).mapToLong(e -> e.getPhoneNumber())
				.peek(p -> System.out.println("Going to send sms on " + p))
				.forEach(employeesConsumer.getHolidayAnnouncementSender());
	}

	public void printDistinctDesignations(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		employees.stream().peek(e -> System.out.println("Next employee in the pipeline is:" + e.getName())).map(e -> {
			Objects.requireNonNull(e);
			return e.getDesignation();
		}).peek(s -> System.out.println("Next employee designation in the pipeline is:" + s)).distinct()
				.peek(s -> System.out.println("Next ditinct employee designation in the pipeline is:" + s))
				.forEach(System.out::println);
	}

	public void printDistinctSalaries(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		employees.stream().peek(e -> System.out.println("Next employee in the pipeline is:" + e.getName()))
				.mapToDouble(e -> e.getSalary())
				.peek(s -> System.out.println("Next employee salary in the pipeline is:" + s)).distinct()
				.peek(s -> System.out.println("Next Distinct salry in the pipeline is:" + s))
				.forEach(System.out::println);
	}

	public void printEmployeesSortedByRelevance(List<Employee> employees) {
		Objects.requireNonNull(employees, "Employee List cannot be empty.");
		System.out.println("Employee List without sorting : " + employees);
		Employee[] employeesArr = employees.stream()
				.peek(e -> System.out.println("Next employee in the pipeline " + "is :" + e.getName()))
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.peek(e -> System.out.println(e.getName() + " is Promotion eligible ."))
				.sorted(employeeListSorterCmp.getByRelevance()).toArray(Employee[]::new);
		Arrays.stream(employeesArr).forEach(System.out::println);

	}

	public void sendSmsEmailToEmployees(List<Employee> employees) {
		Objects.requireNonNull(employees);
		employees.stream().limit(4)
				.peek(e -> System.out.println("Next employee in the pipeline before max limit is: " + e.getName()))
				.skip(2).peek(e -> System.out.println("Next employee in the pipeline after skip is " + e.getName()))
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.peek(e -> System.out.println(e.getName() + " is Promotion eligible ."))
				.forEach(e -> System.out.println("Sms email sent to " + e.getName()));
	}

	public void minMaxCountOfSortedByRelevanceEmployees(List<Employee> employees) {
		Objects.requireNonNull(employees);
		long count = employees.stream().limit(3).skip(1)
				.peek(e -> System.out.println("Next employee in the pipeline " + "is :" + e.getName()))
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.peek(e -> System.out
						.println("Next employee eligible for promotion in the pipeline " + "is :" + e.getName()))
				// .sorted(employeeListSorterCmp.getByRelevance()).forEach(System.out::println);
				// .min(employeeListSorterCmp.getByRelevance()).ifPresent(System.out::println);
				// .max(employeeListSorterCmp.getByRelevance()).ifPresent(System.out::println);
				.count();
		System.out.println("Num employees eligible for promotion is: " + count);

	}

	public void anyMatchAllMatchNoneMatch(List<Employee> employees) {
		Objects.requireNonNull(employees);
		System.out.println(employees.stream()
				.peek(e -> System.out.println("Next employee in the pipeline " + "is :" + e.getName()))
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.peek(e -> System.out
						.println("Next employee eligible for promotion in the pipeline " + "is :" + e.getName()))
				// .anyMatch(e -> e.getAge() < 40));
				// .allMatch(e -> e.getAge() < 28));
				.noneMatch(e -> e.getAge() < 26));

	}

	public void findFirstFindAny(List<Employee> employees) {
		Objects.requireNonNull(employees);
		employees.stream().peek(e -> System.out.println("Next employee in the pipeline " + "is :" + e.getName()))
				.filter(e -> e.getAge() < 28)
				.peek(e -> System.out
						.println("Next employee eligible after filteration in the pipeline " + "is :" + e.getName()))
				.findFirst().ifPresent(System.out::println);

	}

}
