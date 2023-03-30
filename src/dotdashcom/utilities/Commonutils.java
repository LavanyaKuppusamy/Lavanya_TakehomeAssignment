package dotdashcom.utilities;

import java.io.IOException;
import java.util.Properties;
import dotdashcom.constant.*;

public class Commonutils {

	
public  void loadProperties() {
		
		Properties properties=new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Constant.loginurl= properties.getProperty("loginurl");
		Constant.ActualSuccessMsg= properties.getProperty("ActualSuccessMsg");
        Constant.ErrorMsg=properties.getProperty("ErrorMsg");
		Constant.Checkboxurl=properties.getProperty("Checkboxurl");
		Constant.Contextmenuurl=properties.getProperty("Contextmenuurl");
		Constant.draganddropurl=properties.getProperty("draganddropurl");
		Constant.dropdownurl=properties.getProperty("dropdownurl");
		Constant.dynamiccontenturl=properties.getProperty("dynamiccontenturl");
		Constant.dynamiccontrolurl=properties.getProperty("dynamiccontrolurl");
		Constant.dynamicloading=properties.getProperty("dynamicloading");
		Constant.FileDownload=properties.getProperty("FileDownload");
		Constant.FloatingMenu=properties.getProperty("FloatingMenu");
		Constant.iframeurl=properties.getProperty("iframeurl");
		Constant.MouseHover=properties.getProperty("MouseHover");
		Constant.JavaScriptAlert=properties.getProperty("JavaScriptAlert");
	    Constant.JavaScriptError=properties.getProperty("JavaScriptError");
	    Constant.Window=properties.getProperty("Window");
		Constant.Notificationmsg=properties.getProperty("Notificationmsg");
		
}}

