package com.katariasoft.technologies.Java8.methods.defaultmethods;

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

	// All method overriding rules will be same as before following all Liskov's
	// substitution principle
	default void printMessage() {
		Upper.super.printMessage();
		System.out.println("Message printed by Left Interface message printer.");
	}

}
