package org.iit.dr.subsystems.doc_archive.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.File;
import java.util.Date;

/**
* Created with IntelliJ IDEA.
* User: Piligrim
* Date: 13.05.14
* Time: 18:04
* To change this template use File | Settings | File Templates.
*/

@Entity
@Table(name = "DOCUMENT")
public class Document {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "NAME")
    private String title;

    @Column(name = "LOAD_DATE")
    private Date loadDate;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "DOC_DESCRIPTION")
    private  String description;

    @Column(name = "FILE")
    private File file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TYPE_ID")
    private DocType documentType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public DocType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocType documentType) {
        this.documentType = documentType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
