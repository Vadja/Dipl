package org.iit.dr.subsystems.publication_subsyst.database.DAO;

import org.iit.dr.subsystems.publication_subsyst.entities.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 14.05.14
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO {

    public void addUser(User user) throws Exception;
    public void updateUser(User user) throws Exception;
    public User getUserById(Long id) throws Exception;
    public List getAllUsers() throws Exception;
    public void deleteUser(User user) throws Exception;

}
