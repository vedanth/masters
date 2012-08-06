package net.treetechnologies.audit.listeners;

/**  
* <b>Purpose:</b><br>
*  Listener class to lookup user name from the session context and assign it to the revision history<br><br>
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
* 1        03-07-2012          Vedanth
* </pre><br>
*/


import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.audit.entities.RevisionHistory;

import org.hibernate.envers.RevisionListener;

public class RevisionHistoryListener implements RevisionListener
{
	@Override
	public void newRevision(Object revisionEntity) 
	{
		InitialContext ic = null;
		SessionContext sessionContext = null;
		try
		{
			ic = new InitialContext();
			sessionContext = (SessionContext) ic.lookup("java:comp/EJBContext");
			RevisionHistory revisionHistory = (RevisionHistory) revisionEntity;
			revisionHistory.setUserName(sessionContext.getCallerPrincipal().getName());
		}
		catch(NamingException namingException)
		{
			TLogger.error("Unable to retrieve the session context");
			namingException.printStackTrace();
		}
	}
	
    }
