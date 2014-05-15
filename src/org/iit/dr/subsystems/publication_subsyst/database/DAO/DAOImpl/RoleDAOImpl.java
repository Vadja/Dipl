package org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.RoleDAO;
import org.iit.dr.subsystems.publication_subsyst.entities.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 13.05.14
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public class RoleDAOImpl extends AbstractDAO implements RoleDAO{

    @Override
    public void addRole(Role role) throws Exception {
        super.addRecord(role);
    }

    @Override
    public void updateRole(Role role) throws Exception {
        super.updateRecord(role);
    }

    @Override
    public Role getRoleById(Long id) throws Exception {
        return (Role)super.searchById(id, Role.class);
    }

    @Override
    public List<Role> getAllRoles() throws Exception {
        return (List)super.getAllRecords(Role.class);
    }

    @Override
    public void deleteRole(Role role) throws Exception {
        super.deleteRecord(role);
    }
}
