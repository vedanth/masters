<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>

<#macro @element></#macro>
<#macro @text></#macro>

<#import "general-functions.ftl" as gf/>
<#import "jpa-functions.ftl" as jpa/>
<#import "jaxb-functions.ftl" as jaxb/>
<#import "generic-annotation.ftl" as ga>

<#macro attributes><#recurse></#macro>

<#macro id>
    <#switch targetType>
        <#case "entityClass">
    <#recurse using ga>
    <#if !(.node["java-annotations"][0]??)>${jaxb.XmlElement(.node)}</#if>
    ${jpa.Id(.node)}
    <#recurse>
    ${gf.field(.node)} 
    
            <#break>
        <#case "attributesClass">
            <#break>
        <#case "idClass">
    ${gf.field(.node)} 
    
            <#break>
    </#switch>
</#macro>

<#macro version>
    <#switch targetType>
        <#case "entityClass">
    <#recurse using ga>
    <#if !(.node["java-annotations"][0]??)>${jaxb.XmlElement(.node)}</#if>
    ${jpa.Version(.node)}
    <#recurse>
    ${gf.field(.node)} 
    
            <#break>
        <#case "attributesClass">
    <#if !(.node["java-annotations"][0]??)>${jaxb.XmlElement(.node)}</#if>
    ${gf.field(.node)} 
    
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro basic>
    <#switch targetType>
        <#case "entityClass">
    <#recurse using ga>
    <#if !(.node["java-annotations"][0]??)>${jaxb.XmlElement(.node)}</#if>
    ${jpa.Basic(.node)}
    <#recurse>
    ${gf.field(.node)} 
    
            <#break>
        <#case "attributesClass">
    <#if !(.node["java-annotations"][0]??)>${jaxb.XmlElement(.node)}</#if>
    ${gf.field(.node)}
     
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro "many-to-one">
    <#switch targetType>
        <#case "entityClass">
    <#recurse using ga>
    <#if !(.node["java-annotations"][0]??)>
    ${jaxb.XmlJavaTypeAdapter(.node)}
    ${jaxb.XmlElement(.node)}
    </#if>
    ${jpa.ManyToOne(.node)}
    <#recurse>
    ${gf.field(.node)} 
    
            <#break>
        <#case "attributesClass">
    <#if !gf.isManyToManyComponent(.node)>
    <#if !(.node["java-annotations"][0]??)>${jaxb.XmlElement(.node)}</#if>
    ${gf.field(.node)}

    </#if>
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro "one-to-many">
    <#switch targetType>
        <#case "entityClass">
    <#recurse using ga>
    ${jpa.OneToMany(.node)}
    <#recurse>
    ${gf.fieldScope(.node)} java.util.Set<${gf.type(.node)}> ${gf.fieldName(.node)} = new java.util.HashSet<${gf.type(.node)}>(); 
    
            <#break>
        <#case "attributesClass">
    ${gf.fieldScope(.node)} java.util.Set<${gf.type(.node)}> ${gf.fieldName(.node)} = new java.util.HashSet<${gf.type(.node)}>(); 
    
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro "many-to-many">
    <#switch targetType>
        <#case "entityClass">
    <#recurse using ga>
    ${jpa.ManyToMany(.node)}
    <#recurse>
    ${gf.fieldScope(.node)} java.util.Set<${gf.type(.node)}> ${gf.fieldName(.node)} = new java.util.HashSet<${gf.type(.node)}>(); 
    
            <#break>
        <#case "attributesClass">
    ${gf.fieldScope(.node)} java.util.Set<${gf.type(.node)}> ${gf.fieldName(.node)} = new java.util.HashSet<${gf.type(.node)}>(); 
    
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro "many-to-many-ex">
    <#switch targetType>
        <#case "entityClass">
    <#recurse using ga>
    ${jpa.OneToMany(.node)}
    <#recurse>
    ${gf.fieldScope(.node)} java.util.Map<${gf.qualifiedName(.node.@peer)},${gf.type(.node)}> ${gf.fieldName(.node)} = new java.util.HashMap<${gf.qualifiedName(.node.@peer)},${gf.type(.node)}>();
            
            <#break>
        <#case "attributesClass">
    <#if !gf.isManyToManyComponent(.node)>
        ${gf.fieldScope(.node)} java.util.Map<${gf.qualifiedName(.node.@peer)},${gf.type(.node)}> ${gf.fieldName(.node)} = new java.util.HashMap<${gf.qualifiedName(.node.@peer)},${gf.type(.node)}>();
    
    </#if> 
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro column>
    ${jpa.Column(.node)}
</#macro>

<#macro "join-columns">
    <#local first="true"/>
    <#local size=.node["join-column"]?size>
    @JoinColumns
    ({
    <#list .node["join-column"] as col>
        <#if first = "true"><#local first="false">${jpa.JoinColumn(col)}<#else>,
        ${jpa.JoinColumn(col)}        
        </#if>
    </#list>
    })
</#macro>

<#macro "join-column">
    ${jpa.JoinColumn(.node)}
</#macro>

<#macro "join-table">
    ${jpa.JoinTable(.node)}
</#macro>

<#macro temporal>
    @Temporal(TemporalType.${.node})
</#macro>

<#macro "map-key">
    @MapKey(name="${.node.@name}")
</#macro>

<#macro "generated-value">
    ${jpa.GeneratedValue(.node)}
</#macro>

<#macro "sequence-generator">
    ${jpa.SequenceGenerator(.node)}
</#macro>

<#macro lob>
    @Lob
</#macro>
