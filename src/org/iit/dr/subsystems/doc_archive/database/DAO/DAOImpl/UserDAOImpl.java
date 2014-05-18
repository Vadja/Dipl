package org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl;

import org.iit.dr.subsystems.doc_archive.database.DAO.UserDAO;
import org.iit.dr.subsystems.doc_archive.entities.Role;
import org.iit.dr.subsystems.doc_archive.entities.User;

import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: Vadja
* Date: 14.05.14
* Time: 11:59
* To change this template use File | Settings | File Templates.
*/
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Override
    public void addUser(User user) throws Exception {
        super.addRecord(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        super.updateRecord(user);
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return (User)super.searchById(id, User.class);
    }

    @Override
    public User getUserByFIO(String lastName, String firstName, String midleName, Role role) throws Exception {
        User user = null;
        try {
            openSession();
            user = (User) getSession().createQuery("from User where (lastName like  ?) and (firstName like ?) " +
                    "and (midleName like  ?)")
                    .setString(0, "%" + lastName + "%").setString(1, "%" + firstName + "%")
                    .setString(2, "%" + midleName + "%").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            closeSession();
        }
        return user;
    }

    @Override
    public List getAllUsers() throws Exception {
        return (List)super.getAllRecords(User.class);
    }

    @Override
    public void deleteUser(User user) throws Exception {
        super.deleteRecord(user);
    }
}
