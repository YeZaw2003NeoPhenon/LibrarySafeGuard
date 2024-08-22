package com.example.empirical_libary_management_system.user;

import java.util.List;

public interface UserService {
	public abstract User add(User user);
	public abstract List<UserRecord> getAllUsers();
	public abstract void delete(String email);
	public abstract User getUser(String email);
	public abstract User update(User user , Long id);
}
