package org.iit.dr.subsystems.doc_archive.view.form;

import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vadzim on 12.05.2014.
 */
public class DocArchiveForm extends JInternalFrameExt<Object> {

    private JButton uploadButton;

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
        this.setMinimumSize(new Dimension(400, 500));
        uploadButton = new JButton("Загрузить");
        documentTable = new JTable();
    }

    @Override
    protected void generateComponents() {
        add(uploadButton);

    }
}
