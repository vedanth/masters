<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>

<#macro @element></#macro>
<#macro @text></#macro>
<#import "entity.ftl" as entity>

<#recurse doc>

<#macro "mapping-files"><#recurse></#macro>

<#macro "entity-mappings">
    <@gen_package_info .node/>
    <#global targetType="entityClass">
    <#recurse using entity/>
    <#-- 
    <#global targetType="attributesClass">
    <#recurse using entity/>
    -->
    <#global targetType="idClass">
    <#recurse using entity/>
</#macro>

<#macro gen_package_info mappings>
<#local package>${mappings.package}</#local>
FILE ${package?replace (".","/")}/package-info.java
@javax.xml.bind.annotation.XmlSchema(namespace="http://${package?replace(".","/")?substring(0,package?last_index_of("."))}/schema/${package?substring(package?last_index_of(".") + 1)}")
package ${package};  
</#macro>