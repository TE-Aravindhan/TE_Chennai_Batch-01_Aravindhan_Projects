package com.te.consolebasedapplication.utility;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.te.consolebasedapplication.bean.EmpInfo;

public class ToShowData {

	private ToShowData() {
	}

	static EntityManagerFactory emf = null;
	static EntityManager em = null;
	static Scanner scanner = new Scanner(System.in);

	public static void toFetchData(Integer empno) {

		LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);

		emf = Persistence.createEntityManagerFactory("emp");
		em = emf.createEntityManager();

		EmpInfo info = em.getReference(EmpInfo.class, empno);

		if (info != null) {
			System.out.println(":::::::::::::Employee Details::::::::::::::");
			System.out.println("-------------------------------------------");
			System.out.println("Id             : " + info.getEmpno());
			System.out.println("Name           : " + info.getEname());
			System.out.println("Date           : " + info.getHiredate());
			System.out.println("Designation    : " + info.getRole());
			System.out.println("Salary         : " + info.getSalary());
			System.out.println("Department Id  : " + info.getDeptno());
			System.out.println("-------------------------------------------");

		}

		System.out.println("If you want proceed anything enter Y/N");
		String userInput = scanner.next();

		if (userInput.equalsIgnoreCase("Y")) {

			UserChoice.toChooseFuction(empno);

		} else if (userInput.equalsIgnoreCase("N")) {

			System.out.println("Thank You Have a Nice Day -_-");
		}

		try {
			if (em != null) {

				em.close();
			}
			if (emf != null) {

				emf.close();
			}

		} catch (Exception e) {

			System.out.println("system Error Try After sometime");
		}
	}

}
