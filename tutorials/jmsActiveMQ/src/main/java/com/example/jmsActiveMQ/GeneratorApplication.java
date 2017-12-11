/**
 * Put your copyright and license info here.
 */
package com.example.jmsActiveMQ;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.lib.io.ConsoleOutputOperator;

@ApplicationAnnotation(name = "DataGenerator")
public class GeneratorApplication implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    RandomNumberGenerator randomGenerator = dag.addOperator("randomGenerator", RandomNumberGenerator.class);
    randomGenerator.setNumTuples(100);

    StringMessageJMSSinglePortOutputOperator jmsOutputOperator = dag.addOperator("jmsOutputOperator", StringMessageJMSSinglePortOutputOperator.class);
    
    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("randomData", randomGenerator.out, jmsOutputOperator.inputPort, cons.input).setLocality(DAG.Locality.THREAD_LOCAL);
  }
}
