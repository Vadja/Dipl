package org.iit.dr.utils;

import org.iit.dr.view.form.graduate_work.CommonDefenceGWFrame;
import org.iit.dr.view.form.graduate_work.DefenceGraduateWorkListFrame;
import org.iit.dr.view.form.graduate_work.GekFrame;
import org.iit.dr.view.form.graduate_work.GraduateWorkListFrame;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Constructor;

/**
 * MemoryManager.
 * 
 * @author Yuriy Karpovich
 */
public class MemoryManager
{

  private static final Object syncObject = new Object();

  boolean init = false;

  private Map<String, Object> objectMap;

  private static MemoryManager instance;

  private MemoryManager()
  {

    objectMap = new HashMap<String, Object>();
  }

  public static MemoryManager getInstance()
  {

    if( instance == null )
    {

      synchronized( MemoryManager.class )
      {

        if( instance == null )
        {

          instance = new MemoryManager();
        }
      }
    }

    return instance;
  }

  public void init()
  {

    if( !init )
    {

      Thread initThread = new Thread()
      {

        @Override
        public void run()
        {

          getObjectInner( GekFrame.class );
          getObjectInner( CommonDefenceGWFrame.class );
          getObjectInner( DefenceGraduateWorkListFrame.class );
          getObjectInner( GraduateWorkListFrame.class );
        }
      };
      initThread.start();

      init = true;
    }

  }

  public Object getObjectInner( Class clazz )
  {

    if( clazz == null )
    {

      return null;
    }

    Object objectInstance = objectMap.get( clazz.toString() );

    if( objectInstance == null )
    {

      try
      {

        synchronized( syncObject )
        {

          objectInstance = objectMap.get( clazz.toString() );

          if( objectInstance == null )
          {

            objectInstance = clazz.newInstance();

            objectMap.put( clazz.toString(), objectInstance );
          }
        }
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }

    return objectInstance;
  }

  public static Object getObject( Class clazz )
  {

    return getInstance().getObjectInner( clazz );
  }

  public Object getObjectInner( Class clazz, Object... args )
  {

    if( clazz == null )
    {

      return null;
    }

    Object objectInstance = objectMap.get( getKey( clazz, args ) );

    if( objectInstance == null )
    {

      try
      {

        Class[] classes = new Class[args.length];

        for( int i = 0; i < args.length; i++ )
        {
          Object arg = args[i];

          classes[i] = arg.getClass();
        }

        Constructor constructor = clazz.getConstructor( classes );
        objectInstance = constructor.newInstance( args );

        objectMap.put( getKey( clazz, args ), objectInstance );

      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }

    return objectInstance;
  }

  public static Object getObject( Class clazz, Object... args )
  {

    return getInstance().getObjectInner( clazz, args );
  }

  private String getKey( Class clazz, Object... args )
  {

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append( clazz.toString() );

    for( Object arg : args )
    {

      stringBuilder.append( arg );
    }

    return stringBuilder.toString();
  }
}
