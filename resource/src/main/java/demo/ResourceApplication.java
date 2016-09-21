package demo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class ResourceApplication {

	@RequestMapping("/")
	public Message home(Principal user) {
		System.out.println(user);
		String email = "Hello";//((ArrayList)((LinkedHashMap)((OAuth2Authentication) user).getUserAuthentication().getDetails()).get("emails")).get(0).toString();
		Message m = new Message(email);
		m.setId(user.getName());
		return m;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public Message samplePost() {
		System.out.println("in post222222");
		String email = "Hello";//((ArrayList)((LinkedHashMap)((OAuth2Authentication) user).getUserAuthentication().getDetails()).get("emails")).get(0).toString();
		Message m = new Message(email);
		m.setId("12323232323");
		return m;
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}

}

class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setId(String id) {
		this.id = id;
	}
}
