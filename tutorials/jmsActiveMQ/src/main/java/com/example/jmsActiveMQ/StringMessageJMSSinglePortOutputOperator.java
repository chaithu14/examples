package com.example.jmsActiveMQ;

import javax.jms.JMSException;
import javax.jms.Message;

import com.datatorrent.lib.io.jms.AbstractJMSSinglePortOutputOperator;

/**
 * Created by lakshmi on 10/18/17.
 */
public class StringMessageJMSSinglePortOutputOperator extends AbstractJMSSinglePortOutputOperator
{
  @Override
  protected Message createMessage(Object tuple)
  {
    Message msg;

    try {
      msg = getSession().createTextMessage(tuple.toString());
    } catch (JMSException ex) {
      throw new RuntimeException(ex);
    }

    return msg;
  }
}
