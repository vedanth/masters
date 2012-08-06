<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>
<#macro @element></#macro>
<#macro @text></#macro>

<#macro "java-annotations"><#recurse></#macro>
<#macro "java-annotation">
    <#if .node?parent?parent?node_name = "entity">
${.node}
    <#else>
    ${.node}
    </#if>
</#macro>