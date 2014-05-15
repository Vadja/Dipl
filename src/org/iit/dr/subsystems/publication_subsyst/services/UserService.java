package org.iit.dr.subsystems.publication_subsyst.services;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl.UserDAOImpl;
import org.iit.dr.subsystems.publication_subsyst.database.DAO.UserDAO;
import org.iit.dr.subsystems.publication_subsyst.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 14.05.14
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public class UserService {

    private UserDAO userDAO;

    {
        userDAO = new UserDAOImpl();
    }

    public User addUser(String lastName, String firstName, String midleName) throws Exception {
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setMidleName(midleName);
        user.setRole(new RoleService().searchRoleById(1L));
        userDAO.addUser(user);
        return user;
    }
}
