package org.iit.dr.view.form.graduate_work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.Gek;
import org.iit.dr.services.GekService;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;

/**
 * LoginDialog.
 * 
 * @author Yuriy Karpovich
 */
public class ToDefenceDialog extends JInternalFrameExt<List<String>>
{
  private static final long serialVersionUID = 3484884534564255959L;

  JCheckBox generateTime;

  JDateField dateDefence;

  List<String> selectedItemList;

  public void login()
  {

    setVisible( true );
  }

  @Override
  protected void init()
  {

    setTitle( "Формирование протокола" );
    setMinimumSize( new Dimension( 300, 100 ) );
    setResizable( false );
  }

  @Override
  protected void generateComponents()
  {

    JButton loginButton = new JButton( "Выполнить" );
    loginButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        try
        {

          Integer protokolNumber = GraduateWorkService.getDefenceGraduateWorkProtokolNumber();

          if( selectedItemList != null )
          {

            Gek gek = GekService.getGek();

            for( String selectedItem : selectedItemList )
            {

              DefenceGraduateWork defenceGraduateWork =
                GraduateWorkService.getDefenceGraduateWorkByGraduateWorkId( selectedItem );

              if( defenceGraduateWork == null )
              {

                protokolNumber++;
                defenceGraduateWork = new DefenceGraduateWork();
                defenceGraduateWork.setId( UUIDUtils.getUUID() );
                defenceGraduateWork.setGraduateWorkId( selectedItem );
                defenceGraduateWork.setActualDate( dateDefence.getDate() );
                // defenceGraduateWork.setProtocolNumber(protokolNumber < 10
                // ? "0" + protokolNumber : protokolNumber.toString());
                defenceGraduateWork.setPresideId( gek.getPresideId() );
                defenceGraduateWork.setCommissionerSecrId( gek.getCommissionerSecrId() );
                defenceGraduateWork.setCommissioner0Id( gek.getCommissioner0Id() );
                defenceGraduateWork.setCommissioner1Id( gek.getCommissioner1Id() );
                defenceGraduateWork.setCommissioner2Id( gek.getCommissioner2Id() );
                defenceGraduateWork.setCommissioner3Id( gek.getCommissioner3Id() );
                defenceGraduateWork.setCommissioner4Id( gek.getCommissioner4Id() );
                defenceGraduateWork.setCommissioner5Id( gek.getCommissioner5Id() );
                defenceGraduateWork.setCommissioner6Id( gek.getCommissioner6Id() );
                defenceGraduateWork.setCommissioner7Id( gek.getCommissioner7Id() );
                defenceGraduateWork.setCommissioner8Id( gek.getCommissioner8Id() );
                defenceGraduateWork.setCommissioner9Id( gek.getCommissioner9Id() );

                GraduateWorkService.getDefenceGraduateWorkList().add( defenceGraduateWork );
              }

            }

          }

          generateTime.setSelected( false );
          dateDefence.setDate( null );
          setVisible( false );

        }
        catch( SecurityException ex )
        {

          System.err.println( "-" );
        }
      }
    } );

    JButton exitButton = new JButton( "Отмена" );
    exitButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        generateTime.setSelected( false );
        dateDefence.setDate( null );
        setVisible( false );

      }
    } );

    generateTime = createJCheckBox( "Заполнять время" );
    dateDefence = createJDateField();

    JPanel centerJPane = createVPane( 0, createField( dateDefence, "Дата защиты" ) );
    // createField(generateTime, " "));

    getContentPane().add( centerJPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, loginButton, exitButton ), BorderLayout.SOUTH );
  }

  @Override
  public boolean showFrame( Object parent, List<String> o )
  {

    selectedItemList = o;

    setVisible( true );

    return true;
  }
}