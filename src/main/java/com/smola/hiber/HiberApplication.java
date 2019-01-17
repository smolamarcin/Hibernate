package com.smola.hiber;

import com.smola.hiber.model.*;
import com.smola.hiber.repositories.BookRepository;
import com.smola.hiber.repositories.PublisherRepository;
import com.smola.hiber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class HiberApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;



	public static void main(String[] args) {
		SpringApplication.run(HiberApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		Route gubalowka = new Route("Gubalowka");
		Route giewont = new Route("Giewont");
		Route kasprowy = new Route("Kasprowy Wierch");

		User user = new User();
		user.setFirstName("Marcin");
		user.setLastName("Smola");

		User mati = new User();
		mati.setFirstName("Mateusz");
		mati.setFirstName("Tapa");

		mati.addRoute(giewont);

		user.addRoute(giewont);
		user.addRoute(gubalowka);
//		userRepository.save(user);
//		userRepository.save(mati);

		final Publisher publisherA = new Publisher("Publisher A");
		final Publisher publisherB = new Publisher("Publisher B");
		final Publisher publisherC = new Publisher("Publisher C");

		Book book_a = new Book("Book A", new HashSet<Publisher>() {{
			add(publisherA);
			add(publisherB);
		}});
		Book book_b = new Book("Book B", new HashSet<Publisher>() {{
			add(publisherA);
			add(publisherC);
		}});
		bookRepository.save(book_a);
		bookRepository.save(book_b);

	}
}

