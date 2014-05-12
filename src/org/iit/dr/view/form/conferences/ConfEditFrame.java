package org.iit.dr.view.form.conferences;

import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.utils.UUIDUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 17.12.2010
 * Time: 12:58:10
 * To change this template use File | Settings | File Templates.
 */


import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.model.ConferenceTreeSimplePanel;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.data_model.Conference;
import org.iit.dr.data_model.OrganizationUnitWrapper;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.UnitType;
import org.iit.dr.data_storage.DataStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 15.12.2010
 * Time: 5:02:13
 * To change this template use File | Settings | File Templates.
 */
public class ConfEditFrame extends JInternalFrameExt<Conference> {

  Conference conf;

  ConferenceTreeSimplePanel confTreeFrame;

  JTextField titleField;
  JTextField yearField;
  JTextField cityField;
//  JTextField cityCodeField;
//  JComboBox typeComboBox;

  public boolean showFrame(Object parent, Conference conf) {

    this.conf = conf;

    confTreeFrame = (ConferenceTreeSimplePanel) parent;

    if (conf != null) {


      titleField.setText(conf.getTitle());
      yearField.setText(String.valueOf(conf.getYear()));
      cityField.setText(conf.getCity());
//      typeComboBox.setSelectedItem(organizationUnitWrapper.getOrganizationUnit().getType());

    }

    setVisible(true);
    return true;
  }

  protected void init() {

    setTitle("Форма редактирования: " +
            "");

    setMinimumSize(new Dimension(300, 250));
    setResizable(false);
  }

  protected void generateComponents() {

    titleField = createJTextField();
    yearField = createJTextField();
    cityField = createJTextField();
//    cityCodeField = createJTextField();
//    typeComboBox = createJComboBox();
//    typeComboBox.addItem(UnitType.STREAM);
//    typeComboBox.addItem(UnitType.GROUP);
//    typeComboBox.addItem(UnitType.SUBGROUP);

    JPanel fieldsPane = createVPane(5,
        createField(titleField, "Название: "),
        createField(cityField, "Город: "),
        createField(yearField, "Год проведения: "));

    JButton applyButton = new JButton("Применить");
    applyButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        conf.setTitle(checkFieldNull(titleField));
        conf.setYear(Integer.valueOf(checkFieldNull(yearField)));
        conf.setCity(checkFieldNull(cityField));
//        conf.setcityCode(checkFieldNull(cityCodeField));
//        conf.setType((UnitType) typeComboBox.getSelectedItem());

        if (conf.getId() == null) {

          conf.setId(UUIDUtils.getUUID());
          DataStorage.getInstance().getConferenceList().add(conf);
        }

        if (confTreeFrame != null) {

          confTreeFrame.updateData();
        }

        setVisible(false);
      }
    });

    JButton cancelButton = new JButton("Отмена");
    cancelButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        setVisible(false);
      }
    });


    getContentPane().add(fieldsPane, BorderLayout.CENTER);
    getContentPane().add(createNorthButtonPane(10, applyButton, cancelButton), BorderLayout.SOUTH);
  }
}

