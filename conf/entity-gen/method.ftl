<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>

<#macro @element></#macro>
<#macro @text></#macro>

<#import "general-functions.ftl" as gf>
<#import "jpa-functions.ftl" as jpa>
<#import "generic-annotation.ftl" as ga>
<#import "method-m2o.ftl" as m2o>
<#import "method-o2m.ftl" as o2m>
<#import "method-m2m.ftl" as m2m>
<#import "method-m2m-ex.ftl" as m2mex>

<#macro "java-attributes"><#recurse></#macro>
<#macro attributes><#recurse></#macro>

<#macro id>
    <#assign association=.node>
    <#switch targetType>
        <#case "entityClass">
            <#recurse>
            <#break>
        <#case "attributesClass">
            <#break>
        <#case "idClass">
            <#recurse>
            <#break>
    </#switch>
</#macro>

<#macro basic>
    <#assign association=.node>
    <#switch targetType>
        <#case "entityClass">
            <#recurse>
            <#break>
        <#case "attributesClass">
            <#recurse>
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro version>
    <#assign association=.node>
    <#switch targetType>
        <#case "entityClass">
            <#recurse>
            <#break>
        <#case "attributesClass">
            <#recurse>
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro "many-to-one">
    <#switch targetType>
        <#case "entityClass">
            <#assign entity=entity in m2o>
            <#assign association=.node in m2o>
            <#recurse using m2o>
            <#break>
        <#case "attributesClass">
            <#if !gf.isManyToManyComponent(.node)>
                <#assign entity=entity in m2o>
                <#assign association=.node in m2o>
                <#recurse using m2o>
            </#if>
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro "one-to-many">
    <#switch targetType>
        <#case "entityClass">
            <#assign entity=entity in o2m>
            <#assign association=.node in o2m>
            <#recurse using o2m>
            <#break>
        <#case "attributesClass">
            <#assign entity=entity in o2m>
            <#assign association=.node in o2m>
            <#recurse using o2m>
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro "many-to-many">
    <#switch targetType>
        <#case "entityClass">
            <#assign entity=entity in m2m>
            <#assign association=.node in m2m>
            <#recurse using m2m>
            <#break>
        <#case "attributesClass">
            <#assign entity=entity in m2m>
            <#assign association=.node in m2m>
            <#recurse using m2m>
            <#break>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro "many-to-many-ex">
    <#switch targetType>
        <#case "entityClass">
            <#assign entity=entity in m2mex>
            <#assign association=.node in m2mex>
            <#recurse using m2mex>
            <#break>
        <#case "attributesClass">
            <#if !gf.isManyToManyComponent(.node)>
                <#assign entity=entity in m2mex>
                <#assign association=.node in m2mex>
                <#recurse using m2mex>
                <#break>
            </#if>
        <#case "idClass">
            <#break>
    </#switch>
</#macro>

<#macro getter>
    <#recurse using ga>
    <#if targetType="idClass">public<#else>${gf.methodScope(.node)}</#if> ${gf.type(association)} ${gf.methodName(.node)}()
    {
        return this.${gf.fieldName(association)};
    }
    
</#macro>

<#macro setter>
    <#recurse using ga>
    <#if targetType="idClass">public<#else>${gf.methodScope(.node)}</#if> void ${gf.methodName(.node)}(${gf.type(association)} arg)
    {
        this.${gf.fieldName(association)} = arg;
    }
    
</#macro>

<#macro "version-getter">
    <#local versionField=gf.versionField(entity)>
    <#local versionTypeField=gf.versionTypeField(entity)>
    <#local versionTimestampField=gf.versionTimestampField(entity)>
    ${gf.methodScope(.node)} ${gf.className(entity)} ${gf.methodName(.node)}(Date asOn)
    {
        if(${gf.fieldName(versionTypeField)} == ${gf.objectVersionType()})
        {
            throw new net.treetechnologies.common.exception.IllegalOperationException("Operation not allowed on versions");
        } 

        java.util.Set<${gf.className(entity)}> versions = this.${gf.methodName(entity["java-attributes"]["version-list-getter"])}();
        java.util.Date latestDate = null;
        ${gf.className(entity)} latestVersion = null;
        
        for(${gf.className(entity)} version:versions)
        {
            if(asOn.after(version.${gf.methodName(versionTimestampField["java-attributes"].getter)}()) || asOn.equals(version.${gf.methodName(versionTimestampField["java-attributes"].getter)}()))
            {
                if(latestDate == null || version.${gf.methodName(versionTimestampField["java-attributes"].getter)}().after(latestDate))
                {
                    latestDate = version.${gf.methodName(versionTimestampField["java-attributes"].getter)}();
                    latestVersion = version;
                }
            }
            <#-- 
            if(latestDate == null)
            {
                latestDate = version.${gf.methodName(versionTimestampField["java-attributes"].getter)}();
                latestVersion = version;
            }
            else
            {
                if(version.${gf.methodName(versionTimestampField["java-attributes"].getter)}().after(latestDate) && version.${gf.methodName(versionTimestampField["java-attributes"].getter)}().before(asOn))
                {
                    latestDate = version.${gf.methodName(versionTimestampField["java-attributes"].getter)}();
                    latestVersion = version;
                }
            } 
            -->
        }
        return latestVersion;
    }
    
    ${gf.methodScope(.node)} ${gf.className(entity)} ${gf.methodName(.node)}(Date asOn, java.util.List<Long> statusFilter)
    {
        if(${gf.fieldName(versionTypeField)} == ${gf.objectVersionType()})
        {
            throw new net.treetechnologies.common.exception.IllegalOperationException("Operation not allowed on versions");
        } 

        java.util.Set<${gf.className(entity)}> versions = this.${gf.methodName(entity["java-attributes"]["version-list-getter"])}(statusFilter);
        java.util.Date latestDate = null;
        ${gf.className(entity)} latestVersion = null;
        
        for(${gf.className(entity)} version:versions)
        {
            
            if(asOn.after(version.${gf.methodName(versionTimestampField["java-attributes"].getter)}()) || asOn.equals(version.${gf.methodName(versionTimestampField["java-attributes"].getter)}()))
            {
                if(latestDate == null || version.${gf.methodName(versionTimestampField["java-attributes"].getter)}().after(latestDate))
                {
                    latestDate = version.${gf.methodName(versionTimestampField["java-attributes"].getter)}();
                    latestVersion = version;
                }
            }
            <#-- 
            if(latestDate == null)
            {
                latestDate = version.${gf.methodName(versionTimestampField["java-attributes"].getter)}();
                latestVersion = version;
            }
            else
            {
                if(version.${gf.methodName(versionTimestampField["java-attributes"].getter)}().after(latestDate) && version.${gf.methodName(versionTimestampField["java-attributes"].getter)}().before(asOn))
                {
                    latestDate = version.${gf.methodName(versionTimestampField["java-attributes"].getter)}();
                    latestVersion = version;
                }
            } 
            -->
        }
        return latestVersion;
    }
    
</#macro>

<#macro "version-list-getter">
    <#local statusField=gf.statusField(entity)>
    <#local statusClassName>${statusField["java-attributes"].@type}</#local>
    <#local statusEntity = gf.findEntity(.node,statusClassName)>
    <#local statusIdMethodName=gf.methodName(statusEntity.attributes.id["java-attributes"].getter)>
    <#local versionField=gf.versionField(entity)>
    <#local versionTypeField=gf.versionTypeField(entity)>
    <#local versionContainer = gf.findEntity(doc,gf.type(versionField))>
    <#local versionContainerAttribute = gf.findAttribute(versionContainer,entity["java-attributes"]["@version-container-attribute"])>
    ${gf.methodScope(.node)} java.util.Set<${gf.className(entity)}> ${gf.methodName(.node)}()
    {
        if(${gf.fieldName(versionTypeField)} == ${gf.objectVersionType()})
        {
            throw new net.treetechnologies.common.exception.IllegalOperationException("Operation not allowed on versions");
        } 

        java.util.Set<${gf.className(entity)}> retval = new java.util.HashSet<${gf.className(entity)}>();
        
        retval.addAll(this.${gf.fieldName(versionField)}.iterator().next().${gf.methodName(versionContainerAttribute["java-attributes"].getter)}());
        
        return java.util.Collections.unmodifiableSet(retval);
    }

    ${gf.methodScope(.node)} java.util.Set<${gf.className(entity)}> ${gf.methodName(.node)}(java.util.List<Long> statusFilter)
    {
        if(${gf.fieldName(versionTypeField)} == ${gf.objectVersionType()})
        {
            throw new net.treetechnologies.common.exception.IllegalOperationException("Operation not allowed on versions");
        } 

        java.util.Set<${gf.className(entity)}> retval = new java.util.HashSet<${gf.className(entity)}>();
        
        java.util.Set<${gf.className(entity)}> versions = this.${gf.fieldName(versionField)}.iterator().next().${gf.methodName(versionContainerAttribute["java-attributes"].getter)}();
        
        for(${gf.className(entity)} version:versions)
        {
            for(Long status : statusFilter)
            {
                if(version.${gf.methodName(statusField["java-attributes"].getter)}().${statusIdMethodName}().equals(status))
                {
                    retval.add(version);
                }
            }
        }
                    
        return retval;
    }

</#macro>
<#--
<#macro "version-creator">
    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${gf.className(entity)} ${gf.className(entity)?uncap_first})
    {
    }
    
</#macro>

<#macro "version-remover">
    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${gf.className(entity)} ${gf.className(entity)?uncap_first})
    {
    }
    
</#macro>
-->