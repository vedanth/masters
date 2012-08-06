package net.treetechnologies.ui.table;

/* Basic Java Imports */
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* VaadinUtil Imports */
import net.treetechnologies.common.configuration.TConfig;
import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.common.ui.util.CommonManagement;

/* ORM Imports */
import net.treetechnologies.entities.schema.masters.TagType;
import net.treetechnologies.ui.util.Constants;
import net.treetechnologies.ui.util.LangTranslator;

/* Project Imports */

import com.vaadin.ui.Table;
/**  
* <b>Purpose:</b><br>
*  To create a Table<br><br>
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
public class TagTypeTable extends Table
{
    private static final long serialVersionUID = 1L;
    public TagTypeTable(TConfig config, String langId) throws TagTypeTableException
    {
        try
        {
            TLogger.debug("Entry in TagTypeTable - TagTypeTable Constructor ");
            this.setSelectable(true);
            this.setImmediate(true);
            this.setColumnReorderingAllowed(true);
            this.setColumnCollapsingAllowed(true);
            this.setPageLength(10);
            this.setWriteThrough(false);
            this.setHeight(Constants.TAB_SHEET_HEIGHT);
            this.setWidth(Constants.TAB_SHEET_WIDTH);
            
            LangTranslator translator = new LangTranslator(config, langId);
            final String TAGTYPE_ID = translator.getLabel(this.TAG_TYPE_ID, TAG_TYPE_MASTER);
            final String NAME = translator.getLabel(this.NAME, TAG_TYPE_MASTER);
            final String START_DATE = translator.getLabel(this.START_DATE, TAG_TYPE_MASTER);
            final String END_DATE = translator.getLabel(this.END_DATE, TAG_TYPE_MASTER);
            final String STATUS = translator.getLabel(this.STATUS, TAG_TYPE_MASTER);
            final String TENANT = translator.getLabel(this.TENANT, TAG_TYPE_MASTER);
            
            
            
            
            this.addContainerProperty(TAGTYPE_ID,  String.class,  null);
            this.addContainerProperty(NAME,  String.class,  null);
            this.addContainerProperty(START_DATE,  String.class,  null);
            this.addContainerProperty(END_DATE,  String.class,  null);
            this.addContainerProperty(STATUS,  String.class,  null);
            this.addContainerProperty(TENANT,  String.class,  null);
            
            setTableDataSource();
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagTypeTable - TagTypeTable Constructor ",exception);
            throw new TagTypeTableException("Un Handled Exception."+ exception.getMessage(),exception.getCause());
        }
        finally
        {
            TLogger.debug("Exit in TagTypeTable - TagTypeTable Constructor ");
        }
    }
    /**
     * <b>Algorithm:</b>
     * <pre>      
     *  1. Get the itemId
     *  2. Remove the Row from the Table.
     * </pre>
     *
     * @param itemId
     * @return boolean
     */
    @Override
    public boolean removeItem(Object itemId)
    {
        try
        {
            TLogger.debug("Entry in TagTypeTable - removeItem");
            if(itemId instanceof TagType)
            {
                TagType one = (TagType)itemId;
                Collection allItems =  this.getItemIds();
                for(Object existingItem : allItems)
                {
                   TagType two = (TagType)existingItem;
                   if(one.getTagTypeId().equals(two.getTagTypeId()))
                   {
                       return super.removeItem(two);
                   }
               }
            }
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagTypeTable - removeItem",exception);
        }
        finally
        {
            TLogger.debug("Exit in TagTypeTable - removeItem");
        }
        return super.removeItem(itemId);
    }
    /**
     * <b>Algorithm:</b>
     * <pre>      
     *  1. Inserting the Data into the table
     * </pre>
     *
     * @param itemId
     * @return boolean
     * @throws setTableDataSourceException
     */
    private void setTableDataSource() throws setTagTypeTableDataSourceException
    {
        try
        {
            TLogger.debug("Entry in TagTypeTable - setTableDataSource");
            List<TagType> tagTypeList= CommonManagement.masterDataManagement.getTagTypes();
            Iterator<TagType> tagTypeItr=tagTypeList.iterator();
            int i = 0;
            while(tagTypeItr.hasNext())
            {   
                TagType tagTypeObject = (TagType)tagTypeItr.next();
                String id = tagTypeObject.getTagTypeId().toString();
                String name = tagTypeObject.getName();
                String startDate = tagTypeObject.getStartDateTime().toString();
                String endDate = tagTypeObject.getEndDateTime().toString();
                String tenantName = CommonManagement.masterDataManagement.getTenantsById(tagTypeObject.getTenantId()).getName();
                String statusName = CommonManagement.masterDataManagement.getStatusById(tagTypeObject.getStatusId()).getName();
                
                this.addItem(new Object[] {id,name,startDate,endDate,statusName,tenantName},tagTypeObject);
                i++;
            }
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagTypeTable - setTableDataSource",exception);
            throw new setTagTypeTableDataSourceException("Un Handled Exception."+ exception.getMessage(),exception.getCause());
        }
        finally
        {
            TLogger.debug("Exit in TagTypeTable - setTableDataSource");
        }
    }
    /* Declaring Local Variables */
    private String TAG_TYPE_ID = "tagTypeId";
    private String NAME = "name";
    private String START_DATE = "startDate";
    private String END_DATE = "endDate";
    private String STATUS = "status";
    private String TENANT = "tenant";
    private String TAG_TYPE_MASTER = "TagTypeMaster";
}
class TagTypeTableException extends ApplicationException
{
    private static final long serialVersionUID = 1L;
    public TagTypeTableException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
class setTagTypeTableDataSourceException extends ApplicationException
{
    private static final long serialVersionUID = 1L;
    public setTagTypeTableDataSourceException(String message,Throwable cause)
    {
        super(message,cause);
    }
}