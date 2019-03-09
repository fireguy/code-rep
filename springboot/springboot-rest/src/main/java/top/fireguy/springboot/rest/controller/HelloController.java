package top.fireguy.springboot.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloService")
//http://localhost:8080/helloService
public class HelloController {
	@GetMapping
	public Map<String,Object> sayHello(){
		Map<String, Object> result = new HashMap<>();
		result.put("message", "Hello world! Spring boot rest");
		return result;
	}
	

}
