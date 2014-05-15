package org.iit.dr.subsystems.publication_subsyst.database.DAO;

import org.iit.dr.subsystems.publication_subsyst.entities.DocType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
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
