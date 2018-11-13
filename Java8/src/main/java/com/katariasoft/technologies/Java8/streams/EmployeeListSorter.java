package com.katariasoft.technologies.Java8.streams;

import java.util.Comparator;
import com.katariasoft.technologies.Java8.beans.Employee;

public class EmployeeListSorter {

	private Comparator<Employee> nameComparator = (e1, e2) -> e1.getName().compareTo(e2.getName());
	private Comparator<Employee> ageComparator = (e1, e2) -> Integer.valueOf(e1.getAge())
			.compareTo(Integer.valueOf(e2.getAge()));
	private Comparator<Employee> designationBandComparator = (e1, e2) -> Integer.valueOf(e1.getDesignationBand())
			.compareTo(Integer.valueOf(e2.getDesignationBand()));
	private Comparator<Employee> salaryComparator = (e1, e2) -> Double.valueOf(e1.getSalary())
			.compareTo(e2.getSalary());
	private Comparator<Employee> nameComparatorDesc = nameComparator.reversed();
	private Comparator<Employee> ageComparatorDesc = ageComparator.reversed();
	private Comparator<Employee> designationBandComparatorDesc = designationBandComparator.reversed();
	private Comparator<Employee> salaryComparatorDesc = salaryComparator.reversed();
	private Comparator<Employee> relevanceSorter = salaryComparator.thenComparing(ageComparator)
			.thenComparing(designationBandComparatorDesc).thenComparing(nameComparator);

	public Comparator<Employee> getNameComparator() {
		return nameComparator;
	}

	public Comparator<Employee> getAgeComparator() {
		return ageComparator;
	}

	public Comparator<Employee> getDesignationBandComparator() {
		return designationBandComparator;
	}

	public Comparator<Employee> getSalaryComparator() {
		return salaryComparator;
	}

	public Comparator<Employee> getNameComparatorDesc() {
		return nameComparatorDesc;
	}

	public Comparator<Employee> getAgeComparatorDesc() {
		return ageComparatorDesc;
	}

	public Comparator<Employee> getDesignationBandComparatorDesc() {
		return designationBandComparatorDesc;
	}

	public Comparator<Employee> getSalaryComparatorDesc() {
		return salaryComparatorDesc;
	}

	public Comparator<Employee> getRelevanceSorter() {
		return relevanceSorter;
	}

}
