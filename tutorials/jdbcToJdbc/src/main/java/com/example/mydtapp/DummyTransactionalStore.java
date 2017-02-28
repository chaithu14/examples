package com.example.mydtapp;

import com.datatorrent.lib.db.jdbc.JdbcTransactionalStore;

public class DummyTransactionalStore extends JdbcTransactionalStore
{
  @Override
  public void beginTransaction()
  {

  }

  @Override
  public void commitTransaction()
  {

  }

  @Override
  public void rollbackTransaction()
  {

  }

  @Override
  public boolean isInTransaction()
  {
    return false;
  }

  @Override
  public long getCommittedWindowId(String appId, int operatorId)
  {
    return 0;
  }

  @Override
  public void storeCommittedWindowId(String appId, int operatorId, long windowId)
  {

  }

  @Override
  public void removeCommittedWindowId(String appId, int operatorId)
  {

  }
}
