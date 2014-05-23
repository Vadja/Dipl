package org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl;

import org.hibernate.Session;
import org.iit.dr.subsystems.doc_archive.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 14.05.14
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDAO {

    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void closeSession() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    protected void addRecord(Object obj) throws Exception {
        try {
            openSession();
            getSession().beginTransaction();
            getSession().save(obj);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            closeSession();
        }
    }

    protected void updateRecord(Object obj) throws Exception {
        try {
            openSession();
            getSession().beginTransaction();
            getSession().update(obj);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            closeSession();
        }
    }

    protected Object searchById(Long id, Class c) throws Exception {
        Object obj = null;
        try {
            openSession();
            obj = getSession().get(c, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            closeSession();
        }
        return obj;
    }

    protected List<Object> getAllRecords(Class c) throws Exception {
        List<Object> list = new ArrayList<Object>();
        try {
            openSession();
            list = getSession().createCriteria(c).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            closeSession();
        }
        return list;
    }

    protected void deleteRecord(Object obj) throws Exception {
        try {
            openSession();
            getSession().beginTransaction();
            getSession().delete(obj);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            closeSession();
        }
    }

}
