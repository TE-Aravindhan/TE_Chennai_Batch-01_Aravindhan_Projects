package com.te.consolebasedapplication.utility;

import java.sql.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.consolebasedapplication.bean.EmpInfo;

public class RegisterUser {

	private RegisterUser() {
	}

	static EntityManagerFactory emf = null;
	static EntityManager em = null;
	static EntityTransaction transaction = null;
	static Scanner scanner = new Scanner(System.in);

	public static void register() {

		try {

			LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
			emf = Persistence.createEntityManagerFactory("emp");
			em = emf.createEntityManager();

			transaction = em.getTransaction();
			transaction.begin();

			EmpInfo empInfo = new EmpInfo();

			System.out.println("Enter the your Name                         : ");
			empInfo.setEname(scanner.next().toUpperCase());

			System.out.println("Enter the your Date of joining (YYYY-MM-DD) : ");
			empInfo.setHiredate(Date.valueOf(scanner.next()));

			System.out.println("Enter the your Salary                       : ");
			empInfo.setSalary(Double.parseDouble(scanner.next()));

			System.out.println("Enter the your Designation                  : ");
			empInfo.setRole(scanner.next().toUpperCase());

			System.out.println("Enter the your Dept No                      : ");
			empInfo.setDeptno(Integer.parseInt(scanner.next()));

			System.out.println("Enter the your Password                     : ");
			empInfo.setPassword(scanner.next());

			EmpNoAutoGenerate.empNoGenerator();
			empInfo.setEmpno(EmpNoAutoGenerate.empno);

			em.persist(empInfo);

			transaction.commit();

			System.out.println("-------------------------------------------");
			System.out.println("Your details entered successfully -_-");
			System.out.println("Your Employee Id is : " + empInfo.getEmpno());
			System.out.println("-------------------------------------------");

			LoginPage.choicesOfUser();

		} catch (Exception e) {

			transaction.rollback();

			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Oops !!! Some of the entered details was wrong please re-enter your details");
			System.out.println("---------------------------------------------------------------------------");

			register();

		} finally {

			try {
				if (em != null) {

					em.close();
				}
				if (em != null) {

					emf.close();
				}

			} catch (Exception e) {

				System.out.println("system Error Try After sometime");
			}
		}
	}
}
