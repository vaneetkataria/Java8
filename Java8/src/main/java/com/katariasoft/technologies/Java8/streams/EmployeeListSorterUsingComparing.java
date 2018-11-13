package com.katariasoft.technologies.Java8.streams;

import java.util.Comparator;
import java.util.Objects;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

public class EmployeeListSorterUsingComparing {

	// sort by employeeName length
	private Comparator<String> stringLengthComparator = (s1, s2) -> (Objects.isNull(s1) || Objects.isNull(s2)) ? 0
			: s1.length() > s2.length() ? 1 : s1.length() < s2.length() ? -1 : 0;

	private Comparator<Employee> byName = Comparator.comparing(e -> e.getName());
	private Comparator<Employee> byAge = Comparator.comparingInt(e -> e.getAge());
	private Comparator<Employee> byDesignationBand = Comparator.comparing(e -> e.getDesignationBand());
	private Comparator<Employee> bySalary = Comparator.comparingDouble(e -> e.getSalary());
	private Comparator<Employee> byMobileNo = Comparator.comparingLong(e -> e.getPhoneNumber());
	private Comparator<Employee> byAddress = Comparator.comparing(e -> e.getAddress());
	private Comparator<Employee> byLocationNameLength = Comparator.comparing(e -> e.getLocation(),
			stringLengthComparator);

	// composite sorter
	private Comparator<Employee> byRelevance = bySalary.thenComparing(byAge).thenComparing(byDesignationBand)
			.thenComparing(byName).thenComparing(byMobileNo).thenComparing(byAddress);

	// composite sorter chained
	private Comparator<Employee> byRelevanceChained = Comparator.comparingDouble((Employee e) -> e.getSalary())
			.thenComparing(Comparator.comparingInt(e -> e.getAge()))
			.thenComparing(Comparator.comparing(e -> e.getDesignationBand()))
			.thenComparing(Comparator.comparing(e -> e.getName()))
			.thenComparing(Comparator.comparingLong(e -> e.getPhoneNumber()))
			.thenComparing(Comparator.comparing(e -> e.getAddress())).thenComparing(byLocationNameLength);

	private Comparator<Employee> nullFirstByRelevanceChained = Comparator.nullsFirst(byRelevanceChained);
	private Comparator<Employee> nullLastByRelevanceChained = Comparator.nullsLast(byRelevanceChained);
	private Comparator<Employee> naturalOrderCompataror = Comparator.naturalOrder();
	private Comparator<Employee> reverseOrderCompataror = Comparator.reverseOrder();

	public Comparator<Employee> getByName() {
		return byName;
	}

	public Comparator<Employee> getByAge() {
		return byAge;
	}

	public Comparator<Employee> getByDesignationBand() {
		return byDesignationBand;
	}

	public Comparator<Employee> getBySalary() {
		return bySalary;
	}

	public Comparator<Employee> getByMobileNo() {
		return byMobileNo;
	}

	public Comparator<Employee> getByAddress() {
		return byAddress;
	}

	public Comparator<Employee> getByRelevance() {
		return byRelevanceChained;
	}

	public Comparator<Employee> getByRelevanceChained() {
		return byRelevanceChained;
	}

	public Comparator<Employee> getByRelevanceNullFirst() {
		return nullFirstByRelevanceChained;
	}

	public Comparator<Employee> getByRelevanceNullLast() {
		return nullLastByRelevanceChained;
	}

	public static void main(String args[]) {
		EmployeeListSorterUsingComparing sorter = new EmployeeListSorterUsingComparing();
		System.out.println("EmployeeList before sorting is :" + EmployeeList.get());
		System.out.println("EmployeeList after sorting is :");
		EmployeeList.get().stream().sorted(sorter.byRelevance).forEach(System.out::println);
		System.out.println("###By Chained comparator");
		EmployeeList.get().stream().sorted(sorter.byRelevanceChained).forEach(System.out::println);
		System.out.println("###Null First sorted by refrence");
		EmployeeList.getIncludingNulls().stream().sorted(sorter.nullFirstByRelevanceChained)
				.forEach(System.out::println);
		System.out.println("Null Last sorted by refrence");
		EmployeeList.getIncludingNulls().stream().sorted(sorter.nullLastByRelevanceChained)
				.forEach(System.out::println);
		System.out.println("######################");
		System.out.println("Employees sorted by natural order");
		EmployeeList.get().stream().sorted(sorter.naturalOrderCompataror).forEach(System.out::println);
		System.out.println("######################");
		System.out.println("Employees sorted by reverse order");
		EmployeeList.get().stream().sorted(sorter.reverseOrderCompataror).forEach(System.out::println);

	}

}