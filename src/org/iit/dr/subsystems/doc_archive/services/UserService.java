package org.iit.dr.subsystems.doc_archive.services;

import org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl.UserDAOImpl;
import org.iit.dr.subsystems.doc_archive.database.DAO.UserDAO;
import org.iit.dr.subsystems.doc_archive.entities.Role;
import org.iit.dr.subsystems.doc_archive.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: Vadja
 * Date: 14.05.14
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public class UserService {

    private UserDAO userDAO;

    {
        userDAO = new UserDAOImpl();
    }

    public User addUser(String lastName, String firstName, String midleName, Role role) throws Exception {
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setMidleName(midleName);
        user.setRole(role);
        userDAO.addUser(user);
        return user;
    }

    public User searchUser(String lastName, String firstName, String midleName, Role role) throws Exception {
//        User user = new User();
        return userDAO.getUserByFIO(lastName, firstName, midleName, role);
    }
}
