package com.te.consolebasedapplication.utility;

import java.util.logging.Level;
import java.util.logging.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmpNoAutoGenerate {

	private EmpNoAutoGenerate() {
	}

	static Integer empno;
	static EntityManagerFactory emf = null;
	static EntityManager em = null;

	public static void empNoGenerator() {

		try {
			LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
			emf = Persistence.createEntityManagerFactory("emp");
			em = emf.createEntityManager();

			String fetch = "select max(empno) from EmpInfo";

			Query query = em.createQuery(fetch);

			Integer maxEmpno = (Integer) query.getSingleResult();

			if (maxEmpno == null) {
				empno = 1000;
			} else {
				empno = maxEmpno;
			}

			while (empno <= maxEmpno) {
				empno++;

			}

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
