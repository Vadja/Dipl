package org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.DocTypeDAO;
import org.iit.dr.subsystems.publication_subsyst.entities.DocType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 14.05.14
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class DocTypeDAOImpl extends AbstractDAO implements DocTypeDAO {

    @Override
    public void addDocType(DocType type) throws Exception {
        super.addRecord(type);
    }

    @Override
    public void updateDocType(DocType type) throws Exception {
        super.updateRecord(type);
    }

    @Override
    public DocType getDocTypeById(Long id) throws Exception {
        return (DocType)super.searchById(id, DocType.class);
    }

    @Override
    public List getAllDocTypes() throws Exception {
        return (List)super.getAllRecords(DocType.class);
    }

    @Override
    public void deleteDocType(DocType type) throws Exception {
        super.deleteRecord(type);
    }
}
