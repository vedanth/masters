<#ftl strip_whitespace=true strip_text=true ns_prefixes={"D":"http://treetechnologies.net/xml/ns/persistence/orm"}>

<#macro @element></#macro>
<#macro @text></#macro>

<@start/>

<#macro start>
FILE persistence.xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="1.0">
    <persistence-unit name="HELLO" transaction-type="JTA">
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <!-- Conigure the follwing as per application needs -->  
        <jta-data-source>java:jboss/datasources/postgres</jta-data-source>
        <properties>
            <!-- Conigure the follwing as per application needs -->  
            <property name="hibernate.cache.infinispan.entity.eviction.strategy" value= "LRU"/>
            <property name="hibernate.cache.infinispan.entity.eviction.wake_up_interval" value= "2000"/>
            <property name="hibernate.cache.infinispan.entity.eviction.max_entries" value= "500000"/>
            <property name="hibernate.cache.infinispan.entity.expiration.lifespan" value= "6000000"/>
            <property name="hibernate.cache.infinispan.entity.expiration.max_idle" value= "3000000"/>
            
            <!-- Normally no need to change the following -->
            <property name="hibernate.max_fetch_depth" value="0"/>          
            <property name="hibernate.cache.use_second_level_cache" value="true"/>
            <property name="hibernate.cache.use_query_cache" value="true"/>
            <property name="hibernate.transaction.manager_lookup_class" value="org.hibernate.transaction.JBossTransactionManagerLookup"/>
            <property name="hibernate.cache.infinispan.statistics" value="true"/>
            
<#recurse doc>
        </properties>       
    </persistence-unit>
</persistence>
</#macro>


<#macro "mapping-files"><#recurse></#macro>

<#macro "entity-mappings">
    <#recurse>
</#macro>

<#macro package>
    <#assign packageName>${.node}</#assign>
</#macro>

<#macro entity>
    <#assign className>${.node.@class}</#assign>
        <#if ((.node["java-attributes"].@extends[0])!"") = "">
            <property name="hibernate.ejb.classcache.${packageName}.${className}" value="transactional"/>
        </#if>  
    <#recurse>  
</#macro>

<#macro attributes>
    <#recurse>
</#macro>

<#macro "one-to-many">
    <@generateCollectionCache .node/>
</#macro>

<#macro "many-to-many">
    <@generateCollectionCache .node/>
</#macro>

<#macro "many-to-many-ex">
    <@generateCollectionCache .node/>
</#macro>

<#macro generateCollectionCache node>
            <property name="hibernate.ejb.collectioncache.${packageName}.${className}.${node.@name}" value="transactional"/>        
</#macro>