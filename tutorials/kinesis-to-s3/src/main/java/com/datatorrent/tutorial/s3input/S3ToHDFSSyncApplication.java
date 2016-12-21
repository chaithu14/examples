package com.datatorrent.tutorial.s3input;

import org.apache.apex.malhar.lib.fs.s3.S3BytesOutputModule;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.lib.io.fs.HDFSFileCopyModule;
import com.datatorrent.lib.io.fs.S3InputModule;

/**
 * Simple application illustrating file copy from S3
 */
@ApplicationAnnotation(name="kinesis-to-s3-App")
public class S3ToHDFSSyncApplication implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    KinesisByteArrayInputOperator inputModule = dag.addOperator("KinesisInput", new KinesisByteArrayInputOperator());
    S3BytesOutputModule outputModule = dag.addModule("S3OutputModule", new S3BytesOutputModule());

    dag.addStream("KinesisToS3", inputModule.outputPort, outputModule.input);
  }
}
