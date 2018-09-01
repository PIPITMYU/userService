package com.user.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.user.utils.redis.MyRedis;






public class TomcatListener implements ServletContextListener{
	
	

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		
		System.out.println("tomcat关闭..........");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		 //初始化redis
		MyRedis.initRedis();
		System.out.println("tomcate启动..............");
	}

}