package org.iit.dr.subsystems.doc_archive.view.form;

import com.toedter.calendar.JCalendar;
import org.iit.dr.subsystems.doc_archive.entities.Document;
import org.iit.dr.subsystems.doc_archive.services.DocumentService;
import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.GregorianCalendar;

/**
* Created by Piligrim on 22.05.14.
*/
public class AddToDocArchiveForm extends JInternalFrameExt{

    private JLabel nameLabel;
    private JCalendar jCalendar;
    private JButton addButton;

    @Override
    public boolean showFrame(Object parent, Object o) {
        this.setTitle("В архив");
        this.setVisible(true);
        return true;
    }

    @Override
    protected void init() {
        this.setMinimumSize(new Dimension(350, 150));
    }

    @Override
    protected void generateComponents() {
        initMyComponents();
    }

    private void initMyComponents() {
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));
//        panel.add(new JLabel());
//        panel.add(new JLabel());
//        panel.add(new JLabel());
        nameLabel = new JLabel("Архив за:");
        panel.add(nameLabel);
        jCalendar = new JCalendar();
        panel.add(jCalendar.getYearChooser());
        initAddButton();
        panel.add(addButton);
        add(panel, BorderLayout.CENTER);
    }

    private void initAddButton() {
        addButton = new JButton("Переместить в архив");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToArchive();
            }
        });
    }

    private void addToArchive() {
        File[] catalogs = new File("files/doc_archive/current").listFiles();
        File dir = null;
        Document document;
        GregorianCalendar calendar = new GregorianCalendar();
        for(File file : catalogs) {
            try {
                File oldFile = file;
                document = new DocumentService().getDocumentByFile(file);
                if(document != null) {
                    if(dir == null) {
//                        calendar.setGregorianChange(document.getCreateDate());
                        dir = new File("./files/doc_archive/archive/" + jCalendar.getYearChooser().getYear());
                        if(!dir.exists()) {
                            dir.mkdir();
                        }
                    }
//                    file = new File(dir.getPath() + "/" + file.getName());
//                    if(!file.exists()) file.createNewFile();
                    file = copyFile(file.getName(), new FileInputStream(file), dir.getName());
                    document.setFile(file);
                    document.setTitle(file.getName());
                    new DocumentService().updateDocument(document);
                    oldFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private File renameFile(String fileName, File file) {
        int i = 1;
        if(fileName.contains("(")) {
            i = Integer.parseInt(fileName.substring(fileName.indexOf("(")+1, fileName.indexOf(")")));

        }
//          else {
//            file = new File(file.getPath().substring(0, file.getPath().indexOf(".")) + "(" + i + ")"
//                    + fileName.substring(fileName.indexOf(".")));
//        }
        while(file.exists()) {
            //            String fName = fileName;
            //            fName = fName.substring(0,fName.indexOf("."));
            if(file.getName().contains("(")) {
                file = new File(file.getPath().substring(0, file.getPath().indexOf("(")) + "(" + i
                        + file.getName().substring(file.getName().indexOf(")")));
            } else {
                file = new File(file.getPath().substring(0, file.getPath().indexOf(".")) + "(" + i + ")"
                        + fileName.substring(fileName.indexOf(".")));
            }
            i++;
        }
        return file;
    }

    private File copyFile(final String fileName, final InputStream in, String dirName) {
        File file = new File("files/doc_archive/archive/" + dirName + "/" + fileName);
        if(file.exists()) {
            file = renameFile(fileName, file);
        }
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return file;
    }
}
