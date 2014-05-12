package org.iit.dr.utils;

import java.util.Locale;

/**
 * LabelData.
 * 
 * @author Yuriy Karpovich
 */
public class LabelData
{

  public static Locale getLocale( String loc )
  {
    if( loc != null && loc.length() > 0 )
      return new Locale( loc );
    else
      return Locale.US;
  }

  public String getLabel( LabelUtilsNames labelId )
  {

    switch( labelId )
    {

      case APPLICATION_NAME:
        return "Система планирования и формирования отчетной документации";
      case MAIN_MENU_FILE:
        return "Файл";
      case MAIN_MENU_DICTIONARY:
        return "Справочники";
      case MAIN_MENU_HELP:
        return "Справка";
      case LOGIN_DIALOG_NAME:
        return "Вход в систему";
    }

    return "";
  }
}
