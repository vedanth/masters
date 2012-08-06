<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>

<#macro @element></#macro>
<#macro @text></#macro>

<#import "general-functions.ftl" as gf>

<#function Entity node>
    <#local retval>@Entity<#if node.@name[0]??>(name="${node.@name[0]}")</#if></#local>
    <#return retval>
</#function>

<#function Table node>
    <#local retval="@Table"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "name">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "name=\"" + attribute + "\"">
                <#break>
            <#case "catalog">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "catalog=\"" + attribute + "\"">
                <#break>
            <#case "schema">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "schema=\"" + attribute + "\"">
                <#break>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function Access node>
    <#local retval>@Access(AccessType.${(node.@access[0])!"FIELD"})</#local>
    <#return retval>
</#function>

<#function Column node>
    <#local retval="@Column"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "name">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "name=\"" + attribute + "\"">
                <#break>
            <#case "column-definition">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "columnDefinition=\"" + attribute + "\"">
                <#break>
            <#case "table">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "table=\"" + attribute + "\"">
                <#break>
            <#default>
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + attribute?node_name + "=" + attribute>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function JoinColumn node>
    <#local retval="@JoinColumn"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "name">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "name=\"" + attribute + "\"">
                <#break>
            <#case "referenced-column-name">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "referencedColumnName=\"" + attribute + "\"">
                <#break>
            <#case "column-definition">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "columnDefinition=\"" + attribute + "\"">
                <#break>
            <#case "table">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "table=\"" + attribute + "\"">
                <#break>
            <#default>
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + attribute?node_name + "=" + attribute>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function JoinTable node>
    <#local retval>@JoinTable
    (
        name="${node.@name}",
        joinColumns = ${JoinColumn(node["join-column"])},
        inverseJoinColumns =  ${JoinColumn(node["inverse-join-column"])}
    )</#local>
    <#return retval>
</#function>

<#function Id node>
    <#local retval>@Id</#local>
    <#return retval>
</#function>

<#function IdClass node>
    <#local retval>@IdClass(${gf.className(node["id-class"])}.class)</#local>
    <#return retval>
</#function>

<#function Basic node>
    <#local retval="@Basic"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "fetch">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "fetch=FetchType." + attribute>
                <#break>
           <#case "optional">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "optional=" + attribute>
                <#break>
             <#default>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function Version node>
    <#local retval="@Version"/>
    <#return retval/>
</#function>

<#function ManyToOne node>
    <#local retval="@ManyToOne"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "target-entity">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "targetEntity=" + attribute>
                <#break>
            <#case "fetch">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "fetch=FetchType." + attribute>
                <#break>
            <#case "optional">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "optional=" + attribute>
                <#break>
            <#default>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function OneToMany node>
    <#local retval="@OneToMany"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "target-entity">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "targetEntity=" + attribute>
                <#break>
            <#case "fetch">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "fetch=FetchType." + attribute>
                <#break>
            <#case "mapped-by">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "mappedBy=\"" + attribute + "\"">
                <#break>
            <#case "orphan-removal">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "orphanRemoval=" + attribute>
                <#break>
            <#default>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function ManyToMany node>
    <#local retval="@ManyToMany"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "target-entity">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "targetEntity=" + attribute>
                <#break>
            <#case "fetch">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "fetch=FetchType." + attribute>
                <#break>
            <#case "mapped-by">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "mappedBy=\"" + attribute + "\"">
                <#break>
            <#default>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function MapKey node>
    <#local retval="@MapKey(name=\"" + node.@name + "\")"/>
    <#return retval>
</#function>

<#function SequenceGenerator node>
    <#local retval="@SequenceGenerator"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "name">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "name=\"" + attribute + "\"">
                <#break>
            <#case "sequence-name">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "sequenceName=\"" + attribute + "\"">
                <#break>
            <#case "initial-value">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "initialValue=" + attribute>
                <#break>
            <#case "allocation-size">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "allocationSize=" + attribute>
                <#break>
            <#default>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function GeneratedValue node>
    <#local retval="@GeneratedValue"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "generator">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "generator=\"" + attribute + "\"">
                <#break>
            <#case "strategy">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "strategy=GenerationType." + attribute>
                <#break>
            <#default>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function Inheritance node>
        <#local retval="@Inheritance(strategy = InheritanceType." + node.@strategy + ")"/>
        <#return retval/>
</#function>

<#function DiscriminatorColumn node>
    <#local retval="@DiscriminatorColumn"/>
    <#local first = "true"/>
    <#list node.@@ as attribute>
        <#switch attribute?node_name>
            <#case "name">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "name=\"" + attribute + "\"">
                <#break>
            <#case "discriminator-type">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "discriminatorType=DiscriminatorType." + attribute>
                <#break>
            <#case "length">
                <#if first = "true">
                    <#local first = "false"/>
                    <#local retval=retval + "("/>
                <#else>
                    <#local retval=retval + ", "/>
                </#if>
                <#local retval= retval + "length=" + attribute>
                <#break>
            <#default>
        </#switch>
    </#list>
    <#if first="false">
        <#local retval = retval + ")">
    </#if>
    <#return retval/>
</#function>

<#function DiscriminatorValue node>
    <#local retval>@DiscriminatorValue(value="${node}")</#local>
    <#return retval>
</#function>