package net.treetechnologies.masters.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.entities.masters.Currency;
import net.treetechnologies.entities.masters.EntityType;
import net.treetechnologies.entities.masters.Enumeration;
import net.treetechnologies.entities.masters.EnumerationMaster;
import net.treetechnologies.entities.masters.Language;
import net.treetechnologies.entities.masters.Status;
import net.treetechnologies.entities.masters.Tag;
import net.treetechnologies.entities.masters.TagType;
import net.treetechnologies.entities.masters.Tags;
import net.treetechnologies.entities.masters.Tenant;
import net.treetechnologies.entities.masters.LifecycleStateTransition;
import net.treetechnologies.masters.services.interfaces.MasterDataManagement;
import net.treetechnologies.masters.services.interfaces.MasterDataManagementLocal;

/**
 * <b>Purpose:</b><br>
 * Service class to provide the retrieval operations for master data<br>
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
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        28-03-2012           Vedanth
 *     -- Base Release
 *
 * 2        29-03-2012           Gopinath
 *     -- Added getTagTypes, getTag, getTags Operations
 *
 * 3        29-03-2012           Srinivas Sanda
 *     -- Added Services for Policy & PolicyMapping
 *        
 * 4        30-03-2012           Gopinath
 *     -- Added getEntityTypeById, getLangaugeById, getStatusById, getTenantById Operations
 *     -- getTagTypeById, getTagById, getTagsById Operations
 *        
 * 5        05-04-2012           Suresh Upparu
 * 	   -- Added create, update, remove methods for the TagType, Tag and Tags.
 *          
 * 6        05-04-2012           Suresh Upparu
 * 	   -- Added getTagMastersByTagTypeId method to get all the TagMasters associated with TagTypeMaster.
 * 
 * 7        25-04-2012           Yoganand JG
 *     -- Added TLogger functionality
 *     -- Added getTagsByEntityId method to get all tags based on entityId
 *      
 * 8        27-04-2012           Gopinath JG
 *     -- Added getTagsByEntityTypeIdWithEntityId method
 *      
 * 9       14-05-2012           Yoganand JG
 *  -- changed return type from Object to void in all remove methods
 * 
 * </pre>
 * 
 * <br>
 */

@SuppressWarnings("unchecked")
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@WebService(endpointInterface = "net.treetechnologies.masters.services.interfaces.MasterDataManagement", name = "MasterDataManagement")
@Remote(
{ MasterDataManagement.class })
@Local(
{ MasterDataManagementLocal.class })
public class MasterDataManagementImpl
{

    @EJB
    MasterDataManagementLocal masterDataManagementLocal;

    @PersistenceContext(unitName = "PC_DEV")
    EntityManager entityManager;
    
    @Resource
    EJBContext ejbContext;

    
    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getTenants to get the all tenant master details from the table tenant_master.
     * </pre>
     * 
     * 
     * @return List<Tenant>
     * @throws ApplicationException
     */

    @RolesAllowed({"default"})
    public List<Tenant> getTenants() throws ApplicationException
    {
        TLogger.debug("Entry getTenants");
        List<Tenant> tenantList = null;
        try
        {
            tenantList = entityManager.createQuery("from "+Tenant.class.getCanonicalName()).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTenantsException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTenantsException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTenants");
        }
        return tenantList;
    }
    
    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getTenantsById to get the tenant master details by tenantId from the table tenant_master.
     * </pre>
     * 
     * @param tenantId
     * @return List<Tenant>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public Tenant getTenantsById(Long tenantId) throws ApplicationException
    {
        TLogger.debug("Entry getTenantsById");
        Tenant tenant = null;
        try
        {
            tenant = entityManager.find(Tenant.class, tenantId);
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTenantsByIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("tenantId : " + tenantId + " does not exist or cannot be null", illegalArgumentException);
            throw new MasterDataManagement.GetTenantsByIdException("tenantId : " + tenantId + " does not exist or cannot be null", illegalArgumentException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTenantsByIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTenantsById");
        }
        return tenant;
    }
    
    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getStatus to get the all status master details from the table status_master.
     * </pre>
     * 
     * @return List<Status>
     * @throws ApplicationException
     */

    @RolesAllowed({"default"})
    public List<Status> getStatus() throws ApplicationException
    {
        TLogger.debug("Entry getStatus");
        List<Status> statusList = null;
        try
        {
            statusList = entityManager.createQuery("from "+Status.class.getCanonicalName()).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetStatusException("Error in initialising entity manager", nullPointerException);
        }

        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetStatusException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getStatus");
        }
        return statusList;
    }

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getStatusById to get the status master details by statusId from the table status_master.
     * </pre>
     * 
     * @param statusId
     * @return List<Status>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public Status getStatusById(Long statusId) throws ApplicationException
    {
        TLogger.debug("Entry getStatusById");
        Status status = null;
        try
        {
            status = entityManager.find(Status.class, statusId);
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetStatusByIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException e)
        {
            TLogger.error("statusId : " + statusId + " does not exist or cannot be null", e);
            throw new MasterDataManagement.GetStatusByIdException("statusId : " + statusId + " does not exist or cannot be null", e);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetStatusByIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getStatusById");
        }
        return status;
    }
    @RolesAllowed({"default"})
    public List<Status> getStatusByIdList(@WebParam(name = "statusId") List<Long> statusIdList) throws ApplicationException
    {
        TLogger.debug("Entry getStatusById");
        List<Status> status = null;
        try
        {
            String list = statusIdList.toString();
            list = list.substring(1, list.length() - 1);
            status = entityManager.createQuery("from Status where statusId in(" + list + ")").getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            if (statusIdList == null)
            {
                TLogger.error("statusIdList : " + statusIdList + " does not exist or cannot be null", nullPointerException);
                throw new MasterDataManagement.GetStatusByIdListException("statusIdList : " + statusIdList + " does not exist or cannot be null", nullPointerException);
            }
            else
            {
                TLogger.error("Error in initialising entity manager", nullPointerException);
                throw new MasterDataManagement.GetStatusByIdListException("Error in initialising entity manager", nullPointerException);
            }
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetStatusByIdListException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getStatusById");
        }
        return status;
    }

    @RolesAllowed({"default"})
    public List<Currency> getCurrency() throws ApplicationException
    {
        TLogger.debug("Entry getCurrency");
        List<Currency> currencyList = null;
        try
        {
            currencyList = entityManager.createQuery("from "+Currency.class.getCanonicalName()).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetCurrencyException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetCurrencyException("Unhandled Exception " + exception.getMessage(), exception);
        }

        finally
        {
            TLogger.debug("Exit getCurrency");
        }
        return currencyList;
    }

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getCurrencyById to get the status master details by statusId from the table status_master.
     * </pre>
     * 
     * @param statusId
     * @return List<Status>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public Currency getCurrencyById(String currencyId) throws ApplicationException
    {
        TLogger.debug("Entry getCurrencyById");
        Currency currency = null;
        try
        {
            currency = entityManager.find(Currency.class, currencyId);
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetCurrencyByIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException e)
        {
            TLogger.error("currencyId : " + currencyId + " does not exist or cannot be null", e);
            throw new MasterDataManagement.GetCurrencyByIdException("currencyId : " + currencyId + " does not exist or cannot be null", e);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetCurrencyByIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getCurrencyById");
        }
        return currency;
    }

	 /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     * getCurrencyValue to get Currency value based on the OperationMode "+" , "-",.
	 * " + " means while inserting into database.
	 * " - " means while reading from database.
     * </pre>
     * 
     * @param value
     * @param currency
	 * @param operationMode
     * @return String
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
	public String getCurrencyValue(String value,Currency currency,String operationMode) throws ApplicationException
	{
	      
       TLogger.debug("Entry getNumberValueByCurrency");
       String fValue = new Long(0).toString();       
       Currency currency2 = entityManager.find(Currency.class,currency.getCurrencyId());
       Integer noOfDecimals = currency2.getNumberOfDecimals().intValue();
       try
       {
           Double doubleValue= Double.parseDouble(value);
           if(operationMode.equals("+"))
           {            
              Double dv=doubleValue * Math.pow(new Double("10"), noOfDecimals);
              fValue=String.valueOf(dv.longValue());
           }
          
           if(operationMode.equals("-"))
           {
                Double dv=doubleValue / Math.pow(new Double("10"), noOfDecimals);
                fValue=String.valueOf(dv);
           }
          
           entityManager.flush();
       }
       catch (NumberFormatException numberFormatException)
       {
           TLogger.error("Number format in string value : "+value, numberFormatException);
           throw new MasterDataManagement.GetCurrencyException("Number format in string value : "+value, numberFormatException);
       }
       catch (PersistenceException persistenceException)
       {
           throw new MasterDataManagement.GetCurrencyException(persistenceException.getMessage(), persistenceException );
       }
       catch (NullPointerException nullPointerException)
       {
           throw new MasterDataManagement.GetCurrencyException("Error in initialising entity manager", nullPointerException );
       }
      
       catch (Exception exception)
       {
           throw new MasterDataManagement.GetCurrencyException("Unhandled Exception" + exception.getMessage(), exception );
       }
       finally
       {
           TLogger.debug("Exit getNumberValueByCurrency");
       }
       return fValue;
	}

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getEntityTypes to get the all entity type master details from the table entity_type_master.
     * </pre>
     * 
     * @return List<EntityType>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public List<EntityType> getEntityTypes() throws ApplicationException
    {
        TLogger.debug("Entry getEntityTypes");
        List<EntityType> entityTypeList = null;
        try
        {
        	
            entityTypeList = entityManager.createQuery("from "+EntityType.class.getCanonicalName()).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in Intialising entity Manager", nullPointerException);
            throw new MasterDataManagement.GetEntityTypesException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception", exception);
            throw new MasterDataManagement.GetEntityTypesException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getEntityTypes");
        }
        return entityTypeList;
    }

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getEntityTypeById to get the entity type master record details from the table entity_type_master using entityTypeId.
     * </pre>
     * 
     * @param entityTypeId
     * @return EntityType
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public EntityType getEntityTypeById(Long entityTypeId) throws ApplicationException
    {
        TLogger.debug("Entry getEntityTypeById");
        EntityType entityType = null;
        try
        {
            entityType = entityManager.find(EntityType.class, entityTypeId);
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetEntityTypeByIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("entityTypeId : " + entityTypeId + " does not exist or cannot be null", illegalArgumentException);
            throw new MasterDataManagement.GetEntityTypeByIdException("entityTypeId : " + entityTypeId + " does not exist or cannot be null", illegalArgumentException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetEntityTypeByIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getEntityTypeById");
        }
        return entityType;

    }
    
    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getLanguages to get the all language master details from the table language_master.
     * </pre>
     * 
     * @return List<Language>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public List<Language> getLanguages() throws ApplicationException
    {
        TLogger.debug("Entry getLanguages");
        List<Language> languageTypeList = null;
        try
        {
            languageTypeList = entityManager.createQuery("from "+Language.class.getCanonicalName()).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetLanguagesException("Error in initialising entity manager", nullPointerException);
        }

        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetLanguagesException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getLanguages");
        }
        return languageTypeList;
    }

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     *  getLanguageById to get the language master record details from the table sd_language using languageId.
     * </pre>
     * 
     * @param languageId
     * @return Language
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public Language getLanguageById(String languageId) throws ApplicationException
    {
        TLogger.debug("Entry getLanguageById");
        Language language = null;
        try
        {
            language = entityManager.find(Language.class, languageId);
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetLanguageByIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("languageId : " + languageId + " does not exist or cannot be null", illegalArgumentException);
            throw new MasterDataManagement.GetLanguageByIdException("languageId : " + languageId + " does not exist or cannot be null", illegalArgumentException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetLanguageByIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getLanguageById");
        }
        return language;

    }

    
    /**
     * <pre>
     * Algorithm
     * </pre>
     * 
     * createTagType to create the new tag type master details in the
     * tag_type_master table.
     * 
     * @param tagType
     * @return TagType
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public TagType createTagType(TagType tagType) throws ApplicationException
    {
        TLogger.debug("Entry createTagType");
        if (tagType == null)
        {
            TLogger.error("TagType entity object cannot be null");
            throw new MasterDataManagement.CreateTagTypeException("TagType entity object cannot be null", null);
        }

        TagType tagType2 = new TagType();
        try
        {
            tagType2.setName(tagType.getName());
            tagType2.setStartDateTime(tagType.getStartDateTime());
            tagType2.setEndDateTime(tagType.getEndDateTime());
            tagType2.setStatus(entityManager.find(Status.class, tagType.getStatus().getStatusId()));
            tagType2.setTenant(entityManager.find(Tenant.class, tagType.getTenant().getTenantId()));
            entityManager.persist(tagType2);
            entityManager.flush();
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.CreateTagTypeException(persistenceException.getMessage(), persistenceException);
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error(nullPointerException.getMessage(), nullPointerException);
            throw new MasterDataManagement.CreateTagTypeException(nullPointerException.getMessage(), nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.CreateTagTypeException("UnHandled Exception" + exception.getMessage(), exception);
        }

        finally
        {
            TLogger.debug("Exit getTag");
        }
        return tagType2;
    }

    /**
     * <pre>
     * Algorithm
     * </pre>
     * 
     * updateTagType to update the existing tag type details in the table
     * tag_Type_master.
     * 
     * @param tagType
     * @return TagType
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public TagType updateTagType(TagType tagType) throws ApplicationException
    {
        TLogger.debug("Entry createTagType");
        TagType tagType2 = null;
        if (tagType == null)
        {
            TLogger.error("TagType entity cannot be null");
            throw new MasterDataManagement.UpdateTagTypeException("TagType entity cannot be null", null);
        }
        try
        {
            Class<TagType> classObject = TagType.class;
            String operationName = classObject.getName() + "." + new Exception().getStackTrace()[0].getMethodName();
            tagType2 = entityManager.find(TagType.class, tagType.getTagTypeId());
            validateOperation(LIFECYCLEID, operationName, tagType2.getStatus().getStatusId());
            tagType2.setName(tagType.getName());
            tagType2.setStartDateTime(tagType.getStartDateTime());
            tagType2.setEndDateTime(tagType.getEndDateTime());
            tagType2.setStatus(entityManager.find(Status.class, tagType.getStatus().getStatusId()));
            tagType2.setTenant(entityManager.find(Tenant.class, tagType.getTenant().getTenantId()));
            entityManager.flush();
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.UpdateTagTypeException(persistenceException.getMessage(), persistenceException);
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.UpdateTagTypeException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.UpdateTagTypeException("UnHandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTag");
        }
        return tagType2;
    }

    /**
     * <pre>
     * Algorithm
     * </pre>
     * 
     * removeTagType to remove the existing tag type details in the table
     * tag_Type_master.
     * 
     * @param tagTypeId
     * @return void
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public void removeTagType(Long tagTypeId) throws ApplicationException
    {
        TLogger.debug("Entry removeTagType");
        try
        {
            Class<TagType> classObject = TagType.class;
            String operationName = classObject.getName() + "." + new Exception().getStackTrace()[0].getMethodName();

            TagType tagType = entityManager.find(TagType.class, tagTypeId);
            validateOperation(LIFECYCLEID, operationName, tagType.getStatus().getStatusId());
            if (tagType != null)
            {
                entityManager.remove(tagType);

            }
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.RemoveTagTypeException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("tagTypeId : " + tagTypeId + " does not exist or cannot be null", illegalArgumentException);
            throw new MasterDataManagement.RemoveTagTypeException("tagTypeId : " + tagTypeId + " does not exist or cannot be null", illegalArgumentException);
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.RemoveTagTypeException(persistenceException.getMessage(), persistenceException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.RemoveTagTypeException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit removeTagType");
        }
    }

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     * getTagTypes to get the all tag type details from the table tag_type_master.
     * </pre>
     * 
     * @return List<TagType>
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public List<TagType> getTagTypes() throws ApplicationException
    {
        TLogger.debug("Entry getTagTypes");
        List<TagType> tagTypeList = null;
        try
        {
            tagTypeList = entityManager.createQuery("from "+TagType.class.getCanonicalName()).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagTypesException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagTypesException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTagTypes");
        }
        return tagTypeList;
    }

    /**
     * <pre>
     * Algorithm
     * </pre>
     * 
     * getTagTypeById to get the tag type master details from the
     * tag_type_master table.
     * 
     * @param tagTypeId
     * @return TagType
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public TagType getTagTypeById(Long tagTypeId) throws ApplicationException
    {
        TLogger.debug("Entry getTagTypeById");
        TagType tagType = null;
        try
        {
            tagType = entityManager.find(TagType.class, tagTypeId);
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagTypeByIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException e)
        {
            TLogger.error("tagTypeId : " + tagTypeId + " does not exist or cannot be null", e);
            throw new MasterDataManagement.GetTagTypeByIdException("tagTypeId : " + tagTypeId + " does not exist or cannot be null", e);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagTypeByIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTagTypeById");
        }
        return tagType;

    }

    /**
     * <pre>
     * Algorirhm
     * </pre>
     * 
     * createTag to create new tag master detials in the table tag_master.
     * 
     * @param tag
     * @return Tag
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public Tag createTag(Tag tag) throws ApplicationException
    {
        TLogger.debug("Entry createTag");
        if (tag == null)
        {
            TLogger.error("Tag entity object cannot be null");
            throw new MasterDataManagement.CreateTagTypeException("Tag entity object cannot be null", null);
        }

        Tag tag2 = new Tag();
        try
        {
            tag2.setName(tag.getName());
            tag2.setStartDateTime(tag.getStartDateTime());
            tag2.setEndDateTime(tag.getEndDateTime());
            tag2.setTagType(entityManager.find(TagType.class, tag.getTagType().getTagTypeId()));
            tag2.setStatus(entityManager.find(Status.class, tag.getStatus().getStatusId()));
            tag2.setTenant(entityManager.find(Tenant.class, tag.getTenant().getTenantId()));
            entityManager.persist(tag2);
            entityManager.flush();
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.CreateTagException(persistenceException.getMessage(), persistenceException);
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error(nullPointerException.getMessage(), nullPointerException);
            throw new MasterDataManagement.CreateTagException(nullPointerException.getMessage(), nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.CreateTagException("Unhandled Exception" + exception.getMessage(), exception);
        }

        finally
        {
            TLogger.debug("Exit createTag");
        }
        return tag2;
    }

    /**
     * <pre>
     * Algorirhm
     * </pre>
     * 
     * updateTag to update the existing tag master detials in the table
     * tag_master.
     * 
     * @param tag
     * @return Tag
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public Tag updateTag(Tag tag) throws ApplicationException
    {
        TLogger.debug("Entry updateTag");
        Tag tag2 = null;
        if (tag == null)
        {
            TLogger.error("Tag entity object is null");
            throw new MasterDataManagement.UpdateTagException("Tag entity object is null", null);
        }
        try
        {
            Class<Tag> classObject = Tag.class;
            String operationName = classObject.getName() + "." + new Exception().getStackTrace()[0].getMethodName();
            tag2 = entityManager.find(Tag.class, tag.getTagId());
            validateOperation(LIFECYCLEID, operationName, tag2.getStatus().getStatusId());
            tag2.setName(tag.getName());
            tag2.setStartDateTime(tag.getStartDateTime());
            tag2.setEndDateTime(tag.getEndDateTime());
            tag2.setTagType(entityManager.find(TagType.class, tag.getTagType().getTagTypeId()));
            tag2.setStatus(entityManager.find(Status.class, tag.getStatus().getStatusId()));
            tag2.setTenant(entityManager.find(Tenant.class, tag.getTenant().getTenantId()));
            entityManager.flush();
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.UpdateTagException(persistenceException.getMessage(), persistenceException);
        }
        catch (NullPointerException nullPointerException)
        {

            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.UpdateTagException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.UpdateTagException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit updateTag");
        }
        return tag2;
    }

    /**
     * <pre>
     * Algorirhm
     * </pre>
     * 
     * removeTag to create new tag master detials in the table tag_master.
     * 
     * @param tagId
     * @return void
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public void removeTag(Long tagId) throws ApplicationException
    {
        TLogger.debug("Entry removeTag");
        try
        {
            Class<Tag> classObject = Tag.class;
            String operationName = classObject.getName() + "." + new Exception().getStackTrace()[0].getMethodName();
            Tag tag = entityManager.find(Tag.class, tagId);
            validateOperation(LIFECYCLEID, operationName, tag.getStatus().getStatusId());
            if (tag != null)
            {
                entityManager.remove(tag);

            }
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.RemoveTagException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("tagId : " + tagId + " does not exist or cannot be null", illegalArgumentException);
            throw new MasterDataManagement.RemoveTagException("tagId : " + tagId + " does not exist or cannot be null", illegalArgumentException);
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.RemoveTagException(persistenceException.getMessage(), persistenceException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.RemoveTagException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit removeTag");
        }

    }

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     * getTag to get the all tag details from the table tag_master.
     * </pre>
     * 
     * @return List<Tag>
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public List<Tag> getTag() throws ApplicationException
    {
        TLogger.debug("Entry getTag");
        List<Tag> tagList = null;
        try
        {
            tagList = entityManager.createQuery("from "+Tag.class.getCanonicalName()).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagMasterException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagMasterException("Unhandled Exception " + exception.getMessage(), exception);
        }

        finally
        {
            TLogger.debug("Exit getTag");
        }
        return tagList;
    }

    /**
     * <pre>
     * Algorithm
     * </pre>
     * 
     * getTagById to get the tag master details from the tag_master table.
     * 
     * @param tagId
     * @return Tag
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public Tag getTagById(Long tagId) throws ApplicationException
    {
        TLogger.debug("Entry getTagById");
        Tag tag = null;
        try
        {
            tag = entityManager.find(Tag.class, tagId);
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagByIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException e)
        {
            TLogger.error("tagId : " + tagId + " does not exist or cannot be null", e);
            throw new MasterDataManagement.GetTagByIdException("tagId : " + tagId + " does not exist or cannot be null", e);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagByIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTagById");
        }
        return tag;
    }

    /**
     * <pre>
     * Algorithm
     * </pre>
     * 
     * getTagMastersByTagTypeId to get all the existing tagMasters for a given
     * tagTypeId
     * 
     * @param tagTypeId
     * @return Set<Tag>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public Set<Tag> getTagMastersByTagTypeId(Long tagTypeId) throws ApplicationException
    {
        TLogger.debug("Entry getTagMastersByTagTypeId");
        TagType tagType = null;
        Set<Tag> tagMasters = new HashSet<Tag>();
        try
        {
            tagType = entityManager.find(TagType.class, tagTypeId);
            tagMasters = tagType.getTags();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagMastersByTagTypeIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException e)
        {
            TLogger.error("tagTypeId : " + tagTypeId + " does not exist or cannot be null", e);
            throw new MasterDataManagement.GetTagMastersByTagTypeIdException("tagTypeId : " + tagTypeId + " does not exist or cannot be null", e);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagMastersByTagTypeIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTagMastersByTagTypeId");
        }
        return tagMasters;
    }

    /**
     * <pre>
     * Algorirhm
     * </pre>
     * 
     * createTags to create new tags detials in the table tags.
     * 
     * @param tags
     * @return Tags
     * @ApplicationExcepteion
     */
    @RolesAllowed({"default"})
    public Tags createTags(Tags tags) throws ApplicationException
    {

        TLogger.debug("Entry createTags");
        if (tags == null)
        {
            TLogger.error("Tags entity object cannot be null");
            throw new MasterDataManagement.CreateTagsException("Tags entity object cannot be null", null);
        }
        Tags tags2 = new Tags();
        try
        {
            tags2.setEntityId(tags.getEntityId());
            tags2.setTagValue(tags.getTagValue());
            tags2.setTagMaster(entityManager.find(Tag.class, tags.getTagMaster().getTagId()));
            tags2.setEntityType(entityManager.find(EntityType.class, tags.getEntityType().getEntityTypeId()));
            entityManager.persist(tags2);
            entityManager.flush();
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.CreateTagsException(persistenceException.getMessage(), persistenceException);
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error(nullPointerException.getMessage(), nullPointerException);
            throw new MasterDataManagement.CreateTagsException(nullPointerException.getMessage(), nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.CreateTagsException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit createTags");
        }
        return tags2;
    }

    /**
     * <pre>
     * Algorirhm
     * </pre>
     * 
     * updateTags to update the existing tags in the table tags.
     * 
     * @param tagsId
     * @return Tags
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public Tags updateTags(Tags tags) throws ApplicationException
    {
        TLogger.debug("Entry updateTags");
        Tags tags2 = null;
        if (tags == null)
        {
            TLogger.error("Tags entity object is null");
            throw new MasterDataManagement.UpdateTagsException("Tags entity object is null", null);
        }
        try
        {

            tags2 = entityManager.find(Tags.class, tags.getTagId());

            tags2.setEntityId(tags.getEntityId());
            tags2.setTagValue(tags.getTagValue());
            tags2.setTagMaster(entityManager.find(Tag.class, tags.getTagMaster().getTagId()));
            tags2.setEntityType(entityManager.find(EntityType.class, tags.getEntityType().getEntityTypeId()));
            entityManager.flush();
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.UpdateTagsException(persistenceException.getMessage(), persistenceException);
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.UpdateTagsException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.UpdateTagsException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit updateTags");
        }
        return tags2;
    }

    /**
     * <pre>
     * Algorirhm
     * </pre>
     * 
     * removeTags to remove the existing tags in the table tags.
     * 
     * @param tagsId
     * @return void
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public void removeTags(Long tagsId) throws ApplicationException
    {
        TLogger.debug("Entry removeTags");
        try
        {

            Tags tags = entityManager.find(Tags.class, tagsId);
            if (tags != null)
            {
                entityManager.remove(tags);

            }
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.RemoveTagsException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("tagsId : " + tagsId + " does not exist or cannot be null", illegalArgumentException);
            throw new MasterDataManagement.RemoveTagsException("tagsId : " + tagsId + " does not exist or cannot be null", illegalArgumentException);
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.RemoveTagsException(persistenceException.getMessage(), persistenceException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.RemoveTagsException("Unhandled Exception " + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit removeTags");
        }
    }

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     * getTags to get the all tag details from the table tags.
     * </pre>
     * 
     * @return List<Tags>
     * @ApplicationException
     */
    @RolesAllowed({"default"})
    public List<Tags> getTags() throws ApplicationException
    {
        TLogger.debug("Entry getTags");
        List<Tags> tagsList = new ArrayList<Tags>();
        try
        {
            tagsList = entityManager.createQuery("from "+Tags.class.getCanonicalName()).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagsException("Error in initialising entity manager", nullPointerException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagsException("Unhandled Exception " + exception.getMessage(), exception);
        }

        finally
        {
            TLogger.debug("Exit getTags");
        }
        return tagsList;
    }

    /**
     * <pre>
     * Algorithm
     * </pre>
     * 
     * getTagsById to get the existing tags details in the tags table.
     * 
     * @param tagsId
     * @return Tags
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public Tags getTagsById(Long tagsId) throws ApplicationException
    {
        TLogger.debug("Entry getTagsById");
        Tags tags = null;
        try
        {
            tags = entityManager.find(Tags.class, tagsId);
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagsByIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException e)
        {
            TLogger.error("tagsId : " + tagsId + " does not exist or cannot be null", e);
            throw new MasterDataManagement.GetTagsByIdException("tagsId : " + tagsId + " does not exist or cannot be null", e);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagsByIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTagsById");
        }
        return tags;
    }

    /**
     * <pre>
     * Algorithm
     * </pre>
     * 
     * getTagsByEntityId to get all the existing tags for a given tagTypeId
     * 
     * @param entityId
     * @return List<Tags>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public List<Tags> getTagsByEntityId(Long entityId) throws ApplicationException
    {
        TLogger.debug("Entry getTagsByEntityId");
        List<Tags> tagsList = new ArrayList<Tags>();
        try
        {
            tagsList = entityManager.createQuery("from Tags where entityId=" + entityId).getResultList();
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagMastersByTagTypeIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException e)
        {
            TLogger.error("entityId : " + entityId + " does not exist or cannot be null", e);
            throw new MasterDataManagement.GetTagMastersByTagTypeIdException("tagTypeId : " + entityId + " does not exist or cannot be null", e);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagMastersByTagTypeIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTagMastersByTagTypeId");
        }
        return tagsList;
    }

    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     * getTagsByEntityIdWithEntityType to get the tags details based on entityTypeId,entityId  from the table tags.
     * </pre>
     * 
     * @param entityTypeId
     * @param entityId
     * @return List<Tags>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public List<Tags> getTagsByEntityIdWithEntityType(Long entityId, Long entityTypeId) throws ApplicationException
    {
        TLogger.debug("Entry getTagsByEntityIdWithEntityType");
        List<Tags> tagsList = new ArrayList<Tags>();
        try
        {
            Query query = entityManager.createQuery("from "+Tags.class.getCanonicalName()+" where entityType = " +entityTypeId  +" and entityId = "+entityId);
            /*query.setParameter(1, entityManager.find(EntityType.class, entityTypeId));
            query.setParameter(2, entityId);*/
            tagsList = query.getResultList();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetTagsByEntityIdWithEntityTypeException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("entityTypeId : " + entityTypeId + " or entityId : " + entityId + " does not exist or cannot be null", illegalArgumentException);
            throw new MasterDataManagement.GetTagsByEntityIdWithEntityTypeException("entityTypeId : " + entityTypeId + " or entityId : " + entityId + " does not exist or cannot be null",
                    illegalArgumentException );
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetTagsByEntityIdWithEntityTypeException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getTagsByEntityIdWithEntityType");
        }
        return tagsList;
    }

    public List<Status> getStateTransitions(Long lifecycleId, Long status) throws ApplicationException
    {
        TLogger.debug("Entry getCharacteristicValuesRelation");
        List<Status> statusList = new ArrayList<Status>();
        try
        {
            Query query = entityManager.createQuery("from net.treetechnologies.entities.masters.LifecycleStateTransition lifecycleStateTransition where lifecycleStateTransition.lifeCycleId = ? and lifecycleStateTransition.sourceStatus = ? ");
            query.setParameter(1, lifecycleId);
            query.setParameter(2, status);
            List<LifecycleStateTransition> lifecycleStateTransitionList = query.getResultList();
            if (lifecycleStateTransitionList.size() != 0)
            {
                for (LifecycleStateTransition resultList : lifecycleStateTransitionList)
                {
                    statusList.add(entityManager.find(Status.class, resultList.getTargetStatus()));
                }
            }
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error(nullPointerException.getMessage(), nullPointerException);
            throw new MasterDataManagement.GetStateTransitionsException(nullPointerException.getMessage(), nullPointerException);
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.GetStateTransitionsException(persistenceException.getMessage(), persistenceException);
        }
        catch (Exception exception)
        {
            TLogger.error("UnHandled Exception" + exception.getMessage(), exception);
            throw new MasterDataManagement.GetStateTransitionsException("UnHandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getCharacteristicValuesRelation");
        }
        return statusList;
    }
    
    /*public void validateOperation(Long lifecycleId, String operationName, Long status) throws ApplicationException
    {
        TLogger.debug("Entry validateOperation");
        try
        {
            Query query = entityManager.createQuery("from LifecycleOperations lifecycleOperations where lifecycleOperations.lifecycleId = ? and lifecycleOperations.operation = ? and lifecycleOperations.status = ? and lifecycleOperations.allow = ?");
            query.setParameter(1, entityManager.find(LifecycleMaster.class, lifecycleId));
            query.setParameter(2, operationName);
            query.setParameter(3, entityManager.find(Status.class, status));
            query.setParameter(4, ALLOW);
            query.getResultList();
            
            if(query.getResultList().size() == 0)
            {
                TLogger.error("Unable to do operation", null);
                throw new ValidateOperationException("Unable to do operation", null); 
            }
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error(nullPointerException.getMessage(), nullPointerException);
            throw new ValidateOperationException(nullPointerException.getMessage(), nullPointerException);
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new ValidateOperationException(persistenceException.getMessage(), persistenceException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("LifecycleId : " + lifecycleId + " OperationName : " + operationName + " Status : " + status + " does not exist or cannot be null ", illegalArgumentException);
            throw new ValidateOperationException("LifecycleId : " + lifecycleId + " OperationName : " + operationName + " Status : " + status + " does not exist or cannot be null ", illegalArgumentException);
        }
        catch (Exception exception)
        {
            TLogger.error("UnHandled Exception" + exception.getMessage(), exception);
            throw new ValidateOperationException("UnHandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit validateOperation");
        }

    }*/

    public void validateOperation(Long lifecycleId, String operationName, Long status) throws ApplicationException
    {
        TLogger.debug("Entry validateOperation");
        try
        {
            TLogger.debug("validateOperation params : lifecycleId : " + lifecycleId + " : operationName : " + operationName + " : status : " + status);
            /*Query query = entityManager.createQuery("from LifecycleOperations lifecycleOperations where lifecycleOperations.lifecycleId = ? and lifecycleOperations.operation = ? and lifecycleOperations.status = ? and lifecycleOperations.allow = ?");
            query.setParameter(1, entityManager.find(LifecycleMaster.class, lifecycleId));
            query.setParameter(2, operationName);
            query.setParameter(3, entityManager.find(Status.class, status));
            query.setParameter(4, ALLOW);
            query.getResultList();

            if (query.getResultList().size() == 0)
            {
            	TLogger.error("Unable to do operation", null);
            	throw new ValidateOperationException("Unable to do operation", null);
            }*/
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error(nullPointerException.getMessage(), nullPointerException);
            throw new MasterDataManagement.ValidateOperationException(nullPointerException.getMessage(), nullPointerException);
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.ValidateOperationException(persistenceException.getMessage(), persistenceException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("LifecycleId : " + lifecycleId + " OperationName : " + operationName + " Status : " + status + " does not exist or cannot be null ", illegalArgumentException);
            throw new MasterDataManagement.ValidateOperationException("LifecycleId : " + lifecycleId + " OperationName : " + operationName + " Status : " + status + " does not exist or cannot be null ",
                    illegalArgumentException);
        }
        catch (Exception exception)
        {
            TLogger.error("UnHandled Exception" + exception.getMessage(), exception);
            throw new MasterDataManagement.ValidateOperationException("UnHandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit validateOperation");
        }

    }

    public void validateStateTransition(Long lifecycleId, Long fromStatus, Long toStatus) throws ApplicationException
    {
        TLogger.debug("Entry validateStateTransition");
        try
        {
            Query query = entityManager.createQuery("from net.treetechnologies.entities.masters.LifecycleStateTransition lifecycleStateTransition where lifecycleStateTransition.lifeCycleId = ? and lifecycleStateTransition.sourceStatus = ? and lifecycleStateTransition.targetStatus = ?  and lifecycleStateTransition.allow = ?");
            query.setParameter(1, lifecycleId);
            query.setParameter(2, fromStatus);
            query.setParameter(3, toStatus);
            query.setParameter(4, ALLOW);
            query.getResultList();
            if(query.getResultList().size() == 0)
            {
                TLogger.error("Unable to Validate Operation",null);
                throw new MasterDataManagement.ValidateStateTransitionException("Unable to Validate Operation",null);
            }
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error(nullPointerException.getMessage(), nullPointerException);
            throw new MasterDataManagement.ValidateStateTransitionException(nullPointerException.getMessage(), nullPointerException);
        }
        catch (PersistenceException persistenceException)
        {
            TLogger.error(persistenceException.getMessage(), persistenceException);
            throw new MasterDataManagement.ValidateStateTransitionException(persistenceException.getMessage(), persistenceException);
        }
        catch (Exception exception)
        {
            TLogger.error("UnHandled Exception" + exception.getMessage(), exception);
            throw new MasterDataManagement.ValidateStateTransitionException("UnHandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit validateStateTransition");
        }

    }
    
    /**
     * <b>Algorithm:</b>
     * 
     * <pre>
     * getEnumerationMastersByEnumerationId to get the list of Enumeration details from the table enumerations.
     * </pre>
     * 
     * @param enumerationMasterId
     * @return List<Enumeration>
     * @throws ApplicationException
     */
    @RolesAllowed({"default"})
    public List<Enumeration> getEnumerationMastersByEnumerationId(Long enumerationMasterId) throws ApplicationException
    {
        TLogger.debug("Entry getEnumerationMastersByEnumerationId");
        List enumerations = null;
        EnumerationMaster enumerationMaster = null;
        try
        {
            enumerationMaster = entityManager.find(EnumerationMaster.class, enumerationMasterId);
            enumerations = new ArrayList<Enumeration>();
            enumerations.addAll(enumerationMaster.getEnumerations());
            entityManager.flush();
        }
        catch (NullPointerException nullPointerException)
        {
            TLogger.error("Error in initialising entity manager", nullPointerException);
            throw new MasterDataManagement.GetEnumerationMastersByEnumerationIdException("Error in initialising entity manager", nullPointerException);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            TLogger.error("enumerationMasterId : " + enumerationMasterId + " does not exist or cannot be null", illegalArgumentException);
            throw new MasterDataManagement.GetEnumerationMastersByEnumerationIdException("enumerationMasterId : " + enumerationMasterId + " does not exist or cannot be null", illegalArgumentException);
        }
        catch (Exception exception)
        {
            TLogger.error("Unhandled Exception " + exception.getMessage(), exception);
            throw new MasterDataManagement.GetEnumerationMastersByEnumerationIdException("Unhandled Exception" + exception.getMessage(), exception);
        }
        finally
        {
            TLogger.debug("Exit getEnumerationMastersByEnumerationId");
        }
        return enumerations;
    }

    private Long LIFECYCLEID = 1L;
    private final Long ALLOW = new Long(1);
}
