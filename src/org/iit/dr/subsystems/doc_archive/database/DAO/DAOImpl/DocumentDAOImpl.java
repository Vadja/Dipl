package org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl;

import org.iit.dr.subsystems.doc_archive.database.DAO.DocumentDAO;
import org.iit.dr.subsystems.doc_archive.entities.Document;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vadja
 * Date: 13.05.14
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
public class DocumentDAOImpl extends AbstractDAO implements DocumentDAO {

    @Override
    public void addDocument(Document document) throws Exception {
        super.addRecord(document);
    }

    @Override
    public void updateDocument(Document document) throws Exception {
        super.updateRecord(document);
    }

    @Override
    public Document getDocumentById(Long id) throws Exception {
        return (Document)super.searchById(id, Document.class);
    }

    @Override
    public List getAllDocuments() throws Exception {
        return (List)super.getAllRecords(Document.class);
    }

    @Override
    public void deleteDocument(Document document) throws Exception {
        super.deleteRecord(document);
    }
}
