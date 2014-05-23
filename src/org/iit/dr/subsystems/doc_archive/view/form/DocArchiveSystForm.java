package org.iit.dr.subsystems.doc_archive.view.form;

import org.iit.dr.subsystems.doc_archive.entities.Document;
import org.iit.dr.subsystems.doc_archive.services.DocumentService;
import org.iit.dr.view.MainWindow;
import org.iit.dr.view.action.OpenFrameAction;
import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreeModel;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
* Created with IntelliJ IDEA.
* User: Piligrim
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
    private JButton toArchiveButton;

    private TreeModel treeModel;
    private JTree catalogTree;
    public static final int MAX_LEVELS = 3;

    private Vector catalogsInformation;
    private DefaultTableModel catalogsTableModel;
    private JTable catalogsTable;

    private JTextField searchField;
    private JButton searchButton;

//    public PublicationsSystForm() {
//
//    }

    @Override
    public boolean showFrame(Object parent, Object o) {
        this.setTitle("Подсистема архивации документации выпускающей кафедры");
        this.setVisible(true);
        return true;
    }

    @Override
    protected void init() {
        documentsInformation = new Vector();
        catalogsInformation = new Vector();
        this.setMinimumSize(new Dimension(1000, 500));
    }

    @Override
    protected void generateComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        publicationsTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable( int rowIndex, int columnIndex ) {
                switch( columnIndex ) {
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

        catalogsTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable( int rowIndex, int columnIndex ) {
                switch( columnIndex ) {
                    default:
                        return false;
                }
            }
        };
        initCatalogsTableModel();
        catalogsTable = new JTable(catalogsTableModel);
        catalogsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        catalogsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println(catalogsTable.rowAtPoint(evt.getPoint()));
                addDataToTableModel();
                publicationsTable.updateUI();
            }
        });
//        catalogsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                System.out.println(catalogsTable.getSelectedRow());
//            }
//        });
        JScrollPane scrollPane = new JScrollPane(catalogsTable);
        scrollPane.setPreferredSize(new Dimension(150,this.getHeight()));
        add(scrollPane, BorderLayout.WEST);

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
                addDataToCatalogTableModel();
                catalogsTable.setRowSelectionInterval(0, catalogsTable.getRowCount()-1);
                addDataToTableModel();
//                catalogTree.updateUI();
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
//        initCatalogTree();
//        add(new JScrollPane(catalogTree), BorderLayout.WEST);
//        initSearch();
//
//        add(new )
    }

    private void buttonInit() {
        buttonPanel = new JPanel(new GridLayout(7, 1));
        uploadButton = new JButton(new OpenFrameAction("Добавить документацию", new MainWindow(), DocArchiveUploaderForm.class));
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
        toArchiveButton = new JButton("В архив");
        toArchiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Document document = (Document)((Vector)documentsInformation.get(publicationsTable.getSelectedRow())).lastElement();
                AddToDocArchiveForm frame = new AddToDocArchiveForm();
                frame.setModal(true);
                frame.showFrame(e.getSource(), e);
            }
        });
        buttonPanel.add(uploadButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(toArchiveButton);
    }

    private void initPublicationsTableModel() {
        publicationsTableModel.addColumn("Название");
        publicationsTableModel.addColumn("Дата загрузки");
        publicationsTableModel.addColumn("Дата создания");
        publicationsTableModel.addColumn("ФИО автора");
        publicationsTableModel.addColumn("Описание");
//        addDataToTableModel();
    }

    private void initCatalogsTableModel() {
        catalogsTableModel.addColumn("");
        catalogsTableModel.addColumn("");
    }

    private void addDataToCatalogTableModel() {
        for(int i = catalogsTableModel.getRowCount(); i > 0; i--) {
            catalogsTableModel.removeRow(i-1);
        }
        catalogsInformation.clear();
        File[] catalog = new File("./files/doc_archive").listFiles();
        for(File f : catalog) {
            Vector data = new Vector();
            data.add(f.getName());
            catalogsInformation.add(data.clone());
            catalogsTableModel.addRow(data);
            if(f.getName().equals("archive")) {
                File[] archive = new File(f.getPath()).listFiles();
                for(File file : archive) {
                    Vector archiveFiles = new Vector();
                    archiveFiles.add("");
                    archiveFiles.add(file.getName());
                    catalogsInformation.add(archiveFiles.clone());
                    catalogsTableModel.addRow(archiveFiles);
                }
            }
        }
    }

    private void addDataToTableModel() {
        for(int i = publicationsTableModel.getRowCount(); i > 0; i--) {
            publicationsTableModel.removeRow(i-1);
        }
        documentsInformation.clear();
        DocumentService documentService = new DocumentService();
        try {
            java.util.List<Document> l;
            String catalogName = ((Vector)catalogsInformation.get(catalogsTable.getSelectedRow())).get(0).toString();

            if(catalogName.length()<2) {
                catalogName = ((Vector)catalogsInformation.get(catalogsTable.getSelectedRow())).get(1).toString();
            }
            System.out.println(catalogName);
//            catalogName = catalogName.substring(1,catalogName.length()-1);
//            System.out.println(catalogsInformation.get(catalogsTable.getSelectedRow()).toString());
            if(catalogName.equals("current")){
                l = documentService.getDocumentsByDateCreation(new Date());
                System.out.println("+");
            } else if(checkString(catalogName)){
                int year = Integer.parseInt(catalogName);
                GregorianCalendar calendar = new GregorianCalendar(year, 0, 1);
                Date date = calendar.getTime();
                l = documentService.getDocumentsByDateCreation(date);
            } else {
                l = documentService.getAllDocuments();
                System.out.println("-");
            }
            documentsToTable(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void documentsToTable(java.util.List<Document> list) {
            for(Document document : list) {
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
    }
}