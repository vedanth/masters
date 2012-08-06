package net.treetechnologies.ui.form;

/* VaadinUtil Imports */
import net.treetechnologies.common.configuration.TConfig;
import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.common.ui.notification.UINotifications;
import net.treetechnologies.common.ui.util.CommonException;
import net.treetechnologies.common.ui.util.CommonManagement;

/* Project Imports */

/* ORM Imports */
import net.treetechnologies.entities.schema.masters.TagType;
import net.treetechnologies.ui.application.TagTypeMasterApplication;
import net.treetechnologies.ui.util.Constants;
import net.treetechnologies.ui.util.LangTranslator;

/* Vaadin Imports */
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
/**  
* <b>Purpose:</b><br>
*  To remove existing TagType<br><br>
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
public class RemoveTagTypeForm extends Form implements ClickListener
{
	private static final long serialVersionUID = 1L;
	public RemoveTagTypeForm(final TagTypeMasterApplication tagTypeMasterApplication,TagType tagType,TConfig config,String langId)
	{
	    try
	    {
	        TLogger.debug("Entry in RemoveTagTypeForm - RemoveTagTypeForm Constructor");
	        this._languageId = langId;
    	    this._tagTypeObject = tagType;
    	    this._tagTypeMasterApplication = tagTypeMasterApplication;
    	    
    		getLayout().setMargin(true); 
    		setSizeFull();
    		setWriteThrough(false);
    		
    		LangTranslator translator = new LangTranslator(config, langId);
    		final String LAN_TAGTYPE_ID = translator.getLabel(this.NAME, TAG_TYPE_MASTER);
    		final String LAN_CANCEL = translator.getValue(Constants.CANCEL);
            final String LAN_SUBMIT = translator.getValue(Constants.SUBMIT);
    		
    		/* Defining the components to the form*/
    		_tagType = new TextField(LAN_TAGTYPE_ID);
    		_tagType.setWidth(Constants.FIELD_WIDTH);
    		_tagType.setValue(_tagTypeObject.getName());
    		_tagType.setReadOnly(true);
    		
    		_confirm = new Button(LAN_SUBMIT,(ClickListener) this);
    		_cancel = new Button(LAN_CANCEL,(ClickListener) this);
    		
    		/* Add the components to the form*/
    		addField(this.TAG_TYPE, _tagType);
    		
    		_horizontalLayout = new HorizontalLayout();
    		_horizontalLayout.setMargin(true);
    		_horizontalLayout.addComponent(_confirm);
    		_horizontalLayout.addComponent(_cancel);
    		_horizontalLayout.setSpacing(true);
    		setFooter(_horizontalLayout);
    		_horizontalLayout.setVisible(true);
	    }
	    catch(ApplicationException applicationException)
        {
	        TLogger.error("Exception in RemoveTagTypeForm - RemoveTagTypeForm Constructor",applicationException);
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW,
                                                  _languageId,Notification.TYPE_ERROR_MESSAGE,applicationException);
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in RemoveTagTypeForm - RemoveTagTypeForm Constructor",exception);
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        finally
        {
            TLogger.debug("Exit in RemoveTagTypeForm - RemoveTagTypeForm Constructor");
        }
	}
	public void buttonClick(ClickEvent event)
	{
		try
		{
		    TLogger.debug("Entry in RemoveTagTypeForm - buttonClickEvent");
			 Button source = event.getButton();		
		     if (source == _confirm)
			 {
		    	 _confirm.commit();
		    	 
		    	 Long _tagTypeId = _tagTypeObject.getTagTypeId();
		         CommonManagement.masterDataManagement.removeTagType(_tagTypeId);
		         
		         _tagTypeMasterApplication.table.removeItem(_tagTypeObject);
		         _tagTypeMasterApplication.removelayout.removeAllComponents();
		         _tagTypeMasterApplication.editlayout.removeAllComponents();
		         _tagTypeMasterApplication.tabSheet.setSelectedTab(_tagTypeMasterApplication.resultlayout);
		         
		         UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, 
		                                               _languageId, Notification.TYPE_HUMANIZED_MESSAGE,Constants.RECORD_REMOVED_SUCCESSFULLY,"");
		         uiNotifications.getNotification();
			 }
		     else if (source == _cancel)
			 {
		    	 discard();
		    	 _tagTypeMasterApplication.tabSheet.setSelectedTab(_tagTypeMasterApplication.resultlayout);
			     this.getFooter().setVisible(true);
			     this.getLayout().setVisible(true);
		     }
		}
		catch(net.treetechnologies.masters.services.interfaces.masterdatamanagement.ApplicationException applicationException)
        { 
		    TLogger.error("Exception in RemoveTagTypeForm - buttonClickEvent",applicationException);
            CommonException commonException = new CommonException(applicationException);
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, _languageId, 
                                                                Notification.TYPE_ERROR_MESSAGE,commonException.getApplicationException());
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in RemoveTagTypeForm - buttonClickEvent",exception);
            exception.printStackTrace();
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, _languageId, 
                                                                Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        finally
        {
            TLogger.debug("Exit in RemoveTagTypeForm - buttonClickEvent");
        }
	}
	/* Declaring the components to the form*/
	private Button _confirm,_cancel;
	
	/* Declaring Local Variables */
	private TextField _tagType;
	private HorizontalLayout _horizontalLayout;
	private TagType  _tagTypeObject = null;
	private String _languageId = null;
	private TagTypeMasterApplication _tagTypeMasterApplication;
    private final String TAG_TYPE_MASTER = "TagTypeMaster";
    private final String NAME = "name";
    private final String TAG_TYPE = "tagType";
}

