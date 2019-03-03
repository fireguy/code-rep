package top.fireguy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is known as a stereotypeannotation. It provides hints for people reading
 * the code and for Spring that the class plays a specificrole. In this case,
 * our class is a web @Controller, so Spring considers it when handling
 * incomingweb requests.
 * 
 * See the MVC section(https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-framework-reference/web.html#mvc) in the Spring ReferenceDocumentation for more details.
 * 
 * The second class-level annotation is @EnableAutoConfiguration. This
 * annotation tells Spring Bootto “guess” how you want to configure Spring,
 * based on the jar dependencies that you have added.
 * Sincespring-boot-starter-web added Tomcat and Spring MVC, the
 * auto-configuration assumes thatyou are developing a web application and sets
 * up Spring accordingly.
 * 
 * @author slfu
 *
 */
@RestController
@EnableAutoConfiguration
public class Example {

	/**
	 * The @RequestMapping annotation provides “routing” information. It tells
	 * Spring that any HTTP requestwith the / path should be mapped to the home
	 * method. The @RestController annotation tells Springto render the resulting
	 * string directly back to the caller.
	 * 
	 * If you open a web browser to localhost:8080, you should see the following
	 * output:Hello World
	 * 
	 * @return
	 */
	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	/**
	 * This is just a standard method that followsthe Java convention for an
	 * application entry point. Our main method delegates to Spring
	 * Boot’sSpringApplication class by calling run. SpringApplication bootstraps
	 * our application, startingSpring, which, in turn, starts the auto-configured
	 * Tomcat web server. We need to pass Example.classas an argument to the run
	 * method to tell SpringApplication which is the primary Spring component.The
	 * args array is also passed through to expose any command-line arguments.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Example.class, args);

	}

}
