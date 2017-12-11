/**
 * Put your copyright and license info here.
 */
package com.example.jmsActiveMQ;

import java.util.Random;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

/**
 * This is a simple operator that emits random number.
 */
public class RandomNumberGenerator extends BaseOperator implements InputOperator
{
  private int numTuples = 100;
  private transient int count = 0;
  private transient int currentVal = 0;

  String data[] = {"PROCESS_TIMESTAMP::2017-08-17 20:37:17||SOURCE::POS_TIBCO||SCHEMA::HDFS||TABLE_NAME::POS_LOG||MATERIAL_CODE::610214645098||PLANT_CODE::9181||START_DATE::2017-08-17 20:33:33||QUANTITY::1||RETURN_FLAG::0||EXTENDED_AMOUNT::0||TRANSACTION_ID::7354||LINE_NUMBER::4",
  "PROCESS_TIMESTAMP::2017-08-17 20:37:17||SOURCE::POS_TIBCO||SCHEMA::HDFS||TABLE_NAME::POS_LOG||MATERIAL_CODE::610214652218||PLANT_CODE::9181||START_DATE::2017-08-17 20:33:33||QUANTITY::1||RETURN_FLAG::0||EXTENDED_AMOUNT::0||TRANSACTION_ID::7354||LINE_NUMBER::3",
  "PROCESS_TIMESTAMP::2017-08-17 20:37:17||SOURCE::POS_TIBCO||SCHEMA::HDFS||TABLE_NAME::POS_LOG||MATERIAL_CODE::TM6762||PLANT_CODE::9181||START_DATE::2017-08-17 20:33:33||QUANTITY::1||RETURN_FLAG::0||EXTENDED_AMOUNT::0||TRANSACTION_ID::7354||LINE_NUMBER::8"};

  public final transient DefaultOutputPort<String> out = new DefaultOutputPort<>();

  @Override
  public void beginWindow(long windowId)
  {
    count = 0;
  }

  @Override
  public void emitTuples()
  { 
    while (count++ < numTuples) {
      //out.emit(data[new Random().nextInt(data.length)]);
      out.emit(Integer.toString(currentVal++));
    }
  }

  public int getNumTuples()
  {
    return numTuples;
  }

  /**
   * Sets the number of tuples to be emitted every window.
   * @param numTuples number of tuples
   */
  public void setNumTuples(int numTuples)
  {
    this.numTuples = numTuples;
  }
}
