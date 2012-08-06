package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="lifecycleStateTransition")
@XmlRootElement(name="lifecycleStateTransition")
@Entity(name="net.treetechnologies.entities.masters.LifecycleStateTransition")
@Table(name="sd_lifecycle_state_transition")
@Access(AccessType.FIELD)
@IdClass(LifecycleStateTransitionId.class)
public class LifecycleStateTransition implements java.io.Serializable 
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
    
    @XmlElement(name="allow",required=true)
    public Long getAllow()
    {
        return this.allow;
    }
    
    public void setAllow(Long arg)
    {
        this.allow = arg;
    }
    
    @Audited
    
    @Id
    @Column(name="lifecycle_id_n")
    protected Long lifeCycleId; 
    
    @Audited
    
    @Id
    @Column(name="source_status_n")
    protected Long sourceStatus; 
    
    @Audited
    
    @Id
    @Column(name="target_status_n")
    protected Long targetStatus; 
    
    @Audited
    
    @Basic
    @Column(name="allow_n", nullable=false)
    protected Long allow; 
    
}     
