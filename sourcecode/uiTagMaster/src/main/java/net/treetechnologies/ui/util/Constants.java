package net.treetechnologies.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
/**  
* <b>Purpose:</b><br>
*  To Declare the Constant Variables<br><br>
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
* 1        31-07-2012          VV Nagesh 
*   -- Base Release   
* </pre><br>
*/


public class Constants 
{
    /* To set the all the default values to the ConstantsBean Class */
    public ConstantsBean getDefaultValues() throws GetDefaultValuesException
    {
        TLogger.debug("Entry Constants - getDefaultValues");
        ConstantsBean constantsBean = new ConstantsBean();
        Properties properties = new Properties();
        String propertiesFilePath = null;
        propertiesFilePath = System.getenv(LUCENE_SEARCH);
        try
        {
            properties.load(new FileInputStream(new File(propertiesFilePath)));
            String defaultEndDate = properties.getProperty(DEFAULT_END_DATE);
            String defaultDataType = properties.getProperty(DEFAULT_DATATYPE);
            String defaultFactFiledType = properties.getProperty(DEFAULT_FACT_FIELD_TYPE);
            String defaultPolicyType = properties.getProperty(DEFAULT_POLICY_TYPE);
            String defaultTagType = properties.getProperty(DEFAULT_TAG_TYPE);
            String defaultStatus = properties.getProperty(DEFAULT_STATUS);
            String defaultTenant = properties.getProperty(DEFAULT_TENANT);
            String defaultTemplate = properties.getProperty(DEFAULT_TEMPLATE);
            String activeStatus = properties.getProperty(ACTIVE_STATUS);
            String retireStatus = properties.getProperty(RETIRE_STATUS);
            String factFiledType = properties.getProperty(FACT_FIELD_TYPE);
            String enumerationDataType = properties.getProperty(ENUMERATION_DATA_TYPE);
            String enumerationFactFieldType = properties.getProperty(ENUMERATION_FACT_FIELD_TYPE);
            String displayDateFormat = properties.getProperty(DISPLAY_DATE_FORMAT);
            
            
            constantsBean.setDefaultEndDate(defaultEndDate);
            constantsBean.setDefaultDataType(defaultDataType);
            constantsBean.setDefaultFactFiledType(defaultFactFiledType);
            constantsBean.setDefaultPolicyType(defaultPolicyType);
            constantsBean.setDefaultTagType(defaultTagType);
            constantsBean.setDefaultStatus(defaultStatus);
            constantsBean.setDefaultTenant(defaultTenant);
            constantsBean.setDefaultTemplate(defaultTemplate);
            constantsBean.setActiveStatus(activeStatus);
            constantsBean.setRetireStatus(retireStatus);
            constantsBean.setFactFiledType(factFiledType);
            constantsBean.setEnumerationDataType(enumerationDataType);
            constantsBean.setEnumerationFactFieldType(enumerationFactFieldType);
            constantsBean.setDisplayDateFormat(displayDateFormat);
            
        } 
        catch (FileNotFoundException e)
        {
            TLogger.error("FileNotFoundException in Constants - getDefaultValues"+e);
            throw new GetDefaultValuesException(e.getMessage(),e.getCause());
        } 
        catch (IOException e)
        {
            TLogger.error("IOException in Constants - getDefaultValues"+e);
            throw new GetDefaultValuesException(e.getMessage(),e.getCause());
        }
        catch(Exception e)
        {
            TLogger.error("Exception in Constants - getDefaultValues"+e);
            throw new GetDefaultValuesException(e.getMessage(),e.getCause());
        }
        finally
        {
            TLogger.debug("Exit Constants - getDefaultValues");
        }
        return constantsBean;
    }
    /* Default Values  */
    public static final String DEFAULT_END_DATE = "DEFAULT_END_DATE";
    public static final String DEFAULT_DATATYPE = "DEFAULT_DATATYPE";
    public static final String DEFAULT_FACT_FIELD_TYPE = "DEFAULT_FACT_FIELD_TYPE";
    public static final String DEFAULT_POLICY_TYPE = "DEFAULT_POLICY_TYPE";
    public static final String DEFAULT_TAG_TYPE = "DEFAULT_TAG_TYPE";
    public static final String DEFAULT_STATUS = "DEFAULT_STATUS";
    public static final String DEFAULT_TENANT = "DEFAULT_TENANT";
    public static final String DEFAULT_TEMPLATE = "DEFAULT_TEMPLATE";
    public static final String ACTIVE_STATUS = "ACTIVE_STATUS";
    public static final String RETIRE_STATUS = "RETIRE_STATUS";
    public static final String FACT_FIELD_TYPE = "FACT_FIELD_TYPE";
    public static final String ENUMERATION_DATA_TYPE = "ENUMERATION_DATA_TYPE";
    public static final String ENUMERATION_FACT_FIELD_TYPE = "ENUMERATION_FACT_FIELD_TYPE";
    public static final String DISPLAY_DATE_FORMAT = "DISPLAY_DATE_FORMAT";
    public static final String LUCENE_SEARCH = "LUCENE_SEARCH";
    
    /* Common Variables used in All Masters */
    public static final String EDIT = "edit";
    public static final String STATUS_CHANGE = "statusChange";
    public static final String REMOVE = "remove";
    public static final String VIEW = "view";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String SUBMIT = "submit";
    public static final String CANCEL = "cancel";
    public static final String ADD_POLICY = "Add Policy";
    
    /* Variables used in Policy Master */
    public static final String RULE = "rule";
    public static final String ACTION = "action";
    public static final String PREFIX = "prefix";
    public static final String SELECT = "Select";
    public static final String AVAILABLE = "available";
    public static final String SELECTED = "selected";
    public static final String CONDITION = "condition";
    public static final String RULE_DESCRIPTION = "ruleDescription";
    
    
    /* Constants Declared in Masters */
    public static final String FIELD_WIDTH = "80%";
    public static final String POPUP_FIELD_WIDTH = "60%";
    public static final int POPUP_UNIT = 8;
    public static final float MODIFY_POLICY_HEIGHT = 95f;
    public static final float MODIFY_POLICY_WIDTH = 85f;
    public static final int MODIFY_POLICY_X_POSITION = 300;
    public static final int MODIFY_POLICY_Y_POSITION = 200;
    public static final float ADD_POLICY_HEIGHT = 65f;
    public static final float ADD_POLICY_WIDTH = 65f;
    public static final int ADD_POLICY_X_POSITION = 200;
    public static final int ADD_POLICY_Y_POSITION = 120;
    public static final String TAB_SHEET_HEIGHT = "50%";
    public static final String TAB_SHEET_WIDTH = "100%";
    
    /* IPC Variables Declared in All Masters */
    public static final String IPC_FACT_ID = "factId";
    public static final String IPC_TAG_TYPE_ID = "tagTypeId";
    public static final String IPC_TEMPLATE_ID = "templateId";
    public static final String IPC_SEARCH_ID = "IPC_SEARCH_ID";
    
    /* UI Notifications Related Messages */ 
    public static final String RECORD_UPDATED_SUCCESSFULLY = "masters_record_updated_successfully";
    public static final String RECORD_CREATED_SUCCESSFULLY = "masters_record_created_successfully";
    public static final String RECORD_REMOVED_SUCCESSFULLY = "masters_record_removed_successfully";
    public static final String DATE_PARSE_EXCEPTION = "masters_date_parse_Exception";
    public static final String CREATE_FORM_EXCEPTION = "masters_create_form_Exception";
    public static final String INVALID_OPERATION = "masters_invalidOperation";
    
    public static final String RULE_BUILDING_IS_FAIED = "rule_building_is_failed";
    public static final String NO_ITEM_SELECTED = "rule_ui_noItemSelected";
    public static final String ADD_EMPTY_RULE = "rule_ui_addOneMoreEmptyRule";
    public static final String REMOVE_RULE = "rule_ui_removeRule";
    public static final String INVALID_RULE = "rule_ui_invalidRule";
    public static final String SAVE_RULE = "rule_ui_saveRule";
    public static final String SELECT_RULE = "rule_ui_withoutSelection";
    public static final String UNABLE_TO_FETCH_DB = "rule_ui_unableToFetchDB";
    public static final String UNABLE_TO_SHOW_TAB_EXCEPTION = "rule_ui_unableToShowTab";
    
    public static final String TEMPLATE_RETRIVE_EXCEPTION = "policy_pc_ui_form_templateRetrieveException";
    public static final String RULE_ADD_EXCEPTION = "rule_dataAddException";
    public static final String DATE_VALIDATION_EXCEPTION = "masters_date_validation_Exception";
}
class GetDefaultValuesException extends ApplicationException
{
    private static final long serialVersionUID = 1L;

    public GetDefaultValuesException()
    {
       
    }
    
    public GetDefaultValuesException(String message,Throwable cause)
    {
            super(message, cause);

    }
}