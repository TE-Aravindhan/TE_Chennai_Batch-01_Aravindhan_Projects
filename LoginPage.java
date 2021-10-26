package com.te.consolebasedapplication.utility;

import java.util.Scanner;

import com.te.consolebasedapplication.bean.EmpInfo;

public class LoginPage {

	private LoginPage() {
	}

	static Scanner scanner = new Scanner(System.in);

	public static void choicesOfUser() {

		System.out.println("**********Welcome*********");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("-------------------------------------------");
		System.out.println("Please enter the number you want to proceed");
		System.out.println("-------------------------------------------");

		int number = scanner.nextInt();

		switch (number) {
		case 1:
			LoginCheck.toCheckLogindetails(new EmpInfo());
			break;
		case 2:
			RegisterUser.register();
			break;

		default:
			System.out.println("----------------------------------------------------------");
			System.out.println("Oops... Wrong selection please enter appropriate number!!!");
			System.out.println("----------------------------------------------------------");

			choicesOfUser();
			break;
		}
	}
}
