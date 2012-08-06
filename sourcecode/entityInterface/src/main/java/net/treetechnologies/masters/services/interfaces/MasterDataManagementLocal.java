package net.treetechnologies.masters.services.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jws.WebParam;

import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.entities.masters.Currency;
import net.treetechnologies.entities.masters.EntityType;
import net.treetechnologies.entities.masters.Enumeration;
import net.treetechnologies.entities.masters.Language;
import net.treetechnologies.entities.masters.Status;
import net.treetechnologies.entities.masters.Tag;
import net.treetechnologies.entities.masters.TagType;
import net.treetechnologies.entities.masters.Tags;
import net.treetechnologies.entities.masters.Tenant;

/**
 * <b>Purpose:</b><br>
 * Interface to provide the retrieval operations on master data<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Tree Technologies 2012<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
  *  1        28-03-2012          Vedanth
 *    	-- Base Release
 * 
 *  2        29-03-2012          Gopinath
 *      -- Added TagTypeServiceResult, TagServiceResult, TagsServiceResult Classes
 *      -- Replaced List instead of Set in all the serviceResult classes
 *          
 *  3        30-03-2012          Gopinath
 *      -- Added getEntityTypeById, getLangaugeById, getStatusById, getTenantById Operations
 *      -- Added getTagTypeById, getTagById, getTagsById Operations
 *         
 *  4      	 05-04-2012			 Suresh Upparu
 *  	-- Added create, update, remove methods for the TagType, Tag and Tags.
 *  	
 *  5	     05-04-2012			 Suresh Upparu
 *  	-- Added getTagMastersByTagTypeId method to get all the TagMasters associated with TagTypeMaster.
 *  
 *  6        25-04-2012          Yoganand JG
 *      -- Added getTagsByEntityId method to get all tags based on entityId
 *      
 *  7        27-04-2012          Gopinath
 *      -- Added getTagsByEntityTypeIdWithEntityId method
 *      
 *  8       14-05-2012          Yoganand JG
 *      -- changed return type from Object to void in all remove methods
 *
 * </pre>
 * 
 * <br>
 */

public interface MasterDataManagementLocal
{
   
	/** Tenant Services */
	public Tenant getTenantsById(Long tenantId) throws ApplicationException;

	public List<Tenant> getTenants() throws ApplicationException;

	
	/** Status Services */
	public Status getStatusById(Long statusId) throws ApplicationException;
	
	public List<Status> getStatus() throws ApplicationException;
	
	public List<Status> getStatusByIdList(List<Long> statusId) throws ApplicationException;

	
	/** Currency Services */
	public Currency getCurrencyById(String currencyId) throws ApplicationException;

	public List<Currency> getCurrency() throws ApplicationException;
	
	public String getCurrencyValue(String value, Currency currency, String operationMode) throws ApplicationException;

	
	/** EntityType Services */
	public EntityType getEntityTypeById(Long entityTypeId) throws ApplicationException;
	
	public List<EntityType> getEntityTypes() throws ApplicationException;
	
	
	/** Language Services */
	public Language getLanguageById(String languageId) throws ApplicationException;

	public List<Language> getLanguages() throws ApplicationException;    

	
	/** TagType Services */
    public TagType createTagType(TagType tagType) throws ApplicationException;

    public TagType updateTagType(TagType tagType) throws ApplicationException;

    public void removeTagType(Long tagTypeId) throws ApplicationException;

    public TagType getTagTypeById(Long tagTypeId) throws ApplicationException;

    public List<TagType> getTagTypes() throws ApplicationException;

    
    /** Tag Services */
    public Tag createTag(Tag tag) throws ApplicationException;

    public Tag updateTag(Tag tag) throws ApplicationException;

    public void removeTag(Long tagId) throws ApplicationException;

    public Tag getTagById(Long tagId) throws ApplicationException;

    public List<Tag> getTag() throws ApplicationException;

    public Set<Tag> getTagMastersByTagTypeId(Long tagTypeId) throws ApplicationException;


    /** Tags Services */
    public Tags createTags(Tags tags) throws ApplicationException;

    public Tags updateTags(Tags tags) throws ApplicationException;

    public void removeTags(Long tagsId) throws ApplicationException;

    public Tags getTagsById(Long tagsId) throws ApplicationException;

    public List<Tags> getTags() throws ApplicationException;

    public List<Tags> getTagsByEntityId(Long entityId) throws ApplicationException;

    public List<Tags> getTagsByEntityIdWithEntityType(Long entityId, Long entityTypeId) throws ApplicationException;

    
    /** LifeCycle Services */
    public void validateOperation(Long lifecycleId,String operationName,Long status) throws ApplicationException;
    
    public void validateStateTransition(Long lifecycleId,Long fromStatus,Long toStatus) throws ApplicationException;
    
    public  List<Status> getStateTransitions( Long lifecycleId,Long status) throws ApplicationException;

    
    /** Enumeration Services */
    public List<Enumeration> getEnumerationMastersByEnumerationId(Long enumerationMasterId) throws ApplicationException;
    
}
