package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="Enumeration")
@XmlRootElement(name="Enumeration")
@Entity(name="net.treetechnologies.entities.masters.Enumeration")
@Table(name="sd_enumerations")
@Access(AccessType.FIELD)
@EntityListeners(value={net.treetechnologies.entities.masters.EnumerationMaster.EnumerationsListener.class})
@IdClass(EnumerationId.class)
public class Enumeration implements java.io.Serializable 
{
    @XmlElement(name="enumerationId", required=true)
    public Long getEnumerationId()
    {
        return this.enumerationId;
    }
    
    public void setEnumerationId(Long arg)
    {
        this.enumerationId = arg;
    }
    
    @XmlElement(name="enumerationTypeId", required=true)
    public Long getEnumerationTypeId()
    {
        return this.enumerationTypeId;
    }
    
    public void setEnumerationTypeId(Long arg)
    {
        this.enumerationTypeId = arg;
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
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    public net.treetechnologies.entities.masters.EnumerationMaster getEnumerationType()
    {
        return this.enumerationType;
    }
    
    public void setEnumerationType(net.treetechnologies.entities.masters.EnumerationMaster arg)
    {
        if(this.enumerationType == arg)
            return;
            
        net.treetechnologies.entities.masters.EnumerationMaster oldEnumerationType = this.enumerationType;
        this.enumerationType = null;
        if(oldEnumerationType != null)
            oldEnumerationType.removeEnumeration(this);
        
        this.enumerationType = arg;

        if(this.enumerationType != null)
            this.enumerationType.addEnumeration(this);
    }
    
    @Audited
    
    @Id
    @Column(name="enumeration_id_n")
    protected Long enumerationId; 
    
    @Audited
    
    @Id
    @Column(name="enumeration_type_id_n")
    protected Long enumerationTypeId; 
    
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
    
    @XmlJavaTypeAdapter(value=net.treetechnologies.entities.masters.EnumerationMaster.IDAdapter.class)
    @XmlElement(name="enumerationType", required=true)
    @ManyToOne
    @JoinColumn(insertable=false, name="enumeration_type_id_n", nullable=false, updatable=false)
    protected net.treetechnologies.entities.masters.EnumerationMaster enumerationType; 
    
}     
