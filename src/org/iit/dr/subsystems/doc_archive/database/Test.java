package org.iit.dr.subsystems.doc_archive.database;

import org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl.RoleDAOImpl;
import org.iit.dr.subsystems.doc_archive.entities.Role;

import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: Vadja
* Date: 13.05.14
* Time: 14:18
* To change this template use File | Settings | File Templates.
*/
public class Test {

    public static void main(String[] args) throws Exception {
        RoleDAOImpl rd = new RoleDAOImpl();
        List<Role> l = rd.getAllRoles();

        for(Role r : l) {
            System.out.println(r.getName() + " " + r.getId());
        }

    }
}
