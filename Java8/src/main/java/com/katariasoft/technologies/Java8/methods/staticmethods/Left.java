package com.katariasoft.technologies.Java8.methods.staticmethods;

//Any method declared as private , protected is not allowed . 
//If not any modifies is declared then it is by default public .

public interface Left extends Upper {
	// 1. Not allowed only public
	// private static final int a = 10;
	// 2. Not allowed only public
	// protected static final int a = 10;
	// 3.c By default varibales are public static final
	// int a =10 or public int a = 10;
	// 4. Not allowed only public
	// private void m1();
	// 5. Not allowed only public
	// protected void m1();
	// 6. allowed by default only public
	// void m1(); or public void m1();
	// 7. Not allowed as default methods are only for non static context .
	// default static void m1() {}
	default void printMessage() {
		Upper.super.printMessage();
		System.out.println("Message printed by Left Interface message printer.");
	}

	// 8. Not possible to have private static method .
	// private static String getMessage()
	// {
	// }
	// 9. Not possible to have protected static method .
	// protected static String getMessage() {
	// }
	// 10. possible to have public static method . specify public or not its by
	// default public
	static String getMessage() {
		return "Message from Left interface static method .";

	}

	// 1 . This is a general public utility method only which cannot be overridden
	// by any implementation class . It can be accessed from any where with the name
	// of this interface only
	// 2. possible to have main method in interfaces in java 8 . By default it is
	// public .
	static void main(String args[]) {
		System.out.println(getMessage());
		System.out.println(Upper.getMessage());
	}

}
