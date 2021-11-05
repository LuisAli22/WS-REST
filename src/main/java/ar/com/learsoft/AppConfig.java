package ar.com.learsoft;

import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ar.com")
@EntityScan("ar.com")
public class AppConfig {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/checkservice/*");
		return registrationBean;
	}

}
