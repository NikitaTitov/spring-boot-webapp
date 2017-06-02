package app.dao_impl;


import app.dao.RoleDao;
import app.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RoleDaoImpl extends AbstractDao<Long, Role> implements RoleDao {

    @Override
    public Role getRoleByRoleName(String roleName) {
        Role role = (Role) entityManager.createQuery("SELECT u from Role u where u.roleName =:roleName").setParameter("roleName", roleName).getSingleResult();
        return role;
    }
}
