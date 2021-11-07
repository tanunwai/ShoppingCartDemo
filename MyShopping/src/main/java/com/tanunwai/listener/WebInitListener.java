package com.tanunwai.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp2.BasicDataSource;
import com.tanunwai.beans.DBConfigBeans;

@WebListener
public class WebInitListener implements ServletContextListener {

	public WebInitListener() {
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce)  {
		/*聆聽應用系統物件取得實際目錄*/
         String realPath=sce.getServletContext().getRealPath("/WEB-INF/resources/properties/datasource.properties");
         /*建構Properties物件以問出資料庫設定組態字串*/
         Properties properties=new Properties();
         try {
			properties.load(new FileInputStream(realPath));
			String dbURL=properties.getProperty("dbURL");
			String userName=properties.getProperty("userName");
			String password=properties.getProperty("password");
			String driverClassName=properties.getProperty("driverclassname");
			/*將其封裝成JavaBean物件*/
			DBConfigBeans dbConfig=new DBConfigBeans();
			dbConfig.setUrl(dbURL);
			dbConfig.setUserName(userName);
			dbConfig.setPassword(password);
			dbConfig.setDriverClassName(driverClassName);
			sce.getServletContext().setAttribute("dbConfig", dbConfig);
			/*建構dataSource物件並注入讓應用系統狀態管理*/
			BasicDataSource dataSource=new BasicDataSource();
			dataSource.setUrl(dbConfig.getUrl());
			dataSource.setUsername(dbConfig.getUserName());
			dataSource.setPassword(dbConfig.getPassword());
			dataSource.setDriverClassName(dbConfig.getDriverClassName());
			sce.getServletContext().setAttribute("dataSource", dataSource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
         
	}

}