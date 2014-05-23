package org.iit.dr.subsystems.doc_archive.database.DAO;

import org.iit.dr.subsystems.doc_archive.entities.Document;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 13.05.14
 * Time: 18:35
 * To change this template use File | Settings | File Templates.
 */
public interface DocumentDAO {

    public void addDocument(Document document) throws Exception;
    public void updateDocument(Document document) throws Exception;
    public Document getDocumentById(Long id) throws Exception;
    public List getAllDocuments() throws Exception;
    public List<Document> getDocumentsByDateCreation(Date dateCreation) throws Exception;
    public List<Document> getDocumentsByName(String title) throws Exception;
    public Document getDocumentsByFile(File file) throws Exception;
    public List<Document> getDocumentsByUserFIO(String fIO) throws Exception;
    public void deleteDocument(Document document) throws Exception;

}
