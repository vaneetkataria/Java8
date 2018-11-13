package com.katariasoft.technologies.Java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MiscStreamProcessor {

	public void printAll(List<String> values) {
		Objects.requireNonNull(values);
		values.stream().forEach(System.out::println);
	}

	public void sayHelloToAll(List<String> values) {
		Objects.requireNonNull(values);
		values.stream().forEach(e -> System.out.println("Hello " + e));
	}

	public static void main(String args[]) {
		MiscStreamProcessor processor = new MiscStreamProcessor();
		List<String> teamMembers = Arrays.asList("Vaneet", "Pratapi", "Deepak", "Dheeraj", "Franka");
		processor.printAll(teamMembers);
		processor.sayHelloToAll(teamMembers);
	}

}
