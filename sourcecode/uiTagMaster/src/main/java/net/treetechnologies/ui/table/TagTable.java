package net.treetechnologies.ui.table;

/* Basic Java imports */
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* VaadinUtil Imports */
import net.treetechnologies.common.configuration.TConfig;
import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.common.ui.util.CommonManagement;

/* ORM Imports */
import net.treetechnologies.entities.schema.masters.Tag;
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
public class TagTable extends Table
{
    private static final long serialVersionUID = 1L;
    public TagTable(TConfig config, String langId) throws TagTableException
    {
        try
        {
            TLogger.debug("Entry in TagTable - TagTable Constructor ");
            this.setSelectable(true);
            this.setImmediate(true);
            this.setColumnReorderingAllowed(true);
            this.setColumnCollapsingAllowed(true);
            this.setPageLength(10);
            this.setWriteThrough(false);
            this.setHeight(Constants.TAB_SHEET_HEIGHT);
            this.setWidth(Constants.TAB_SHEET_WIDTH);
            
            LangTranslator translator = new LangTranslator(config, langId);
            final String TAG_ID = translator.getLabel(this.TAG_ID, TAG_MASTER);
            final String NAME = translator.getLabel(this.NAME, TAG_MASTER);
            final String START_DATE = translator.getLabel(this.START_DATE, TAG_MASTER);
            final String END_DATE = translator.getLabel(this.END_DATE, TAG_MASTER);
            final String STATUS = translator.getLabel(this.STATUS, TAG_MASTER);
            final String TENANT = translator.getLabel(this.TENANT, TAG_MASTER);
            final String TAGTYPE = translator.getLabel(this.TAG_TYPE, TAG_MASTER);
            
            this.addContainerProperty(TAG_ID,  String.class,  null);
            this.addContainerProperty(NAME,  String.class,  null);
            this.addContainerProperty(START_DATE,  String.class,  null);
            this.addContainerProperty(END_DATE,  String.class,  null);
            this.addContainerProperty(STATUS,  String.class,  null);
            this.addContainerProperty(TENANT,  String.class,  null);
            this.addContainerProperty(TAGTYPE,  String.class,  null);
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagTable - FactTable Constructor ",exception);
            throw new TagTableException("Un Handled Exception."+ exception.getMessage(),exception.getCause());
        }
        finally
        {
            TLogger.debug("Exit in TagTable - FactTable Constructor ");
        }
    }
    /**
     * <b>Algorithm:</b>
     * <pre>      
     *  1. Get the itemId
     *  2. Filter the Items with itemId 
     *  3. return the remove Item.
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
            TLogger.debug("Entry in TagTable - removeItem");
            if(itemId instanceof Tag)
            {
                Tag one = (Tag)itemId;
                Collection allItems =  this.getItemIds();
                for(Object existingItem : allItems)
                {
                   Tag two = (Tag)existingItem;
                   if(one.getTagId().equals(two.getTagId()))
                   {
                       return super.removeItem(two);
                   }
               }
            }
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagTable - removeItem",exception);
        }
        finally
        {
            TLogger.debug("Exit in TagTable - removeItem");
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
    public void setTableDataSource(Long tagtypeId) throws SetTagTableDataSourceException
    {
        try
        {
            TLogger.debug("Entry in TagTable - setTableDataSource");
            List<Tag> tagList = CommonManagement.masterDataManagement.getTagMastersByTagTypeId(tagtypeId);
            Iterator<Tag> tagTypeItr=tagList.iterator();
            int i = 0;
            while(tagTypeItr.hasNext())
            {   
                Tag tagObject = (Tag)tagTypeItr.next();
                String id = tagObject.getTagId().toString();
                String name = tagObject.getName();
                String startDate = tagObject.getStartDateTime().toString();
                String endDate = tagObject.getEndDateTime().toString();
                String tenantName = CommonManagement.masterDataManagement.getTenantsById(tagObject.getTenantId()).getName();
                String statusName = CommonManagement.masterDataManagement.getStatusById(tagObject.getStatusId()).getName();
                String tagTypeName = CommonManagement.masterDataManagement.getTagTypeById(tagObject.getTagTypeId()).getName();
                
                this.addItem(new Object[] {id,name,startDate,endDate,statusName,tenantName,tagTypeName},tagObject);
                i++;
            }
        }
        catch(Exception exception)
        {
            TLogger.error("Exception in TagTable - setTableDataSource",exception);
            throw new SetTagTableDataSourceException("Un Handled Exception."+ exception.getMessage(),exception.getCause());
        }
        finally
        {
            TLogger.debug("Exit in TagTable - setTableDataSource");
        }
    }
    /* Declaring Local Variables */
    private String TAG_ID = "tagId";
    private String NAME = "name";
    private String START_DATE = "startDate";
    private String END_DATE = "endDate";
    private String STATUS = "status";
    private String TENANT = "tenant";
    private String TAG_MASTER = "TagMaster";
    private String TAG_TYPE = "tagType";
}
class TagTableException extends ApplicationException
{
    private static final long serialVersionUID = 1L;
    public TagTableException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
class SetTagTableDataSourceException extends ApplicationException
{
    private static final long serialVersionUID = 1L;
    public SetTagTableDataSourceException(String message,Throwable cause)
    {
        super(message,cause);
    }
}