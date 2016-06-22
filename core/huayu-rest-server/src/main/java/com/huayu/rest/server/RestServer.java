package com.huayu.rest.server;

import java.util.Collections;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.util.security.Constraint;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;


/**
 * @author wangmingzhe
 * 
 */
public class RestServer{
	private String xmlPath;
	
	public RestServer(String xmlPath){
		this.xmlPath = xmlPath;
	}
	
	public RestServer(){}
	
	public void start(int port, String path, String packages) throws Exception{
		Server server = makeServer(port);        
        server.setHandler(makeContexts(path, packages));        
        server.start();
        while(!server.isStarted()){
        	Thread.sleep(100);
        }
        System.out.println("server has been started!");
		server.join();
	}	

	public void startWithRealm(int port, String path, String packages, String loginName, String realmPath) throws Exception{
		Server server = makeServer(port);        
        server.setHandler(getSecurityHandler(loginName, realmPath, server, makeContexts(path, packages)));        
        server.start();
        while(!server.isStarted()){
        	Thread.sleep(100);
        }
        System.out.println("server has been started!");
		server.join();		
	}
	
	private Server makeServer(int port){
        Server server = new Server();		
        ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);		
        server.setConnectors(new Connector[] { connector });
        return server;
	}
	
	private ContextHandlerCollection makeContexts(String path, String packages){
    	ContextHandlerCollection contexts = new ContextHandlerCollection();
		ServletHolder sh = new ServletHolder(org.glassfish.jersey.servlet.ServletContainer.class);
        sh.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, "com.huayu.rest.server.RestApplication");
        sh.setInitParameter(ServerProperties.PROVIDER_PACKAGES, packages);
        

        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(sh, path);
        if(StringUtil.isNotBlank(xmlPath)){
        	context.addEventListener(new org.springframework.web.context.ContextLoaderListener());
        	context.setInitParameter("contextConfigLocation", xmlPath);
        }
        contexts.setHandlers(new Handler[] {context });
        return contexts;

	}
	
	private ConstraintSecurityHandler getSecurityHandler(String loginName, String realmPath, Server server, ContextHandlerCollection contexts){
        HashLoginService login = new HashLoginService();
        login.setName(loginName);
        login.setConfig(realmPath);
        server.addBean(login);
        
        ConstraintSecurityHandler security = new ConstraintSecurityHandler();

        Constraint constraint = new Constraint();
        constraint.setName("auth");
        constraint.setAuthenticate(true);
        constraint.setRoles(new String[] { "user", "admin" });
        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec("/*");
        mapping.setConstraint(constraint);
        security.setConstraintMappings(Collections.singletonList(mapping));
        security.setAuthenticator(new BasicAuthenticator());
        security.setLoginService(login);        
        security.setHandler(contexts); 
        return security;
	}
}