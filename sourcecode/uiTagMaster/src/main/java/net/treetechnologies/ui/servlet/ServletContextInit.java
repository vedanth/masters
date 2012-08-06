package net.treetechnologies.ui.servlet;
 
/*  Basic Java Imports */
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/* VaadinUtil Imports */
import net.treetechnologies.common.configuration.TConfig;
/**  
* <b>Purpose:</b><br>
*  To Read the Language xml Files<br><br>
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
public class ServletContextInit implements ServletContextListener
{ 
	ServletContext context;
	
	public void contextInitialized(ServletContextEvent contextEvent) 
	{
		context = contextEvent.getServletContext();
	
	try
	{
		TConfig config = TConfig.getInstance();
		
		String directoryPath = System.getenv("LANG_XML");

		String fileName=null;

		File directory =new File(directoryPath);

		File[] listOfFiles = directory.listFiles();

		for (int i = 0; i < listOfFiles.length; i++)
		{
			if (listOfFiles[i].isFile())
			{
				fileName = listOfFiles[i].getName();
				
				if (fileName.endsWith(".xml") || fileName.endsWith("XML"))
				{
					config.addConfigurationFile(directoryPath+"/"+fileName);
				}
			}
		}
		
		context.setAttribute("CONFIG", config);
	}
	catch(Exception e)
	{
	   e.printStackTrace();
	}
	}
	public void contextDestroyed(ServletContextEvent contextEvent) {
		context = contextEvent.getServletContext();
		context.removeAttribute("CONFIG");
	}
}