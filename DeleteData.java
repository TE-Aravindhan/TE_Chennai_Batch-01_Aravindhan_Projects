package com.te.consolebasedapplication.utility;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.consolebasedapplication.bean.EmpInfo;

public class DeleteData {

	private DeleteData() {
	}

	static EntityManagerFactory emf = null;
	static EntityManager em = null;
	static EntityTransaction transaction = null;
	static Scanner scanner = new Scanner(System.in);

	public static void ensureDelete(Integer empno) {

		LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
		System.out.println("Are you sure Y/N");

		String condition = scanner.nextLine();

		if (condition.equalsIgnoreCase("Y")) {

			DeleteData.toDelete(empno);

		} else if (condition.equalsIgnoreCase("N")) {

			UserChoice.toChooseFuction(empno);
		}
	}

	public static void toDelete(Integer empno) {

		try {
			emf = Persistence.createEntityManagerFactory("emp");
			em = emf.createEntityManager();
			transaction = em.getTransaction();

			transaction.begin();

			EmpInfo info = em.getReference(EmpInfo.class, empno);

			em.remove(info);

			transaction.commit();

			System.out.println("Your record was deleted successfully...");

			LoginPage.choicesOfUser();

		} catch (Exception e) {

			transaction.rollback();

		} finally {
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

}
