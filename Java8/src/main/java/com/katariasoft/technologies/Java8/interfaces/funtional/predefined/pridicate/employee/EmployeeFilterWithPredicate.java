package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

public class EmployeeFilterWithPredicate {

	private Predicate<Employee> havingDesignationTechLead = e -> Objects.nonNull(e)
			&& Objects.nonNull(e.getDesignation()) && "techlead".equalsIgnoreCase(e.getDesignation());
	private Predicate<Employee> havingSalaryGreterThan25000 = e -> Objects.nonNull(e) && Objects.nonNull(e.getSalary())
			&& e.getSalary() > 25000;
	private Predicate<Employee> havingLocationAmbala = e -> Objects.nonNull(e) && Objects.nonNull(e.getLocation())
			&& e.getLocation().equalsIgnoreCase("ambala");
	private Predicate<Employee> havingLocationUp = e -> Objects.nonNull(e) && Objects.nonNull(e.getLocation())
			&& e.getLocation().equalsIgnoreCase("up");
	private Predicate<Employee> withSexMale = e -> Objects.nonNull(e) && Objects.nonNull(e.getSex())
			&& e.getSex().equalsIgnoreCase("male");
	private Predicate<Employee> havingMaritalStatusMarried = e -> Objects.nonNull(e)
			&& Objects.nonNull(e.getMaritalStatus()) && e.getMaritalStatus().equalsIgnoreCase("married");
	// provide pink slip to male employees working as technical lead in Bangluru
	// having salary
	// greater than 25000.
	private Predicate<Employee> pinkSlipEmployee = havingDesignationTechLead.and(havingSalaryGreterThan25000)
			.and(withSexMale).and(havingLocationAmbala);
	// promote male working in up having status married.
	private Predicate<Employee> promotionEligible = withSexMale.and(havingMaritalStatusMarried.negate())
			.and(havingLocationUp);
	// on site eligible
	private Predicate<Employee> onSiteEligible = (withSexMale.and(havingMaritalStatusMarried)).or(havingLocationUp)
			.or(havingMaritalStatusMarried.and(withSexMale.negate()));

	public List<Employee> filterEmployeesForPinkSlip(List<Employee> employees) {
		return filter(pinkSlipEmployee, employees);
	}

	public List<Employee> filterEmployeesForPromotion(List<Employee> employees) {
		return filter(promotionEligible, employees);
	}

	public List<Employee> filterEmployeesOnSiteEligible(List<Employee> employees) {
		return filter(onSiteEligible, employees);
	}

	private List<Employee> filter(Predicate<Employee> coditionalPredicate, List<Employee> employees) {
		List<Employee> filteredList = new ArrayList<>();
		for (Employee employee : employees)
			if (coditionalPredicate.test(employee))
				filteredList.add(employee);
		return filteredList;
	}

	public static void main(String args[]) {
		EmployeeFilterWithPredicate filter = new EmployeeFilterWithPredicate();
		System.out.println("Pink slip eligible: ");
		System.out.println(filter.filterEmployeesForPinkSlip(EmployeeList.get()));
		System.out.println("Promotion eligible: ");
		System.out.println(filter.filterEmployeesForPromotion(EmployeeList.get()));
		System.out.println("Onsite eligible: ");
		System.out.println(filter.filterEmployeesOnSiteEligible(EmployeeList.get()));

	}

}
