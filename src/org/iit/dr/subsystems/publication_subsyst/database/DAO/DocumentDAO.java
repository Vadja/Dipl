package org.iit.dr.subsystems.publication_subsyst.database.DAO;

import org.iit.dr.subsystems.publication_subsyst.entities.Document;

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
    public void deleteDocument(Document document) throws Exception;

}
