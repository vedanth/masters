package net.treetechnologies.masters.services.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.WebFault;

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

@WebService(targetNamespace="http://net/treetechnologies/masters/services/interfaces/MasterDataManagement")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public interface MasterDataManagement
{
	
	/** Tenant Services */
	@WebMethod(operationName = "getTenants")
    public @WebResult(name = "getTenants")
    List<Tenant> getTenants() throws ApplicationException;

	@WebMethod(operationName = "getTenantsById")
    public @WebResult(name = "getTenantsById")
    Tenant getTenantsById(Long tenantId) throws ApplicationException;

	
    /** Status Services */
    @WebMethod(operationName = "getStatus")
    public @WebResult(name = "getStatus")
    List<Status> getStatus() throws ApplicationException;

    @WebMethod(operationName = "getStatusById")
    public @WebResult(name = "getStatusById")
    Status getStatusById(Long statusId) throws ApplicationException;

    @WebMethod(operationName = "getStatusByList")
    public @WebResult(name = "getStatusByList")
    List<Status> getStatusByIdList(List<Long> statusId) throws ApplicationException;

    
    /** Currency Services */
    @WebMethod(operationName = "getCurrency")
    public @WebResult(name = "getCurrency")
    List<Currency> getCurrency() throws ApplicationException;

    @WebMethod(operationName = "getCurrencyById")
    public @WebResult(name = "getCurrencyById")
    Currency getCurrencyById(String currencyId) throws ApplicationException;

	@WebMethod(operationName = "getCurrencyValue")
	public @WebResult(name = "getCurrencyValue")
	String getCurrencyValue(String value,Currency currency,String operationMode) throws ApplicationException;

	
    /** EntityType Services */
    @WebMethod(operationName = "getEntityTypes")
    public @WebResult(name = "getEntityTypes")
    List<EntityType> getEntityTypes() throws ApplicationException;

    @WebMethod(operationName = "getEntityTypeById")
    public @WebResult(name = "getEntityTypeById")
    EntityType getEntityTypeById(Long entityTypeId) throws ApplicationException;

    
    /** Language Services */
    @WebMethod(operationName = "getLanguageById")
    public @WebResult(name = "getLanguageById")
    Language getLanguageById(String languageId) throws ApplicationException;

    @WebMethod(operationName = "getLanguages")
    public @WebResult(name = "getLanguages")
    List<Language> getLanguages() throws ApplicationException;
    
    
    /** TagType Services */
    @WebMethod(operationName = "createTagType")
    public @WebResult(name = "createTagType")
    TagType createTagType(TagType tagType) throws ApplicationException;

    @WebMethod(operationName = "updateTagType")
    public @WebResult(name = "updateTagType")
    TagType updateTagType(TagType tagType) throws ApplicationException;

    @WebMethod(operationName = "removeTagType")
    public @WebResult(name = "removeTagType")
    void removeTagType(Long tagTypeId) throws ApplicationException;

    @WebMethod(operationName = "getTagTypes")
    public @WebResult(name = "getTagTypes")
    List<TagType> getTagTypes() throws ApplicationException;

    @WebMethod(operationName = "getTagTypeById")
    public @WebResult(name = "getTagTypeById")
    TagType getTagTypeById(Long tagTypeId) throws ApplicationException;


    /** Tag Services */
    @WebMethod(operationName = "createTag")
    public @WebResult(name = "createTag")
    Tag createTag(Tag tag) throws ApplicationException;

    @WebMethod(operationName = "updateTag")
    public @WebResult(name = "updateTag")
    Tag updateTag(Tag tag) throws ApplicationException;

    @WebMethod(operationName = "removeTag")
    public @WebResult(name = "removeTag")
    void removeTag(Long tagId) throws ApplicationException;

    @WebMethod(operationName = "getTag")
    public @WebResult(name = "getTag")
    List<Tag> getTag() throws ApplicationException;

    @WebMethod(operationName = "getTagById")
    public @WebResult(name = "getTagById")
    Tag getTagById(Long tagId) throws ApplicationException;

    @WebMethod(operationName = "getTagMastersByTagTypeId")
    public @WebResult(name = "getTagMastersByTagTypeId")
    Set<Tag> getTagMastersByTagTypeId(Long tagTypeId) throws ApplicationException;

    
    /** Tags Services */
    @WebMethod(operationName = "createTags")
    public @WebResult(name = "createTags")
    Tags createTags(Tags tags) throws ApplicationException;

    @WebMethod(operationName = "updateTags")
    public @WebResult(name = "updateTags")
    Tags updateTags(Tags tags) throws ApplicationException;

    @WebMethod(operationName = "removeTags")
    public @WebResult(name = "removeTags")
    void removeTags(Long tagsId) throws ApplicationException;

    @WebMethod(operationName = "getTags")
    public @WebResult(name = "getTags")
    List<Tags> getTags() throws ApplicationException;

    @WebMethod(operationName = "getTagsById")
    public @WebResult(name = "getTagsById")
    Tags getTagsById(Long tagsId) throws ApplicationException;

    @WebMethod(operationName = "getTagsByEntityId")
    public @WebResult(name = "getTagsByEntityId")
    List<Tags> getTagsByEntityId(Long entityId) throws ApplicationException;

    @WebMethod(operationName = "getTagsByEntityIdWithEntityType")
    public @WebResult(name = "getTagsByEntityIdWithEntityType")
    List<Tags> getTagsByEntityIdWithEntityType(Long entityId, Long entityTypeId) throws ApplicationException;

    
    /** LifeCycle Services */
    @WebMethod(operationName="validateOperation")
	public @WebResult(name = "validateOperation") 
    void validateOperation(Long lifecycleId,String operationName,Long status) throws ApplicationException;
    
    @WebMethod(operationName="validateStateTransition")
	public @WebResult(name = "validateStateTransition") 
    void validateStateTransition(Long lifecycleId,Long fromStatus,Long toStatus) throws ApplicationException;
	
	@WebMethod(operationName="getStateTransitions")
	public @WebResult(name = "getStateTransitions")
	List<Status> getStateTransitions(@WebParam(name="lifecycleId") Long lifecycleId,@WebParam(name="status") Long status) throws ApplicationException;

	
    /** Enumeration Services */
    @WebMethod(operationName = "getEnumerationMastersByEnumerationId")
    public @WebResult(name = "getEnumerationMastersByEnumerationId")
    List<Enumeration> getEnumerationMastersByEnumerationId(Long enumerationMasterId) throws ApplicationException;

    
    
    /** Exception Classes */
    
    @WebFault(name = "EntityManagerInitializationException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class EntityManagerInitializationException extends ApplicationException
    {
        public EntityManagerInitializationException()
        {

        }

        public EntityManagerInitializationException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTenantsByIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTenantsByIdException extends ApplicationException
    {
        public GetTenantsByIdException()
        {

        }

        public GetTenantsByIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTenantsException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTenantsException extends ApplicationException
    {
        public GetTenantsException()
        {

        }

        public GetTenantsException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetStatusException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetStatusException extends ApplicationException
    {
        public GetStatusException()
        {

        }

        public GetStatusException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetStatusByIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetStatusByIdException extends ApplicationException
    {
        public GetStatusByIdException()
        {

        }

        public GetStatusByIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetStatusByIdListException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetStatusByIdListException extends ApplicationException
    {
        public GetStatusByIdListException()
        {

        }

        public GetStatusByIdListException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetCurrencyException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetCurrencyException extends ApplicationException
    {
        public GetCurrencyException()
        {

        }

        public GetCurrencyException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetCurrencyByIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetCurrencyByIdException extends ApplicationException
    {
        public GetCurrencyByIdException()
        {

        }

        public GetCurrencyByIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetEntityTypesException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetEntityTypesException extends ApplicationException
    {
        public GetEntityTypesException()
        {

        }

        public GetEntityTypesException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetEntityTypeByIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetEntityTypeByIdException extends ApplicationException
    {
        public GetEntityTypeByIdException()
        {

        }

        public GetEntityTypeByIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetLanguagesException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetLanguagesException extends ApplicationException
    {
        public GetLanguagesException()
        {

        }

        public GetLanguagesException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "CreateTagTypeException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class CreateTagTypeException extends ApplicationException
    {
        public CreateTagTypeException()
        {

        }

        public CreateTagTypeException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "UpdateTagTypeException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class UpdateTagTypeException extends ApplicationException
    {
        public UpdateTagTypeException()
        {

        }

        public UpdateTagTypeException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "RemoveTagTypeException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class RemoveTagTypeException extends ApplicationException
    {
        public RemoveTagTypeException()
        {

        }

        public RemoveTagTypeException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTagTypesException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTagTypesException extends ApplicationException
    {
        public GetTagTypesException()
        {

        }

        public GetTagTypesException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTagTypeByIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTagTypeByIdException extends ApplicationException
    {
        public GetTagTypeByIdException()
        {

        }

        public GetTagTypeByIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }
    
    @WebFault(name = "CreateTagException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class CreateTagException extends ApplicationException
    {
        public CreateTagException()
        {

        }

        public CreateTagException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "UpdateTagException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class UpdateTagException extends ApplicationException
    {
        public UpdateTagException()
        {

        }

        public UpdateTagException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "RemoveTagException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class RemoveTagException extends ApplicationException
    {
        public RemoveTagException()
        {

        }

        public RemoveTagException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTagMasterException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTagMasterException extends ApplicationException
    {
        public GetTagMasterException()
        {

        }

        public GetTagMasterException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }
    
    @WebFault(name = "GetTagByIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTagByIdException extends ApplicationException
    {
        public GetTagByIdException()
        {

        }

        public GetTagByIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTagMastersByTagTypeIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTagMastersByTagTypeIdException extends ApplicationException
    {
        public GetTagMastersByTagTypeIdException()
        {

        }

        public GetTagMastersByTagTypeIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "CreateTagsException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class CreateTagsException extends ApplicationException
    {
        public CreateTagsException()
        {

        }

        public CreateTagsException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "UpdateTagsException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class UpdateTagsException extends ApplicationException
    {
        public UpdateTagsException()
        {

        }

        public UpdateTagsException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }
    
    @WebFault(name = "RemoveTagsException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class RemoveTagsException extends ApplicationException
    {
        public RemoveTagsException()
        {

        }

        public RemoveTagsException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTagsException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTagsException extends ApplicationException
    {
        public GetTagsException()
        {

        }

        public GetTagsException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTagsByIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTagsByIdException extends ApplicationException
    {
        public GetTagsByIdException()
        {

        }

        public GetTagsByIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetTagsByEntityIdWithEntityTypeException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetTagsByEntityIdWithEntityTypeException extends ApplicationException
    {
        public GetTagsByEntityIdWithEntityTypeException()
        {

        }

        public GetTagsByEntityIdWithEntityTypeException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetStateTransitionsException")
	@javax.ejb.ApplicationException(rollback = true)
	public static class GetStateTransitionsException extends ApplicationException
	{
	    public GetStateTransitionsException()
	    {

	    }

	    public GetStateTransitionsException(String message, Throwable cause)
	    {
	        super(message, cause);
	    }
	}

	@WebFault(name = "ValidateOperationException")
	@javax.ejb.ApplicationException(rollback = true)
	public static class ValidateOperationException extends ApplicationException
	{
	    public ValidateOperationException()
	    {

	    }

	    public ValidateOperationException(String message, Throwable cause)
	    {
	        super(message, cause);
	    }
	}

	@WebFault(name = "ValidateStateTransitionException")
	@javax.ejb.ApplicationException(rollback = true)
	public static class ValidateStateTransitionException extends ApplicationException
	{
	    public ValidateStateTransitionException()
	    {

	    }

	    public ValidateStateTransitionException(String message, Throwable cause)
	    {
	        super(message, cause);
	    }
	}
	
    @WebFault(name = "GetEnumerationMastersByEnumerationIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetEnumerationMastersByEnumerationIdException extends ApplicationException
    {
        public GetEnumerationMastersByEnumerationIdException()
        {

        }

        public GetEnumerationMastersByEnumerationIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    @WebFault(name = "GetLanguageByIdException")
    @javax.ejb.ApplicationException(rollback = true)
    public static class GetLanguageByIdException extends ApplicationException
    {
        public GetLanguageByIdException()
        {

        }

        public GetLanguageByIdException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

}
