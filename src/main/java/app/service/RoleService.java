package app.service;


import app.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void addRole(Set<Role> role);

    Role getRoleByRoleName(String roleName);

    Set<Role> getSetOfRoles(Set<Role> roles);

    Role getRoleById(Long id);

    List<Role> getAllRoles();

    void updateRoles(Role role);

    void deleteRoleById(Long id);

    Set<Role> getSetOfRoles(String role);
}
