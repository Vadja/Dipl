package org.iit.dr.subsystems.doc_archive.database.DAO;

import org.iit.dr.subsystems.doc_archive.entities.DocType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vadja
 * Date: 14.05.14
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public interface DocTypeDAO {

    public void addDocType(DocType type) throws Exception;
    public void updateDocType(DocType type) throws Exception;
    public DocType getDocTypeById(Long id) throws Exception;
    public List getAllDocTypes() throws Exception;
    public void deleteDocType(DocType type) throws Exception;
}
