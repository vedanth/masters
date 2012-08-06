<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>

<#macro @element></#macro>
<#macro @text></#macro>
<#import "general-functions.ftl" as gf>
<#import "jpa-functions.ftl" as jpa>
<#import "jaxb-functions.ftl" as jaxb>
<#import "field.ftl" as field>
<#import "method.ftl" as method>
<#import "method-m2o.ftl" as m2o>
<#import "generic-annotation.ftl" as ga>

<#macro entity>
<#if (targetType="attributesClass" && !.node["detail-class"][0]??) || (targetType="idClass" && !.node["id-class"][0]??)><#return></#if>
${fileSeparator(.node)}
package ${gf.packageName(.node)};

${imports(.node)}

<#recurse .node using ga>
${classAnnotations(.node)}
<#recurse>
${classScope(.node)} ${gf.abstract(.node)}class ${className(.node)} ${gf.extends(.node)}implements java.io.Serializable 
{
    <#if targetType = "entityClass" && .node.attributes.id?size=1><@idAdapter .node/></#if>
    <#if targetType = "entityClass" && (.node.attributes["one-to-many"]?size > 0)><@oneToManyListeners .node/></#if>
    <#assign entity = .node in method/>
    <#recurse .node<#--.attributes--> using method>
    <#-- 
    <#if .node["detail-class"][0]?? && targetType = "entityClass">
        <@association_methods .node/>
    </#if> 
    -->
    <#if targetType = "idClass">
        <@equal_hashcode .node/>
    </#if>
    <#assign entity = .node in field/>
    <#recurse .node<#--.attributes--> using field>
}     
</#macro>

<#function imports node>
    <#switch targetType>
        <#case "entityClass">
            <#local retval>
import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;
            </#local>
            <#break>
        <#case "attributesClass">
            <#local retval>
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;
            </#local>
            <#break>
        <#case "idClass">
            <#local retval>
import javax.xml.bind.annotation.*;
import java.util.Date;
            </#local>
            <#break>
    </#switch>
    <#return retval>
</#function>

<#function fileSeparator node>
    <#local retval =""/>
    <#switch targetType>
        <#case "entityClass">
            <#local retval>FILE ${gf.packageName(node)[0]?replace (".","/")}/${gf.className(node)}.java</#local>
            <#break>
        <#case "attributesClass">
            <#local retval>FILE ${gf.packageName(node)[0]?replace (".","/")}/${gf.className(node["detail-class"])}.java</#local>
            <#break>
        <#case "idClass">
            <#local retval>FILE ${gf.packageName(node)[0]?replace (".","/")}/${gf.className(node["id-class"])}.java</#local>
            <#break>
    </#switch>
    <#return retval>
</#function>

<#function classAnnotations node>
    <#switch targetType>
        <#case "entityClass">
            <#local retval>
<#if !(node["java-annotations"][0]??)>            
${jaxb.XmlAccessorType(node)}
${jaxb.XmlType(node)}
${jaxb.XmlRootElement(node)}         
</#if>   
${jpa.Entity(node)}(name="${gf.packageName(node)}.${className(node)}")
<#if node.table[0]??>
${jpa.Table(node.table)}
</#if>
${jpa.Access(node)}
                <#if hasEntityListeners(node)>
${registerEntityListeners(node)}
                </#if>                
                <#if node["id-class"][0]??>
${jpa.IdClass(node)}
                </#if>
            </#local>
            <#break>
        <#case "attributesClass">
            <#local retval>
<#if !(node["java-annotations"][0]??)>            
${jaxb.XmlAccessorType(node["detail-class"])}
${jaxb.XmlType(node["detail-class"])}
${jaxb.XmlRootElement(node["detail-class"])}         
</#if>               </#local>
            <#break>
        <#case "idClass">
            <#local retval>
@XmlTransient
            </#local>
            <#break>
    </#switch>
    <#return retval?chop_linebreak>
</#function>

<#function classScope node>
    <#switch targetType>
        <#case "entityClass">
            <#local retval>${gf.classScope(node)}</#local>
            <#break>
        <#case "attributesClass">
            <#local retval>${gf.classScope(node["detail-class"])}</#local>
            <#break>
        <#case "idClass">
            <#local retval>${gf.classScope(node["id-class"])}</#local>
            <#break>
    </#switch>
    <#return retval>
</#function>

<#function className node>
    <#switch targetType>
        <#case "entityClass">
            <#local retval>${gf.className(node)}</#local>
            <#break>
        <#case "attributesClass">
            <#local retval>${gf.className(node["detail-class"])}</#local>
            <#break>
        <#case "idClass">
            <#local retval>${gf.className(node["id-class"])}</#local>
            <#break>
    </#switch>
    <#return retval>
</#function>

<#macro association_methods entity>
    protected ${gf.className(entity["detail-class"])} get${gf.className(entity["detail-class"])}()
    {
        ${gf.className(entity["detail-class"])} ${gf.className(entity["detail-class"])?uncap_first} = new ${gf.className(entity["detail-class"])}();
        <#list entity.attributes.* as attribute>
            <#if attribute?node_name != "id" && !gf.isManyToManyComponent(attribute)>
        ${gf.className(entity["detail-class"])?uncap_first}.${gf.fieldName(attribute)} = this.${gf.fieldName(attribute)};
            </#if>
        </#list>
        return ${gf.className(entity["detail-class"])?uncap_first};
    }

    protected void set${gf.className(entity["detail-class"])}(${gf.className(entity["detail-class"])} ${gf.className(entity["detail-class"])?uncap_first})
    {
        <#list entity.attributes.* as attribute>
            <#if attribute?node_name != "id" && !gf.isManyToManyComponent(attribute)>
        this.${gf.fieldName(attribute)} = ${gf.className(entity["detail-class"])?uncap_first}.${gf.fieldName(attribute)};
            </#if>
        </#list>
    }

    protected ${gf.className(entity)}()
    {
    }
    
</#macro>

<#macro equal_hashcode entity>
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        <#list entity.attributes.id as attribute>
        result = prime * result + ((${gf.fieldName(attribute)} == null) ? 0 : ${gf.fieldName(attribute)}.hashCode());
        </#list>
        return result;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof ${gf.className(entity)})) 
            return false;
            
        ${gf.className(entity)} other = (${gf.className(entity)}) obj;
        
        <#list entity.attributes.id as attribute>
        if (${gf.fieldName(attribute)} == null) 
        {
            if (other.${gf.fieldName(attribute)} != null) 
            {
                return false;
            }
        } 
        else if (!${gf.fieldName(attribute)}.equals(other.${gf.fieldName(attribute)})) 
        {
            return false;
        }
        </#list>
        return true;
    }

</#macro>

<#macro idAdapter entity>
    @XmlTransient
    public static class IDAdapter extends XmlAdapter<${gf.type(entity.attributes.id[0])},${gf.className(entity)}>
    {
        @Override
        public ${gf.type(entity.attributes.id[0])} marshal(${gf.className(entity)} ${gf.className(entity)?uncap_first}) throws Exception 
        {
            return ${gf.className(entity)?uncap_first}.${gf.methodName(entity.attributes.id[0]["java-attributes"].getter)}();
        }

        @Override
        public ${gf.className(entity)} unmarshal(${gf.type(entity.attributes.id[0])} arg) throws Exception 
        {
            ${gf.className(entity)} ${gf.className(entity)?uncap_first} = new ${gf.className(entity)}();
            ${gf.className(entity)?uncap_first}.${gf.methodName(entity.attributes.id[0]["java-attributes"].setter)}(arg);
            return ${gf.className(entity)?uncap_first};
        }
    
    }

</#macro>

<#macro oneToManyListeners entity>
    <#list entity.attributes["one-to-many"] as attribute>
    <#local owningAssociation = gf.owningAssociation(attribute)>
    @XmlTransient
    public static class ${gf.fieldName(attribute)?cap_first}Listener
    {
        @PrePersist
        public void onPersist(${gf.type(attribute)} arg)
        {
            ${gf.className(entity)} obj = arg.${gf.methodName(owningAssociation["java-attributes"].getter)}();
            obj._add${gf.fieldName(attribute)?cap_first}(arg);
        }
        
        @PreRemove
        public void onRemove(${gf.type(attribute)} arg)
        {
            ${gf.className(entity)} obj = arg.${gf.methodName(owningAssociation["java-attributes"].getter)}();
            obj._remove${gf.fieldName(attribute)?cap_first}(arg);
        }
        
    }
    
    void _add${gf.fieldName(attribute)?cap_first}(${gf.type(attribute)} arg)
    {
        this.${gf.fieldName(attribute)}.add(arg);
    }

    void _remove${gf.fieldName(attribute)?cap_first}(${gf.type(attribute)} arg)
    {
        this.${gf.fieldName(attribute)}.remove(arg);
    }
    
    </#list>
</#macro>

<#macro inheritance>
${jpa.Inheritance(.node)}
</#macro>

<#macro "discriminator-column">
${jpa.DiscriminatorColumn(.node)}
</#macro>

<#macro "discriminator-value">
${jpa.DiscriminatorValue(.node)}
</#macro>

<#function hasEntityListeners entity>
    <#list entity.attributes["many-to-one"] as attribute>
        <#if !gf.isManyToManyComponent(attribute)>
            <#if gf.inverseAssociation(attribute)[0]??>
                <#return true>
            </#if>
        </#if>
    </#list>
    <#return false>
</#function>

<#function registerEntityListeners entity>
    <#local retval = "@EntityListeners(value={">
    <#local first = "true"/>
    <#list entity.attributes["many-to-one"] as attribute>
        <#if !gf.isManyToManyComponent(attribute)>
            <#local inverseAssociation = gf.inverseAssociation(attribute)>
            <#if inverseAssociation[0]??>
                <#if first = "true">
                    <#local first = "false"/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval=retval + gf.type(attribute) + "." + gf.fieldName(inverseAssociation)?cap_first + "Listener.class"/>
            </#if>
        </#if>
    </#list>
    <#local retval=retval + "})"/>
    
    <#return retval>
</#function>