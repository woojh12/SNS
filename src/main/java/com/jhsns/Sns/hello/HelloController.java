package com.jhsns.Sns.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@ResponseBody
	@GetMapping("/test/hello")
	public String hello()
	{
		return "hellotest";
	}
}
