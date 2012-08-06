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
import net.treetechnologies.entities.schema.masters.Tag;
import net.treetechnologies.entities.schema.masters.TagType;
import net.treetechnologies.entities.schema.masters.Tenant;
import net.treetechnologies.masters.services.interfaces.masterdatamanagement.ApplicationException;
import net.treetechnologies.ui.application.TagMasterApplication;
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
* To add Tag or edit Tag<br><br>
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
public class TagForm extends Form implements ClickListener
{
	private static final long serialVersionUID = 1L;
	public TagForm(final TagMasterApplication tagMasterApplication,Tag tag,Long tagTypeId,TConfig config,String langId,String tabName)
	{	
	    try
	    {
	        TLogger.debug("Entry in TagForm - TagForm Constructor");
    	    this._tag = tag;
            this._tagTypeId = tagTypeId;
            this._tabName = tabName;
            this._languageId = langId;
            this._tagMasterApplication  = tagMasterApplication;
            getLayout().setMargin(true); 
    		setSizeFull();
    		
    		LangTranslator translator = new LangTranslator(config, langId);
    		final String LAN_NAME = translator.getLabel(NAME, TAG_MASTER);
    		final String LAN_STARTDATE = translator.getLabel(START_DATE, TAG_MASTER);
    		final String LAN_ENDDATE = translator.getLabel(END_DATE, TAG_MASTER);
    		final String LAN_STATUS = translator.getLabel(STATUS, TAG_MASTER);
    		final String LAN_TENANT = translator.getLabel(TENANT, TAG_MASTER);
    		    
    		
    		final String LAN_NAME_REQ = translator.getMandatoryMessage(NAME, TAG_MASTER);
    		final String LAN_NAME_LENGTH = translator.getLength(NAME, TAG_MASTER);
    		final int LAN_NAME_LENGTH_MIN= Integer.parseInt(translator.getMinLength(NAME, TAG_MASTER));
    		final int LAN_NAME_LENGTH_MAX = Integer.parseInt(translator.getMaxLength(NAME, TAG_MASTER));
    		    
    		
    		final String LAN_STARTDATE_REQ = translator.getMandatoryMessage(START_DATE, TAG_MASTER);
    		final String LAN_ENDDATE_REQ = translator.getMandatoryMessage(END_DATE, TAG_MASTER);
    		final String LAN_STATUS_REQ = translator.getMandatoryMessage(STATUS, TAG_MASTER);
    		final String LAN_TENANT_REQ = translator.getMandatoryMessage(TENANT, TAG_MASTER);
    		
            final String LAN_SAVE = translator.getValue(Constants.SUBMIT);
            final String LAN_CANCEL = translator.getValue(Constants.CANCEL);
            
            Constants constants = new Constants();
            ConstantsBean constantsBean = constants.getDefaultValues();
    		
    		_name = new TextField(LAN_NAME);
    		_name.setWidth(Constants.FIELD_WIDTH);
    		_startDate = new DateField(LAN_STARTDATE);
    		_startDate.setDateFormat(constantsBean.getDisplayDateFormat());
    		_startDate.setWidth(Constants.FIELD_WIDTH);
    		_endDate = new DateField(LAN_ENDDATE);
    		_endDate.setDateFormat(constantsBean.getDisplayDateFormat());
    		_endDate.setWidth(Constants.FIELD_WIDTH);
    		_tenant = new CustomDropDown(LAN_TENANT,this._languageId,this._tagMasterApplication.MAIN_WINDOW);
            _tenant.setWidth(Constants.FIELD_WIDTH);
            _status = new CustomDropDown(LAN_STATUS,this._languageId,this._tagMasterApplication.MAIN_WINDOW);
            _status.setWidth(Constants.FIELD_WIDTH);
    		_save = new Button(LAN_SAVE,(ClickListener) this);
    		_cancel = new Button(LAN_CANCEL,(ClickListener) this);

    		/* Defining the mandatory fields of the form*/
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
    		
    		/* Adding components to the form */
    		addField(NAME, _name);
    		addField(START_DATE, _startDate);
    		addField(END_DATE, _endDate);
    		addField(STATUS, _status);
    		addField(TENANT,_tenant);
            
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
            List<Status> statusList =  CommonManagement.masterDataManagement.getStatus();
            Iterator<Status> statusItr = statusList.iterator();
            String statusName = null;
            Long statusId = 0L;
            while(statusItr.hasNext())
            {   
                Status statusObject = (Status)statusItr.next();
                statusName = statusObject.getName();
                statusId = statusObject.getStatusId();
                _status.addItem(statusId);
                _status.setItemCaption(statusId, statusName);
            }
            _status.sort();
    		if(_tabName.equalsIgnoreCase(Constants.EDIT))
            {
    		    if(_tag != null)
                {
                    /*Fetch the Policy object based on tagTypeId */
                    String dbTag_Name = _tag.getName();
                    Date dbStartDate = (Date) _tag.getStartDateTime();
                    Date dbEndDate = (Date) _tag.getEndDateTime();
    
                    /*Setting the default values too the form*/
                    _name.setValue(dbTag_Name);
                    _startDate.setValue(dbStartDate);
                    _endDate.setValue(dbEndDate);
                    _tenant.select(_tag.getTenantId());
                    _status.select(_tag.getStatusId());
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
    		setWriteThrough(false);
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setMargin(true);
            horizontalLayout.setSpacing(true);
            horizontalLayout.addComponent(_save);
            horizontalLayout.addComponent(_cancel);
            horizontalLayout.setSpacing(true);
            setFooter(horizontalLayout);
            horizontalLayout.setVisible(true);
	    }
        catch(ParseException dateParsingException)
        {
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,Constants.DATE_PARSE_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        catch(net.treetechnologies.masters.services.interfaces.masterdatamanagement.ApplicationException applicationException)
        {
            TLogger.error("Exception in TagForm - TagForm Constructor",applicationException);
            CommonException commonException = new CommonException(applicationException);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,commonException.getApplicationException());
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagForm - TagForm Constructor",exception);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        finally
        {
            TLogger.debug("Exit in TagForm - TagForm Constructor");
        }
	}
	/* Handling the user interaction*/
	public void buttonClick(ClickEvent event) 
	{
		try
		{
		    TLogger.debug("Entry in TagForm - buttonClick Event");
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
                    throw new TagDateValidationException(Constants.DATE_VALIDATION_EXCEPTION,null);
                }
                name = (name.equals("")?null:name);
				if(_tabName.equalsIgnoreCase(Constants.EDIT))
			    {
                     _tag.setStartDateTime(startDate);
                     _tag.setEndDateTime(endDate);
                     _tag.setName(name);
                     _tag.setStatusId(statusId);
                     _tag.setTenantId(tenantId);
                     _tag.setTagTypeId(_tagTypeId);
                     Tag updatedObj = CommonManagement.masterDataManagement.updateTag(_tag);
                     Status statusObject = CommonManagement.masterDataManagement.getStatusById(updatedObj.getStatusId());
                     Tenant tenantObject = CommonManagement.masterDataManagement.getTenantsById(updatedObj.getTenantId());
                     TagType tagTypeObject = CommonManagement.masterDataManagement.getTagTypeById(updatedObj.getTagTypeId());
                     _tagMasterApplication.removelayout.removeAllComponents();
                     _tagMasterApplication.table.removeItem(updatedObj);
                     _tagMasterApplication.table.addItem(new Object[]{updatedObj.getTagId().toString(),updatedObj.getName(),
                                             updatedObj.getStartDateTime().toString(),updatedObj.getEndDateTime().toString(),
                                             statusObject.getName(),tenantObject.getName(),
                                             tagTypeObject.getName()},updatedObj);
                     _tagMasterApplication.table.refreshRowCache();
                     _tagMasterApplication.tabSheet.setSelectedTab(_tagMasterApplication.resultlayout);
                     UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW,
                                                           _languageId, Notification.TYPE_HUMANIZED_MESSAGE,Constants.RECORD_UPDATED_SUCCESSFULLY,"");
                     uiNotifications.getNotification(); 
                }
				else
				{
			        Tag tag = new Tag();
		            tag.setEndDateTime(endDate);
                    tag.setStartDateTime(startDate);
                    tag.setName(name);
                    tag.setStatusId(statusId);
                    tag.setTenantId(tenantId);
                    tag.setTagTypeId(_tagTypeId);
                    Tag returnObject = CommonManagement.masterDataManagement.createTag(tag);
                    Status statusObject = CommonManagement.masterDataManagement.getStatusById(returnObject.getStatusId());
                    Tenant tenantObject = CommonManagement.masterDataManagement.getTenantsById(returnObject.getTenantId());
                    TagType tagTypeObject = CommonManagement.masterDataManagement.getTagTypeById(returnObject.getTagTypeId());
                    _tagMasterApplication.table.addItem(new Object[]{returnObject.getTagId().toString(),returnObject.getName(),
                                returnObject.getStartDateTime().toString(),returnObject.getEndDateTime().toString(),
                                statusObject.getName(),tenantObject.getName(),
                                tagTypeObject.getName()},returnObject);
                    _tagMasterApplication.table.refreshRowCache();
                    _tagMasterApplication.tabSheet.setSelectedTab(_tagMasterApplication.resultlayout);
                    UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, _languageId, 
                                                                        Notification.TYPE_HUMANIZED_MESSAGE,Constants.RECORD_CREATED_SUCCESSFULLY,"");
                    uiNotifications.getNotification();
			    }
		    }
		    else if (source == _cancel)
			{
		        discard();
                _tagMasterApplication.tabSheet.setSelectedTab(_tagMasterApplication.resultlayout);
                this.getFooter().setVisible(true);
                this.getLayout().setVisible(true);
            }    
		}
		catch(TagDateValidationException dateValidationException)
        {
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, _languageId,
                    Notification.TYPE_ERROR_MESSAGE, dateValidationException.getMessage(),"");
            uiNotifications.getNotification();
        }
        catch(net.treetechnologies.masters.services.interfaces.masterdatamanagement.ApplicationException applicationException)
        { 
            TLogger.error("Exception in TagForm - buttonClick Event",applicationException);
            CommonException commonException = new CommonException(applicationException);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,commonException.getApplicationException());
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagForm - buttonClick Event",exception);
        }
        finally
        {
            TLogger.debug("Exit in TagForm - buttonClick Event");
        }
	}
	/* Declaring Vaadin Components */
	private Button _save,_cancel;
	private TextField _name;
	private DateField _startDate,_endDate;
	private CustomDropDown _tenant,_status;
	
	/* Declaring Local Variables */
	private Tag _tag = null;
	private Long _tagTypeId = new Long(0);
	private String _tabName = null;
	private String _languageId = null;
	private String NAME = "name";
    private String START_DATE = "startDate";
    private String END_DATE = "endDate";
    private String STATUS = "status";
    private String TENANT = "tenant";
    private String TAG_MASTER = "TagMaster";
	private TagMasterApplication _tagMasterApplication;
	 
}
@SuppressWarnings("serial")
class TagDateValidationException extends ApplicationException
{
    TagDateValidationException()
    {
        
    }
    TagDateValidationException(String message,Throwable cause)
    {
        super(message,cause);
    }
}