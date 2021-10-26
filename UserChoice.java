package com.te.consolebasedapplication.utility;

import java.util.Scanner;

public class UserChoice {

	private UserChoice() {
	}

	static Scanner scanner = new Scanner(System.in);

	public static void toChooseFuction(Integer empno) {

		System.out.println("-------------------------------------------");
		System.out.println("Please enter the number you want to proceed");
		System.out.println("1. To get all your details");
		System.out.println("2. To Update your details");
		System.out.println("3. To delete the record");
		System.out.println("-------------------------------------------");

		int number = scanner.nextInt();

		switch (number) {
		case 1:
			ToShowData.toFetchData(empno);
			break;
		case 2:
			UpdateDetails.toUpdatingDetails(empno);
			break;
		case 3:
			DeleteData.ensureDelete(empno);
			break;
		default:
			System.out.println("Wrong selection");
			break;
		}

	}

}
