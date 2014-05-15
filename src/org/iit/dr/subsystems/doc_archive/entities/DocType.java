package org.iit.dr.subsystems.doc_archive.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Vadja
 * Date: 13.05.14
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "DOC_TYPE")
public class DocType {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "NAME")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentType")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private Set<Document> documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
