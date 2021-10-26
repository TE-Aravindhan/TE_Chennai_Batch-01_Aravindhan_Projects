package com.te.consolebasedapplication.utility;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.consolebasedapplication.bean.EmpInfo;

public class LoginCheck {

	private LoginCheck() {
	}

	static Scanner scanner = new Scanner(System.in);

	static EntityManagerFactory emf = null;
	static EntityManager em = null;

	public static void toCheckLogindetails(EmpInfo emp) {

		LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);

		emf = Persistence.createEntityManagerFactory("emp");
		em = emf.createEntityManager();

		System.out.println("Enter your Employee Number     : ");

		emp.setEmpno(Integer.parseInt(scanner.next()));
		String fetch = "select empno,password from EmpInfo where empno = :user";

		Query query = em.createQuery(fetch);
		query.setParameter("user", emp.getEmpno());

		List<Object[]> list = query.getResultList();

		if (!list.isEmpty()) {

			for (Object[] info : list) {

				System.out.println("Enter your Password    : ");

				emp.setPassword(scanner.next());

				if (info[1].equals(emp.getPassword())) {

					System.out.println("----------------------");
					System.out.println("Login Successfully -_-");
					System.out.println("----------------------");

					UserChoice.toChooseFuction(emp.getEmpno());
				} else {
					System.out.println("----------------------------");
					System.out.println("XXX---Wrong Credential---XXX");
					System.out.println("----------------------------");

					LoginPage.choicesOfUser();
				}
			}

		} else {
			System.out.println("----------------------------------------------");
			System.out.println("Employee Id does not exist Please Register....");
			System.out.println("----------------------------------------------");

			RegisterUser.register();

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
