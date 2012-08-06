package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="entityType")
@XmlRootElement(name="entityType")
@Entity(name="net.treetechnologies.entities.masters.EntityType")
@Table(name="sd_entity_type")
@Access(AccessType.FIELD)
public class EntityType implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,EntityType>
    {
        @Override
        public Long marshal(EntityType entityType) throws Exception 
        {
            return entityType.getEntityTypeId();
        }

        @Override
        public EntityType unmarshal(Long arg) throws Exception 
        {
            EntityType entityType = new EntityType();
            entityType.setEntityTypeId(arg);
            return entityType;
        }
    
    }

    @XmlElement(name="entityTypeId",required=true)
    public Long getEntityTypeId()
    {
        return this.entityTypeId;
    }
    
    public void setEntityTypeId(Long arg)
    {
        this.entityTypeId = arg;
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
    public Date getStartDate()
    {
        return this.startDate;
    }
    
    public void setStartDate(Date arg)
    {
        this.startDate = arg;
    }
    
    @XmlElement(name="endDateTime",required=true)
    public Date getEndDate()
    {
        return this.endDate;
    }
    
    public void setEndDate(Date arg)
    {
        this.endDate = arg;
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
    
    @XmlJavaTypeAdapter(value=Status.IDAdapter.class)
    @XmlElement(name="statusId",required=true)
    public net.treetechnologies.entities.masters.Status getStatus()
    {
        return this.status;
    }
    
    public void setStatus(net.treetechnologies.entities.masters.Status arg)
    {
        if(this.status == arg)
            return;
            
        
        this.status = arg;

    }
    
    @Audited
    
    @Id
    @Column(name="entity_type_n")
    protected Long entityTypeId; 
    
    @Audited
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 
    
    @Audited
    
    @Basic
    @Column(name="start_date_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date startDate; 
    
    @Audited
    
    @Basic
    @Column(name="end_date_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date endDate; 
    
    @XmlElement(name="lastUpdatedTime", required=true)
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tenant_id_n", nullable=false)
    protected net.treetechnologies.entities.masters.Tenant tenant; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected net.treetechnologies.entities.masters.Status status; 
    
}     
