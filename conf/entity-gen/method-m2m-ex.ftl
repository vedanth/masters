<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>
<#macro @element></#macro>
<#macro @text></#macro>
<#import "general-functions.ftl" as gf>
<#import "generic-annotation.ftl" as ga>

<#macro "java-attributes"><#recurse></#macro>

<#macro "getter">
    <#recurse using ga>
    ${gf.methodScope(.node)} java.util.Set<${peerClass(association)}> ${gf.methodName(.node)}()
    {
        ${gf.fieldName(association)}.size();
        return java.util.Collections.unmodifiableSet(this.${gf.fieldName(association)}.keySet());
    }
    
</#macro>

<#macro enquirer>
    <#recurse using ga>
    ${gf.methodScope(.node)} Boolean ${gf.methodName(.node)}(${peerClass(association)} arg)
    {
        return this.${gf.fieldName(association)}.keySet().contains(arg);
    }
    
</#macro>

<#macro "detail-getter">
    <#recurse using ga>
    <#local peerEntity = gf.findEntity(association,peerClass(association))/>
    <#local peerAssociation=gf.findAttribute(peerEntity,peerField(association))/>
    <#local associationEntity = gf.findEntity(association,gf.type(association))/>
    <#if (associationEntity?size > 1)>ERROR<#return></#if>
    <#local associationClass=gf.packageName(associationEntity) + "." + gf.className(associationEntity)/>
    <#-- <#local associationClass=gf.qualifiedName(associationEntity)>-->
    ${gf.methodScope(.node)} ${associationClass} ${gf.methodName(.node)}(${peerClass(association)} arg)
    {
        <#if ((association.@owning[0])!"false") = "true">
        if(this.${gf.fieldName(association)}.containsKey(arg))
            return this.${gf.fieldName(association)}.get(arg);
        else
            return null;
        <#else>
        if(arg != null)
            return arg.${gf.methodName(peerAssociation["java-attributes"]["detail-getter"])}(this);
        else
            return null;
        </#if>
    }
    
</#macro>
  
<#macro "adder">
    <#recurse using ga>
    <#local peerEntity = gf.findEntity(association,peerClass(association))/>
    <#local peerAssociation=gf.findAttribute(peerEntity,peerField(association))/>
    <#local associationEntity = gf.findEntity(association,gf.type(association))/>
    <#-- AAA ${association["//D:entity-mappings[D:package=$packageName]"].package[0]}-->
    <#if (associationEntity?size > 1)>ERROR<#return></#if>
    <#-- -->
    <#local associationClass=gf.packageName(associationEntity) + "." + gf.className(associationEntity)/> 
    <#-- <#local associationClass=gf.qualifiedName(associationEntity)/> -->
    <#-- <#local associationAttributeClass=gf.packageName(associationEntity) + "." + gf.className(associationEntity["detail-class"])/> -->
    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${peerClass(association)} arg, ${associationClass} arg1,EntityManager em)
    {
        if(arg == null || this.${gf.fieldName(association)}.containsKey(arg))
            return;
        <#if ((association.@owning[0])!"false") = "true">
        if(em.contains(arg1))
            return;
        <#list entity.attributes.id as id>
        arg1.${gf.methodName(id["java-attributes"].setter)}(this.${gf.methodName(id["java-attributes"].getter)}());
        </#list>
        <#list peerEntity.attributes.id as id>
        arg1.${gf.methodName(id["java-attributes"].setter)}(arg.${gf.methodName(id["java-attributes"].getter)}());
        </#list>
        em.persist(arg1);
        this.${gf.fieldName(association)}.put(arg,arg1);  
            <#if peerAssociation[0]??>
        if(!arg.${gf.methodName(peerAssociation["java-attributes"].enquirer)}(this))
        {
            arg.${gf.methodName(peerAssociation["java-attributes"].adder)}(this,arg1,em);
        }
            </#if>
        <#else>
        if(!arg.${gf.methodName(peerAssociation["java-attributes"].enquirer)}(this))
        {
            arg.${gf.methodName(peerAssociation["java-attributes"].adder)}(this,arg1,em);
        }
        if(arg.${gf.methodName(peerAssociation["java-attributes"]["detail-getter"])}(this) != arg1)
            return;
        if(!this.${gf.fieldName(association)}.containsKey(arg))
        {
            this.${gf.fieldName(association)}.put(arg,arg1);
        }
        </#if>
    }
    
</#macro>


<#macro "remover">
    <#recurse using ga>
    <#local peerEntity = gf.findEntity(association,peerClass(association))/>
    <#local peerAssociation=gf.findAttribute(peerEntity,peerField(association))/>
    <#local associationEntity = gf.findEntity(association,gf.type(association))/>
    <#-- <#local associationAttributeClass=gf.className(associationEntity["detail-class"])/> -->
    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${peerClass(association)} arg,EntityManager em)
    {
        if(arg == null || !this.${gf.fieldName(association)}.containsKey(arg))
            return;
        <#if ((association.@owning[0])!"false") = "true">
        em.remove(this.${gf.fieldName(association)}.get(arg));
        </#if>
        this.${gf.fieldName(association)}.remove(arg);
        <#if peerAssociation[0]??>
        if(arg.${gf.methodName(peerAssociation["java-attributes"].enquirer)}(this))
        {
            arg.${gf.methodName(peerAssociation["java-attributes"].remover)}(this,em);
        }
        </#if>
    }
    
</#macro>

<#function peerClass association>
    <#return gf.qualifiedName(association.@peer[0])>
</#function>

<#function peerField association>
    <#return association["@peer-name"][0]>
</#function>
