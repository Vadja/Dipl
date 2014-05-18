package org.iit.dr.subsystems.doc_archive.view.form;

import org.iit.dr.subsystems.doc_archive.database.DAO.DAOImpl.DocumentDAOImpl;
import org.iit.dr.subsystems.doc_archive.database.DAO.DocumentDAO;
import org.iit.dr.subsystems.doc_archive.entities.Document;
import org.iit.dr.subsystems.doc_archive.services.DocumentService;
import org.iit.dr.view.MainWindow;
import org.iit.dr.view.action.OpenFrameAction;
import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
* Created with IntelliJ IDEA.
* User: Vadja
* Date: 13.05.14
* Time: 10:58
* To change this template use File | Settings | File Templates.
*/
public class DocArchiveSystForm extends JInternalFrameExt<Object> {

    private JPanel mainPanel;

    private DefaultTableModel publicationsTableModel;
    private JTable publicationsTable;
    private JScrollPane publicationsScrollPane;

    private Vector documentsInformation;
    private JPanel buttonPanel;
    private JButton uploadButton;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton openButton;

    @Override
    public boolean showFrame(Object parent, Object o) {
        this.setTitle("Подсистема архивации документации");
        this.setVisible(true);
        return true;
    }

    @Override
    protected void init() {
        documentsInformation = new Vector();
        this.setMinimumSize(new Dimension(1000, 500));
    }

    @Override
    protected void generateComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        publicationsTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable( int rowIndex, int columnIndex )
            {
                switch( columnIndex )
                {
                    default:
                        return false;
                }
            }
        };
        initPublicationsTableModel();
        publicationsTable = new JTable(publicationsTableModel);
        publicationsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        publicationsScrollPane = new JScrollPane(publicationsTable);
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
                addDataToTableModel();
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    private void buttonInit() {
        buttonPanel = new JPanel(new GridLayout(7, 1));
        uploadButton = new JButton(new OpenFrameAction("Добавить документ", new MainWindow(), DocArchiveUploaderForm.class));
        updateButton = new JButton("Обновить данные");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = (Document)((Vector)documentsInformation.get(publicationsTable.getSelectedRow())).lastElement();
                DocArchiveUploaderForm frame = new DocArchiveUploaderForm(document);
                frame.setModal(true);
                frame.showFrame(e.getSource(), e);
            }
        });
        openButton = new JButton("Открыть документ");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = (Document)((Vector)documentsInformation.get(publicationsTable.getSelectedRow())).lastElement();
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(document.getFile());
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
        saveButton = new JButton("Сохранить");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = (Document)((Vector)documentsInformation.get(publicationsTable.getSelectedRow())).lastElement();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("myFile.txt"));
                int ret = fileChooser.showSaveDialog(null);
                if(ret == fileChooser.APPROVE_OPTION){
                    File publication = new File(fileChooser.getCurrentDirectory(), fileChooser.getSelectedFile().getName());
                    if(!publication.exists()) {
                        try {
                            InputStream is = null;
                            OutputStream os = null;
                            try {
                                is = new FileInputStream(document.getFile());
                                os = new FileOutputStream(publication);
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = is.read(buffer)) > 0) {
                                    os.write(buffer, 0, length);
                                }
                            } finally {
                                is.close();
                                os.close();
                            }
                            publication.createNewFile();
//                            System.out.println(publication.renameTo(new File(fileChooser.getCurrentDirectory(), "hhhh/kkk")));
//                            publication.renameTo(new File(fileChooser.getCurrentDirectory(), "hhhh/kkk"));
//                            publication.createNewFile();
                            System.out.println(publication.getName());
                            System.out.println(publication.getAbsolutePath());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            }
        });
        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = (Document)((Vector)documentsInformation.get(publicationsTable.getSelectedRow())).lastElement();
                try {
                    new DocumentService().deleteDocument(document);
                    addDataToTableModel();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        buttonPanel.add(uploadButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
    }

    private void initPublicationsTableModel() {
        publicationsTableModel.addColumn("Название");
        publicationsTableModel.addColumn("Дата загрузки");
        publicationsTableModel.addColumn("Дата создания");
        publicationsTableModel.addColumn("ФИО автора");
        publicationsTableModel.addColumn("Описание");
//        addDataToTableModel();
    }

    private void addDataToTableModel() {
        for(int i = publicationsTableModel.getRowCount(); i > 0; i--){
            publicationsTableModel.removeRow(i-1);
        }
        documentsInformation.clear();
        DocumentDAO documentDAO = new DocumentDAOImpl();
        try {
            java.util.List<Document> l = documentDAO.getAllDocuments();
            for(Document document : l) {
                Vector data = new Vector();
                data.add(document.getTitle());
                data.add(document.getLoadDate());
                data.add(document.getCreateDate());
                data.add(document.getUser().getLastName() + " " + document.getUser().getFirstName() + " " +
                        document.getUser().getMidleName() + "(" + document.getUser().getRole().getName() + ")");
                data.add(document.getDescription());
                data.add(document);
                documentsInformation.add(data.clone());
                publicationsTableModel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}