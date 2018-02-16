package com.control2me.javaapi;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerDocumentSetup extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		BeanConfig beanconfig = new BeanConfig();
		beanconfig.setVersion("1.0.0");
		beanconfig.setTitle("Sample REST APIs for testing purpose");
		beanconfig.setDescription("For testing only");
		beanconfig.setSchemes(new String[] {"http"});
		beanconfig.setHost("localhost:8082");
		beanconfig.setBasePath("/webservices/services");
		beanconfig.setResourcePackage("com.control2me.javaapi.services");
		beanconfig.setScan(true);
	}
}
