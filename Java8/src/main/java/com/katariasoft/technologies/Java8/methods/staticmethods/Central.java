package com.katariasoft.technologies.Java8.methods.staticmethods;

public class Central implements Left, Right {

	public void printMessage() {
		Left.super.printMessage();
		Right.super.printMessage();
		System.out.println("Message printed by Central Interface message printer.");
	}

	public static void main(String args[]) {
		Central c = new Central();
		System.out.println(c.getMessage());
	}

	// This message is not been overridden this is only a new defenition at this
	// class level . If this was overridden then below instance method was not
	// possible . But these two as defined below cannot be in the same class as they
	// have the same name .
	public String getMessage() {
		return "Message from Central interface static method .";
	}

	// public static String getMessage() {
	// return "Message from Central interface static method .";
	// }
}
