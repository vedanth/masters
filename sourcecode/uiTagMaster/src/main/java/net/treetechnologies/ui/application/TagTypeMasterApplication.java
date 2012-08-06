package net.treetechnologies.ui.application;

/* Basic Java Imports */
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*VaadinUtil Imports*/
import net.treetechnologies.common.configuration.TConfig;
import net.treetechnologies.common.ui.notification.UINotifications;
import net.treetechnologies.common.ui.util.CommonManagement;

/* Project Imports */
import net.treetechnologies.masters.services.client.ServiceClient;
import net.treetechnologies.ui.form.TagTypeFormHandler;
import net.treetechnologies.ui.table.TagTypeTable;
import net.treetechnologies.ui.util.Constants;

/*Vaadin Imports*/
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.PortletApplicationContext2;
import com.vaadin.terminal.gwt.server.PortletApplicationContext2.PortletListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;
/**  
* <b>Purpose:</b><br>
*  To create TagType Master Portlet<br><br>
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
public class TagTypeMasterApplication extends Application 
{
    /**
     * <b>Algorithm:</b>
     * <pre>      
     *  1. Create the TagType Portlet. 
     *  2. Create a class which implements PortletListener.
     *  3. Create the form inside the TagType Portlet.
     * </pre>
     **/
	private static final long serialVersionUID = 1L;
	@Override
	public void init() 
	{ 
		setMainWindow(MAIN_WINDOW);
		if (getContext() instanceof PortletApplicationContext2)
        {
            PortletApplicationContext2 portletApplicationContext2 =(PortletApplicationContext2) getContext();
            portletApplicationContext2.addPortletListener(this, new LiferayPortletListener());
        }
        else
        {
            getMainWindow().showNotification( "Not initiated via Portal!", Notification.TYPE_ERROR_MESSAGE);
        }
	}
	private class LiferayPortletListener implements PortletListener 
    { 
       private static final long serialVersionUID = 1L;
       /**
        * <b>Algorithm:</b>
        * <pre>      
        *  1. Get the renderRequest and renderResponse. 
        *  2. Get the languageId based using renderRequest.
        *  3. Create the form by calling setForm method.
        * </pre>
        * @param request,response and window
        * @return void
        * @throws Exception.
        **/
        public void handleRenderRequest(RenderRequest request,RenderResponse response, Window window)
        {
            HttpServletRequest httpRequest = null;
            ThemeDisplay themeDisplay = (ThemeDisplay) request
            .getAttribute(WebKeys.THEME_DISPLAY);
            try 
            {
                httpRequest = PortalUtil.getHttpServletRequest(request);
                config=(TConfig)httpRequest.getSession().getServletContext().getAttribute("CONFIG");
                languageId = LanguageUtil.getLanguageId( request );
                CommonManagement.USER_NAME = themeDisplay.getUser().getFirstName();                             
                HttpSession session2 = PortalUtil.getHttpServletRequest(request).getSession();
                CommonManagement.PASSWORD =  (String)session2.getAttribute("USER_PASSWORD");  
                CommonManagement.masterDataManagement = ServiceClient.getMasterDataMangementInterface(CommonManagement.USER_NAME, CommonManagement.PASSWORD);
                if(table == null)
                {
                    table =  new TagTypeTable(config,languageId);
                }
                setForm(config,languageId);
            } 
            catch(Exception e)
            {
                UINotifications uiNotifications = new UINotifications(MAIN_WINDOW, languageId, 
                                                        Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
                uiNotifications.getNotification();
            }
        }
        public void handleActionRequest(ActionRequest request,ActionResponse response, Window window) 
        {
        }
        public void handleEventRequest(EventRequest request,EventResponse response, Window window) 
        {
        }
        public void handleResourceRequest(ResourceRequest request,ResourceResponse response, Window window) 
        {
        }
    }
	/**
     * <b>Algorithm:</b>
     * <pre>      
     *  1. Get the TConfig object and languageId.
     *  2. Create the form using TagTypeFormHandler class.
     *  3. If form is already created, reuse the existing form
     *  4. Assign the Form component to the current class form component.
     *  5. Return the void.
     * </pre>
     *
     * @param config and languageId
     * @return void
     * @throws UnsupportedOperationException,ConfigurationTagNotFound,ConfigurationTypeMismatch.
     */
	private void  setForm( TConfig config,String languageId)
	{	
	    TagTypeFormHandler form = null;
	    try
	    {
    		form = new TagTypeFormHandler(this,config,languageId);
    		if(this._old_form != null)
            {
                MAIN_WINDOW.replaceComponent(_old_form,form);
            }
            else
            {
                MAIN_WINDOW.addComponent(form);
            }
    		this._old_form = form;
	    }
	    catch(Exception exception)
	    {
	        UINotifications uiNotifications = new UINotifications(MAIN_WINDOW, languageId, 
	                                                Notification.TYPE_ERROR_MESSAGE,Constants.CREATE_FORM_EXCEPTION,"");
	        uiNotifications.getNotification();
	    }
	}
	/* Declaring the General Variables */
	public TConfig config;
    public String languageId = null;    
    
    /* Declaring the Vaadin Components */
	public final Window MAIN_WINDOW =  new Window();
	public VerticalLayout resultlayout,addlayout,editlayout,removelayout;
	public TabSheet tabSheet = new TabSheet();
    public TagTypeTable table = null;
	private Component _old_form = null;
}
