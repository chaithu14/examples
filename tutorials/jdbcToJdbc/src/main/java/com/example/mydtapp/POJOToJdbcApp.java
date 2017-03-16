package com.example.mydtapp;

import java.util.List;

import org.apache.apex.malhar.lib.db.redshift.RedshiftOutputModule;
import org.apache.hadoop.conf.Configuration;

import com.google.common.collect.Lists;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.lib.db.jdbc.JdbcFieldInfo;
import com.datatorrent.lib.util.FieldInfo;

import static com.datatorrent.api.Context.OperatorContext.TIMEOUT_WINDOW_COUNT;

@ApplicationAnnotation(name="POJOToJdbcApp")
public class POJOToJdbcApp implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    POJOEventGenerator input = dag.addOperator("Generator", new POJOEventGenerator());

    RedshiftOutputModule jdbcOutputOperator = dag.addModule("JdbcOutput", new RedshiftOutputModule());
    //JdbcPOJOInsertOutputOperator jdbcOutputOperator = dag.addOperator("JdbcOutput", new JdbcPOJOInsertOutputOperator());
    /*JdbcTransactionalStore outputStore = new JdbcTransactionalStore();
    outputStore.setConnectionProperties("ssl:true");
    jdbcOutputOperator.setStore(outputStore);*/

    /**
     * The class given below can be updated to the user defined class based on
     * input table schema The addField infos method needs to be updated
     * accordingly This line can be commented and class can be set from the
     * properties file
     */
    //dag.setInputPortAttribute(jdbcOutputOperator.input, Context.PortContext.TUPLE_CLASS, PojoEvent.class);

    dag.addStream("POJO's", input.outputBytes, jdbcOutputOperator.input).setLocality(DAG.Locality.CONTAINER_LOCAL);
    dag.setAttribute(input, TIMEOUT_WINDOW_COUNT, 6000);
  }

  private List<JdbcFieldInfo> addJdbcFieldInfos()
  {
    List<com.datatorrent.lib.db.jdbc.JdbcFieldInfo> fieldInfos = Lists.newArrayList();
    fieldInfos.add(new com.datatorrent.lib.db.jdbc.JdbcFieldInfo("ACCOUNT_NO", "accountNumber", FieldInfo.SupportType.INTEGER,0));
    fieldInfos.add(new com.datatorrent.lib.db.jdbc.JdbcFieldInfo("NAME", "name", FieldInfo.SupportType.STRING,0));
    fieldInfos.add(new com.datatorrent.lib.db.jdbc.JdbcFieldInfo("AMOUNT", "amount", FieldInfo.SupportType.INTEGER,0));
    return fieldInfos;
  }
}
