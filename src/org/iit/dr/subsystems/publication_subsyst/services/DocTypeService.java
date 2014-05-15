package org.iit.dr.subsystems.publication_subsyst.services;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl.DocTypeDAOImpl;
import org.iit.dr.subsystems.publication_subsyst.database.DAO.DocTypeDAO;
import org.iit.dr.subsystems.publication_subsyst.entities.DocType;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 14.05.14
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class DocTypeService {

    private DocTypeDAO docTypeDAO;

    {
        docTypeDAO = new DocTypeDAOImpl();
    }

    public DocType searchDocTypeById(Long id) throws Exception {
        return docTypeDAO.getDocTypeById(id);
    }
}
