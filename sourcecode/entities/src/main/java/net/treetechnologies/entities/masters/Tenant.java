package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="tenant")
@XmlRootElement(name="tenant")
@Entity(name="net.treetechnologies.entities.masters.Tenant")
@Table(name="sd_tenant")
@Access(AccessType.FIELD)
public class Tenant implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,Tenant>
    {
        @Override
        public Long marshal(Tenant tenant) throws Exception 
        {
            return tenant.getTenantId();
        }

        @Override
        public Tenant unmarshal(Long arg) throws Exception 
        {
            Tenant tenant = new Tenant();
            tenant.setTenantId(arg);
            return tenant;
        }
    
    }

    @XmlElement(name="tenantId",required=true)
    public Long getTenantId()
    {
        return this.tenantId;
    }
    
    public void setTenantId(Long arg)
    {
        this.tenantId = arg;
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
    
    @Field(name="tenant_id_n",index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    @Audited
    
    @Id
    @Column(name="tenant_id_n")
    protected Long tenantId; 
    
    @Field(name="name_v", index=Index.YES, analyze=Analyze.YES, store=Store.YES)
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
    
    @XmlElement(name="lastUpdatedTime", required=true)
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
