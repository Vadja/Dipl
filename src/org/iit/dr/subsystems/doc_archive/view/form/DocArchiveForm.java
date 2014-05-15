package org.iit.dr.subsystems.doc_archive.view.form;

import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vadzim on 12.05.2014.
 */
public class DocArchiveForm extends JInternalFrameExt<Object> {

    private JButton uploadButton;
    private JButton overviewButton;
    private JButton downloadButton;

    private JPanel mainPanel;

    private final String[] colNames = {"Назвние", "Дата создания", "Дата добавления", "Описание", "Тип"};

    private  JTable documentTable;

    @Override
    public boolean showFrame(Object parent, Object o) {
        this.setTitle("Подсистема для хранения информации");
        this.setVisible(true);
        return true;
    }

    @Override
    protected void init() {
        this.setMinimumSize(new Dimension(800, 600));
        mainPanel = new JPanel(new GridLayout(3, 1));
        uploadButton = new JButton("Загрузить документы в архив");
        overviewButton = new JButton("Обзор архива документации");
        downloadButton = new JButton("Загрузка документации за год");
        documentTable = new JTable();
    }

    @Override
    protected void generateComponents() {
        mainPanel.add(uploadButton);
        mainPanel.add(overviewButton);
        mainPanel.add(downloadButton);
        add(mainPanel);

    }
}
