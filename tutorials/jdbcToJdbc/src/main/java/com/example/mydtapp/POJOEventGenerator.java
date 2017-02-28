package com.example.mydtapp;

/**
 * Copyright (c) 2016 DataTorrent, Inc.
 * All rights reserved.
 */

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

  private Random r;
  private int count = 0;

  public final transient DefaultOutputPort<Object> output = new DefaultOutputPort<>();
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
    if(count++ < LIMIT) {
      PojoEvent record = (PojoEvent)getRecord();
      if (output.isConnected()) {
        output.emit(record);
      }
      if (outputBytes.isConnected()) {
        outputBytes.emit(record.toString().getBytes());
      }
    }
  }

  private Object getRecord()
  {
    PojoEvent record = new PojoEvent();
    record.setAccountNumber(r.nextInt(1560));
    record.setName("xkalk");
    record.setAmount(r.nextInt(1200));
    return record;
  }

  private String getRandomNumber(int numDigits)
  {
    String retVal = (r.nextInt((9 - 1) + 1) + 1) + "";

    for (int i = 0; i < numDigits - 1; i++) {
      retVal += (r.nextInt((9 - 0) + 1) + 0);
    }
    return retVal;
  }

}

