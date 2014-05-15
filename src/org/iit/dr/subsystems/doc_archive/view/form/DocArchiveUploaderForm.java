package org.iit.dr.subsystems.doc_archive.view.form;

import org.iit.dr.subsystems.doc_archive.services.DocumentService;
import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Vadja
 * Date: 14.05.14
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
public class DocArchiveUploaderForm extends JInternalFrameExt {

    private JPanel mainPanel;
    private JTextArea informationTextArea;

    private JPanel formPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField middleNameTextField;
    private JButton chooseFileButton;
    private JButton uploadPublicationButton;

    private JPanel labelPanel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel middleNameLabel;

    private File file;

    @Override
    public boolean showFrame(Object parent, Object o) {
        this.setTitle("Загрузка документации");
        this.setVisible(true);
        return true;
    }

    @Override
    protected void init() {
        this.setMinimumSize(new Dimension(500, 350));
    }

    @Override
    protected void generateComponents() {
        JPanel framePanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        mainPanel = new JPanel(new GridLayout(1, 2));
        initLabelPanel();
        initFormPanel();
        mainPanel.add(labelPanel);
        mainPanel.add(formPanel);
//        uploadPublicationButton = new JButton("Загрузить");
        initUploadPublicationButton();
        informationTextArea = new JTextArea();
        informationTextArea.setEditable(false);
        southPanel.add(uploadPublicationButton);
        southPanel.add(informationTextArea);
        framePanel.add(mainPanel, BorderLayout.CENTER);
        framePanel.add(southPanel, BorderLayout.SOUTH);
        add(framePanel);
    }

    private void initUploadPublicationButton() {
        uploadPublicationButton = new JButton("Загрузить");
        uploadPublicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file == null) {
                    informationTextArea.setText("Файл не выбран");
                } else {
                    if(isTextFieldEmpty()) {
                        informationTextArea.setText("Нужно ввести ФИО автора");
                    } else {
                        try {
                            new DocumentService().addDocument(file, lastNameTextField.getText().trim(),
                                    firstNameTextField.getText().trim(), middleNameTextField.getText().trim());
                            informationTextArea.setText("Документ сохранен");
                        } catch (Exception e1) {
                            informationTextArea.setText("Произошла ошибка при загрузке файла");
                        }
                    }
                }
            }
        });
    }

    private boolean isTextFieldEmpty() {
        boolean flag = true;
        if(lastNameTextField.getText().length() > 2
                && firstNameTextField.getText().length() > 2
                && middleNameTextField.getText().length() > 2) {
            flag = false;
        }
        return flag;
    }

    private void initFormPanel() {
        formPanel = new JPanel(new GridLayout(11, 1));
        lastNameTextField = new JTextField();
        firstNameTextField = new JTextField();
        middleNameTextField = new JTextField();
        initChooseFileButton();
        formPanel.add(lastNameTextField);
        formPanel.add(firstNameTextField);
        formPanel.add(middleNameTextField);
        formPanel.add(chooseFileButton);
    }

    private void initChooseFileButton() {
        chooseFileButton = new JButton("Выбрать файл");
        chooseFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Документы", "doc", "docx", "pdf", "txt"));
                int ret = fileChooser.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if(isCorrectFileType(file.getName())) {
                        System.out.println(";)");
                    } else {
                        informationTextArea.setText("Неверный формат файла. \n" +
                                "Поддерживаемые форматы: '.doc|.docx|.txt|.pdf'");
                    }
                }
            }
        });
    }

    private boolean isCorrectFileType(String fileName) {
        boolean flag = false;
        if(fileName.endsWith(".pdf") || fileName.endsWith(".doc")
                || fileName.endsWith(".docx") || fileName.endsWith(".txt")) {
            flag = true;
        }
        return flag;
    }

    private void initLabelPanel() {
        labelPanel = new JPanel(new GridLayout(11, 1));
        firstNameLabel = new JLabel("Имя");
        lastNameLabel = new JLabel("Фамилия");
        middleNameLabel = new JLabel("Отчество");
        labelPanel.add(lastNameLabel);
        labelPanel.add(firstNameLabel);
        labelPanel.add(middleNameLabel);
    }
}
