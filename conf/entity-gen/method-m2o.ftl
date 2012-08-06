<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>
<#macro @element></#macro>
<#macro @text></#macro>
<#import "general-functions.ftl" as gf>
<#import "generic-annotation.ftl" as ga>


<#macro "java-attributes"><#recurse></#macro>

<#macro getter>
    <#recurse using ga>
    ${gf.methodScope(.node)} ${gf.type(association)} ${gf.methodName(.node)}()
    {
        return this.${gf.fieldName(association)};
    }
    
</#macro>

<#macro "setter">
    <#if !gf.isManyToManyComponent(association)>
        <#recurse using ga>
        <#if ((association.@optional[0])!"true") = "true" >
            <@optionalSetter .node/>
        <#else>
            <@mandatorySetter .node/> 
        </#if>
    </#if>
</#macro>

<#macro optionalSetter method>
    <#local inverseAssociation = gf.inverseAssociation(association)>
    ${gf.methodScope(method)} void ${gf.methodName(method)}(${gf.type(association)} arg)
    {
        if(this.${gf.fieldName(association)} == arg)
            return;
            
        <#if inverseAssociation[0]??>
        ${gf.type(association)} old${gf.fieldName(association)?cap_first} = this.${gf.fieldName(association)};
        this.${gf.fieldName(association)} = null;
        if(old${gf.fieldName(association)?cap_first} != null)
            old${gf.fieldName(association)?cap_first}.${gf.associationMethodName(inverseAssociation,"remover")}(this);
        </#if>
        
        this.${gf.fieldName(association)} = arg;

        <#if inverseAssociation[0]??>
        if(this.${gf.fieldName(association)} != null)
            this.${gf.fieldName(association)}.${gf.associationMethodName(inverseAssociation,"adder")}(this);
        </#if>
    }
    
</#macro>

<#macro mandatorySetter method>
    <#local inverseAssociation = gf.inverseAssociation(association)>
    ${gf.methodScope(method)} void ${gf.methodName(method)}(${gf.type(association)} arg)
    {
        if(arg == null || this.${gf.fieldName(association)} == arg)
            return;
        <#if inverseAssociation[0]??>

        ${gf.type(association)} old${gf.fieldName(association)?cap_first} = this.${gf.fieldName(association)};
        </#if>

        this.${gf.fieldName(association)} = arg;
        <#if inverseAssociation[0]??>

        this.${gf.fieldName(association)}.${gf.associationMethodName(inverseAssociation,"acquirer")}(this,old${gf.fieldName(association)?cap_first});
        </#if>
    }
    
</#macro>

