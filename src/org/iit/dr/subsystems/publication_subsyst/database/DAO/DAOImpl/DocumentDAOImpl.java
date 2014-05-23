package org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.DocumentDAO;
import org.iit.dr.subsystems.publication_subsyst.entities.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
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
    public List<Document> getDocumentsByDateCreation(Date dateCreation) throws Exception {
        List<Document> result = new ArrayList<Document>();
        dateCreation.setDate(1);
        dateCreation.setMonth(0);
        Date date = (Date) dateCreation.clone();
        date.setYear(dateCreation.getYear()+1);
        System.out.println(date);
        try {
            openSession();
            result = (List) getSession().createQuery("from Document where createDate between (?) and (?)")
                    .setDate(0, dateCreation).setDate(1, date).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            closeSession();
        }
        return result;
    }

    @Override
    public Document getDocumentsByFile(File file) throws Exception {
        Document result = null;
        try {
            openSession();
            List<Document> list = (List) getSession().createQuery("from Document where (title = ?) and (loadDate = ?)")
                    .setString(0, file.getName()).setDate(1, new Date(file.lastModified())).list();
            for(Document d : list) {
                System.out.println(d.getFile().getPath() + "^(");
                System.out.println(file.getPath() + "(");
                if((d.getFile().getPath()).equals(file.getPath())) {
                    result = d;
                    System.out.println(result.getFile().getPath());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            closeSession();
        }
        return result;
    }

    @Override
    public List<Document> getDocumentsByName(String title) throws Exception {
        List<Document> result = new ArrayList<Document>();
        return result;
    }

    @Override
    public List<Document> getDocumentsByUserFIO(String fIO) throws Exception {
        List<Document> result = new ArrayList<Document>();
        return result;
    }

    @Override
    public void deleteDocument(Document document) throws Exception {
        super.deleteRecord(document);
    }
}
