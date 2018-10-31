package com.katariasoft.technologies.Java8.lambdas;

import java.util.Objects;

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

	}

	public static void main(String args[]) {
		Lambdas lambdas = new Lambdas();
		lambdas.executeCase(LambdaTestCases.NON_FUNCTIONAL_INTERFACE);
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

}