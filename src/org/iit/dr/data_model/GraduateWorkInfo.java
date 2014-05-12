package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * GraduateWorkInfo.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "graduateWorkInfo" )
public class GraduateWorkInfo
{

  @XmlElement( name = "pageCount" )
  String pageCount;

  @XmlElement( name = "graphicCount" )
  String graphicCount;

  @XmlElement( name = "reviewMark" )
  Integer reviewMark;

  @XmlElement( name = "question1" )
  String question1;

  @XmlElement( name = "question2" )
  String question2;

  @XmlElement( name = "question3" )
  String question3;

  @XmlElement( name = "question4" )
  String question4;

  @XmlElement( name = "question5" )
  String question5;

  @XmlElement( name = "question6" )
  String question6;

  @XmlElement( name = "question1AuthorId" )
  String question1AuthorId;

  @XmlElement( name = "question2AuthorId" )
  String question2AuthorId;

  @XmlElement( name = "question3AuthorId" )
  String question3AuthorId;

  @XmlElement( name = "question4AuthorId" )
  String question4AuthorId;

  @XmlElement( name = "question5AuthorId" )
  String question5AuthorId;

  @XmlElement( name = "question6AuthorId" )
  String question6AuthorId;

  @XmlElement( name = "answer" )
  String answer;

  @XmlElement( name = "training" )
  String training;

  @XmlElement( name = "markCountDistinction" )
  String markCountDistinction;

  @XmlElement( name = "markCountGood" )
  String markCountGood;

  @XmlElement( name = "markCountSatisfactory" )
  String markCountSatisfactory;

  @XmlElement( name = "mark" )
  Integer mark;

  @XmlElement( name = "opinion" )
  String opinion;

  @XmlElement( name = "outputMark" )
  String outputMark;

  @XmlElement( name = "resultCommon" )
  String resultCommon;

  @XmlElement( name = "timeCount" )
  String timeCount;

  @XmlElement( name = "managerResult" )
  String managerResult;

  @XmlElement( name = "compPresent" )
  Boolean compPresent;

  @XmlElement( name = "distribution" )
  Boolean distribution;

  @XmlElement( name = "bestWork" )
  Boolean bestWork;

  @XmlElement( name = "magistracy" )
  Boolean magistracy;

  @XmlElement( name = "printedPublication" )
  Boolean printedPublication;

  public String getPageCount()
  {
    return pageCount;
  }

  public void setPageCount( String pageCount )
  {
    this.pageCount = pageCount;
  }

  public String getGraphicCount()
  {
    return graphicCount;
  }

  public void setGraphicCount( String graphicCount )
  {
    this.graphicCount = graphicCount;
  }

  public Integer getReviewMark()
  {
    return reviewMark;
  }

  public void setReviewMark( Integer reviewMark )
  {
    this.reviewMark = reviewMark;
  }

  public String getQuestion1()
  {
    return question1;
  }

  public void setQuestion1( String question1 )
  {
    this.question1 = question1;
  }

  public String getQuestion2()
  {
    return question2;
  }

  public void setQuestion2( String question2 )
  {
    this.question2 = question2;
  }

  public String getQuestion3()
  {
    return question3;
  }

  public void setQuestion3( String question3 )
  {
    this.question3 = question3;
  }

  public String getQuestion4()
  {
    return question4;
  }

  public void setQuestion4( String question4 )
  {
    this.question4 = question4;
  }

  public String getQuestion5()
  {
    return question5;
  }

  public void setQuestion5( String question5 )
  {
    this.question5 = question5;
  }

  public String getQuestion6()
  {
    return question6;
  }

  public void setQuestion6( String question6 )
  {
    this.question6 = question6;
  }

  public String getQuestion1AuthorId()
  {
    return question1AuthorId;
  }

  public void setQuestion1AuthorId( String question1AuthorId )
  {
    this.question1AuthorId = question1AuthorId;
  }

  public String getQuestion2AuthorId()
  {
    return question2AuthorId;
  }

  public void setQuestion2AuthorId( String question2AuthorId )
  {
    this.question2AuthorId = question2AuthorId;
  }

  public String getQuestion3AuthorId()
  {
    return question3AuthorId;
  }

  public void setQuestion3AuthorId( String question3AuthorId )
  {
    this.question3AuthorId = question3AuthorId;
  }

  public String getQuestion4AuthorId()
  {
    return question4AuthorId;
  }

  public void setQuestion4AuthorId( String question4AuthorId )
  {
    this.question4AuthorId = question4AuthorId;
  }

  public String getQuestion5AuthorId()
  {
    return question5AuthorId;
  }

  public void setQuestion5AuthorId( String question5AuthorId )
  {
    this.question5AuthorId = question5AuthorId;
  }

  public String getQuestion6AuthorId()
  {
    return question6AuthorId;
  }

  public void setQuestion6AuthorId( String question6AuthorId )
  {
    this.question6AuthorId = question6AuthorId;
  }

  public String getAnswer()
  {
    return answer;
  }

  public void setAnswer( String answer )
  {
    this.answer = answer;
  }

  public String getTraining()
  {
    return training;
  }

  public void setTraining( String training )
  {
    this.training = training;
  }

  public Integer getMark()
  {
    return mark;
  }

  public void setMark( Integer mark )
  {
    this.mark = mark;
  }

  public String getOpinion()
  {
    return opinion;
  }

  public void setOpinion( String opinion )
  {
    this.opinion = opinion;
  }

  public String getMarkCountDistinction()
  {
    return markCountDistinction;
  }

  public void setMarkCountDistinction( String markCountDistinction )
  {
    this.markCountDistinction = markCountDistinction;
  }

  public String getMarkCountGood()
  {
    return markCountGood;
  }

  public void setMarkCountGood( String markCountGood )
  {
    this.markCountGood = markCountGood;
  }

  public String getMarkCountSatisfactory()
  {
    return markCountSatisfactory;
  }

  public void setMarkCountSatisfactory( String markCountSatisfactory )
  {
    this.markCountSatisfactory = markCountSatisfactory;
  }

  public String getOutputMark()
  {
    return outputMark;
  }

  public void setOutputMark( String outputMark )
  {
    this.outputMark = outputMark;
  }

  public String getResultCommon()
  {
    return resultCommon;
  }

  public void setResultCommon( String resultCommon )
  {
    this.resultCommon = resultCommon;
  }

  public String getTimeCount()
  {
    return timeCount;
  }

  public void setTimeCount( String timeCount )
  {
    this.timeCount = timeCount;
  }

  public String getManagerResult()
  {
    return managerResult;
  }

  public void setManagerResult( String managerResult )
  {
    this.managerResult = managerResult;
  }

  public Boolean isCompPresent()
  {
    if( compPresent == null )
      return false;
    return compPresent;
  }

  public Boolean getCompPresent()
  {
    return compPresent;
  }

  public void setCompPresent( Boolean compPresent )
  {
    this.compPresent = compPresent;
  }

  public Boolean isDistribution()
  {
    if( distribution == null )
      return false;

    return distribution;
  }

  public void setDistribution( Boolean distribution )
  {
    this.distribution = distribution;
  }

  public Boolean isBestWork()
  {
    if( bestWork == null )
      return false;

    return bestWork;
  }

  public void setBestWork( Boolean bestWork )
  {
    this.bestWork = bestWork;
  }

  public Boolean isMagistracy()
  {
    if( magistracy == null )
      return false;

    return magistracy;
  }

  public void setMagistracy( Boolean magistracy )
  {
    this.magistracy = magistracy;
  }

  public Boolean isPrintedPublication()
  {
    if( printedPublication == null )
      return false;

    return printedPublication;
  }

  public void setPrintedPublication( Boolean printedPublication )
  {
    this.printedPublication = printedPublication;
  }
}
