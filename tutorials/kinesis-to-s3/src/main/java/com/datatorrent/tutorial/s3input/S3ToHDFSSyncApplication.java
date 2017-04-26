package com.datatorrent.tutorial.s3input;

import org.apache.apex.malhar.lib.fs.s3.S3TupleOutputModule;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;

/**
 * Simple application illustrating file copy from S3
 */
@ApplicationAnnotation(name="Gen-to-s3-App")
public class S3ToHDFSSyncApplication implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    POJOEventGenerator inputModule = dag.addOperator("Generate", new POJOEventGenerator());
    S3TupleOutputModule.S3BytesOutputModule outputModule = dag.addModule("S3OutputModule", new S3TupleOutputModule.S3BytesOutputModule());

    dag.addStream("KinesisToS3", inputModule.outputBytes, outputModule.input);
  }
}
