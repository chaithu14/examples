package com.datatorrent.tutorial.s3input;

import java.math.BigInteger;
import java.util.Random;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.lib.testbench.RandomEventGenerator;

public class ByteArrayGenerator extends RandomEventGenerator
{
  private final Random random = new Random();
  private transient int count = 0;
  public final transient DefaultOutputPort<byte[]> outputPort = new DefaultOutputPort<byte[]>();

  @Override
  public void beginWindow(long windowId)
  {
    super.beginWindow(windowId);
    count = 0;
  }

  @Override
  public void emitTuples()
  {
    int range = getMaxvalue() - getMinvalue() + 1;
    while (count < getTuplesBlast()) {
      int rval = getMinvalue() + random.nextInt(range);
      if (outputPort.isConnected()) {
        outputPort.emit(BigInteger.valueOf(rval).toByteArray());
      }
      count++;
    }

    if (getTuplesBlastIntervalMillis() > 0) {
      try {
        Thread.sleep(getTuplesBlastIntervalMillis());
      } catch (InterruptedException e) {
        //fixme
      }
    }
  }
}
