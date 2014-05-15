package org.iit.dr.subsystems.publication_subsyst.database;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl.RoleDAOImpl;
import org.iit.dr.subsystems.publication_subsyst.entities.Role;

import java.sql.SQLException;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: Piligrim
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
