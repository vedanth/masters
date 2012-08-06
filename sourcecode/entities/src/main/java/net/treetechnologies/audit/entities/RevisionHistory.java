package net.treetechnologies.audit.entities;

/**  
* <b>Purpose:</b><br>
*  Entity Class to maintain the revision history on entities
*  along with the user name<br><br>
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


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import net.treetechnologies.audit.listeners.RevisionHistoryListener;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;

@Entity(name="revision_history")
@RevisionEntity(RevisionHistoryListener.class)
public class RevisionHistory extends DefaultRevisionEntity implements Serializable 
{ 	
	public String getUserName() 
	{
		return this.userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	
	private String userName;

	@ElementCollection
    @JoinTable(name = "rev_changes", joinColumns = @JoinColumn(name = "rev"))
    @Column(name = "entity_name_v")
    @ModifiedEntityNames
    private Set<String> modifiedEntityNames;
    
}
