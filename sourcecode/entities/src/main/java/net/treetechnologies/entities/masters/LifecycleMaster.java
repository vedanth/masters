package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="lifecycleMaster")
@XmlRootElement(name="lifecycleMaster")
@Entity(name="net.treetechnologies.entities.masters.LifecycleMaster")
@Table(name="sd_lifecycle")
@Access(AccessType.FIELD)
public class LifecycleMaster implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,LifecycleMaster>
    {
        @Override
        public Long marshal(LifecycleMaster lifecycleMaster) throws Exception 
        {
            return lifecycleMaster.getLifecycleId();
        }

        @Override
        public LifecycleMaster unmarshal(Long arg) throws Exception 
        {
            LifecycleMaster lifecycleMaster = new LifecycleMaster();
            lifecycleMaster.setLifecycleId(arg);
            return lifecycleMaster;
        }
    
    }

    @XmlElement(name="lifecycleId",required=true)
    public Long getLifecycleId()
    {
        return this.lifecycleId;
    }
    
    public void setLifecycleId(Long arg)
    {
        this.lifecycleId = arg;
    }
    
    @XmlElement(name="name",required=true)
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String arg)
    {
        this.name = arg;
    }
    
    @XmlElement(name="startDateTime",required=true)
    public Date getStartDateTime()
    {
        return this.startDateTime;
    }
    
    public void setStartDateTime(Date arg)
    {
        this.startDateTime = arg;
    }
    
    @XmlElement(name="endDateTime",required=true)
    public Date getEndDateTime()
    {
        return this.endDateTime;
    }
    
    public void setEndDateTime(Date arg)
    {
        this.endDateTime = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @XmlJavaTypeAdapter(value=Tenant.IDAdapter.class)
    @XmlElement(name="tenantId",required=true)
    public net.treetechnologies.entities.masters.Tenant getTenant()
    {
        return this.tenant;
    }
    
    public void setTenant(net.treetechnologies.entities.masters.Tenant arg)
    {
        if(this.tenant == arg)
            return;
            
        
        this.tenant = arg;

    }
    
    @Audited
    
    @Id
    @Column(name="lifecycle_id_n")
    protected Long lifecycleId; 
    
    @Audited
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 
    
    @Audited
    
    @Basic
    @Column(name="start_date_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date startDateTime; 
    
    @Audited
    
    @Basic
    @Column(name="end_date_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date endDateTime; 
    
    @Audited
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tenant_id_n", nullable=false)
    protected net.treetechnologies.entities.masters.Tenant tenant; 
    
}     
