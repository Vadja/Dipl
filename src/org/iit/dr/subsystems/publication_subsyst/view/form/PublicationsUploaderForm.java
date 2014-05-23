package org.iit.dr.subsystems.publication_subsyst.view.form;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import org.iit.dr.subsystems.publication_subsyst.entities.Document;
import org.iit.dr.subsystems.publication_subsyst.entities.Role;
import org.iit.dr.subsystems.publication_subsyst.entities.User;
import org.iit.dr.subsystems.publication_subsyst.services.DocumentService;
import org.iit.dr.subsystems.publication_subsyst.services.RoleService;
import org.iit.dr.subsystems.publication_subsyst.services.UserService;
import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Piligrim
 * Date: 14.05.14
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
public class PublicationsUploaderForm extends JInternalFrameExt {

    private JPanel mainPanel;
    private JTextArea informationTextArea;

    private JPanel formPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField midleNameTextField;
    private JComboBox userRoleComboBox;
    private JTextArea descriptionTextArea;
    private JButton chooseFileButton;
    private JButton uploadPublicationButton;

    private JPanel labelPanel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel midleNameLabel;
    private JLabel userRoleLabel;
    private JLabel descriptionLabel;

    private JDateChooser calendarChooser;

    private Vector<Role> rolesVector;

    private Document document;

    private final boolean update;

    private File file;

    public PublicationsUploaderForm() {
        document = null;
        update = false;
        calendarChooser.setDate(new Date());
    }

    public PublicationsUploaderForm(Document document) {
        super();
        this.document = document;
        update = true;
        loadDocument();
//        calendarChooser.setDate(new Date());
    }

    @Override
    public boolean showFrame(Object parent, Object o) {
        this.setTitle("Загрузка публикации");
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
        initUploadPublicationButton();
        informationTextArea = new JTextArea();
        informationTextArea.setEditable(false);
        southPanel.add(uploadPublicationButton);
        southPanel.add(informationTextArea);
        framePanel.add(mainPanel, BorderLayout.CENTER);
        framePanel.add(southPanel, BorderLayout.SOUTH);
        add(framePanel);
//        loadDocument();
    }

    private void loadDocument() {
        if(update) {
            this.descriptionTextArea.setText(document.getDescription());
            this.file = document.getFile();
            this.firstNameTextField.setText(document.getUser().getFirstName());
            this.lastNameTextField.setText(document.getUser().getLastName());
            this.midleNameTextField.setText(document.getUser().getMidleName());
            this.userRoleComboBox.setSelectedItem(document.getUser().getRole().getName());
            this.calendarChooser.setDate(document.getCreateDate());
            repaint();
        }
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
                            file = copyFile(file.getName(), new FileInputStream(file));
                            if(update) {
                                document.setDescription(descriptionTextArea.getText());
                                document.setFile(file);
                                document.setTitle(file.getName());
                                document.setCreateDate(calendarChooser.getDate());
                                User user = new UserService().searchUser(lastNameTextField.getText().trim(),
                                        firstNameTextField.getText().trim(), midleNameTextField.getText().trim(),
                                        rolesVector.get(userRoleComboBox.getSelectedIndex()));
                                if(user == null) {
                                    document.setUser(new UserService().addUser(lastNameTextField.getText().trim(),
                                            firstNameTextField.getText().trim(), midleNameTextField.getText().trim(),
                                            rolesVector.get(userRoleComboBox.getSelectedIndex())));
                                } else {
                                    document.setUser(user);
                                }
                                new DocumentService().updateDocument(document);
                            } else {
                                new DocumentService().addDocument(file, lastNameTextField.getText().trim(),
                                        firstNameTextField.getText().trim(), midleNameTextField.getText().trim(),
                                        rolesVector.get(userRoleComboBox.getSelectedIndex()), descriptionTextArea.getText(),
                                        calendarChooser.getDate());
                                informationTextArea.setText("Публикация сохранена");
                            }
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
                && midleNameTextField.getText().length() > 2) {
            flag = false;
        }
        return flag;
    }

    private void initFormPanel() {
        formPanel = new JPanel(new GridLayout(11, 1));
        lastNameTextField = new JTextField();
        firstNameTextField = new JTextField();
        midleNameTextField = new JTextField();
        calendarChooser = new JDateChooser();
        try {
            rolesVector = new RoleService().searchAllRoles();
            Vector v = new Vector();
            for(Role r : rolesVector) {
                v.add(r.getName());
            }
            userRoleComboBox = new JComboBox(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        descriptionTextArea = new JTextArea();
        initChooseFileButton();
        formPanel.add(lastNameTextField);
        formPanel.add(firstNameTextField);
        formPanel.add(midleNameTextField);
        formPanel.add(userRoleComboBox);
        formPanel.add(descriptionTextArea);
        formPanel.add(chooseFileButton);
        calendarChooser.setDateFormatString("dd/MM/yyyy");
        formPanel.add(calendarChooser);
    }

    private void initChooseFileButton() {
        chooseFileButton = new JButton("Выбрать файл");
        chooseFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Публикации", "doc", "docx", "pdf", "txt"));
                int ret = fileChooser.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if(isCorrectFileType(file.getName())) {
//                            file = copyFile(file.getName(), new FileInputStream(file));
                        informationTextArea.setText("Файл выбран");
                    } else {
                        file = null;
                        informationTextArea.setText("Неверный формат файла, \n" +
                                "следует загружать файлы следующих форматов: '.doc|.docx|.txt|.pdf'");
                    }
                }
            }
        });
    }

    private File renameFile(String fileName, File file) {
        int i = 1;
        if(fileName.contains("(")) {
            i = Integer.parseInt(fileName.substring(fileName.indexOf("(")+1, fileName.indexOf(")")));

        } else {
            file = new File(file.getPath().substring(0, file.getPath().indexOf(".")) + "(" + i + ")"
                    + fileName.substring(fileName.indexOf(".")));
        }
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

    private File copyFile(final String fileName, final InputStream in) {
        File file = new File("files/publications/current/" + fileName);
        if(file.exists()) {
            file = renameFile(fileName, file);
        }
        try {
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
        midleNameLabel = new JLabel("Отчество");
        userRoleLabel = new JLabel("");
        descriptionLabel = new JLabel("Описание");
        labelPanel.add(lastNameLabel);
        labelPanel.add(firstNameLabel);
        labelPanel.add(midleNameLabel);
        labelPanel.add(userRoleLabel);
        labelPanel.add(descriptionLabel);
    }
}
