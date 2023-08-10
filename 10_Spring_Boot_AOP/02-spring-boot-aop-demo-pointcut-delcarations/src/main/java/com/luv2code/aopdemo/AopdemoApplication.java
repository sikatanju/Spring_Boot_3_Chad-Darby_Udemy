package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO,
											   TrafficService trafficService)	{
		return runner->	{
			// demoTheBeforeAdvice(accountDAO, membershipDAO);
			// demoTheAfterReturningAdvice(accountDAO);
			// demoTheAfterThrowingAdvice(accountDAO);
			// demoTheAfterAdvice(accountDAO);
			// demoTheAroundAdvice(trafficService);
			// demoTheAroundAdviceHandleException(trafficService);

			demoTheAroundAdviceRethrowException(trafficService);
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficService trafficService) {
		System.out.println("\nMain Program : demoTheAroundAdviceRethrowException ");

		System.out.println("Calling getUpdate() with tripWire");

		boolean tripWire = true;

		String data = trafficService.getUpdate(tripWire);;

		System.out.println("----> Main Program, Traffic Update is : " + data);
	}

	private void demoTheAroundAdviceHandleException(TrafficService trafficService) {
		System.out.println("\nMain Program : demoTheAroundAdviceHandleException ");

		System.out.println("Calling getUpdate() with tripWire");

		boolean tripWire = true;

		String data = trafficService.getUpdate(tripWire);

		System.out.println("----> Main Program, Traffic Update is : " + data);
	}

	private void demoTheAroundAdvice(TrafficService trafficService) {
		System.out.println("\nMain Program : demoTheAroundAdvice");

		System.out.println("Calling getUpdate()");

		String data = trafficService.getUpdate();

		System.out.println("----> Main Program, Traffic Update is : " + data);
	}

	private void demoTheAfterAdvice(AccountDAO accountDAO) {
		List<Account> accountList = null;

		try	{
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;
			accountList = accountDAO.findAccounts(tripWire);
		}
		catch (Exception exc)	{
			System.out.println("\n\nMain Program : Caught Exception : " + exc);
		}

		// Display the accounts
		System.out.println("\n\nMain Program : demoTheAfterThrowingAdvice : ---> ");

		System.out.println(accountList);

		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
		List<Account> accountList = null;

		try	{
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accountList = accountDAO.findAccounts(tripWire);
		}
		catch (Exception exc)	{
			System.out.println("\n\nMain Program : Caught Exception : " + exc);
		}

		// Display the accounts
		System.out.println("\n\nMain Program : demoTheAfterThrowingAdvice : ---> ");

		 System.out.println(accountList);

		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {
		List<Account> accountList = accountDAO.findAccounts();

		// Display the accounts
		System.out.println("\n\nMain Program : demoTheAfterReturningAdvice : --- ");
		for (var temp : accountList)
			System.out.println(temp);
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		Account account = new Account();
		account.setName("Sika");
		account.setLevel("5");
		accountDAO.addActualAccount(account, true);

		// accountDAO.addAccount();
		membershipDAO.addAccount();
		accountDAO.setName("Tanjim");
		accountDAO.getName();
		// accountDAO.addActualAccount(new Account(), true);
		// accountDAO.addCheck(1, "Saad", true);
	}
}
