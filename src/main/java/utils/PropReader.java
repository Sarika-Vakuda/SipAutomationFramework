package utils;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

public class PropReader 
{
	private static final String filepath =
		    System.getProperty("user.dir") +
		    File.separator + "src" +
		    File.separator + "test" +
		    File.separator + "resources" +
		    File.separator + "Config" +
		    File.separator + "Config.properties";

	
	//private static String filepath=System.getProperty("user.dir")+"//src//test//resources//Config//Config.properties"; 
	
	private static Properties prop=new Properties();
	
	static
	{
		try 
		{
			FileInputStream file=new FileInputStream(filepath);
			
			prop.load(file);
				
			file.close();
				
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	} 
	 // ---------- BASIC ----------
	
	public static String getProperty(String key)
	{
	    return prop.getProperty(key);
	}
	
    public static String getBrowser() {
        return prop.getProperty("browser");
    }

    public static String getEnv() {
        return prop.getProperty("env");
    }

    public static String getEnvURL() {
        String env = getEnv();
        return prop.getProperty("env." + env + ".url");
    }

    public static String getPassword() {
        String env = getEnv();
        return prop.getProperty("login." + env + ".password");
    }

    // ---------- USER DATA ----------
    public static String getPhoneNum(String user) 
    {
        return prop.getProperty(user + ".mobileNum");
    }

    public static String getPanNumber(String user)
    {
        return prop.getProperty(user + ".pannumber");
    }

    public static String getEmail(String user)
    {
        return prop.getProperty(user + ".email");
    }

    // ---------- BANK ----------
    public static String getBankName(String user) 
    {
        return prop.getProperty(user + ".bankName");
    }
    public static String getAccounHolderName(String user )
	{
		return prop.getProperty(user + ".accountHolderName");
	}
    public static String getAccountNumber(String user)
    {
        return prop.getProperty(user + ".accountNum");
    }

    public static String getIFSCCode(String user) 
    {
        return prop.getProperty(user + ".ifscCode");
    }
    public static String getExecutorName(String executorName)
    {
	
		return prop.getProperty("executor.name");
	}
    public static String getExecutorEmail() {
		
		return prop.getProperty("executor.email");
	}
    public static String getExecutorAppPassword() {
		
		return prop.getProperty("executor.email.password");
	}
    public static String getOSName() {
	
		return prop.getProperty("os.name");
	}
    public static String getJavaVersion() 
    {
		
		return prop.getProperty("java.version");
	}
    public static String getQARecipients() 
    {
        return prop.getProperty("email.qa.recipients");
    }

    public static String getManagerRecipients() 
    {
        return prop.getProperty("email.manager.recipients");
    }

	// ---------- REMOTE EXECUTION ----------
	public static String getRemoteURL() {
	    return prop.getProperty("remote.url"); // reads remote.url from config.properties
	}

	
}
