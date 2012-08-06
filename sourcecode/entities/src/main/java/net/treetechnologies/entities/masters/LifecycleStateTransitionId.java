package net.treetechnologies.entities.masters;

import javax.xml.bind.annotation.*;
import java.util.Date;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="lifecycleStateTransition")
@XmlRootElement(name="lifecycleStateTransition")
@XmlTransient
public class LifecycleStateTransitionId implements java.io.Serializable 
{
    @XmlElement(name="lifeCycleId",required=true)
    public Long getLifeCycleId()
    {
        return this.lifeCycleId;
    }
    
    public void setLifeCycleId(Long arg)
    {
        this.lifeCycleId = arg;
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
    
    @XmlElement(name="targetStatus",required=true)
    public Long getTargetStatus()
    {
        return this.targetStatus;
    }
    
    public void setTargetStatus(Long arg)
    {
        this.targetStatus = arg;
    }
    
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lifeCycleId == null) ? 0 : lifeCycleId.hashCode());
        result = prime * result + ((sourceStatus == null) ? 0 : sourceStatus.hashCode());
        result = prime * result + ((targetStatus == null) ? 0 : targetStatus.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof LifecycleStateTransition)) 
            return false;
            
        LifecycleStateTransition other = (LifecycleStateTransition) obj;
        
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
        if (targetStatus == null) 
        {
            if (other.targetStatus != null) 
            {
                return false;
            }
        } 
        else if (!targetStatus.equals(other.targetStatus)) 
        {
            return false;
        }
        return true;
    }

    protected Long lifeCycleId; 
    
    protected Long sourceStatus; 
    
    protected Long targetStatus; 
    
}     
