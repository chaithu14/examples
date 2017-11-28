/**
 * Put your copyright and license info here.
 */
package com.example.jmsActiveMQ;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.lib.io.ConsoleOutputOperator;
import com.datatorrent.lib.io.jms.JMSStringInputOperator;

@ApplicationAnnotation(name="Amq2HDFS")
public class ActiveMQApplication implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    JMSStringInputOperator amqInput = dag.addOperator("amqIn", 
        new JMSStringInputOperator());
    
    //LineOutputOperator out = dag.addOperator("fileOut", new LineOutputOperator());
    ConsoleOutputOperator out = dag.addOperator("consoleOut", new ConsoleOutputOperator());

    dag.addStream("data", amqInput.output, out.input);
  }
}
