package com.datatorrent.tutorial.s3input;

import java.util.Random;

import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

/**
 * Generates Subscriber Data:
 *    A Party Phone
 *    A Party IMEI
 *    A Party IMSI
 *    Circle Id
 *
 * @author bhupesh
 */
public class POJOEventGenerator extends BaseOperator implements InputOperator
{

  public static int LIMIT = 100;
  private static int MAXACCOUNT = 100000;
  private static int MAXAMOUNT = 1000000;

  private Random r;
  private int count = 0;

  private int tupleBlast = LIMIT;
  public final transient DefaultOutputPort<Object> output = new DefaultOutputPort<>();
  public final transient DefaultOutputPort<String> outputString = new DefaultOutputPort<>();
  public final transient DefaultOutputPort<byte[]> outputBytes = new DefaultOutputPort<>();

  @Override
  public void setup(Context.OperatorContext context)
  {
    r = new Random(System.currentTimeMillis());
  }

  @Override
  public void beginWindow(long windowId) {
    super.beginWindow(windowId);
    count = 0;
  }

  @Override
  public void emitTuples()
  {
    if(count++ < tupleBlast) {
      PojoEvent record = (PojoEvent)getRecord();
      if (output.isConnected()) {
        output.emit(record);
      }
      if (outputBytes.isConnected()) {
        outputBytes.emit(record.toString().getBytes());
      }
      if (outputString.isConnected()) {
        outputString.emit(record.toString());
      }
    }
  }

  private Object getRecord()
  {
    PojoEvent record = new PojoEvent();
    record.setAccountNumber(r.nextInt(MAXACCOUNT));
    record.setName(Long.toHexString(Double.doubleToLongBits(Math.random())));
    record.setAmount(r.nextInt(MAXAMOUNT));
    return record;
  }

  public int getTupleBlast()
  {
    return tupleBlast;
  }

  public void setTupleBlast(int tupleBlast)
  {
    this.tupleBlast = tupleBlast;
  }
}