package app.service_impl;


import app.dao.RoleDao;
import app.model.Role;
import app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void addRole(Set<Role> roles) {
        try {
            for (Role role : roles)
            roleDao.getRoleByRoleName(role.getRoleName());
        } catch (NoResultException e) {
            roles.forEach(roleDao::saveEntity);
        }
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        Role role = new Role();
        try {
            role = roleDao.getRoleByRoleName(roleName);
        } catch (NoResultException e) {
            e.getStackTrace();
        }
        return role;
    }

    @Override
    public Set<Role> getSetOfRoles(Set<Role> roles) {
        Set<Role> result = new HashSet<>();
        for (Role roleName : roles) {
            try {
                result.add(roleDao.getRoleByRoleName(roleName.getRoleName()));
            } catch (NoResultException e) {
                e.getStackTrace();
            }
        }
        return result;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getByKey(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAll();
    }

    @Override
    public void updateRoles(Role role) {
        roleDao.update(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleDao.deleteByKey(id);
    }

    @Override
    public Set<Role> getSetOfRoles(String roles) {
        String[] rolesList = roles.split(",\\s");
        Set<Role> setRole = new HashSet<>();
        for (String s : rolesList) {
            setRole.add(getRoleByRoleName(s));
        }
        return setRole;
    }
}
