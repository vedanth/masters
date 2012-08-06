package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="Tag")
@XmlRootElement(name="Tag")
@Entity(name="net.treetechnologies.entities.masters.Tag")
@Table(name="ms_tag")
@Access(AccessType.FIELD)
@EntityListeners(value={net.treetechnologies.entities.masters.TagType.TagMastersListener.class})
public class Tag implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,Tag>
    {
        @Override
        public Long marshal(Tag tag) throws Exception 
        {
            return tag.getTagId();
        }

        @Override
        public Tag unmarshal(Long arg) throws Exception 
        {
            Tag tag = new Tag();
            tag.setTagId(arg);
            return tag;
        }
    
    }

    @XmlTransient
    public static class TagsListener
    {
        @PrePersist
        public void onPersist(net.treetechnologies.entities.masters.Tags arg)
        {
            Tag obj = arg.getTagMaster();
            obj._addTags(arg);
        }
        
        @PreRemove
        public void onRemove(net.treetechnologies.entities.masters.Tags arg)
        {
            Tag obj = arg.getTagMaster();
            obj._removeTags(arg);
        }
        
    }
    
    void _addTags(net.treetechnologies.entities.masters.Tags arg)
    {
        this.tags.add(arg);
    }

    void _removeTags(net.treetechnologies.entities.masters.Tags arg)
    {
        this.tags.remove(arg);
    }
    
    @XmlElement(name="tagId", required=true)
    public Long getTagId()
    {
        return this.tagId;
    }
    
    public void setTagId(Long arg)
    {
        this.tagId = arg;
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
    
    @XmlJavaTypeAdapter(value=TagType.IDAdapter.class)
    @XmlElement(name="tagTypeId", required=true)
    public net.treetechnologies.entities.masters.TagType getTagType()
    {
        return this.tagType;
    }
    
    public void setTagType(net.treetechnologies.entities.masters.TagType arg)
    {
        if(this.tagType == arg)
            return;
            
        net.treetechnologies.entities.masters.TagType oldTagType = this.tagType;
        this.tagType = null;
        if(oldTagType != null)
            oldTagType.removeTag(this);
        
        this.tagType = arg;

        if(this.tagType != null)
            this.tagType.addTag(this);
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
    
    public java.util.Set<net.treetechnologies.entities.masters.Tags> getTags()
    {
        this.tags.size();
        return java.util.Collections.unmodifiableSet(this.tags);
    }
    
    public Boolean hasTags(net.treetechnologies.entities.masters.Tags arg)
    {
        return this.tags.contains(arg);
    }
    
    public void addTags(net.treetechnologies.entities.masters.Tags arg)
    {
        if(arg == null || this.tags.contains(arg))
            return;
        this.tags.add(arg);
        arg.setTagMaster(this);        
    }
    
    public void removeTags(net.treetechnologies.entities.masters.Tags arg)
    {
        if(arg == null || !this.tags.contains(arg))
            return;
        this.tags.remove(arg);
        arg.setTagMaster(this);        
    }
    
    @Audited
    
    @Id
    @Column(name="tag_master_id_n")
    @GeneratedValue(generator="ms_tag_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="ms_tag_seq", sequenceName="ms_tag_seq")
    protected Long tagId; 
    
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
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected net.treetechnologies.entities.masters.Status status; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tag_type_id", nullable=false)
    protected net.treetechnologies.entities.masters.TagType tagType; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tenant_id_n", nullable=false)
    protected net.treetechnologies.entities.masters.Tenant tenant; 
    
    @AuditMappedBy(mappedBy="tagMaster")
    @OneToMany(fetch=FetchType.LAZY, mappedBy="tagMaster")
    protected java.util.Set<net.treetechnologies.entities.masters.Tags> tags = new java.util.HashSet<net.treetechnologies.entities.masters.Tags>(); 
    
}     
