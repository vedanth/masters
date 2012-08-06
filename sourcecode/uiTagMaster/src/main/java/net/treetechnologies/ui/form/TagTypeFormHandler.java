package net.treetechnologies.ui.form;

/* VaadinUtil Imports */
import net.treetechnologies.common.configuration.TConfig;
import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.common.ui.notification.UINotifications;

/* Project Imports */
import net.treetechnologies.ui.application.TagTypeMasterApplication;
import net.treetechnologies.ui.table.TagTypeTableListener;
import net.treetechnologies.ui.util.Constants;
import net.treetechnologies.ui.util.LangTranslator;

/* Vaadin Imports */
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
public class TagTypeFormHandler extends VerticalLayout
{
	private static final long serialVersionUID = 1L;
	public TagTypeFormHandler(final TagTypeMasterApplication tagTypeMasterApplication,final TConfig config,final String langId) 
	{
	    try
	    {
	        TLogger.debug("Entry in TagTypeFormHandler - TagTypeFormHandler Constructor");
    	    this._tagTypeMasterApplication = tagTypeMasterApplication;
    	    this._languageId = langId;
    	    
    	    LangTranslator translator = new LangTranslator(config, langId);
            final String VIEW = translator.getValue(Constants.VIEW);
            final String ADD = translator.getValue(Constants.ADD);
    	  
            _tagTypeMasterApplication.addlayout = new VerticalLayout();
            _tagTypeMasterApplication.editlayout = new VerticalLayout();
            _tagTypeMasterApplication.removelayout = new VerticalLayout();
            _tagTypeMasterApplication.resultlayout = new VerticalLayout();
            _tagTypeMasterApplication.resultlayout.requestRepaintAll();
    		
            _tagTypeMasterApplication.tabSheet.removeAllComponents();
            _tagTypeMasterApplication.tabSheet.setSelectedTab(_tagTypeMasterApplication.resultlayout);
            
            _tagTypeMasterApplication.tabSheet.addListener( new SelectedTabChangeListener()
            {
    			private static final long serialVersionUID = 1L;
    			public void selectedTabChange(TabSheet.SelectedTabChangeEvent event)
    		    {
            		TabSheet tabsheet = event.getTabSheet();
                    Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
                    if(tab.getCaption().equalsIgnoreCase(ADD))
                    {
                    	try 
    					{
                    	    /*Handling the AddForm*/
                            TagTypeForm addForm = new TagTypeForm(_tagTypeMasterApplication,null,config,langId,ADD);
                            _tagTypeMasterApplication.addlayout.removeAllComponents();
                            _tagTypeMasterApplication.addlayout.addComponent(addForm);
    					} 
                    	catch(Exception exception)
                        {
                            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, 
                                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
                            uiNotifications.getNotification();
                        }
                    }
    		    }
            });
            _tagTypeMasterApplication.table.addListener(new TagTypeTableListener(_tagTypeMasterApplication));
        	_tagTypeMasterApplication.resultlayout.addComponent(_tagTypeMasterApplication.table);
    		_tagTypeMasterApplication.tabSheet.addTab(_tagTypeMasterApplication.resultlayout,VIEW);
    		_tagTypeMasterApplication.tabSheet.addTab(_tagTypeMasterApplication.addlayout,ADD);
    		
    		_tagTypeMasterApplication.tabSheet.setHeight(Constants.TAB_SHEET_HEIGHT);
    		_tagTypeMasterApplication.tabSheet.setWidth(Constants.TAB_SHEET_WIDTH);
    		addComponent(_tagTypeMasterApplication.tabSheet);
	    }
	    catch(ApplicationException applicationException)
        {
	        TLogger.error("Exception in TagTypeFormHandler - TagTypeFormHandler Constructor",applicationException);
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,applicationException);
            uiNotifications.getNotification();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagTypeFormHandler - TagTypeFormHandler Constructor",exception);
            UINotifications uiNotifications = new UINotifications(_tagTypeMasterApplication.MAIN_WINDOW, 
                                                  _languageId, Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
            uiNotifications.getNotification();
        }
        finally
        {
            TLogger.debug("Exit in TagTypeFormHandler - TagTypeFormHandler Constructor");
        }
	}
	/* Declaring Local Variables */
	private String _languageId = null;
	private TagTypeMasterApplication _tagTypeMasterApplication;
}
