package com.example.mydtapp;

public class PojoEvent
{

  private int accountNumber;
  private String name;
  private int amount;

  public int getAccountNumber()
  {
    return accountNumber;
  }

  public void setAccountNumber(int accountNumber)
  {
    this.accountNumber = accountNumber;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getAmount()
  {
    return amount;
  }

  public void setAmount(int amount)
  {
    this.amount = amount;
  }

  @Override
  public String toString()
  {
    return accountNumber + "|" + name + "|" + amount;
  }
}
