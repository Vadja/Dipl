package org.iit.dr.subsystems.doc_archive.services;

import org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl.RoleDAOImpl;
import org.iit.dr.subsystems.doc_archive.database.DAO.RoleDAO;
import org.iit.dr.subsystems.doc_archive.entities.Role;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 14.05.14
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
public class RoleService {

    private RoleDAO roleDAO;

    {
        roleDAO = new RoleDAOImpl();
    }

    public Role searchRoleById(Long id) throws Exception {
        return roleDAO.getRoleById(id);
    }

    public Vector<Role> searchAllRoles() throws Exception {
        return new Vector<Role>(roleDAO.getAllRoles());
    }
}
