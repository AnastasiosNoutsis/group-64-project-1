package mypackage.dao;

import java.util.List;

import mypackage.entity.Role;

public interface RoleDAO {

	public List<Role> getRoles();

	public void saveRole(Role role);

	public Role getRole(String authority);

	public void deleteRole(String authority);
}
