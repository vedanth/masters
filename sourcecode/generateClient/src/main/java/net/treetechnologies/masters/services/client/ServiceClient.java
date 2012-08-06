package net.treetechnologies.masters.services.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import net.treetechnologies.masters.services.interfaces.masterdatamanagement.MasterDataManagement;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class ServiceClient 
{
	
	public static MasterDataManagement getMasterDataMangementInterface(String userName, String password)
	{
		MasterDataManagement masterDataManagement = null;
		try
		{
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			jaxWsProxyFactoryBean.setAddress("http://"+readUrl()+"/EntityServices/MasterDataManagement");
			jaxWsProxyFactoryBean.setServiceClass(MasterDataManagement.class);
			jaxWsProxyFactoryBean.setUsername(userName);
			jaxWsProxyFactoryBean.setPassword(password);
			masterDataManagement = (MasterDataManagement)jaxWsProxyFactoryBean.create();
		}
		catch(Exception applicationException)
		{
			applicationException.printStackTrace();
		}
		return masterDataManagement;
	}
	
	private static String readUrl()
	{
		Properties properties = new Properties();
		try
		{
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/ClientConfig.properties"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return (String)properties.get("url");
		
	}


}
