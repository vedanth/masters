package net.treetechnologies.ui.form;

/* VaadinUtil Imports */
import net.treetechnologies.common.configuration.TConfig;
import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.common.ui.notification.UINotifications;

/* Project Imports */
import net.treetechnologies.ui.application.TagMasterApplication;
import net.treetechnologies.ui.table.TagTableListener;
import net.treetechnologies.ui.util.Constants;
import net.treetechnologies.ui.util.LangTranslator;

/* Vaadin Imports */
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEvent;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEventListener;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Window.Notification;
/**  
* <b>Purpose:</b><br>
*  To create tabSheet in the Form<br><br>
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
* 1        09-04-2012          VV Nagesh 
*   -- Base Release   
* </pre><br>
*/
public class TagFormHandler extends VerticalLayout 
{
    private static final long serialVersionUID = 1L;
    public TagFormHandler( final TagMasterApplication tagMasterApplication,final TConfig config,final String langId)
    {   
        try
        {
            TLogger.debug("Entry in TagFormHandler - TagFormHandler Constructor");
            this._tagMasterApplication = tagMasterApplication;
            this._languageId = langId;

            LiferayIPC liferayipc = new LiferayIPC();
            _tagMasterApplication.resultlayout = new VerticalLayout();
            _tagMasterApplication.resultlayout.addComponent(liferayipc);
            
            LangTranslator translator = new LangTranslator(config, langId);
            final String VIEW = translator.getValue(Constants.VIEW);
            final String ADD = translator.getValue(Constants.ADD);
            
            _tagMasterApplication.addlayout = new VerticalLayout();
            _tagMasterApplication.editlayout = new VerticalLayout();
            _tagMasterApplication.removelayout = new VerticalLayout();
            _tagMasterApplication.tabSheet.removeAllComponents();
            
            _tagMasterApplication.tabSheet.setSelectedTab(_tagMasterApplication.resultlayout);
            _tagMasterApplication.tabSheet.addTab(_tagMasterApplication.resultlayout,VIEW);
          
            _tagMasterApplication.tabSheet.setHeight(Constants.TAB_SHEET_HEIGHT);
            _tagMasterApplication.tabSheet.setWidth(Constants.TAB_SHEET_WIDTH);
            addComponent(_tagMasterApplication.tabSheet);
            _tagMasterApplication.tabSheet.addListener(new SelectedTabChangeListener()
            {
                private static final long serialVersionUID = 1L;

                public void selectedTabChange(TabSheet.SelectedTabChangeEvent event)
                {
                    TabSheet tabsheet = event.getTabSheet();
                    Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
                    if (tab.getCaption().equalsIgnoreCase(ADD))
                    {
                        try
                        {
                            TagForm addForm = new TagForm(_tagMasterApplication, null,_tagTypeId, config, langId, ADD);
                            _tagMasterApplication.addlayout.removeAllComponents();
                            _tagMasterApplication.addlayout.addComponent(addForm);

                        } 
                        catch (Exception exception)
                        {
                            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW,
                                                                  _languageId, Notification.TYPE_ERROR_MESSAGE, 
                                                                  Constants.CREATE_FORM_EXCEPTION, "");
                            uiNotifications.getNotification();
                        }
                    }
                }
            });
            liferayipc.addListener(Constants.IPC_TAG_TYPE_ID, new LiferayIPCEventListener() 
            {
               private static final long serialVersionUID = 1L;
               public void eventReceived(LiferayIPCEvent event) 
               {
                   try
                   {
                       //_tagMasterApplication.addlayout.removeAllComponents();
                       _tagMasterApplication.editlayout.removeAllComponents();
                       _tagMasterApplication.removelayout.removeAllComponents();
                       
                       _tagMasterApplication.tabSheet.removeComponent(_tagMasterApplication.editlayout);
                       _tagMasterApplication.tabSheet.removeComponent(_tagMasterApplication.removelayout);
                       _tagMasterApplication.table.removeAllItems();
                       _tagTypeId = Long.parseLong(event.getData().toString());
                      
                       _tagMasterApplication.table.setTableDataSource(_tagTypeId);
                       
                       _tagMasterApplication.resultlayout.addComponent(_tagMasterApplication.table);
                       _tagMasterApplication.tabSheet.addTab(_tagMasterApplication.addlayout,ADD);
                       
                       _tagMasterApplication.table.addListener(new TagTableListener(_tagMasterApplication,_tagTypeId));
					   
					   _tagMasterApplication.tabSheet.setSelectedTab(_tagMasterApplication.resultlayout);
                   } 
                   catch(Exception exception)
                   {
                       TLogger.error("Exception in TagFormHandler - TagFormHandler Constructor(IPC Listener)",exception);
                       UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, _languageId, 
                                                                                   Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
                       uiNotifications.getNotification();
                  }
               }
           });
        }
        catch(ApplicationException applicationException)
        {
            TLogger.error("Exception in TagFormHandler - TagFormHandler Constructor",applicationException);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, _languageId, 
                                                                        Notification.TYPE_ERROR_MESSAGE,applicationException);
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagFormHandler - TagFormHandler Constructor",exception);
            UINotifications uiNotifications = new UINotifications(_tagMasterApplication.MAIN_WINDOW, _languageId, 
                                                                        Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        finally
        {
            TLogger.debug("Exit in TagFormHandler - TagFormHandler Constructor");
        }
    }
    /* Declaring Local Variables */
    private String _languageId = null;
    private Long _tagTypeId = new Long(0);
    private TagMasterApplication _tagMasterApplication;
}
