package org.iit.dr.documents.word.write.graduate_work;

import org.iit.dr.documents.word.write.common.DocumentGenerator;

/**
 * DPTitleDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class DPProtokolDocumentGenerator extends DocumentGenerator
{

  @Override
  protected void init()
  {
    templateName = "Протокол.doc";
    fieldNames =
      new String[] {"Num", "adD", "adM", "adY", "stTH", "stTM", "endTH", "endTM", "student", "title", "pred", "secr",
        "cos0", "cos1", "cos2", "cos3", "cos4", "cos5", "cos6", "cos7", "cos8", "cos9", "manager", "managerData",
        "consults", "pc", "dc", "rMark", "reviewer", "question1", "question2", "question3", "question4", "question5",
        "question6", "qperson1", "qperson2", "qperson3", "qperson4", "qperson5", "qperson6", "answer", "trainingDop",
        "opinion", "mark", "cos_0", "cos_1", "cos_2", "cos_3", "cos_4", "mq", "training", "resultCommon", "outputMark",
        "managerResult", "studentShort", "compPresent", "distribution", "bestWork", "magistracy", "printedPublication",
        "faculty"};
    fieldValues =
      new String[] {"Num", "activeDate", "stTH", "stTM", "endTH", "endTM", "student", "title", "pred", "secr", "cos0",
        "cos1", "cos2", "cos3", "cos4", "cos5", "cos6", "cos7", "cos8", "cos9", "manager", "managerData", "consults",
        "pc", "dc", "rMark", "reviewer", "question1", "question2", "question3", "question4", "question5", "question6",
        "qperson1", "qperson2", "qperson3", "qperson4", "qperson5", "qperson6", "answer", "training", "opinion", "mark"};
  }

  public void generate( String filePath, Object[] fieldValues )
  {

    this.fieldValues = fieldValues;

    generateDocument( filePath );
  }
}