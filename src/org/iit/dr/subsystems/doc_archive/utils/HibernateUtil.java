package org.iit.dr.subsystems.doc_archive.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: Vadja
 * Date: 13.05.14
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    private static ServiceRegistry serviceRegistry;

    static {
        try {
            sessionFactory = new Configuration().configure("docArchiveHibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
