package net.treetechnologies.entities.masters;

import javax.xml.bind.annotation.*;
import java.util.Date;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="Enumeration")
@XmlRootElement(name="Enumeration")
@XmlTransient
public class EnumerationId implements java.io.Serializable 
{
    @XmlElement(name="enumerationId", required=true)
    public Long getEnumerationId()
    {
        return this.enumerationId;
    }
    
    public void setEnumerationId(Long arg)
    {
        this.enumerationId = arg;
    }
    
    @XmlElement(name="enumerationTypeId", required=true)
    public Long getEnumerationTypeId()
    {
        return this.enumerationTypeId;
    }
    
    public void setEnumerationTypeId(Long arg)
    {
        this.enumerationTypeId = arg;
    }
    
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((enumerationId == null) ? 0 : enumerationId.hashCode());
        result = prime * result + ((enumerationTypeId == null) ? 0 : enumerationTypeId.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Enumeration)) 
            return false;
            
        Enumeration other = (Enumeration) obj;
        
        if (enumerationId == null) 
        {
            if (other.enumerationId != null) 
            {
                return false;
            }
        } 
        else if (!enumerationId.equals(other.enumerationId)) 
        {
            return false;
        }
        if (enumerationTypeId == null) 
        {
            if (other.enumerationTypeId != null) 
            {
                return false;
            }
        } 
        else if (!enumerationTypeId.equals(other.enumerationTypeId)) 
        {
            return false;
        }
        return true;
    }

    protected Long enumerationId; 
    
    protected Long enumerationTypeId; 
    
}     
