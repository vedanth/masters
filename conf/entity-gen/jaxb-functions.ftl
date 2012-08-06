<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>

<#macro @element></#macro>
<#macro @text></#macro>

<#import "general-functions.ftl" as gf/>

<#function XmlAccessorType entity>
    <#local retval="@XmlAccessorType(XmlAccessType.NONE)">
    <#return retval/>
</#function>

<#function XmlType entity>
    <#local retval="@XmlType(name=\"" + gf.className(entity)?uncap_first + "Type\")"/>
    <#return retval/>
</#function>

<#function XmlRootElement entity>
    <#local retval="@XmlRootElement(name=\"" + gf.className(entity)?uncap_first + "\")"/>
    <#return retval/>
</#function>

<#function XmlElement attribute>
    <#local retval="@XmlElement(name=\"" + gf.fieldName(attribute) + "\", required=true)"/>
    <#return retval/>
</#function>

<#function XmlJavaTypeAdapter attribute>
    <#local retval="@XmlJavaTypeAdapter(value=" + gf.type(attribute) + ".IDAdapter.class)"/>
    <#return retval/>
</#function>
