package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="EnumerationMaster")
@XmlRootElement(name="EnumerationMaster")
@Entity(name="net.treetechnologies.entities.masters.EnumerationMaster")
@Table(name="sd_enumeration_type")
@Access(AccessType.FIELD)
public class EnumerationMaster implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,EnumerationMaster>
    {
        @Override
        public Long marshal(EnumerationMaster enumerationMaster) throws Exception 
        {
            return enumerationMaster.getEnumerationMasterId();
        }

        @Override
        public EnumerationMaster unmarshal(Long arg) throws Exception 
        {
            EnumerationMaster enumerationMaster = new EnumerationMaster();
            enumerationMaster.setEnumerationMasterId(arg);
            return enumerationMaster;
        }
    
    }

    @XmlTransient
    public static class EnumerationsListener
    {
        @PrePersist
        public void onPersist(net.treetechnologies.entities.masters.Enumeration arg)
        {
            EnumerationMaster obj = arg.getEnumerationType();
            obj._addEnumerations(arg);
        }
        
        @PreRemove
        public void onRemove(net.treetechnologies.entities.masters.Enumeration arg)
        {
            EnumerationMaster obj = arg.getEnumerationType();
            obj._removeEnumerations(arg);
        }
        
    }
    
    void _addEnumerations(net.treetechnologies.entities.masters.Enumeration arg)
    {
        this.enumerations.add(arg);
    }

    void _removeEnumerations(net.treetechnologies.entities.masters.Enumeration arg)
    {
        this.enumerations.remove(arg);
    }
    
    @XmlElement(name="enumerationMasterId",required=true)
    public Long getEnumerationMasterId()
    {
        return this.enumerationMasterId;
    }
    
    public void setEnumerationMasterId(Long arg)
    {
        this.enumerationMasterId = arg;
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
    
    public java.util.Set<net.treetechnologies.entities.masters.Enumeration> getEnumerations()
    {
        this.enumerations.size();
        return java.util.Collections.unmodifiableSet(this.enumerations);
    }
    
    public Boolean hasEnumeration(net.treetechnologies.entities.masters.Enumeration arg)
    {
        return this.enumerations.contains(arg);
    }
    
    public void addEnumeration(net.treetechnologies.entities.masters.Enumeration arg)
    {
        if(arg == null || this.enumerations.contains(arg))
            return;
        this.enumerations.add(arg);
        arg.setEnumerationType(this);        
    }
    
    public void removeEnumeration(net.treetechnologies.entities.masters.Enumeration arg)
    {
        if(arg == null || !this.enumerations.contains(arg))
            return;
        this.enumerations.remove(arg);
        arg.setEnumerationType(this);        
    }
    
    @Field(name="enumeration_type_id_n",index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    @Audited
    
    @Id
    @Column(name="enumeration_type_id_n")
    protected Long enumerationMasterId; 
    
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
    protected Date lastUpdatedTime; 
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="enumerationType")
    protected java.util.Set<net.treetechnologies.entities.masters.Enumeration> enumerations = new java.util.HashSet<net.treetechnologies.entities.masters.Enumeration>(); 
    
}     
