package org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.UserDAO;
import org.iit.dr.subsystems.publication_subsyst.entities.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
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
    public List getAllUsers() throws Exception {
        return (List)super.getAllRecords(User.class);
    }

    @Override
    public void deleteUser(User user) throws Exception {
        super.deleteRecord(user);
    }
}
