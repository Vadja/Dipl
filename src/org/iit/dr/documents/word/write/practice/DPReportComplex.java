package org.iit.dr.documents.word.write.practice;

import org.iit.dr.data_model.test.DPData;
import org.iit.dr.documents.word.write.graduate_work.DPDiaryPDocumentGenerator;
import org.iit.dr.documents.word.write.staffers.PR9AktDocumentGenerator;
import org.iit.dr.documents.word.write.staffers.PR9DocumentGenerator;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.view.form.practice.ProgressEvent;

import java.util.List;
import java.util.ArrayList;

/**
 * DPReportComplex.
 * 
 * @author Yuriy Karpovich
 */
public class DPReportComplex
{

  int currentIndex;

  List<ProgressListener> progressListenerList = new ArrayList<ProgressListener>();

  public boolean addProgressListener( ProgressListener listener )
  {

    return progressListenerList.add( listener );
  }

  public boolean removeProgressListener( ProgressListener listener )
  {

    return progressListenerList.remove( listener );
  }

  private void fireProgressUpdate( ProgressEvent progressEvent )
  {

    for( ProgressListener listener : progressListenerList )
    {

      listener.progressUpdate( progressEvent );
    }
  }

  private void fireProgressUpdate( int progressStatus )
  {

    fireProgressUpdate( new ProgressEvent( progressStatus ) );
  }

  public void generateReports( List<DPData> dpDataList )
  {

    for( DPData dpData : dpDataList )
    {

      PDPDocumentGenerator pdpDocumentGenerator = new PDPDocumentGenerator();
      pdpDocumentGenerator.generate( FileUtils.getDocumentPath( dpData.getStudent().getGroup(), dpData.getStudent()
        .getShortName(), "Договор ПДП (договор).doc" ), new String[] {"25.01.2010", "Осипова Анатолия Николаевича",
        dpData.getManager() + ", " + dpData.getManagerData(), "1", dpData.getStudent().getShortName(), "ФИТиУ, ИИ",
        "бюджет", ""} );
      fireProgressUpdate( ++currentIndex );

      PDPAktDocumentGenerator pdpAktDocumentGenerator = new PDPAktDocumentGenerator();
      pdpAktDocumentGenerator.generate( FileUtils.getDocumentPath( dpData.getStudent().getGroup(), dpData.getStudent()
        .getShortName(), "Договор ПДП (акт) [Февраль].doc" ), new String[] {"1", dpData.getStudent().getShortName(),
        "ФИТиУ, ИИ", "бюджет", ""} );
      fireProgressUpdate( ++currentIndex );

      pdpAktDocumentGenerator.generate( FileUtils.getDocumentPath( dpData.getStudent().getGroup(), dpData.getStudent()
        .getShortName(), "Договор ПДП (акт) [Март].doc" ), new String[] {"1", dpData.getStudent().getShortName(),
        "ФИТиУ, ИИ", "бюджет", ""} );
      fireProgressUpdate( ++currentIndex );

      PR9DocumentGenerator pr9DocumentGenerator = new PR9DocumentGenerator();
      pr9DocumentGenerator.generate( FileUtils.getDocumentPath( dpData.getStudent().getGroup(), dpData.getStudent()
        .getShortName(), "Форма-ПР-09 (договор).doc" ),
        new String[] {"Осипова Анатолия Николаевича", dpData.getManager() + ", " + dpData.getManagerData(), "16"} );
      fireProgressUpdate( ++currentIndex );

      PR9AktDocumentGenerator pr9AktDocumentGenerator = new PR9AktDocumentGenerator();
      pr9AktDocumentGenerator.generate( FileUtils.getDocumentPath( dpData.getStudent().getGroup(), dpData.getStudent()
        .getShortName(), "Форма-АПР-09 (акт) [Апрель].doc" ),
        new String[] {"Осипова Анатолия Николаевича", dpData.getManager() + ", " + dpData.getManagerData(),
          "23.03.2010", "26.04.2010", "32"} );
      fireProgressUpdate( ++currentIndex );

      pr9AktDocumentGenerator.generate( FileUtils.getDocumentPath( dpData.getStudent().getGroup(), dpData.getStudent()
        .getShortName(), "Форма-АПР-09 (акт) [Май].doc" ),
        new String[] {"Осипова Анатолия Николаевича", dpData.getManager() + ", " + dpData.getManagerData(),
          "27.04.2010", "28.05.2010", "32"} );
      fireProgressUpdate( ++currentIndex );

      DPDiaryPDocumentGenerator dpDiaryPDocumentGenerator = new DPDiaryPDocumentGenerator();
      dpDiaryPDocumentGenerator.generate( FileUtils.getDocumentPath( dpData.getStudent().getGroup(), dpData
        .getStudent().getShortName(), "Дневник-ДП.doc" ), new String[] {dpData.getManager(), dpData.getConsultant(),
        dpData.getStudent().getFullName(), dpData.getTheme()} );
      fireProgressUpdate( ++currentIndex );
    }

  }

}
