<#ftl strip_whitespace=true strip_text=false ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>
<#macro @element></#macro>
<#macro @text></#macro>
<#import "general-functions.ftl" as gf>
<#import "generic-annotation.ftl" as ga>



<#macro "java-attributes"><#recurse></#macro>

<#macro getter>
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
    <#local owningAssociation = gf.owningAssociation(association)>
    <#if ((owningAssociation.@optional[0])!"true") = "true" >
    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${gf.type(association)} arg)
    {
        if(arg == null || this.${gf.fieldName(association)}.contains(arg))
            return;
        this.${gf.fieldName(association)}.add(arg);
        arg.${gf.methodName(owningAssociation["java-attributes"].setter)}(this);        
    }
    
    </#if>
</#macro>

<#macro remover>
    <#recurse using ga>
    <#local owningAssociation = gf.owningAssociation(association)>
    <#if ((owningAssociation.@optional[0])!"true") = "true" >
    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${gf.type(association)} arg)
    {
        if(arg == null || !this.${gf.fieldName(association)}.contains(arg))
            return;
        this.${gf.fieldName(association)}.remove(arg);
        arg.${gf.methodName(owningAssociation["java-attributes"].setter)}(this);        
    }
    
    </#if>
</#macro>

<#macro acquirer>
    <#recurse using ga>
    <#local owningAssociation = gf.owningAssociation(association)>
    <#if ((owningAssociation.@optional[0])!"true") = "false" >
    ${gf.methodScope(.node)} void ${gf.methodName(.node)}(${gf.type(association)} arg, ${gf.type(owningAssociation)} source)
    {
        if(arg == null || source == null || this.${gf.fieldName(association)}.contains(arg))
            return;

        this.${gf.fieldName(association)}.add(arg);
        source.${gf.fieldName(association)}.remove(arg);
        arg.${gf.methodName(owningAssociation["java-attributes"].setter)}(this);
    }

    </#if>
</#macro>

