package net.treetechnologies.entities.masters;

import javax.xml.bind.annotation.*;
import java.util.Date;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="lifecycleOperations")
@XmlRootElement(name="lifecycleOperations")
@XmlTransient
public class LifecycleOperationsId implements java.io.Serializable 
{
    @XmlElement(name="lifecycleId",required=true)
    public Long getLifeCycleId()
    {
        return this.lifeCycleId;
    }
    
    public void setLifeCycleId(Long arg)
    {
        this.lifeCycleId = arg;
    }
    
    @XmlElement(name="operation",required=true)
    public String getOperation()
    {
        return this.operation;
    }
    
    public void setOperation(String arg)
    {
        this.operation = arg;
    }
    
    @XmlElement(name="sourceStatus",required=true)
    public Long getSourceStatus()
    {
        return this.sourceStatus;
    }
    
    public void setSourceStatus(Long arg)
    {
        this.sourceStatus = arg;
    }
    
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lifeCycleId == null) ? 0 : lifeCycleId.hashCode());
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result + ((sourceStatus == null) ? 0 : sourceStatus.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof LifecycleOperations)) 
            return false;
            
        LifecycleOperations other = (LifecycleOperations) obj;
        
        if (lifeCycleId == null) 
        {
            if (other.lifeCycleId != null) 
            {
                return false;
            }
        } 
        else if (!lifeCycleId.equals(other.lifeCycleId)) 
        {
            return false;
        }
        if (operation == null) 
        {
            if (other.operation != null) 
            {
                return false;
            }
        } 
        else if (!operation.equals(other.operation)) 
        {
            return false;
        }
        if (sourceStatus == null) 
        {
            if (other.sourceStatus != null) 
            {
                return false;
            }
        } 
        else if (!sourceStatus.equals(other.sourceStatus)) 
        {
            return false;
        }
        return true;
    }

    protected Long lifeCycleId; 
    
    protected String operation; 
    
    protected Long sourceStatus; 
    
}     
