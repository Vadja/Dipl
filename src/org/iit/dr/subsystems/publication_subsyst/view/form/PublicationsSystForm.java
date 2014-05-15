package org.iit.dr.subsystems.publication_subsyst.view.form;

import org.iit.dr.subsystems.publication_subsyst.database.DAO.DAOImpl.DocumentDAOImpl;
import org.iit.dr.subsystems.publication_subsyst.database.DAO.DocumentDAO;
import org.iit.dr.subsystems.publication_subsyst.entities.Document;
import org.iit.dr.view.MainWindow;
import org.iit.dr.view.action.OpenFrameAction;
import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 13.05.14
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class PublicationsSystForm extends JInternalFrameExt<Object> {

    private JPanel mainPanel;

    private JTable publicationsTable;
    private JScrollPane publicationsScrollPane;

    private JPanel buttonPanel;
    private JButton uploadButton;

    @Override
    public boolean showFrame(Object parent, Object o) {
        this.setTitle("Подсистема для публикаций");
        this.setVisible(true);
        return true;
    }

    @Override
    protected void init() {
        this.setMinimumSize(new Dimension(1200, 800));
    }

    @Override
    protected void generateComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        publicationsTableView();
        add(publicationsScrollPane, BorderLayout.CENTER);
        buttonInit();
        add(buttonPanel, BorderLayout.EAST);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
            }
            @Override
            public void windowClosed(WindowEvent e) {
            }
            @Override
            public void windowIconified(WindowEvent e) {
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            @Override
            public void windowActivated(WindowEvent e) {
                publicationsTableView();
                publicationsTable.updateUI();
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    private void buttonInit() {
        buttonPanel = new JPanel(new GridLayout(7, 1));
        uploadButton = new JButton(new OpenFrameAction("Добавить публикацию", new MainWindow(), PublicationsUploaderForm.class));
        buttonPanel.add(uploadButton);
    }

    private void publicationsTableView() {
        Vector column_names = new Vector();
        // формируем список названий полей (колонок)
        column_names.add("Название");
        column_names.add("Дата загрузки");
        column_names.add("Дата создания");
        column_names.add("ФИО автора");
        column_names.add("Описание");

        Vector vector = new Vector();

        DocumentDAO documentDAO = new DocumentDAOImpl();
        try {
            java.util.List<Document> l = documentDAO.getAllDocuments();
            for(Document document : l) {
                Vector data = new Vector();
                data.add(document.getTitle());
                data.add(document.getLoadDate());
                data.add(document.getCreateDate());
                data.add(document.getDescription());
                vector.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        publicationsTable = new JTable(vector, column_names);
        publicationsScrollPane = new JScrollPane(publicationsTable);
    }
}
