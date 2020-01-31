package mypackage.dao;

import java.util.List;

import mypackage.entity.User;

public interface UserDAO {

	public List<User> getUsers();

	public void saveUser(User user);

	public User getUser(String username);

	public void deleteUser(String username);

}
