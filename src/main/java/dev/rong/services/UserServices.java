package dev.rong.services;

import java.util.List;

import entities.BankAccount;
import entities.User;

public interface UserServices {
	User enrollUser(String username, String password);
	User loginAsUser(String username, String password);
}
