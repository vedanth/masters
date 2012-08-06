package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="status")
@XmlRootElement(name="status")
@Entity(name="net.treetechnologies.entities.masters.Status")
@Table(name="sd_status")
@Access(AccessType.FIELD)
public class Status implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,Status>
    {
        @Override
        public Long marshal(Status status) throws Exception 
        {
            return status.getStatusId();
        }

        @Override
        public Status unmarshal(Long arg) throws Exception 
        {
            Status status = new Status();
            status.setStatusId(arg);
            return status;
        }
    
    }

    @XmlElement(name="statusId", required=true)
    public Long getStatusId()
    {
        return this.statusId;
    }
    
    public void setStatusId(Long arg)
    {
        this.statusId = arg;
    }
    
    @XmlElement(name="name", required=true)
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String arg)
    {
        this.name = arg;
    }
    
    @XmlElement(name="startDateTime", required=true)
    public Date getStartDate()
    {
        return this.startDate;
    }
    
    public void setStartDate(Date arg)
    {
        this.startDate = arg;
    }
    
    @XmlElement(name="endDateTime", required=true)
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
    @XmlElement(name="tenantId", required=true)
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
    
    @Field(name="status_n",index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    @Audited
    
    @Id
    @Column(name="status_n")
    protected Long statusId; 
    
    @Field(name="name_v", index=Index.YES, analyze=Analyze.YES, store=Store.YES)
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
    
}     
