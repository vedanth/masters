package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="currencyType")
@XmlRootElement(name="currencyType")
@Entity(name="net.treetechnologies.entities.masters.Currency")
@Table(name="sd_currency")
@Access(AccessType.FIELD)
public class Currency implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<String,Currency>
    {
        @Override
        public String marshal(Currency currency) throws Exception 
        {
            return currency.getCurrencyId();
        }

        @Override
        public Currency unmarshal(String arg) throws Exception 
        {
            Currency currency = new Currency();
            currency.setCurrencyId(arg);
            return currency;
        }
    
    }

    @XmlElement(name="currencyId", required=true)
    public String getCurrencyId()
    {
        return this.currencyId;
    }
    
    public void setCurrencyId(String arg)
    {
        this.currencyId = arg;
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
    public Date getStartDateTime()
    {
        return this.startDateTime;
    }
    
    public void setStartDateTime(Date arg)
    {
        this.startDateTime = arg;
    }
    
    @XmlElement(name="endDateTime", required=true)
    public Date getEndDateTime()
    {
        return this.endDateTime;
    }
    
    public void setEndDateTime(Date arg)
    {
        this.endDateTime = arg;
    }
    
    @XmlElement(name="numberOfDecimals", required=true)
    public Long getNumberOfDecimals()
    {
        return this.numberOfDecimals;
    }
    
    public void setNumberOfDecimals(Long arg)
    {
        this.numberOfDecimals = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedDateTime()
    {
        return this.lastUpdatedDateTime;
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
    
    @Field(name="currency_v", index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    @Audited
    
    @Id
    @Column(length=5, name="currency_v", nullable=false)
    protected String currencyId; 
    
    @Field(name="name_v", index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    @Audited
    
    @Basic
    @Column(length=255, name="name_v", nullable=false)
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
    
    @Field(name="no_of_decimals_n", index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    @Audited
    
    @Basic
    @Column(name="no_of_decimals_n", nullable=false)
    protected Long numberOfDecimals; 
    
    @XmlElement(name="lastUpdatedDateTime", required=true)
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedDateTime; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tenant_id_n", nullable=false)
    protected net.treetechnologies.entities.masters.Tenant tenant; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected net.treetechnologies.entities.masters.Status status; 
    
}     
