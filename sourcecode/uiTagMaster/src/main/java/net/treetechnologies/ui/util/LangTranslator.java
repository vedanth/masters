package net.treetechnologies.ui.util;

/* VaadinUtil Imports */
import net.treetechnologies.common.configuration.TConfig;
import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
/**  
* <b>Purpose:</b><br>
*  To Read the labels from the Java Object<br><br>
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
public class LangTranslator
{
    public LangTranslator(TConfig tconfig, String language)
    {
    	TLogger.debug("Entry in Constructor------>LangTranslator");
        this._tConfig = tconfig;
        this._language = language;
                
        // TODO Auto-generated constructor stub
        TLogger.debug("Exit in Constructor------>LangTranslator");
    }
    /* To get the Label from the tConfig Object */
     public String getLabel(String fieldName,String formName) throws GetLabelException
     {
 		String labelMessage = null;
 		try
 		{
 		    TLogger.debug("Entry LangTranslator - getLabel");
 		    
 			labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+formName+DOT+ATTRIBUTES+DOT+fieldName+DOT+LABEL+DOT+VALUE);
 			
 		}
 		catch(Exception ex)
 		{
 		    TLogger.error("** Label name is not configured in language xml for ***"+"formName::"+formName+"fieldName::"+fieldName);
 		    throw new GetLabelException(ex.getMessage(), ex.getCause());
 		}
 		finally
 		{
 		    TLogger.debug("Exit LangTranslator - getLabel");
 		}
 		
 		return labelMessage;
 		
 	 }
     /* To get the RuleAction from the tConfig Object */
     public String getRuleAction(String fieldName,String formName) throws GetRuleActionException
     {
        String labelMessage = null;
        try
        {
            TLogger.debug("Entry LangTranslator - getLabel");
            
            labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+formName+DOT+ATTRIBUTES+DOT+RULE+DOT+LABEL+DOT+fieldName);
            
        }
        catch(Exception ex)
        {
            TLogger.error("** Label name is not configured in language xml for ***"+"formName::"+formName+"fieldName::"+fieldName);
            throw new GetRuleActionException(ex.getMessage(), ex.getCause());
        }
        finally
        {
            TLogger.debug("Exit LangTranslator - getLabel");
        }
        
        return labelMessage;
        
     }
     /* To get the ruleColumn from the tConfig Object */
     public String getRuleColumn(String fieldName,String formName) throws GetRuleColumnException
     {
        String labelMessage = null;
        try
        {
            TLogger.debug("Entry LangTranslator - getLabel");
            
            labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+formName+DOT+ATTRIBUTES+DOT+RULE+DOT+RULE_COLUMN+DOT+fieldName);
            
        }
        catch(Exception ex)
        {
            TLogger.error("** Label name is not configured in language xml for ***"+"formName::"+formName+"fieldName::"+fieldName);
            throw new GetRuleColumnException(ex.getMessage(), ex.getCause());
        }
        finally
        {
            TLogger.debug("Exit LangTranslator - getLabel");
        }
        
        return labelMessage;
        
     }
     /* To get the Form Name from the tConfig Object */
     public String getWindowCaption(String formName) throws GetWindowCaptionException
     {
        String labelMessage = null;
        try
        {
            TLogger.debug("Entry LangTranslator - getLabel");
            
            labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+formName+DOT+FORM+DOT+NAME);
            
        }
        catch(Exception ex)
        {
            TLogger.error("** Label name is not configured in language xml for ***"+"formName::"+formName);
            throw new GetWindowCaptionException(ex.getMessage(), ex.getCause());
        }
        finally
        {
            TLogger.debug("Exit LangTranslator - getLabel");
        }
        
        return labelMessage;
        
     }
     /* To get the MandatoryMessage from the tConfig Object */
     public String getMandatoryMessage(String fieldName,String formName) throws GetMandatoryMessageException
     {
         String labelMessage = null;
         try
         {
             TLogger.debug("Entry LangTranslator - getMandatoryMessage");
             
             labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+formName+DOT+ATTRIBUTES+DOT+fieldName+DOT+VALIDATOR+DOT+MANADATORY_VALIDATOR+DOT+MESSAGE);
             
         }
         catch(Exception ex)
         {
             TLogger.error("** MandatoryMessage is not configured in language xml for ***"+"formName::"+formName+"fieldName::"+fieldName);
             throw new GetMandatoryMessageException(ex.getMessage(), ex.getCause());
         }
         finally
         {
             TLogger.debug("Exit LangTranslator - getMandatoryMessage");
         }
         
         return labelMessage;
         
     }
     /* To get the Length from the tConfig Object */
     public String getLength(String fieldName,String formName)throws GetLengthException
     {
         String labelMessage = null;
         try
         {
             TLogger.debug("Entry LangTranslator - getLength");
             
             labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+formName+DOT+ATTRIBUTES+DOT+fieldName+DOT+VALIDATOR+DOT+LENGTH_VALIDATOR+DOT+MESSAGE);
             
         }
         catch(Exception ex)
         {
             TLogger.error("** Length is not configured in language xml for ***"+"formName::"+formName+"fieldName::"+fieldName);
             throw new GetLengthException(ex.getMessage(), ex.getCause());
         }
         finally
         {
             TLogger.debug("Exit LangTranslator - getLength");
         }
         
         return labelMessage;
         
     }
     /* To get the MaxLength from the tConfig Object */
     public String getMaxLength(String fieldName,String formName) throws GetMaxLengthException
     {
         String labelMessage = null;
         try
         {
             TLogger.debug("Entry LangTranslator - getMaxLength");
             
             labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+formName+DOT+ATTRIBUTES+DOT+fieldName+DOT+VALIDATOR+DOT+LENGTH_VALIDATOR+DOT+MAX_LENGTH);
             
         }
         catch(Exception ex)
         {
             TLogger.error("** MaxLength is not configured in language xml for ***"+"formName::"+formName+"fieldName::"+fieldName);
             throw new GetMaxLengthException(ex.getMessage(), ex.getCause());
         }
         finally
         {
             TLogger.debug("Exit LangTranslator - getMaxLength");
         }
         
         return labelMessage;
         
     }
     /* To get the MinLength from the tConfig Object */
     public String getMinLength(String fieldName,String formName) throws GetMinLengthException
     {
         String labelMessage = null;
         try
         {
             TLogger.debug("Entry LangTranslator - getMinLength");
             
             labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+formName+DOT+ATTRIBUTES+DOT+fieldName+DOT+VALIDATOR+DOT+LENGTH_VALIDATOR+DOT+MIN_LENGTH);
             
         }
         catch(Exception ex)
         {
             TLogger.error("** MinLength is not configured in language xml for ***"+"formName::"+formName+"fieldName::"+fieldName);
             throw new GetMinLengthException(ex.getMessage(), ex.getCause());
         }
         finally
         {
             TLogger.debug("Exit LangTranslator - getMinLength");
         }
         
         return labelMessage;
         
     }
     /* To get the Default Value or Common Labels from the tConfig Object */
     public String getValue(String fieldName) throws GetValueException
     {
         String labelMessage = null;
         try
         {
             TLogger.debug("Entry LangTranslator - getName");
             
             labelMessage = _tConfig.getString(UI_CONFIGURATION+DOT+_language+DOT+COMMON+DOT+BUTTON+DOT+fieldName);
             
         }
         catch(Exception ex)
         {
             TLogger.error("** Name is not configured in language xml for ***fieldName::"+fieldName);
             throw new GetValueException(ex.getMessage(), ex.getCause());
         }
         finally
         {
             TLogger.debug("Exit LangTranslator - getName");
         }
         
         return labelMessage;
         
     }
     private final String UI_CONFIGURATION = "UIConfiguration";   
     private final String DOT = ".";
     private TConfig _tConfig = null;
     private  String _language = null;
     private final String LABEL ="label";
     private final String VALUE ="value";
     private final String ATTRIBUTES = "attributes";
     private final String NAME = "name";
     private final String RULE = "rule";
     private final String FORM = "form";
     private final String RULE_COLUMN = "ruleColumn";
     private final String VALIDATOR = "validator";
     private final String MANADATORY_VALIDATOR = "mandatoryValidator";
     private final String MESSAGE = "message";
     private final String LENGTH_VALIDATOR = "lengthValidator";
     private final String MIN_LENGTH = "minValue";
     private final String MAX_LENGTH = "maxValue";
     private final String COMMON = "common";
     private final String BUTTON = "button";
}
@SuppressWarnings("serial")
class GetLabelException extends ApplicationException
{
    
    public GetLabelException()
    {
       
    }
    
    public GetLabelException(String message,Throwable cause)
    {
			super(message, cause);

    }
}
@SuppressWarnings("serial")
class GetWindowCaptionException extends ApplicationException
{
    
    public GetWindowCaptionException()
    {
       
    }
    
    public GetWindowCaptionException(String message,Throwable cause)
    {
            super(message, cause);

    }
}
@SuppressWarnings("serial")
class GetMandatoryMessageException extends ApplicationException
{
    
    public GetMandatoryMessageException()
    {
       
    }
    
    public GetMandatoryMessageException(String message,Throwable cause)
    {
            super(message, cause);

    }
}
@SuppressWarnings("serial")
class GetMinLengthException extends ApplicationException
{
    
    public GetMinLengthException()
    {
       
    }
    
    public GetMinLengthException(String message,Throwable cause)
    {
            super(message, cause);

    }
}
@SuppressWarnings("serial")
class GetMaxLengthException extends ApplicationException
{
    
    public GetMaxLengthException()
    {
       
    }
    
    public GetMaxLengthException(String message,Throwable cause)
    {
            super(message, cause);

    }
}
@SuppressWarnings("serial")
class GetValueException extends ApplicationException
{
    
    public GetValueException()
    {
       
    }
    
    public GetValueException(String message,Throwable cause)
    {
            super(message, cause);

    }
}

@SuppressWarnings("serial")
class GetLengthException extends ApplicationException
{
    
    public GetLengthException()
    {
       
    }
    
    public GetLengthException(String message,Throwable cause)
    {
            super(message, cause);

    }
}
@SuppressWarnings("serial")
class GetRuleActionException extends ApplicationException
{
    
    public GetRuleActionException()
    {
       
    }
    
    public GetRuleActionException(String message,Throwable cause)
    {
            super(message, cause);

    }
}
@SuppressWarnings("serial")
class GetRuleColumnException extends ApplicationException
{
    
    public GetRuleColumnException()
    {
       
    }
    
    public GetRuleColumnException(String message,Throwable cause)
    {
            super(message, cause);

    }
}