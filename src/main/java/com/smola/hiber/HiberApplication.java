package com.smola.hiber;

import com.smola.hiber.model.*;
import com.smola.hiber.repositories.BookRepository;
import com.smola.hiber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class HiberApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;



	public static void main(String[] args) {
		SpringApplication.run(HiberApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {

		List<Coordinates> gubalowkaRouteCoordinates = Arrays.asList(new Coordinates("59,78", "58,56"), new Coordinates("65,55", "23,55"));
		List<Coordinates> giewontRouteCoordinates = Arrays.asList(new Coordinates("51,78", "52,56"), new Coordinates("75,55", "24,55"));
		Route gubalowka = new Route("Gubalowka");
		Route giewont = new Route("Giewont");
		Route kasprowy = new Route("Kasprowy Wierch");

		gubalowka.addCoordinates(gubalowkaRouteCoordinates);
		giewont.addCoordinates(giewontRouteCoordinates);
		User user = new User();
		user.setFirstName("Marcin");
		user.setLastName("Smola");

		User mati = new User();
		mati.setFirstName("Mateusz");
		mati.setLastName("Tapa");

		mati.addTravelledRoute(giewont);
		mati.addCreatedRoute(gubalowka);
		mati.addCreatedRoute(giewont);
		mati.addCreatedRoute(kasprowy);

		user.addTravelledRoute(giewont);
		user.addTravelledRoute(gubalowka);
		userRepository.save(user);
		userRepository.save(mati);

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


		Book book_c = new Book();
        book_c.addPublisher(publisherA);
        book_c.addPublisher(publisherB);
        book_c.addPublisher(publisherC);
		bookRepository.save(book_a);
		bookRepository.save(book_b);
		bookRepository.save(book_c);

	}
}

