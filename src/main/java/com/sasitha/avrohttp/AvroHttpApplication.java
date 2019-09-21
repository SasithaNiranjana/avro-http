package com.sasitha.avrohttp;

import com.sasitha.springavrowebflux.domain.StudentDomain;
import org.apache.avro.ipc.ResponderServlet;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class AvroHttpApplication {

	@Value("${server.port:8080}")
	private int serverPort;

	@Value("${server.context-path:}")
	private String serverContextPath;

	@Autowired
	private StudentDomain studentDomainImpl;


	public static void main(String[] args) {
		SpringApplication.run(AvroHttpApplication.class, args);
	}

	@Bean
	public ServletWebServerFactory getJettyServletWebFactory() throws IOException {
		JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
		factory.setPort(serverPort);
		factory.setContextPath(serverContextPath);
		SpecificResponder responder = new SpecificResponder(StudentDomain.class,studentDomainImpl);
		ResponderServlet avroResponderServlet = new ResponderServlet(responder);
		factory.addServerCustomizers(s->{
			((WebAppContext)s.getHandler()).addServlet(new ServletHolder(avroResponderServlet),"/avro/");
		});
		return factory;
	}

}
