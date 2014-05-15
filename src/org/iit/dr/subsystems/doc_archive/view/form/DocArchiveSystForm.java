package org.iit.dr.subsystems.doc_archive.view.form;

import org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl.DocumentDAOImpl;
import org.iit.dr.subsystems.doc_archive.database.DAO.DocumentDAO;
import org.iit.dr.subsystems.doc_archive.entities.Document;
import org.iit.dr.view.MainWindow;
import org.iit.dr.view.action.OpenFrameAction;
import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Мфвоф
 * Date: 13.05.14
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class DocArchiveSystForm extends JInternalFrameExt<Object> {

    private JPanel mainPanel;

    private DefaultTableModel docArchiveTableModel;
    private JTable docArchiveTable;
    private JScrollPane docArchiveScrollPane;

    private JPanel buttonPanel;
    private JButton uploadButton;

    @Override
    public boolean showFrame(Object parent, Object o) {
        this.setTitle("Подсистема архивации документации");
        this.setVisible(true);
        return true;
    }

    @Override
    protected void init() {
        this.setMinimumSize(new Dimension(800, 600));
    }

    @Override
    protected void generateComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        docArchiveTableModel = new DefaultTableModel();
        initPublicationsTableModel();
        docArchiveTable = new JTable(docArchiveTableModel);
        docArchiveScrollPane = new JScrollPane(docArchiveTable);
        add(docArchiveScrollPane, BorderLayout.CENTER);
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
                addDataToTableModel();
//                publicationsTable.updateUI();
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    private void buttonInit() {
        buttonPanel = new JPanel(new GridLayout(7, 1));
        uploadButton = new JButton(new OpenFrameAction("Загрузить документацию", new MainWindow(), DocArchiveUploaderForm.class));
        buttonPanel.add(uploadButton);
    }

    private void initPublicationsTableModel() {
        docArchiveTableModel.addColumn("Название");
        docArchiveTableModel.addColumn("Дата загрузки");
        docArchiveTableModel.addColumn("Дата создания");
        docArchiveTableModel.addColumn("ФИО автора");
        docArchiveTableModel.addColumn("Описание");
//        addDataToTableModel();
    }

    private void addDataToTableModel() {
        for(int i = docArchiveTableModel.getRowCount(); i > 0; i--){
            docArchiveTableModel.removeRow(i-1);
        }
        DocumentDAO documentDAO = new DocumentDAOImpl();
        try {
            java.util.List<Document> l = documentDAO.getAllDocuments();
            for(Document document : l) {
                Vector data = new Vector();
                data.add(document.getTitle());
                data.add(document.getLoadDate());
                data.add(document.getCreateDate());
                data.add(document.getDescription());
                docArchiveTableModel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}