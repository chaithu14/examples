/**
 * Copyright (c) 2015 DataTorrent, Inc.
 * All rights reserved.
 */
package com.datatorrent.demos.dimensions.telecom.model;

import java.util.Calendar;

public class CustomerService
{
  public static final String delimiter = ",";

  public static enum IssueType
  {
    DeviceUpgrade, CallQuality, DeviceQuality, Billing, NetworkCoverage, Roaming
  }

  public String imsi;
  public String isdn;
  public String imei;
  public int totalDuration;
  public int wait;
  public String zipCode;
  public String issueType;
  public boolean satisfied;
  public long time = Calendar.getInstance().getTimeInMillis();

  public CustomerService()
  {
    imsi = "";
    isdn = "";
    imei = "";
    totalDuration = 0;
    wait = 0;
    zipCode = "";
    issueType = null;
    satisfied = false;
  }

  public CustomerService(String imsi, String isdn, String imei, int totalDuration, int wait, String zipCode,
      IssueType issueType, boolean satisfied)
  {
    this.imsi = imsi;
    this.isdn = isdn;
    this.imei = imei;
    this.totalDuration = totalDuration;
    this.wait = wait;
    this.zipCode = zipCode;
    this.issueType = issueType.toString();
    this.satisfied = satisfied;
  }

  public CustomerService(CustomerService other)
  {
    this(other.imsi, other.isdn, other.imei, other.totalDuration, other.wait, other.zipCode, IssueType.valueOf(other.issueType),
        other.satisfied);
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(imsi).append(delimiter);
    sb.append(isdn).append(delimiter);
    sb.append(imei).append(delimiter);
    sb.append(totalDuration).append(delimiter);
    sb.append(wait).append(delimiter);
    sb.append(zipCode).append(delimiter);
    sb.append(issueType).append(delimiter);
    sb.append(satisfied).append(delimiter);
    sb.append(time);

    return sb.toString();
  }

  public int getServiceCallCount()
  {
    return 1;
  }

  public int getWait()
  {
    return wait;
  }

  public String getZipCode()
  {
    return zipCode;
  }

  public long getTime()
  {
    return time;
  }

  public String getIssueType()
  {
    return issueType;
  }

  //return 100 is satisfied, else 0
  public long getSatisfaction()
  {
    return satisfied ? 100 : 0;
  }

  /**
   * use 2 letter of zip as the region
   * 
   * @return
   */
  public String getRegionZip2()
  {
    return getZipSubString(2);
  }

  /**
   * use 2 letter of zip as the region
   * 
   * @return
   */
  public String getRegionZip3()
  {
    return getZipSubString(3);
  }

  protected String getZipSubString(int length)
  {
    if (length > zipCode.length()) {
      throw new IllegalArgumentException(
          "The length of the zipCode ( " + zipCode.length() + ") is less than begin length: (" + length + ").");
    }
    return zipCode.substring(0, length);
  }

  public void setImsi(String imsi)
  {
    this.imsi = imsi;
  }

  public void setIsdn(String isdn)
  {
    this.isdn = isdn;
  }

  public void setImei(String imei)
  {
    this.imei = imei;
  }

  public void setTotalDuration(int totalDuration)
  {
    this.totalDuration = totalDuration;
  }

  public void setWait(int wait)
  {
    this.wait = wait;
  }

  public void setZipCode(String zipCode)
  {
    this.zipCode = zipCode;
  }

  public void setIssueType(String issueType)
  {
    this.issueType = issueType;
  }

  public void setSatisfied(boolean satisfied)
  {
    this.satisfied = satisfied;
  }

  public void setTime(long time)
  {
    this.time = time;
  }

  public String getImsi()
  {
    return imsi;
  }

  public String getIsdn()
  {
    return isdn;
  }

  public String getImei()
  {
    return imei;
  }

  public boolean isSatisfied()
  {
    return satisfied;
  }
}
