package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="TagType")
@XmlRootElement(name="TagType")
@Entity(name="net.treetechnologies.entities.masters.TagType")
@Table(name="ms_tag_type")
@Access(AccessType.FIELD)
public class TagType implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,TagType>
    {
        @Override
        public Long marshal(TagType tagType) throws Exception 
        {
            return tagType.getTagTypeId();
        }

        @Override
        public TagType unmarshal(Long arg) throws Exception 
        {
            TagType tagType = new TagType();
            tagType.setTagTypeId(arg);
            return tagType;
        }
    
    }

    @XmlTransient
    public static class TagMastersListener
    {
        @PrePersist
        public void onPersist(net.treetechnologies.entities.masters.Tag arg)
        {
            TagType obj = arg.getTagType();
            obj._addTagMasters(arg);
        }
        
        @PreRemove
        public void onRemove(net.treetechnologies.entities.masters.Tag arg)
        {
            TagType obj = arg.getTagType();
            obj._removeTagMasters(arg);
        }
        
    }
    
    void _addTagMasters(net.treetechnologies.entities.masters.Tag arg)
    {
        this.tagMasters.add(arg);
    }

    void _removeTagMasters(net.treetechnologies.entities.masters.Tag arg)
    {
        this.tagMasters.remove(arg);
    }
    
    @XmlElement(name="tagTypeId", required=true)
    public Long getTagTypeId()
    {
        return this.tagTypeId;
    }
    
    public void setTagTypeId(Long arg)
    {
        this.tagTypeId = arg;
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
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    public java.util.Set<net.treetechnologies.entities.masters.Tag> getTags()
    {
        this.tagMasters.size();
        return java.util.Collections.unmodifiableSet(this.tagMasters);
    }
    
    public Boolean hasTag(net.treetechnologies.entities.masters.Tag arg)
    {
        return this.tagMasters.contains(arg);
    }
    
    public void addTag(net.treetechnologies.entities.masters.Tag arg)
    {
        if(arg == null || this.tagMasters.contains(arg))
            return;
        this.tagMasters.add(arg);
        arg.setTagType(this);        
    }
    
    public void removeTag(net.treetechnologies.entities.masters.Tag arg)
    {
        if(arg == null || !this.tagMasters.contains(arg))
            return;
        this.tagMasters.remove(arg);
        arg.setTagType(this);        
    }
    
    @Audited
    
    @Id
    @Column(name="tag_type_id_n")
    @GeneratedValue(generator="ms_tag_type_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="ms_tag_type_seq", sequenceName="ms_tag_type_seq")
    protected Long tagTypeId; 
    
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
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected net.treetechnologies.entities.masters.Status status; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tenant_id_n", nullable=false)
    protected net.treetechnologies.entities.masters.Tenant tenant; 
    
    @XmlElement(name="lastUpdatedTime", required=true)
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @AuditMappedBy(mappedBy="tagType")
    @OneToMany(fetch=FetchType.LAZY, mappedBy="tagType")
    protected java.util.Set<net.treetechnologies.entities.masters.Tag> tagMasters = new java.util.HashSet<net.treetechnologies.entities.masters.Tag>(); 
    
}     
