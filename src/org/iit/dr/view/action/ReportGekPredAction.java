package org.iit.dr.view.action;

import org.iit.dr.documents.word.write.graduate_work.OpisDPDocumentGenerator;
import org.iit.dr.utils.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * OpisDPAction.
 * 
 * @author Yuriy Karpovich
 */
public class ReportGekPredAction extends CustomAction
{

  public ReportGekPredAction()
  {

    putValue( NAME, "Сформировать отчет председателя ГЭК" );
  }

  public void actionPerformed( ActionEvent e )
  {

    InputStream inputStream = getClass().getResourceAsStream( "reportGek.properties" );
    Properties properties = new Properties();
    try
    {
      properties.load( inputStream );

      System.out.println( "test=" + properties.getProperty( "test" ) );
    }
    catch( IOException e1 )
    {
      e1.printStackTrace();
    }

    // OpisDPDocumentGenerator opisDPDocumentGenerator = new OpisDPDocumentGenerator();
    // opisDPDocumentGenerator.setTemplateName("Опись дипломных проектов.doc");
    // opisDPDocumentGenerator.generateDocument(FileUtils.getCommonDocumentPath("Опись дипломных проектов [2010].doc"));

  }
}