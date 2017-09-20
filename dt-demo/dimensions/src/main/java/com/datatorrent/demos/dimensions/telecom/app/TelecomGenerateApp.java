package com.datatorrent.demos.dimensions.telecom.app;

import org.apache.apex.malhar.lib.fs.GenericFileOutputOperator;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.demos.dimensions.telecom.operator.CallDetailRecordGenerateOperator;
import com.datatorrent.demos.dimensions.telecom.operator.CustomerServiceGenerateOperator;

@ApplicationAnnotation(name = "GenerateTelecomDataApp")
public class TelecomGenerateApp implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // CDR generator
    CallDetailRecordGenerateOperator cdrGenerator = dag.addOperator("IngestCDRfromSolace", new CallDetailRecordGenerateOperator());
    GenericFileOutputOperator.BytesFileOutputOperator cdroutputOperator = dag.addOperator("CDROutput", new GenericFileOutputOperator.BytesFileOutputOperator());

    dag.addStream("IngestToOutput", cdrGenerator.bytesOutputPort, cdroutputOperator.input);

    // Customer service generator
    CustomerServiceGenerateOperator customerServiceGenerator = dag.addOperator("IngestCustomerServiceData", new CustomerServiceGenerateOperator());
    GenericFileOutputOperator.BytesFileOutputOperator custoutputOperator = dag.addOperator("CustOutput", new GenericFileOutputOperator.BytesFileOutputOperator());

    dag.addStream("IngestCustToOutput", customerServiceGenerator.bytesPort, custoutputOperator.input);
  }
}
