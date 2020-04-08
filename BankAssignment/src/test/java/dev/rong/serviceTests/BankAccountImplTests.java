package dev.rong.serviceTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.rong.services.BankAccountServices;
import dev.rong.services.BankAccountServicesImpl;
import dev.rong.services.UserServices;
import dev.rong.services.UserServicesImpl;
import entities.BankAccount;
import entities.User;

public class BankAccountImplTests {

	public static BankAccountServices acctServices = new BankAccountServicesImpl();
	public static UserServices userServices = new UserServicesImpl();
	@Test
	public void accountCreate() {
		User user = userServices.loginAsUser("Dork", "1234");
		BankAccount acct = acctServices.makeBankAccount(user, "awesome", 1000);
		System.out.println(acct);
	}

}
