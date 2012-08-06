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
import net.treetechnologies.entities.schema.masters.Tag;
import net.treetechnologies.ui.application.TagMasterApplication;
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
*  To remove existing Tag<br><br>
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
public class RemoveTagForm extends Form implements ClickListener
{
	private static final long serialVersionUID = 1L;
	public RemoveTagForm(final TagMasterApplication tagMasterApplication,Tag tag,TConfig config,String langId)
	{
	    try
	    {
	        TLogger.debug("Entry in RemoveTagForm - RemoveTagForm Constructor");
    	    this._tagobject = tag;
    	    this._languageId = langId;
    	    this._tagMasterApplication = tagMasterApplication;
    	    
    		getLayout().setMargin(true); 
    		setSizeFull();
    		setWriteThrough(false);
    		
    		LangTranslator translator = new LangTranslator(config, langId);
    		final String LAN_TAG_ID = translator.getLabel(this.NAME, TAG_MASTER);
    		final String LAN_CANCEL = translator.getValue(Constants.CANCEL);
            final String LAN_SUBMIT = translator.getValue(Constants.SUBMIT);

            /* Defining, Declaring and adding the components to the form*/
    		_tag = new TextField(LAN_TAG_ID);
    		_tag.setWidth(Constants.FIELD_WIDTH);
    		_tag.setValue(_tagobject.getName());
    		_tag.setReadOnly(true);
    		_confirm = new Button(LAN_SUBMIT,(ClickListener) this);
    		_cancel = new Button(LAN_CANCEL,(ClickListener) this);
    		
    		addField(this.TAG, _tag);
    		
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
	        TLogger.error("Exception in RemoveTagForm - RemoveTagForm Constructor",applicationException);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,applicationException);
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in RemoveTagForm - RemoveTagForm Constructor",exception);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW,
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        finally
        {
            TLogger.debug("Exit in RemoveTagForm - RemoveTagForm Constructor");
        }
	}
	/* Handling the user action based on save and cancel buttons*/
	public void buttonClick(ClickEvent event)
	{
		try
		{
		    TLogger.debug("Entry in RemoveTagForm - buttonClick Event");
			Button source = event.getButton();
			if (source == _confirm)
			{
				_confirm.commit();
		       CommonManagement.masterDataManagement.removeTag(_tagobject.getTagId());
		        _tagMasterApplication.table.removeItem(_tagobject);
		        _tagMasterApplication.removelayout.removeAllComponents();
		        _tagMasterApplication.editlayout.removeAllComponents();
		        _tagMasterApplication.tabSheet.setSelectedTab(_tagMasterApplication.resultlayout);
		        UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, _languageId, 
                                                                    Notification.TYPE_HUMANIZED_MESSAGE,Constants.RECORD_REMOVED_SUCCESSFULLY,"");
		        uiNotifications.getNotification();
			}
		    else if (source == _cancel)
			{
				discard();
				_tagMasterApplication.tabSheet.setSelectedTab(_tagMasterApplication.resultlayout);
	            this.getFooter().setVisible(true);
	            this.getLayout().setVisible(true);
			}
		}
		catch(net.treetechnologies.masters.services.interfaces.masterdatamanagement.ApplicationException applicationException)
        { 
		    TLogger.error("Exception in RemoveTagForm - buttonClick Event",applicationException);
            CommonException commonException = new CommonException(applicationException);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, _languageId, 
                                                                Notification.TYPE_ERROR_MESSAGE,commonException.getApplicationException());
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in RemoveTagForm - buttonClick Event",exception);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, _languageId, 
                                                                Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        finally
        {
            TLogger.debug("Exit in RemoveTagForm - buttonClick Event");
        }
	}
	/* Declaring Vaadin Components */
	private Button _confirm,_cancel;
	private TextField _tag;
	private HorizontalLayout _horizontalLayout;
	
	/* Declaring Local Variables */
	private Tag _tagobject = null;;
	private String _languageId = null;
    private final String TAG_MASTER = "TagMaster";
    private final String NAME = "name";
    private final String TAG = "tag";
	private TagMasterApplication _tagMasterApplication;
}

