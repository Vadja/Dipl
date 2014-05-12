package org.iit.dr.utils;

import java.io.File;
import java.util.Date;

import org.iit.dr.data_model.unit.OrganizationUnit;

/**
 * FileUtils.
 * 
 * @author Yuriy Karpovich
 */
public class FileUtils
{

  private final static String PATH_SEPARATOR = "\\";

  private static String DATA_FOLDER = "data";

  private static String TEMPLATE_FOLDER = "templates";

  private static String DOCUMENTS_FOLDER = "documents";

  private static String ARCHIVE_FOLDER = "archive";

  private static String COMMON_DATA_FOLDER = "common";

  private static String PRACTICE_DATA_FOLDER = "practice";

  private static String DICTIONARY_DATA_FOLDER = "dictionary";

  private static String PACK_DATA_FOLDER = "pack";

  private static String RATE_DATA_FILE = "rate.xml";

  private static String GRADUATE_WORK_DATA_FILE = "graduateWork.xml";

  private static String PRACTICE_WORK_DATA_FILE = "practiceWork.xml";

  private static String PRODUCTION_PRACTICE_CONSULTANTS = "productionPracticeConsultants.xml";

  private static String GRADUATE_PRACTICE_CONSULTANTS = "graduatePracticeConsultants.xml";

  private static String PRODUCTION_PRACTICE_WORK_DATA_FILE = "prodPracticeWork.xml";

  private static String DEFENCE_GRADUATE_WORK_DATA_FILE = "defenceGraduateWork.xml";

  private static String GEK_DATA_FILE = "gek.xml";

  private static String DEFAULT_YEAR = "2009-2010";

  public static final String XML_EXT = ".xml";

  public static File checkDir( File file )
  {
    file.mkdirs();
    return file;
  }

  public static File checkDir( String fileName )
  {

    return checkDir( new File( fileName ) );
  }

  public static String getLocationPath()
  {

    return System.getProperty( "user.dir" );
  }

  public static File getCollectionData( String collectionName )
  {
    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DATA_FOLDER )
        .append( PATH_SEPARATOR ).append( DICTIONARY_DATA_FOLDER ).append( PATH_SEPARATOR ).toString();
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + collectionName + XML_EXT );
  }

  public static String getTemplate( String templateName )
  {

    return new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DATA_FOLDER )
      .append( PATH_SEPARATOR ).append( TEMPLATE_FOLDER ).append( PATH_SEPARATOR ).append( templateName ).toString();
  }

  public static String getDataPath()
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DATA_FOLDER ).toString();

    return path;
  }

  public static String getArchivePath()
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( ARCHIVE_FOLDER )
        .append( PATH_SEPARATOR ).toString();

    return path;
  }

  public static String getCommonDocumentPath( String documentName )
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DOCUMENTS_FOLDER )
        .append( PATH_SEPARATOR ).append( COMMON_DATA_FOLDER ).append( PATH_SEPARATOR ).toString();

    File file = checkDir( path );

    return file.getAbsolutePath() + PATH_SEPARATOR + documentName;
  }

  public static String getPracticeDocumentPath( String documentName )
  {
    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DOCUMENTS_FOLDER )
        .append( PATH_SEPARATOR ).append( PRACTICE_DATA_FOLDER ).append( PATH_SEPARATOR ).toString();
    File file = checkDir( path );
    return file.getAbsolutePath() + PATH_SEPARATOR + documentName;
  }

  public static String getPracticeContractPath( String companyName, String documentName )
  {
    String path =
      getLocationPath() + PATH_SEPARATOR + DOCUMENTS_FOLDER + PATH_SEPARATOR + PRACTICE_DATA_FOLDER + PATH_SEPARATOR
        + "contracts" + PATH_SEPARATOR;
    File file = checkDir( path );
    return file.getAbsolutePath() + PATH_SEPARATOR + companyName + "_" + documentName;
  }

  public static String getPracticeLettersPath( String companyName, String documentName )
  {
    String path =
      getLocationPath() + PATH_SEPARATOR + DOCUMENTS_FOLDER + PATH_SEPARATOR + PRACTICE_DATA_FOLDER + PATH_SEPARATOR
        + "letters" + PATH_SEPARATOR;
    File file = checkDir( path );
    return file.getAbsolutePath() + PATH_SEPARATOR + companyName + "_" + documentName;
  }

  public static String getDocumentPath( String group, String userName, String documentName )
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DOCUMENTS_FOLDER )
        .append( PATH_SEPARATOR ).append( group ).append( PATH_SEPARATOR ).append( userName ).append( PATH_SEPARATOR )
        .append( documentName ).toString();

    return path;
  }

  public static String getDocumentPath( String userName, String documentName )
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DOCUMENTS_FOLDER )
        .append( PATH_SEPARATOR ).append( userName ).append( PATH_SEPARATOR ).toString();

    File file = checkDir( path );

    return file.getAbsolutePath() + PATH_SEPARATOR + documentName;
  }

  public static String getDocumentPath( Date date, String documentName )
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DOCUMENTS_FOLDER )
        .append( PATH_SEPARATOR ).append( DateUtils.dateToString( date ) ).append( PATH_SEPARATOR ).toString();

    File file = checkDir( path );

    return file.getAbsolutePath() + PATH_SEPARATOR + documentName;
  }

  public static String getDocumentPath( OrganizationUnit ou, String userName, String documentName )
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DOCUMENTS_FOLDER )
        .append( PATH_SEPARATOR ).append( ou.getName() ).append( PATH_SEPARATOR ).append( userName )
        .append( PATH_SEPARATOR ).append( documentName ).toString();

    return path;
  }

  public static File getCommonData( String fileName )
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DATA_FOLDER )
        .append( PATH_SEPARATOR ).append( COMMON_DATA_FOLDER ).append( PATH_SEPARATOR ).toString();

    File file = checkDir( path );

    return new File( file.getAbsolutePath() + PATH_SEPARATOR + fileName );
  }

  public static File getDictionaryData( String fileName )
  {

    String path =
      new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DATA_FOLDER )
        .append( PATH_SEPARATOR ).append( DICTIONARY_DATA_FOLDER ).append( PATH_SEPARATOR ).toString();

    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + fileName );
  }

  public static File getRateData()
  {
    return getRateData( DEFAULT_YEAR );
  }

  public static File getRateData( String year )
  {
    String path = getDataPath( year );
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + RATE_DATA_FILE );
  }

  public static File getGraduateWorkData()
  {
    return getGraduateWorkData( DEFAULT_YEAR );
  }

  public static File getGraduateWorkData( String year )
  {
    String path = getDataPath( year );
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + GRADUATE_WORK_DATA_FILE );
  }

  public static File getPracticeWorkData()
  {
    return getPracticeWorkData( DEFAULT_YEAR );
  }

  public static File getPracticeWorkData( String year )
  {
    String path = getDataPath( year );
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + PRACTICE_WORK_DATA_FILE );
  }

  public static File getProductionPracticeWorkData()
  {
    return getProductionPracticeWorkData( DEFAULT_YEAR );
  }

  public static File getProductionPracticeWorkData( String year )
  {
    String path = getDataPath( year );
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + PRODUCTION_PRACTICE_WORK_DATA_FILE );
  }

  public static File getDefenceGraduateWorkData()
  {
    return getDefenceGraduateWorkData( DEFAULT_YEAR );
  }

  public static File getDefenceGraduateWorkData( String year )
  {
    String path = getDataPath( year );
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + DEFENCE_GRADUATE_WORK_DATA_FILE );
  }

  public static File getGekData()
  {
    return getGekData( DEFAULT_YEAR );
  }

  public static File getGekData( String year )
  {
    String path = getDataPath( year );
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + GEK_DATA_FILE );
  }

  public static File getProductionPracticeConsultantsData( String year )
  {
    String path = getDataPath( year );
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + PRODUCTION_PRACTICE_CONSULTANTS );
  }

  public static File getGraduatePracticeConsultantsData( String year )
  {
    String path = getDataPath( year );
    File file = checkDir( path );
    return new File( file.getAbsolutePath() + PATH_SEPARATOR + GRADUATE_PRACTICE_CONSULTANTS );
  }

  public static File getProductionPracticeConsultantsData()
  {
    return getProductionPracticeConsultantsData( DEFAULT_YEAR );
  }

  public static File getGraduatePracticeConsultantsData()
  {
    return getGraduatePracticeConsultantsData( DEFAULT_YEAR );
  }

  private static String getDataPath( String year )
  {
    return new StringBuilder().append( getLocationPath() ).append( PATH_SEPARATOR ).append( DATA_FOLDER )
      .append( PATH_SEPARATOR ).append( PACK_DATA_FOLDER ).append( PATH_SEPARATOR ).append( year )
      .append( PATH_SEPARATOR ).toString();
  }
}
