package com.ahmed.loaderBalancer;


import com.ahmed.loaderBalancer.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;







@SpringBootApplication
@RestController
@RibbonClient(name="chatbook")
public class LoaderBalancerApplication {
	
	@Autowired
	RestTemplate template;
	
	@GetMapping("/invoke")
	public String invokeCharbook() {
		//String url = "http://chatbook/chatbook-application/chat";
		//return template.getForObject("http://chatbook/chatbook-application/chat", String.class);
		return template.getForObject("http://chatbook/books/testi/port", String.class);
	}
	
	
	
	@GetMapping("")
	//@RequestMapping( method = RequestMethod.GET)
	public String restAllBook() {
		//String url = "http://chatbook/chatbook-application/chat";
		//return template.getForObject("http://chatbook/chatbook-application/chat", String.class);
		return template.getForObject("http://chatbook/books/api", String.class);
		
	}

	
	//@GetMapping("")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String restCharbookOne(@PathVariable("id") Long id) {
		//String url = "http://chatbook/chatbook-application/chat";
		//return template.getForObject("http://chatbook/chatbook-application/chat", String.class);
		String i = String.valueOf(id);
		return template.getForObject("http://chatbook/books/api/"+i, String.class);
	}
	
	//@GetMapping("")
	@RequestMapping(value="/search/{titreBook}", method = RequestMethod.GET)
	public String restCharbookSearch(@PathVariable("titreBook") String titreBook) {
		//String url = "http://chatbook/chatbook-application/chat";
		//return template.getForObject("http://chatbook/chatbook-application/chat", String.class);
		return template.getForObject("http://chatbook/books/api/"+titreBook, String.class);
	}
	
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void restCharbookDelete(@PathVariable("id") Long id) {
		//String url = "http://chatbook/chatbook-application/chat";
		//return template.getForObject("http://chatbook/chatbook-application/chat", String.class);
		String i = String.valueOf(id);
		 template.delete("http://chatbook/books/api/"+i); 
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public void restCharbookPost(@RequestBody Book livre) {
		//String url = "http://chatbook/chatbook-application/chat";
		//return template.getForObject("http://chatbook/chatbook-application/chat", String.class);

		 template.postForLocation("http://chatbook/api/", livre); 
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void restCharbookPut(@RequestBody Produit produit) {
		//String url = "http://chatbook/chatbook-application/chat";
		//return template.getForObject("http://chatbook/chatbook-application/chat", String.class);

		 template.put("http://chatbook/books/api/", produit); 
	}
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(LoaderBalancerApplication.class, args);
	}
	
	
	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}

}

