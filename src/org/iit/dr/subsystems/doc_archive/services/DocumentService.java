package org.iit.dr.subsystems.doc_archive.services;

import org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl.DocumentDAOImpl;
import org.iit.dr.subsystems.doc_archive.database.DAO.DocumentDAO;
import org.iit.dr.subsystems.doc_archive.entities.Document;
import org.iit.dr.subsystems.doc_archive.entities.Role;
import org.iit.dr.subsystems.doc_archive.entities.User;

import java.io.File;
import java.util.Date;
import java.util.List;

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

//    public void addDocument(File file, String lastName, String firstName, String midleName, Role role,
//                            String description) throws Exception {
//        addDocument(file, lastName, firstName, midleName, role, description, new Date(file.lastModified()));
//    }

    public void addDocument(File file, String lastName, String firstName, String midleName, Role role,
                            String description, Date date) throws Exception {
        Document document = new Document();
        User user = new UserService().searchUser(lastName, firstName, midleName, role);
        if(user == null) {
            document.setUser(new UserService().addUser(lastName, firstName, midleName, role));
        } else {
            document.setUser(user);
        }
        document.setCreateDate(date);
        document.setLoadDate(new Date());
        document.setDescription(description);
        document.setFile(file);
        document.setTitle(file.getName());
        document.setDocumentType(new DocTypeService().searchDocTypeById(1L));
        documentDAO.addDocument(document);
    }

    public List<Document> getAllDocuments() throws Exception {
        return documentDAO.getAllDocuments();
    }

    public Document getDocumentByFile(File file) throws Exception {
        return documentDAO.getDocumentsByFile(file);
    }

    public List<Document> getDocumentsByDateCreation(Date dateCreation) throws Exception {
        return documentDAO.getDocumentsByDateCreation(dateCreation);
    }

    public List<Document> getDocumentsByName(String title) throws Exception {
        return documentDAO.getDocumentsByName(title);
    }

    public List<Document> getDocumentsByUserFIO(String fIO) throws Exception {
        return documentDAO.getDocumentsByUserFIO(fIO);
    }

    public void updateDocument(Document document) throws Exception {
        documentDAO.updateDocument(document);
    }

    public void deleteDocument(Document document) throws Exception {
        documentDAO.deleteDocument(document);
    }
}
