package app.dao;


import app.model.Role;

public interface RoleDao extends GenericDao<Long, Role> {
    Role getRoleByRoleName(String roleName);
}
