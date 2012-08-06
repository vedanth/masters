package net.treetechnologies.ui.form;

/* Basic Java Imports */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* VaadinUtil Imports */
import net.treetechnologies.common.configuration.TConfig;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.common.ui.util.CustomDropDown;
import net.treetechnologies.common.ui.notification.UINotifications;
import net.treetechnologies.common.ui.util.CommonException;
import net.treetechnologies.common.ui.util.CommonManagement;

/* Project Imports */

/* ORM Imports */
import net.treetechnologies.entities.schema.masters.Status;
import net.treetechnologies.entities.schema.masters.TagType;
import net.treetechnologies.entities.schema.masters.Tenant;
import net.treetechnologies.masters.services.interfaces.masterdatamanagement.ApplicationException;
import net.treetechnologies.ui.application.TagTypeMasterApplication;
import net.treetechnologies.ui.util.Constants;
import net.treetechnologies.ui.util.ConstantsBean;
import net.treetechnologies.ui.util.LangTranslator;

/* Vaadin Imports */
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
/**  
* <b>Purpose:</b><br>
* <b>To add TagType or edit TagType</b><br>
*
* <b>DesignReference:</b><br>
* <br><br>
*
* <b>CopyRights:</b><br>
* Tree Technologies 2012<br><br>
*
* <b>RevisionHistory:</b>
* <pre><b>
* Sl No   Modified Date        Author</b>
* ==============================================
* 1        09-04-2012		   VV Nagesh 
* 	-- Base Release   
* </pre><br>
*/
public class TagTypeForm extends Form implements ClickListener
{
	private static final long serialVersionUID = 1L;
	public TagTypeForm(final TagTypeMasterApplication typeMasterApplication,TagType tagTypeObject,TConfig config,String langId,String tabName) 
	{
	    try
	    {
	        TLogger.debug("Entry in TagTypeForm - TagTypeForm Constructor");
    	    this._tagTypeMasterApplication = typeMasterApplication;
    	    this._tagType = tagTypeObject;
            this._tabName = tabName;
            this._languageId = langId;
            
    		getLayout().setMargin(true); 
    		setSizeFull();
    		setWriteThrough(false);
    		
    		LangTranslator translator = new LangTranslator(config, langId);
            final String LAN_NAME = translator.getLabel(NAME, TAG_TYPE_MASTER);
            final String LAN_STARTDATE = translator.getLabel(START_DATE, TAG_TYPE_MASTER);
            final String LAN_ENDDATE = translator.getLabel(END_DATE, TAG_TYPE_MASTER);
            final String LAN_STATUS = translator.getLabel(STATUS, TAG_TYPE_MASTER);
            final String LAN_TENANT = translator.getLabel(TENANT, TAG_TYPE_MASTER);
                
            final String LAN_NAME_REQ = translator.getMandatoryMessage(NAME, TAG_TYPE_MASTER);
            final String LAN_NAME_LENGTH = translator.getLength(NAME, TAG_TYPE_MASTER);
            final int LAN_NAME_LENGTH_MIN = Integer.parseInt(translator.getMinLength(NAME, TAG_TYPE_MASTER).toString());
            final int LAN_NAME_LENGTH_MAX = Integer.parseInt(translator.getMaxLength(NAME, TAG_TYPE_MASTER).toString());
            
            final String LAN_STARTDATE_REQ = translator.getMandatoryMessage(START_DATE, TAG_TYPE_MASTER);
            final String LAN_ENDDATE_REQ = translator.getMandatoryMessage(END_DATE, TAG_TYPE_MASTER);
            final String LAN_STATUS_REQ = translator.getMandatoryMessage(STATUS, TAG_TYPE_MASTER);
            final String LAN_TENANT_REQ = translator.getMandatoryMessage(TENANT, TAG_TYPE_MASTER);
               
            final String LAN_SAVE = translator.getValue(Constants.SUBMIT);
            final String LAN_CANCEL = translator.getValue(Constants.CANCEL);
            
            Constants constants = new Constants();
            ConstantsBean constantsBean = constants.getDefaultValues();
               
    		/*Defining the form components*/
    		_name = new TextField(LAN_NAME);
    		_name.setWidth(Constants.FIELD_WIDTH);
    		_startDate = new DateField(LAN_STARTDATE);
    		_startDate.setDateFormat(constantsBean.getDisplayDateFormat());
    		_startDate.setWidth(Constants.FIELD_WIDTH);
    		_endDate = new DateField(LAN_ENDDATE);
    		_endDate.setDateFormat(constantsBean.getDisplayDateFormat());
    		_endDate.setWidth(Constants.FIELD_WIDTH);
    		_tenant = new CustomDropDown(LAN_TENANT,this._languageId,this._tagTypeMasterApplication.MAIN_WINDOW);
    		_tenant.setWidth(Constants.FIELD_WIDTH);
    		_status = new CustomDropDown(LAN_STATUS,this._languageId,this._tagTypeMasterApplication.MAIN_WINDOW);
    		_status.setWidth(Constants.FIELD_WIDTH);
    		_save = new Button(LAN_SAVE,(ClickListener) this);
    		_cancel = new Button(LAN_CANCEL,(ClickListener) this);
		
    		/* Retrieving the tenant master data from database */
    		List<Tenant> tenantList = CommonManagement.masterDataManagement.getTenants();
    		Iterator<Tenant> tenantItr = tenantList.iterator();
    		String tenantName = null;
    		Long tenantId = 0L;
    		while(tenantItr.hasNext())
    		{	
    			Tenant tenantObject = (Tenant)tenantItr.next();
    			tenantName = tenantObject.getName();
    			tenantId = tenantObject.getTenantId();
    			_tenant.addItem(tenantId);
    			_tenant.setItemCaption(tenantId, tenantName);
    		}
    		_tenant.sort();
    		/* Retrieving the status master data from database */
    		List<Status> statusList = CommonManagement.masterDataManagement.getStatus();
    		Iterator<Status> statusItr = statusList.iterator();
    		String statusName = null;
    		//Long statusId = 0L;
    		while(statusItr.hasNext())
    		{	
    			Status statusObject = (Status)statusItr.next();
    			statusName = statusObject.getName();
    			_status.addItem(statusObject.getStatusId());
    			_status.setItemCaption(statusObject.getStatusId(), statusName);
    		}	
    		_status.sort();
    		/* Setting the mandatory fields to the form*/
    		_name.addValidator(new StringLengthValidator(LAN_NAME_LENGTH,LAN_NAME_LENGTH_MIN,LAN_NAME_LENGTH_MAX,false));
    		
    		_name.setRequired(true);
    		_name.setNullSettingAllowed(true);
    		_name.setRequiredError(LAN_NAME_REQ);
    		_startDate.setRequired(true);
    		_startDate.setRequiredError(LAN_STARTDATE_REQ);
    		_endDate.setRequired(true);
    		_endDate.setRequiredError(LAN_ENDDATE_REQ);
    		_status.setRequired(true);
    		_status.setRequiredError(LAN_STATUS_REQ);
    		_tenant.setRequired(true);
    		_tenant.setRequiredError(LAN_TENANT_REQ);
    	
    		/* Adding the fields to the form*/
    		addField(NAME, _name);
    		addField(START_DATE, _startDate);
    		addField(END_DATE, _endDate);
    		addField(STATUS, _status);
    		addField(TENANT,_tenant);
    		if(_tabName.equalsIgnoreCase(Constants.EDIT))
            {
                String dbTagType_Name = null;
                Date dbStartDate = null;
                Date dbEndDate = null;
                if(_tagType != null)
                {
                dbTagType_Name = _tagType.getName();
                dbStartDate = (Date) _tagType.getStartDateTime();
                dbEndDate = (Date) _tagType.getEndDateTime();
                
                /*Setting the default values too the form*/
                _name.setValue(dbTagType_Name);
                _startDate.setValue(dbStartDate);
                _endDate.setValue(dbEndDate);
                _tenant.select(_tagType.getTenantId());
                _status.select(_tagType.getStatusId());
                }
            }
            else
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(constantsBean.getDisplayDateFormat());
                Date _defaultEndDate = simpleDateFormat.parse(constantsBean.getDefaultEndDate());
                _endDate.setValue(_defaultEndDate);
				_startDate.setValue(new Date());
                _tenant.select(constantsBean.getDefaultTenant());
                _status.select(constantsBean.getDefaultStatus());
            }
    		
    		_horizontalLayout = new HorizontalLayout();
    		_horizontalLayout.setMargin(true);
    		_horizontalLayout.setSpacing(true);
    		_horizontalLayout.addComponent(_save);
    		_horizontalLayout.addComponent(_cancel);
    		_horizontalLayout.setSpacing(true);
    		setFooter(_horizontalLayout);
    		_horizontalLayout.setVisible(true);
	    }
	    catch(ParseException dateParsingException)
        {
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW,
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,Constants.DATE_PARSE_EXCEPTION,"");
            uiNotifications.getNotification();
        }
	    catch(net.treetechnologies.masters.services.interfaces.masterdatamanagement.ApplicationException applicationException)
        { 
	        TLogger.error("Exception in TagTypeForm - TagTypeForm Constructor",applicationException);
            CommonException commonException = new CommonException(applicationException);
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,commonException.getApplicationException());
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagTypeForm - TagTypeForm Constructor",exception);
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW,
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        finally
        {
            TLogger.debug("Exit in TagTypeForm - TagTypeForm Constructor");
        }
	}
	
    public void buttonClick(ClickEvent event) 
	{
		try
		{
		    TLogger.debug("Entry in TagTypeForm - buttonClick Event");
			 Button source = event.getButton();
			 if (source == _save)
		     {
				 commit();
				 Date startDate = (Date)this.getField(START_DATE).getValue();
			   	 Date endDate = (Date)this.getField(END_DATE).getValue();
			   	 String name = this.getField(NAME).toString().trim();
			   	 Long tenantId = (Long)this.getField(TENANT).getValue();
	             Long statusId = (Long)this.getField(STATUS).getValue();
	             if(startDate.after(endDate))
	                {
	                    throw new TagTypeDateValidationException(Constants.DATE_VALIDATION_EXCEPTION,null);
	                }
	             name = (name.equals("")?null:name);    
			   	 if(_tabName.equalsIgnoreCase(Constants.EDIT))
			     {
                     _tagType.setStartDateTime(startDate);
                     _tagType.setEndDateTime(endDate);
	    	         _tagType.setName(name);
		        	 _tagType.setStatusId(statusId);
		        	 _tagType.setTenantId(tenantId);
                     TagType updatedObj = CommonManagement.masterDataManagement.updateTagType(_tagType);
                     Status status = CommonManagement.masterDataManagement.getStatusById(updatedObj.getStatusId());
                     Tenant tenant = CommonManagement.masterDataManagement.getTenantsById(updatedObj.getTenantId());
                     _tagTypeMasterApplication.removelayout.removeAllComponents();
                     
                     _tagTypeMasterApplication.table.removeItem(updatedObj);
                     
                     _tagTypeMasterApplication.table.addItem(new Object[]{updatedObj.getTagTypeId().toString(),updatedObj.getName(),
                                         updatedObj.getStartDateTime().toString(),updatedObj.getEndDateTime().toString(),
                                         status.getName(),tenant.getName()},updatedObj);
                     _tagTypeMasterApplication.table.refreshRowCache();
                     _tagTypeMasterApplication.tabSheet.setSelectedTab(_tagTypeMasterApplication.resultlayout);
                     UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, _languageId, 
                                                                         Notification.TYPE_HUMANIZED_MESSAGE,Constants.RECORD_UPDATED_SUCCESSFULLY,"");
                     uiNotifications.getNotification(); 
			     }
				 /* Handling Add form*/
			     else
			     {
			          TagType tagType = new TagType();
		        	  tagType.setEndDateTime(endDate);
		        	  tagType.setStartDateTime(startDate);
		        	  tagType.setName(name);
		        	  tagType.setStatusId(statusId);
		        	  tagType.setTenantId(tenantId);
					  TagType returnObject = CommonManagement.masterDataManagement.createTagType(tagType);
					  Status status = CommonManagement.masterDataManagement.getStatusById(returnObject.getStatusId());
					  Tenant tenant = CommonManagement.masterDataManagement.getTenantsById(returnObject.getTenantId());
					  _tagTypeMasterApplication.table.addItem(new Object[]{returnObject.getTagTypeId().toString(),returnObject.getName(),
					          returnObject.getStartDateTime().toString(),returnObject.getEndDateTime().toString(),
					          status.getName(),tenant.getName()},returnObject);
					  _tagTypeMasterApplication.table.refreshRowCache();
					  _tagTypeMasterApplication.tabSheet.setSelectedTab(_tagTypeMasterApplication.resultlayout);
			          UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW,
			                                                _languageId, Notification.TYPE_HUMANIZED_MESSAGE,Constants.RECORD_CREATED_SUCCESSFULLY,"");
                      uiNotifications.getNotification(); 
			       }
		       }
		       else if (source == _cancel)
			   {
		    	  discard();
		    	  _tagTypeMasterApplication.tabSheet.setSelectedTab(_tagTypeMasterApplication.resultlayout);
		          this.getFooter().setVisible(true);
		          this.getLayout().setVisible(true);
		       }   
		 }
		 catch(TagTypeDateValidationException dateValidationException)
         {
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, _languageId,
                    Notification.TYPE_ERROR_MESSAGE, dateValidationException.getMessage(),"");
            uiNotifications.getNotification();
         }
		 catch(net.treetechnologies.masters.services.interfaces.masterdatamanagement.ApplicationException applicationException)
	     { 
		    TLogger.error("Exception in TagTypeForm - buttonClick Event",applicationException);
	        CommonException commonException = new CommonException(applicationException); 
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW,
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,commonException.getApplicationException());
            uiNotifications.getNotification();
        } 
		catch(Exception exception)
		{
		    TLogger.error("Exception in TagTypeForm - buttonClick Event",exception);
		}
		finally
		{
		    TLogger.debug("Exit in TagTypeForm - buttonClick Event");
		}
	}
	/* Declaring form components*/
	private Button _save,_cancel;
	private TextField _name;
	private DateField _startDate,_endDate;
	private CustomDropDown _tenant,_status;
	private HorizontalLayout _horizontalLayout;
	
	/* Declaring Local Variables */
	private String _tabName = null;
	private TagType _tagType = null;
	private String _languageId = null;
	private String NAME = "name";
    private String START_DATE = "startDate";
    private String END_DATE = "endDate";
    private String STATUS = "status";
    private String TENANT = "tenant";
    private String TAG_TYPE_MASTER = "TagTypeMaster";
	private TagTypeMasterApplication _tagTypeMasterApplication;
}
@SuppressWarnings("serial")
class TagTypeDateValidationException extends ApplicationException
{
    TagTypeDateValidationException()
    {
        
    }
    TagTypeDateValidationException(String message,Throwable cause)
    {
        super(message,cause);
    }
}