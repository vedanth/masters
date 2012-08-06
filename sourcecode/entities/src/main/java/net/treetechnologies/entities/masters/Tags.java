package net.treetechnologies.entities.masters;

import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="Tags")
@XmlRootElement(name="Tags")
@Entity(name="net.treetechnologies.entities.masters.Tags")
@Table(name="ms_tags")
@Access(AccessType.FIELD)
@EntityListeners(value={net.treetechnologies.entities.masters.Tag.TagsListener.class})
public class Tags implements java.io.Serializable 
{
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,Tags>
    {
        @Override
        public Long marshal(Tags tags) throws Exception 
        {
            return tags.getTagId();
        }

        @Override
        public Tags unmarshal(Long arg) throws Exception 
        {
            Tags tags = new Tags();
            tags.setTagId(arg);
            return tags;
        }
    
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
    
    @XmlElement(name="entityId", required=true)
    public Long getEntityId()
    {
        return this.entityId;
    }
    
    public void setEntityId(Long arg)
    {
        this.entityId = arg;
    }
    
    @XmlElement(name="tagValue", required=true)
    public String getTagValue()
    {
        return this.tagValue;
    }
    
    public void setTagValue(String arg)
    {
        this.tagValue = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @XmlJavaTypeAdapter(value=EntityType.IDAdapter.class)
    @XmlElement(name="entityTypeId", required=true)
    public net.treetechnologies.entities.masters.EntityType getEntityType()
    {
        return this.entityType;
    }
    
    public void setEntityType(net.treetechnologies.entities.masters.EntityType arg)
    {
        if(this.entityType == arg)
            return;
            
        
        this.entityType = arg;

    }
    
    @XmlJavaTypeAdapter(value=Tag.IDAdapter.class)
    @XmlElement(name="tagMasterId", required=true)
    public net.treetechnologies.entities.masters.Tag getTagMaster()
    {
        return this.tagMaster;
    }
    
    public void setTagMaster(net.treetechnologies.entities.masters.Tag arg)
    {
        if(this.tagMaster == arg)
            return;
            
        net.treetechnologies.entities.masters.Tag oldTagMaster = this.tagMaster;
        this.tagMaster = null;
        if(oldTagMaster != null)
            oldTagMaster.removeTags(this);
        
        this.tagMaster = arg;

        if(this.tagMaster != null)
            this.tagMaster.addTags(this);
    }
    
    @Audited
    
    @Id
    @Column(name="tag_id_n")
    @GeneratedValue(generator="ms_tags_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="ms_tags_seq", sequenceName="ms_tags_seq")
    protected Long tagId; 
    
    @Audited
    
    @Basic
    @Column(name="entity_id_n", nullable=false)
    protected Long entityId; 
    
    @Audited
    
    @Basic
    @Column(length=2000, name="tag_value_v")
    protected String tagValue; 
    
    @XmlElement(name="lastUpdatedTime", required=true)
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="entity_type_n", nullable=false)
    protected net.treetechnologies.entities.masters.EntityType entityType; 
    
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tag_master_id_n", nullable=false)
    protected net.treetechnologies.entities.masters.Tag tagMaster; 
    
}     
