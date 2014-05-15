package org.iit.dr.subsystems.doc_archive.database.DAO;

import org.iit.dr.subsystems.doc_archive.entities.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vadja
 * Date: 14.05.14
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public interface RoleDAO {

    public void addRole(Role role) throws Exception;
    public void updateRole(Role role) throws Exception;
    public Role getRoleById(Long id) throws Exception;
    public List getAllRoles() throws Exception;
    public void deleteRole(Role role) throws Exception;
}
