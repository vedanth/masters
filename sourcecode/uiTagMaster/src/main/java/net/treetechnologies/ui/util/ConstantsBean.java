package net.treetechnologies.ui.util;

import java.io.Serializable;
/**  
* <b>Purpose:</b><br>
*  To Get the Default Values<br><br>
*
* <b>DesignReference:</b><br>
* <br><br>
*
* <b>CopyRights:</b><br>
* Tree Technologies 2012<br><br>
*
* <b>RevisionHistory:</b>
* <pre><b>
* Sl No   Modified Date        Author</b>
* ==============================================
* 1        31-07-2012          VV Nagesh 
*   -- Base Release   
* </pre><br>
*/
public class ConstantsBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String defaultEndDate;
    private String defaultDataType;
    private String defaultFactFiledType;
    private String defaultPolicyType;
    private String defaultTagType;
    private String defaultStatus;
    private String defaultTenant;
    private String defaultTemplate;
    private String activeStatus;
    private String retireStatus;
    private String factFiledType;
    private String enumerationDataType;
    private String enumerationFactFieldType;
    private String displayDateFormat;

    public String getDisplayDateFormat()
    {
        return this.displayDateFormat;
    }

    public void setDisplayDateFormat(String displayDateFormat)
    {
        this.displayDateFormat = displayDateFormat;
    }

    public String getDefaultDataType()
    {
        return this.defaultDataType;
    }

    public void setDefaultDataType(String defaultDataType)
    {
        this.defaultDataType = defaultDataType;
    }

    public String getDefaultFactFiledType()
    {
        return this.defaultFactFiledType;
    }

    public void setDefaultFactFiledType(String defaultFactFiledType)
    {
        this.defaultFactFiledType = defaultFactFiledType;
    }

    public String getDefaultPolicyType()
    {
        return this.defaultPolicyType;
    }

    public void setDefaultPolicyType(String defaultPolicyType)
    {
        this.defaultPolicyType = defaultPolicyType;
    }

    public String getDefaultTagType()
    {
        return this.defaultTagType;
    }

    public void setDefaultTagType(String defaultTagType)
    {
        this.defaultTagType = defaultTagType;
    }

    public String getDefaultStatus()
    {
        return this.defaultStatus;
    }

    public void setDefaultStatus(String defaultStatus)
    {
        this.defaultStatus = defaultStatus;
    }

    public String getDefaultTenant()
    {
        return this.defaultTenant;
    }

    public void setDefaultTenant(String defaultTenant)
    {
        this.defaultTenant = defaultTenant;
    }

    public String getDefaultTemplate()
    {
        return this.defaultTemplate;
    }

    public void setDefaultTemplate(String defaultTemplate)
    {
        this.defaultTemplate = defaultTemplate;
    }

    public String getActiveStatus()
    {
        return this.activeStatus;
    }

    public void setActiveStatus(String activeStatus)
    {
        this.activeStatus = activeStatus;
    }

    public String getRetireStatus()
    {
        return this.retireStatus;
    }

    public void setRetireStatus(String retireStatus)
    {
        this.retireStatus = retireStatus;
    }

    public String getFactFiledType()
    {
        return this.factFiledType;
    }

    public void setFactFiledType(String factFiledType)
    {
        this.factFiledType = factFiledType;
    }

    public String getEnumerationDataType()
    {
        return this.enumerationDataType;
    }

    public void setEnumerationDataType(String enumerationDataType)
    {
        this.enumerationDataType = enumerationDataType;
    }

    public String getEnumerationFactFieldType()
    {
        return this.enumerationFactFieldType;
    }

    public void setEnumerationFactFieldType(String enumerationFactFieldType)
    {
        this.enumerationFactFieldType = enumerationFactFieldType;
    }

    public String getDefaultEndDate()
    {
        return this.defaultEndDate;
    }

    public void setDefaultEndDate(String defaultEndDate)
    {
        this.defaultEndDate = defaultEndDate;
    }
}
