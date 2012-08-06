package net.treetechnologies.ui.table;

/* VaadinUtil Imports */
import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.common.ui.notification.UINotifications;

/* Project Imports */
import net.treetechnologies.ui.application.TagMasterApplication;
import net.treetechnologies.ui.form.RemoveTagForm;
import net.treetechnologies.ui.form.TagForm;
import net.treetechnologies.ui.util.Constants;
import net.treetechnologies.ui.util.LangTranslator;

/* ORM Imports */
import net.treetechnologies.entities.schema.masters.Tag;

/* Vaadin Imports */
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
public class TagTableListener implements ItemClickListener
{
    private static final long serialVersionUID = 1L;
    public  TagTableListener(TagMasterApplication mainApplication,Long tagTypeId)
    {
        this._mainApplication = mainApplication;
        this._tagTypeId = tagTypeId;
    }
    public void itemClick(ItemClickEvent event)
    {
       try
       {
           TLogger.debug("Entry in TagTableListener - itemClick Event ");
           LangTranslator translator = new LangTranslator(this._mainApplication.config, this._mainApplication.languageId);
           final String EDIT = translator.getValue(Constants.EDIT);
           final String REMOVE = translator.getValue(Constants.REMOVE);
           Tag clickTag = (Tag)event.getItemId();
           
           if(event.isDoubleClick())
           {
               if(!(_tagTypeId.equals(new Long(0))))
               {
                   _mainApplication.editlayout.removeAllComponents();
                   _mainApplication.removelayout.removeAllComponents();
                   
                    /*Handling the Edit form*/ 
                   TagForm tagMasterEditForm = new TagForm(_mainApplication,clickTag,_tagTypeId,_mainApplication.config,
                                                                                       _mainApplication.languageId,EDIT);
                   _mainApplication.editlayout.addComponent(tagMasterEditForm);
                 
                    /*Handling the Remove form*/ 
                   RemoveTagForm removeTagForm = new RemoveTagForm(_mainApplication,clickTag,_mainApplication.config,_mainApplication.languageId);
                   _mainApplication.removelayout.addComponent(removeTagForm);
                 
                   _mainApplication.tabSheet.addTab(_mainApplication.editlayout,EDIT);
                   _mainApplication.tabSheet.addTab(_mainApplication.removelayout,REMOVE);
                   _mainApplication.resultlayout.setVisible(true);
                   _mainApplication.addlayout.setVisible(true);
                   _mainApplication.editlayout.setVisible(true);
                   _mainApplication.removelayout.setVisible(true);
               }
           }
       }
       catch(ApplicationException applicationException)
       {
           TLogger.error("Exception in TagTableListener - itemClick Event ",applicationException);
           UINotifications uiNotifications = new UINotifications(this._mainApplication.MAIN_WINDOW, this._mainApplication.languageId,
                                                                   Notification.TYPE_ERROR_MESSAGE,applicationException);
           uiNotifications.getNotification();
       }
       catch(Exception exception)
       {
           TLogger.error("Exception in TagTableListener - itemClick Event ",exception);
           UINotifications uiNotifications = new UINotifications(this._mainApplication.MAIN_WINDOW,this._mainApplication.languageId,
                                                                   Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
           uiNotifications.getNotification();
       }
       finally
       {
           TLogger.debug("Exit in TagTableListener - itemClick Event ");
       }
    }
    /* Declaring Local Variables */
    private TagMasterApplication  _mainApplication = null;
    private Long _tagTypeId = new Long(0);
}
