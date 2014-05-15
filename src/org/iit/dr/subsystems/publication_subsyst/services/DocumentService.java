package org.iit.dr.subsystems.publication_subsyst.services;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl.DocumentDAOImpl;
import org.iit.dr.subsystems.publication_subsyst.database.DAO.DocumentDAO;
import org.iit.dr.subsystems.publication_subsyst.entities.Document;

import java.io.File;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 14.05.14
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
public class DocumentService {

    private DocumentDAO documentDAO;

    {
        documentDAO = new DocumentDAOImpl();
    }

    public void addDocument(File file, String lastName, String firstName, String midleName) throws Exception {
        Document document = new Document();
        Date date = new Date();
        document.setUser(new UserService().addUser(lastName, firstName, midleName));
//        document.setCreateDate(file.get/);
        document.setCreateDate(new Date(file.lastModified()));
        document.setLoadDate(date);
        document.setDescription("description");
        document.setFile(file);
        document.setTitle(file.getName());
        document.setDocumentType(new DocTypeService().searchDocTypeById(1L));
        documentDAO.addDocument(document);
    }
}
