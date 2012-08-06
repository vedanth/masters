package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="lifecycleOperations")
@XmlRootElement(name="lifecycleOperations")
@Entity(name="net.treetechnologies.entities.masters.LifecycleOperations")
@Table(name="sd_lifecycle_operations")
@Access(AccessType.FIELD)
@IdClass(LifecycleOperationsId.class)
public class LifecycleOperations implements java.io.Serializable 
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
    @Column(name="operation_v")
    protected String operation; 
    
    @Audited
    
    @Id
    @Column(name="source_status_n")
    protected Long sourceStatus; 
    
    @Audited
    
    @Basic
    @Column(name="allow_n")
    protected Long allow; 
    
}     
