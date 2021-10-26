
package com.te.consolebasedapplication.utility;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.consolebasedapplication.bean.EmpInfo;

public class UpdateDetails {

	private UpdateDetails() {
	}

	static Scanner scanner = new Scanner(System.in);
	static EntityManagerFactory emf = null;
	static EntityManager em = null;
	static EntityTransaction transaction = null;

	public static void toUpdatingDetails(Integer empno) {

		LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);

		try {

			emf = Persistence.createEntityManagerFactory("emp");
			em = emf.createEntityManager();
			transaction = em.getTransaction();

			System.out.println("---------------------------------------------------");
			System.out.println("Please select the field you want to update.....");
			System.out.println("---------------------------------------------------");

			System.out.println("1.To update your Name please enter              : 1");
			System.out.println("2.To update your Designation please enter       : 2");
			System.out.println("3.To update your Salary please enter            : 3");
			System.out.println("4.To update your Department Number please enter : 4");
			System.out.println("---------------------------------------------------");

			Integer keys = scanner.nextInt();

			transaction.begin();
			switch (keys) {
			case 1: {
				toUpdateName(empno);
				break;
			}
			case 2: {
				toUpdateRole(empno);
				break;
			}

			case 3: {
				toUpdateSalary(empno);
				break;
			}
			case 4: {
				toUpdateDeptno(empno);
				break;
			}
			default:
				System.out.println("Oops...Wrong selection");
				break;
			}
			transaction.commit();
		} catch (Exception e) {

			transaction.rollback();

			System.out.println("Oops....Update Unsuccessful");

			toUpdatingDetails(empno);

		} finally {
			try {
				if (em != null) {
					em.close();
				}
				if (emf != null) {
					emf.close();
				}

			} catch (Exception e2) {
				System.out.println("system Error Try After sometime");
			}
		}

		System.out.println("If you want update anything enter Y/N");
		String condition = scanner.next();

		if (condition.equalsIgnoreCase("Y")) {

			toUpdatingDetails(empno);

		} else if (condition.equalsIgnoreCase("N")) {

			System.out.println("Details Updated Successfully!!! ");
			System.out.println("If you want proceed anything enter Y/N");
			String userInput = scanner.next();

			if (userInput.equalsIgnoreCase("Y")) {

				UserChoice.toChooseFuction(empno);

			} else if (userInput.equalsIgnoreCase("N")) {

				System.out.println("Thank You Have a Nice Day -_-");
			}
		}

	}

	public static void toUpdateName(Integer empno) {

		System.out.println("Enter the Name :");
		EmpInfo info = em.getReference(EmpInfo.class, empno);
		info.setEname(scanner.next().toUpperCase());
	}

	public static void toUpdateRole(Integer empno) {

		System.out.println("Enter the Designation :");
		EmpInfo info = em.getReference(EmpInfo.class, empno);
		info.setRole(scanner.next().toUpperCase());
	}

	public static void toUpdateSalary(Integer empno) {

		System.out.println("Enter the Salary :");
		EmpInfo info = em.getReference(EmpInfo.class, empno);
		info.setSalary(Double.parseDouble(scanner.next()));
	}

	public static void toUpdateDeptno(Integer empno) {

		System.out.println("Enter the Department Number :");
		EmpInfo info = em.getReference(EmpInfo.class, empno);
		info.setDeptno(Integer.parseInt(scanner.next()));
	}

}
