package com.nag.auctions.configuration;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorConfiguration {
	
	private static final String CONNECTOR = "org.apache.coyote.http11.Http11NioProtocol";
	private static final String SCHEME = "http";
	
	private static final String USER_SECURITY_CONSTRAINT = "CONFIDENTIAL";
	private static final String PATTERN = "/*";
	
	private static final int PORT_INCOMING = 8080;
	private static final int PORT_OUTGOING = 8443;

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tswsf = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint(USER_SECURITY_CONSTRAINT);
                
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern(PATTERN);
                
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        
        tswsf.addAdditionalTomcatConnectors(getHttpConnector());
        return tswsf;
    }

    private Connector getHttpConnector() {
        Connector connector = new Connector(CONNECTOR);
        connector.setScheme(SCHEME);
        connector.setPort(PORT_INCOMING);
        connector.setSecure(false);
        connector.setRedirectPort(PORT_OUTGOING);
        return connector;
    }
    
}