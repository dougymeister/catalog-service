package com.polarbookshop.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;
 
// INTEGRATION TESTS /////////

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {
 
	/* Spring @Autowired annotation is used for automatic dependency injection. 
	 * Spring framework is built on dependency injection and we inject the class dependencies 
	 * through spring bean configuration file
	 * */
	/*Dependency injection (DI) is a process whereby objects define their dependencies, 
	 * that is, the other objects they work with, only through constructor arguments, 
	 * arguments to a factory method, or properties that are set on the object instance after 
	 * it is constructed or returned from a factory method.
	 */
  @Autowired
  private WebTestClient webTestClient; //Utility to perform REST calls for testing
 
  @Test
  void whenPostRequestThenBookCreated() {
    var expectedBook = new Book("1231231231", "Title", "Author", 9.90);
 
    webTestClient // sending post request to endpoint books with body of a book to add
      .post()
      .uri("/books")
      .bodyValue(expectedBook)
      .exchange()
      .expectStatus().isCreated()
      .expectBody(Book.class).value(actualBook -> {
        assertThat(actualBook).isNotNull();
        assertThat(actualBook.isbn())
          .isEqualTo(expectedBook.isbn());
      });
  }
}
