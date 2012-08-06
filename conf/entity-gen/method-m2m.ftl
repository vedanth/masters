<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>
<#macro @element></#macro>
<#macro @text></#macro>
<#import "general-functions.ftl" as gf>
<#import "generic-annotation.ftl" as ga>

<#macro "java-attributes"><#recurse></#macro>

<#macro "getter">
    <#recurse using ga>
    ${gf.methodScope(.node)} java.util.Set<${gf.type(association)}> ${gf.methodName(.node)}()
    {
        this.${gf.fieldName(association)}.size();
        return java.util.Collections.unmodifiableSet(this.${gf.fieldName(association)});
    }
    
</#macro>

<#macro enquirer>
    <#recurse using ga>
    ${gf.methodScope(.node)} Boolean ${gf.methodName(.node)}(${gf.type(association)} arg)
    {
        return this.${gf.fieldName(association)}.contains(arg);
    }
    
</#macro>

<#macro adder>
    <#recurse using ga>
    <#local peerAssociation = gf.peerAssociation(association)>

    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${gf.type(association)} arg)
    {
        if(arg == null || this.${gf.fieldName(association)}.contains(arg))
            return;
        
        this.${gf.fieldName(association)}.add(arg);       
        <#if peerAssociation[0]??>
        
        if(!arg.${gf.methodName(peerAssociation["java-attributes"].enquirer)}(this))
        {
            arg.${gf.methodName(peerAssociation["java-attributes"].adder)}(this);
        }
        </#if>
    }
    
</#macro>

<#macro remover>
    <#recurse using ga>
    <#local peerAssociation = gf.peerAssociation(association)>

    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${gf.type(association)} arg)
    {
        if(arg == null || !this.${gf.fieldName(association)}.contains(arg))
            return;
        
        this.${gf.fieldName(association)}.remove(arg);       
        <#if peerAssociation[0]??>
        
        if(arg.${gf.methodName(peerAssociation["java-attributes"].enquirer)}(this))
        {
            arg.${gf.methodName(peerAssociation["java-attributes"].remover)}(this);
        }
        </#if>
    }
    
</#macro>
