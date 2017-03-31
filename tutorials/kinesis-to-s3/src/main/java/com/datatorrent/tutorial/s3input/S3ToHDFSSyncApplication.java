package com.datatorrent.tutorial.s3input;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.contrib.kinesis.KinesisStringInputOperator;
import com.datatorrent.lib.io.ConsoleOutputOperator;

/**
 * Simple application illustrating file copy from S3
 */
@ApplicationAnnotation(name="kinesis-to-s3-App")
public class S3ToHDFSSyncApplication implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    //ByteArrayGenerator inputModule = dag.addOperator("KinesisInput", new ByteArrayGenerator());
    KinesisStringInputOperator inputModule = dag.addOperator("KinesisInput", new KinesisStringInputOperator());
    //S3BytesOutputModule outputModule = dag.addModule("S3OutputModule", new S3BytesOutputModule());
    ConsoleOutputOperator outputModule = dag.addOperator("Console", new ConsoleOutputOperator());

    dag.addStream("KinesisToS3", inputModule.outputPort, outputModule.input);
  }
}
