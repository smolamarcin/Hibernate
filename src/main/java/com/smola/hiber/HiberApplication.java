package com.smola.hiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HiberApplication {



	public static void main(String[] args) {
		SpringApplication.run(HiberApplication.class, args);
	}

//	@Transactional
//	@Override
//	public void run(String... args) throws Exception {
//
//		List<CoordinatesSQL> gubalowkaRouteCoordinates = Arrays.asList(new CoordinatesSQL("59,78", "58,56"), new CoordinatesSQL("65,55", "23,55"));
//		List<CoordinatesSQL> giewontRouteCoordinates = Arrays.asList(new CoordinatesSQL("51,78", "52,56"), new CoordinatesSQL("75,55", "24,55"));
//		RouteSQL gubalowka = new RouteSQL("Gubalowka");
//		RouteSQL giewont = new RouteSQL("Giewont");
//		RouteSQL kasprowy = new RouteSQL("Kasprowy Wierch");
//
//		gubalowka.addCoordinates(gubalowkaRouteCoordinates);
//		giewont.addCoordinates(giewontRouteCoordinates);
//		UserSQL user = new UserSQL();
//		user.setFirstName("Marcin");
//		user.setLastName("Smola");
//
//		UserSQL mati = new UserSQL();
//		mati.setFirstName("Mateusz");
//		mati.setLastName("Tapa");
//
//		mati.addTravelledRoute(giewont);
//		mati.addCreatedRoute(gubalowka);
//		mati.addCreatedRoute(giewont);
//		mati.addCreatedRoute(kasprowy);
//
//		user.addTravelledRoute(giewont);
//		user.addTravelledRoute(gubalowka);
//		userRepository.save(user);
//		userRepository.save(mati);
//
//		final Publisher publisherA = new Publisher("Publisher A");
//		final Publisher publisherB = new Publisher("Publisher B");
//		final Publisher publisherC = new Publisher("Publisher C");
//
//		Book book_a = new Book("Book A", new HashSet<Publisher>() {{
//			add(publisherA);
//			add(publisherB);
//		}});
//		Book book_b = new Book("Book B", new HashSet<Publisher>() {{
//			add(publisherA);
//			add(publisherC);
//		}});
//
//
//		Book book_c = new Book();
//        book_c.addPublisher(publisherA);
//        book_c.addPublisher(publisherB);
//        book_c.addPublisher(publisherC);
//		bookRepository.save(book_a);
//		bookRepository.save(book_b);
//		bookRepository.save(book_c);
//
//	}
}

