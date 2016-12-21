package com.datatorrent.tutorial.s3input;

import java.nio.ByteBuffer;

import com.amazonaws.services.kinesis.model.Record;

import com.datatorrent.contrib.kinesis.AbstractKinesisInputOperator;

/**
 * Kinesis input adapter which consumes string data from Kinesis
 *
 * @since 2.0.0
 */
public class KinesisByteArrayInputOperator extends AbstractKinesisInputOperator<byte[]>
{
  /**
   * Implement abstract method of AbstractKinesisInputOperator
   */
  @Override
  public byte[] getTuple(Record record)
  {
    byte[] bytes = null;
    try {
      ByteBuffer bb = record.getData();
      bytes = new byte[bb.remaining() ];
      bb.get(bytes);
      return bytes;
    }
    catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}