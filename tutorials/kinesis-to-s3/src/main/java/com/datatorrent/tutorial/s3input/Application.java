package com.datatorrent.tutorial.s3input;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.contrib.kinesis.KinesisStringOutputOperator;

@ApplicationAnnotation(name="WriteToKinesisApp")
public class Application implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration configuration)
  {
    POJOEventGenerator inputOperator = dag.addOperator("Generate", new POJOEventGenerator());
    KinesisStringOutputOperator outputOperator = dag.addOperator("KinesisOutput", new KinesisStringOutputOperator());

    dag.addStream("KinesisToS3", inputOperator.outputString, outputOperator.inputPort);
  }
}
