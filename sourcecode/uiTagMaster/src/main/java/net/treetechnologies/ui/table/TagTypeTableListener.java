package net.treetechnologies.ui.table;

/* vaadinUtil Imports */
import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.common.ui.notification.UINotifications;

/* Project Imports */
import net.treetechnologies.ui.application.TagTypeMasterApplication;
import net.treetechnologies.ui.form.RemoveTagTypeForm;
import net.treetechnologies.ui.form.TagTypeForm;
import net.treetechnologies.ui.util.Constants;
import net.treetechnologies.ui.util.LangTranslator;

/* ORM Imports */
import net.treetechnologies.entities.schema.masters.TagType;

/*  Vadin Imports */
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Window.Notification;
/**  
* <b>Purpose:</b><br>
*  To create a TableListener<br><br>
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
* 1        28-04-2012          VV Nagesh 
*   -- Base Release   
* </pre><br>
*/
public class TagTypeTableListener implements ItemClickListener
{
    private static final long serialVersionUID = 1L;
    public  TagTypeTableListener(TagTypeMasterApplication mainApplication)
    {
        this._mainApplication = mainApplication;
    }
    public void itemClick(ItemClickEvent event)
    {
       try
       {
           TLogger.debug("Entry in TagTypeTableListener - itemClick Event ");
           LangTranslator translator = new LangTranslator(this._mainApplication.config, this._mainApplication.languageId);
           final String EDIT = translator.getValue(Constants.EDIT);
           final String REMOVE = translator.getValue(Constants.REMOVE);
           TagType clickTagType = (TagType)event.getItemId();
           
           String  _tagTypeId = null;
           if(event.isDoubleClick())
           {
               _tagTypeId = clickTagType.getTagTypeId().toString();
           
               LiferayIPC liferayipc = new LiferayIPC();   
               this._mainApplication.resultlayout.addComponent(liferayipc);
               liferayipc.sendEvent(Constants.IPC_TAG_TYPE_ID, _tagTypeId);
           
               this._mainApplication.editlayout.removeAllComponents();
               this._mainApplication.removelayout.removeAllComponents();
               
                 /*Handling the edit form*/
               TagTypeForm editForm = new TagTypeForm(this._mainApplication,clickTagType,this._mainApplication.config,
                                                                               this._mainApplication.languageId,EDIT);
               this._mainApplication.editlayout.addComponent(editForm);
               
                /*Handling the remove form*/  
               RemoveTagTypeForm removeTagTypeForm = new RemoveTagTypeForm(this._mainApplication,clickTagType,
                                                       this._mainApplication.config,this._mainApplication.languageId);
               this._mainApplication.removelayout.addComponent(removeTagTypeForm);
               
               this._mainApplication.tabSheet.addTab(this._mainApplication.editlayout,EDIT);
               this._mainApplication.tabSheet.addTab(this._mainApplication.removelayout,REMOVE);
               
               this._mainApplication.addlayout.setVisible(true);
               this._mainApplication.editlayout.setVisible(true);
               this._mainApplication.removelayout.setVisible(true);
           }
       }
       catch(ApplicationException applicationException)
       {
           TLogger.error("Exception in TagTypeTableListener - itemClick Event ",applicationException);
           UINotifications uiNotifications = new UINotifications(this._mainApplication.MAIN_WINDOW, this._mainApplication.languageId,
                                                                   Notification.TYPE_ERROR_MESSAGE,applicationException);
           uiNotifications.getNotification();
       }
       catch(Exception exception)
       {
           TLogger.error("Exception in TagTypeTableListener - itemClick Event ",exception);
           UINotifications uiNotifications = new UINotifications(this._mainApplication.MAIN_WINDOW,this._mainApplication.languageId,
                                                                   Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
           uiNotifications.getNotification();
       }
       finally
       {
           TLogger.debug("Exit in TagTypeTableListener - itemClick Event ");
       }
    }
    /* Declaring Local Variables */
    private TagTypeMasterApplication  _mainApplication = null;
}

