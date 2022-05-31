package com.ahmed.books;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ahmed.books.entities.Book;
import com.ahmed.books.entities.Genre;
import com.ahmed.books.entities.Role;
import com.ahmed.books.entities.User;
import com.ahmed.books.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/testi")
public class Books1Application {
	
	@Value("${server.port}")
	private String port;

	@GetMapping("/port")
	public String chatNow() {
		return "application is up on port : " + port;
	}
	
	@GetMapping("/")
	public String refresh() {
		return "rien";
	}

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;


	public static void main(String[] args) {
		SpringApplication.run(Books1Application.class, args);
	}
	
	
	public void run(String... args) throws Exception {
	/*produitService.saveProduit(new Produit("PC Dell", 2600.0, new Date()));
	produitService.saveProduit(new Produit("PC Asus", 2800.0, new Date()));
	produitService.saveProduit(new Produit("Imprimante Epson", 900.0, new Date()));*/
		repositoryRestConfiguration.exposeIdsFor(Book.class, Genre.class);
		//repositoryRestConfiguration.exposeIdsFor(Genre.class);
	}
	
	
	@Autowired
	UserService userService;
	
	/*@PostConstruct
	void init_users() {
	//ajouter les rôles
	//userService.addRole(new Role(null,"ADMIN"));
	//userService.addRole(new Role(null,"USER"));
	//ajouter les users
	////////////userService.saveUser(new User("admin","speedo@gmail.com","123",null));
	//userService.saveUser(new User(null,"nadhem","123",true,null));
	//userService.saveUser(new User(null,"yassine","123",true,null));
	//ajouter les rôles aux users
	////////userService.addRoleToUser("speedo@gmail.com", "Admin");
	//userService.addRoleToUser("admin", "USER");
	//userService.addRoleToUser("nadhem", "USER");
	//userService.addRoleToUser("yassine", "USER");
	}*/

	@Bean
	BCryptPasswordEncoder getBCE() {
	return new BCryptPasswordEncoder();
	}
}
