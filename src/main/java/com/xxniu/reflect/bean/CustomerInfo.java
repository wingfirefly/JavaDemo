package com.xxniu.reflect.bean;

public class CustomerInfo
{
    private String customerId;
    private String customerName;
    private String certId;
    private String certType;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCertId()
    {
        return certId;
    }

    public void setCertId(String certId)
    {
        this.certId = certId;
    }

    public String getCertType()
    {
        return certType;
    }

    public void setCertType(String certType)
    {
        this.certType = certType;
    }
}
