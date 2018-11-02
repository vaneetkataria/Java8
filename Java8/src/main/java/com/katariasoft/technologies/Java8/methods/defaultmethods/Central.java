package com.katariasoft.technologies.Java8.methods.defaultmethods;

public class Central implements Left, Right {

	public void printMessage() {
		Left.super.printMessage();
		Right.super.printMessage();
		System.out.println("Message printed by Central Interface message printer.");
	}

	public static void main(String args[]) {
		Central c = new Central();
		c.printMessage();
	}
}
