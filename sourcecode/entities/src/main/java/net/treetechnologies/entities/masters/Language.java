package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="languageType")
@XmlRootElement(name="language")
@Entity(name="net.treetechnologies.entities.masters.Language")
@Table(name="sd_language")
@Access(AccessType.FIELD)
public class Language implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,Language>
    {
        @Override
        public Long marshal(Language language) throws Exception 
        {
            return language.getLanguageId();
        }

        @Override
        public Language unmarshal(Long arg) throws Exception 
        {
            Language language = new Language();
            language.setLanguageId(arg);
            return language;
        }
    
    }

    @XmlElement(name="languageId", required=true)
    public Long getLanguageId()
    {
        return this.languageId;
    }
    
    public void setLanguageId(Long arg)
    {
        this.languageId = arg;
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
    
    @XmlJavaTypeAdapter(value=Status.IDAdapter.class)
    @XmlElement(name="statusId", required=true)
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
    
    @Audited
    
    @Id
    @Column(length=20, name="language_id_v")
    protected Long languageId; 
    
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
    @JoinColumn(name="status_n", nullable=false)
    protected net.treetechnologies.entities.masters.Status status; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tenant_id_n", nullable=false)
    protected net.treetechnologies.entities.masters.Tenant tenant; 
    
}     
