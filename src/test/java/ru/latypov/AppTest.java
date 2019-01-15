package ru.latypov;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class AppTest {


    //@Before
    public void setUp() throws Exception {
       // RouterFunction<?> routes = Application.routes();
      //  this.testClient = WebTestClient.bindToRouterFunction(routes).build();
    }

    @Test
    public void testHello() throws Exception {
      //  this.testClient.get()
   //             .uri("/") //
     //           .exchange() //
       //         .expectStatus().isOk() //
         //       .expectBody(String.class).isEqualTo("Hello World!");
    }
}
