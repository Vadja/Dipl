package org.iit.dr.view.component;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * JDialogExt.
 * 
 * @author Yuriy Karpovich
 */
public abstract class JDialogExt extends JDialog
{

  protected Integer difButtonSize = 10;

  public static final int DEFAULT_WIDTH = 150;

  public static final int DEFAULT_HIGHT = 22;

  protected JDialogExt() throws HeadlessException
  {

    init();
    generateComponents();
    applyBoundsSettings();
  }

  protected abstract void init();

  protected abstract void generateComponents();

  protected void applyBoundsSettings()
  {

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    Dimension minimumSize = getMinimumSize();

    setBounds( ( screenSize.width - minimumSize.width ) / 2, ( screenSize.height - minimumSize.height ) / 2,
      minimumSize.width, minimumSize.height );

  }

  protected JPanel createField( JComponent jComponent, String title )
  {

    return createField( jComponent, new JLabel( title ) );
  }

  protected JScrollPane createVScrollPane( JComponent jComponent )
  {

    JScrollPane jScrollPane =
      new JScrollPane( jComponent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

    jScrollPane.setMinimumSize( jComponent.getMinimumSize() );
    jScrollPane.setMaximumSize( jComponent.getPreferredSize() );
    jScrollPane.setPreferredSize( jComponent.getPreferredSize() );

    return jScrollPane;
  }

  protected JScrollPane createHScrollPane( JComponent jComponent )
  {

    JScrollPane jScrollPane =
      new JScrollPane( jComponent, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

    jScrollPane.setMinimumSize( jComponent.getMinimumSize() );
    jScrollPane.setMaximumSize( jComponent.getPreferredSize() );
    jScrollPane.setPreferredSize( jComponent.getPreferredSize() );

    return jScrollPane;
  }

  protected JScrollPane createScrollPane( JComponent jComponent )
  {

    JScrollPane jScrollPane = new JScrollPane( jComponent );

    jScrollPane.setMaximumSize( jComponent.getMaximumSize() );
    jScrollPane.setPreferredSize( jComponent.getMaximumSize() );

    return jScrollPane;
  }

  protected JPanel createField( JComponent jComponent, JLabel jLabel )
  {

    JPanel fieldPane = new JPanel();
    fieldPane.setLayout( new BoxLayout( fieldPane, BoxLayout.LINE_AXIS ) );

    fieldPane.add( jLabel );
    fieldPane.add( Box.createHorizontalGlue() );
    fieldPane.add( jComponent );

    fieldPane.setBorder( BorderFactory.createEmptyBorder( 10, 10, 0, 10 ) );

    return fieldPane;
  }

  protected JPanel createField( JComponent jComponent )
  {

    JPanel fieldPane = new JPanel();
    fieldPane.setLayout( new BoxLayout( fieldPane, BoxLayout.LINE_AXIS ) );

    fieldPane.add( jComponent );

    fieldPane.setBorder( BorderFactory.createEmptyBorder( 10, 10, 0, 10 ) );

    return fieldPane;
  }

  protected JPanel createLabel( String labelText )
  {

    JLabel jLabel = new JLabel( labelText );

    JPanel fieldPane = new JPanel();
    fieldPane.setLayout( new BoxLayout( fieldPane, BoxLayout.LINE_AXIS ) );

    fieldPane.add( jLabel );

    fieldPane.setBorder( BorderFactory.createEmptyBorder( 10, 10, 0, 10 ) );

    return fieldPane;
  }

  protected JPanel createButtonPane( Integer border, JButton... jButtons )
  {

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout( new BoxLayout( buttonPane, BoxLayout.LINE_AXIS ) );

    buttonPane.add( Box.createHorizontalGlue() );
    for( JButton jButton : jButtons )
    {

      buttonPane.add( Box.createRigidArea( new Dimension( difButtonSize, 0 ) ) );
      buttonPane.add( jButton );
    }

    buttonPane.setBorder( BorderFactory.createEmptyBorder( 0, border, border, border ) );

    return buttonPane;
  }

  protected JPanel createNorthButtonPane( Integer border, JComponent... jComponents )
  {

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout( new BoxLayout( buttonPane, BoxLayout.LINE_AXIS ) );

    buttonPane.add( Box.createHorizontalGlue() );
    for( JComponent jComponent : jComponents )
    {

      buttonPane.add( Box.createRigidArea( new Dimension( difButtonSize, 0 ) ) );
      buttonPane.add( jComponent );
    }

    buttonPane.setBorder( BorderFactory.createEmptyBorder( border, border, border, border ) );

    return buttonPane;
  }

  protected JPanel createVPane( Integer border, JComponent... jComponents )
  {

    JPanel vPane = new JPanel();
    vPane.setLayout( new BoxLayout( vPane, BoxLayout.PAGE_AXIS ) );

    for( JComponent jComponent : jComponents )
    {

      vPane.add( jComponent );
      vPane.add( Box.createRigidArea( new Dimension( 0, border ) ) );
    }
    vPane.remove( vPane.getComponentCount() - 1 );

    return vPane;
  }

  protected JPanel createHPane( Integer border, JComponent... jComponents )
  {

    JPanel hPane = new JPanel();
    hPane.setLayout( new BoxLayout( hPane, BoxLayout.LINE_AXIS ) );

    for( JComponent jComponent : jComponents )
    {

      hPane.add( jComponent );
      hPane.add( Box.createRigidArea( new Dimension( border, 0 ) ) );
    }
    hPane.remove( hPane.getComponentCount() - 1 );

    return hPane;
  }

  protected JTextField createJTextField()
  {

    return createJTextField( DEFAULT_WIDTH );
  }

  protected JTextField createJTextField( int width )
  {

    JTextField textField = new JTextField();
    textField.setMaximumSize( new Dimension( width, DEFAULT_HIGHT ) );
    textField.setPreferredSize( new Dimension( width, DEFAULT_HIGHT ) );

    return textField;
  }

  protected JCheckBox createJCheckBox( String title )
  {

    JCheckBox jCheckBox = new JCheckBox( title );
    jCheckBox.setMaximumSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HIGHT ) );
    jCheckBox.setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HIGHT ) );

    return jCheckBox;
  }

  protected JCheckBox createJCheckBox2( String title )
  {

    JCheckBox jCheckBox = new JCheckBox( title );
    jCheckBox.setMaximumSize( new Dimension( 700, DEFAULT_HIGHT ) );
    jCheckBox.setPreferredSize( new Dimension( 700, DEFAULT_HIGHT ) );

    return jCheckBox;
  }

  protected JTextArea createJTextArea()
  {

    return createJTextArea( DEFAULT_WIDTH );
  }

  protected JTextArea createJTextArea( int width )
  {

    JTextArea jTextArea = new JTextArea();
    jTextArea.setLineWrap( true );
    jTextArea.setWrapStyleWord( true );
    jTextArea.setMaximumSize( new Dimension( width, 70 ) );
    // jTextArea.setPreferredSize(new Dimension(width, 50));

    return jTextArea;
  }

  protected JButtonSelect createJButtonSelect()
  {

    JButtonSelect jButtonSelect = new JButtonSelect();
    jButtonSelect.setMaximumSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HIGHT ) );
    jButtonSelect.setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HIGHT ) );

    return jButtonSelect;
  }

  protected JComboBox createJComboBox()
  {

    return createJComboBox( DEFAULT_WIDTH );
  }

  protected JComboBox createJComboBox( int widht )
  {

    JComboBox comboBox = new JComboBox();
    comboBox.setMaximumSize( new Dimension( widht, DEFAULT_HIGHT ) );
    comboBox.setPreferredSize( new Dimension( widht, DEFAULT_HIGHT ) );

    return comboBox;
  }

  protected Boolean checkFieldNull( JCheckBox jCheckBox )
  {

    return jCheckBox.isSelected();
  }

  protected String checkFieldNull( JComboBox comboBox )
  {

    String textValue = ( String ) comboBox.getSelectedItem();

    return textValue != null && textValue.length() != 0 ? textValue : null;
  }

  protected String checkFieldNull( JTextComponent textComponent )
  {

    String textValue = textComponent.getText();

    return textValue != null && textValue.length() != 0 ? textValue : null;
  }

  protected JDateField createJDateField()
  {

    return createJDateField( DEFAULT_WIDTH );
  }

  protected JDateField createJDateField( int width )
  {

    JDateField jDateField = new JDateField();
    jDateField.setMaximumSize( new Dimension( width, DEFAULT_HIGHT ) );
    jDateField.setPreferredSize( new Dimension( width, DEFAULT_HIGHT ) );
    return jDateField;
  }

  public int getXCommonValue( Container container )
  {

    if( container != null )
    {

      return container.getBounds().x + getXCommonValue( container.getParent() );
    }
    else
    {

      return 0;
    }
  }

  public int getYCommonValue( Container container )
  {

    if( container != null )
    {

      return container.getBounds().y + getYCommonValue( container.getParent() );
    }
    else
    {

      return 0;
    }
  }

}
