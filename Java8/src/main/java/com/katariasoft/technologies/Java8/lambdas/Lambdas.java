package com.katariasoft.technologies.Java8.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.katariasoft.technologies.Java8.interfaces.funtional.AreaCalculator;
import com.katariasoft.technologies.Java8.interfaces.funtional.BaseFunctionalInterface;
import com.katariasoft.technologies.Java8.interfaces.funtional.StringLenghtProvider;

public class Lambdas {

	public static interface LambdaTestCases {
		String HELLO_WORLD = "HELLOWORLD";
		String PROVIDE_STRING_LENGTH = "PROVIDE_STRING_LENGTH";
		String PRINT_RECTANGLE_AREA = "PRINT_RECTANGLE_AREA";
		String CHILD_THREAD_RUN = "CHILD_THREAD_RUN";
		String NON_FUNCTIONAL_INTERFACE = "NON_FUNCTIONAL_INTERFACE";
		String SORT_AN_ARRAYLIST = "SORT_AN_ARRAYLIST";
		String TREE_SET_CASE = "TREE_SET_CASE";
		String TREE_MAP_CASE = "TREE_MAP_CASE";
		String EMPLOYEE_CASE = "EMPLOYEE_CASE";

	}

	public static class Employee {

		private int Age;
		private String name;

		public Employee(int age, String name) {
			super();
			Age = age;
			this.name = name;
		}

		public int getAge() {
			return Age;
		}

		public void setAge(int age) {
			Age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Employee [Age=" + Age + ", name=" + name + "]";
		}

	}

	public static void main(String args[]) {
		Lambdas lambdas = new Lambdas();
		lambdas.executeCase(LambdaTestCases.EMPLOYEE_CASE);
	}

	public void executeCase(String testCase) {
		switch (String.valueOf(testCase)) {
		case LambdaTestCases.HELLO_WORLD:
			helloWorld();
			break;
		case LambdaTestCases.PROVIDE_STRING_LENGTH:
			provideStringLength();
			break;
		case LambdaTestCases.PRINT_RECTANGLE_AREA:
			printRectangleArea();
			break;
		case LambdaTestCases.CHILD_THREAD_RUN:
			childTreadRun();
			break;
		case LambdaTestCases.NON_FUNCTIONAL_INTERFACE:
			nonFunctionalIntreface();
			break;
		case LambdaTestCases.SORT_AN_ARRAYLIST:
			sortAnArrayList();
			break;
		case LambdaTestCases.TREE_SET_CASE:
			treeSetCase();
			break;
		case LambdaTestCases.TREE_MAP_CASE:
			treeMapCase();
			break;
		case LambdaTestCases.EMPLOYEE_CASE:
			employeeCase();
			break;

		default:
			break;
		}

	}

	private void helloWorld() {
		// With braces
		BaseFunctionalInterface bfi = () -> {
			System.out.println("Hello world!!");
		};
		bfi.sayHelloWorld();
		// Without braces
		BaseFunctionalInterface bfi_withoutBraces = () -> System.out.println("Hello world!!");
		bfi_withoutBraces.sayHelloWorld();
	}

	private void provideStringLength() {
		StringLenghtProvider lp = (String s) -> {
			return Objects.isNull(s) ? 0 : s.length();
		};
		System.out.println(lp.provide("vaneet"));
		System.out.println(lp.provide(null));
		System.out.println(lp.provide(""));

		// More concise No need to wtite {} and retrun .
		StringLenghtProvider lpc = (s) -> Objects.isNull(s) ? 0 : s.length();
		System.out.println(lpc.provide("vaneet"));
		System.out.println(lpc.provide(null));
		System.out.println(lpc.provide(""));

		// More concise no need to write {} () and return statement .
		StringLenghtProvider lpmc = s -> Objects.isNull(s) ? 0 : s.length();
		System.out.println(lpmc.provide("vaneet"));
		System.out.println(lpmc.provide(null));
		System.out.println(lpmc.provide(""));

	}

	private void printRectangleArea() {
		AreaCalculator ac = (int l, int b) -> {
			return l * b;
		};
		System.out.println(ac.getArea(5, 4));
		System.out.println(ac.getArea(5, 0));
		System.out.println(ac.getArea(0, 4));

		AreaCalculator acc = (l, b) -> {
			return l * b;
		};
		System.out.println(acc.getArea(5, 4));
		System.out.println(acc.getArea(5, 0));
		System.out.println(acc.getArea(0, 4));

		AreaCalculator acmc = (l, b) -> l * b;
		System.out.println(acmc.getArea(5, 4));
		System.out.println(acmc.getArea(5, 0));
		System.out.println(acmc.getArea(0, 4));

	}

	private void childTreadRun() {
		System.out.println("Hello i am in main tread");
		new Thread(() -> {
			for (int i = 0; i < 1000; i++)
				System.out.println("Child");
		}).start();
		for (int i = 0; i < 1000; i++)
			System.out.println("Main");

	}

	// will not compile
	private void nonFunctionalIntreface() {
		// NonFunctionalInterface nfi = (int i) -> System.out.println(i);
		// nfi.input(5);
	}

	private void sortAnArrayList() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		System.out.println("List is: " + list);
		Collections.sort(list);
		System.out.println("Sorted List is: " + list);
		// using lambdas to descending sorting.
		Comparator<Integer> comparator = (Integer a, Integer b) -> {
			return (a < b) ? 1 : (a > b) ? -1 : 0;
		};
		// using lambdas to descending sorting.
		Comparator<Integer> comparatorMoreConcise = (a, b) -> (a < b) ? 1 : (a > b) ? -1 : 0;
		Collections.sort(list, comparatorMoreConcise);
		System.out.println("Sorted List in descending order is: " + list);

	}

	private void treeSetCase() {
		Set<Integer> list = new TreeSet<>();
		list.add(1);
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(1);
		list.add(2);
		System.out.println("Tree Set is: " + list);

		// Provide descending sort logic to constructor with lambdas

		Set<Integer> set = new TreeSet<>((a, b) -> a < b ? 1 : a > b ? -1 : 0);
		set.add(1);
		set.add(5);
		set.add(4);
		set.add(3);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(5);
		set.add(1);
		set.add(2);
		System.out.println("Descending  sorted Tree Set is: " + set);

	}

	private void treeMapCase() {
		Map<Integer, String> map = new TreeMap<>();
		map.put(6, "6");
		map.put(7, "7");
		map.put(8, "8");
		map.put(3, "3");
		map.put(1, "1");
		map.put(2, "2");
		map.put(4, "4");
		map.put(5, "5");
		System.out.println("Map is : " + map);

		Map<Integer, String> mapDesc = new TreeMap<>((a, b) -> a > b ? -1 : a < b ? 1 : 0);
		mapDesc.put(6, "6");
		mapDesc.put(7, "7");
		mapDesc.put(8, "8");
		mapDesc.put(3, "3");
		mapDesc.put(1, "1");
		mapDesc.put(2, "2");
		mapDesc.put(4, "4");
		mapDesc.put(5, "5");
		System.out.println("Descending Map is : " + mapDesc);

	}

	private void employeeCase() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(20, "ela"));
		employees.add(new Employee(21, "dla"));
		employees.add(new Employee(22, "cla"));
		employees.add(new Employee(23, "bla"));
		employees.add(new Employee(24, "ala"));
		employees.add(new Employee(20, "aela"));
		System.out.println("Employees are " + employees);

		Collections.sort(employees, (e1, e2) -> e1.getAge() < e2.getAge() ? -1 : e1.getAge() > e2.getAge() ? 1 : 0);
		System.out.println("Employees sorted by age are : " + employees);

		Collections.sort(employees, (e1, e2) -> e1.getName().compareTo(e2.getName()));
		System.out.println("Employees sorted by name are : " + employees);

		Collections.sort(employees, (e1, e2) -> (e1.getAge() > e2.getAge()) ? 1
				: (e1.getAge() < e2.getAge()) ? -1 : e1.getName().compareTo(e2.getName()));
		System.out.println("Employees sorted by age then name are : " + employees);

	}

}