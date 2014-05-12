package org.iit.dr.utils;

/**
 * LabelUtils.
 * 
 * @author Yuriy Karpovich
 */
public class LabelUtils
{

  private LabelUtils()
  {
  }

  private static LabelData labelData = null;

  private static LabelData getLabelData()
  {

    if( labelData == null )
    {

      synchronized( LabelUtils.class )
      {

        if( labelData == null )
        {

          labelData = new LabelData();
        }
      }
    }
    return labelData;
  }

  public static String getLabel( LabelUtilsNames labelId )
  {

    return getLabelData().getLabel( labelId );
  }

  public static String getIntegerAsStringWithBr( Integer integer )
  {

    String string = getIntegerAsString( integer );

    if( string.length() > 0 )
    {
      return integer + " (" + string + ")";
    }
    else
    {
      return string;
    }
  }

  public static String getIntegerAsString( Integer integer )
  {

    if( integer == null )
    {

      return "";
    }

    switch( integer )
    {

      case 0:
        return "ноль";
      case 1:
        return "один";
      case 2:
        return "два";
      case 3:
        return "три";
      case 4:
        return "четыре";
      case 5:
        return "пять";
      case 6:
        return "шесть";
      case 7:
        return "семь";
      case 8:
        return "восемь";
      case 9:
        return "девять";
      case 10:
        return "десять";
    }

    return "";
  }

}
