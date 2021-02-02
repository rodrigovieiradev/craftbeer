package com.beerhouse;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.beerhouse.controller.BeerController;
import com.beerhouse.model.Beer;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BeerIntegrationTests {
	
	
	@Autowired
    private MockMvc mvc;
	
	@Mock
    private BeerController statusController;
	
	@LocalServerPort
	private int port;
    
	private final static  String URI ="/api/v1/beers/";
    
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(statusController).setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver()).build();
	}

    @Test
    @Order(1)
    public void shouldReturnBeerCreatedWithSuccess() throws IOException, Exception {

    	Beer beer = new Beer();
		beer.setIngredients("Malte");
		beer.setAlcoholContent("6,5%");
		beer.setName("Cerveja 1906 Reserva Especial");
		beer.setCategory("Helles Bock");
		beer.setPrice(BigDecimal.valueOf(20.00));
 	    
        mvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON).
        		content(toJson(beer))).andExpect(status().is2xxSuccessful());
    }
    
    @Test
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void shouldReturnBeerCreatedWithError() throws IOException, Exception {

    	Beer beer = new Beer();
		beer.setAlcoholContent("6,5%");
		beer.setCategory("Helles Bock");
 	    
		assertThatThrownBy(() -> mvc.perform(get(URI)).andExpect(status().isBadRequest()));
     }
    
    @Test
    public void shouldReturnBeerPutWithSucess() throws IOException, Exception {

    	Beer beer = new Beer();
		beer.setIngredients("Água, malte, milho e lúpulo");
		beer.setAlcoholContent("5,5%");
		beer.setName("Brahma");
		beer.setCategory("Pilsen");
		beer.setPrice(BigDecimal.valueOf(20.00));
 	    
        mvc.perform(put(URI+"{beerId}", 1l).contentType(MediaType.APPLICATION_JSON).pathInfo("/1").
        		content(toJson(beer))).andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void shouldReturnBeerPutWithError() throws IOException, Exception  {

    	Beer beer = new Beer();
    	beer.setId(1l);
		beer.setIngredients("Água, malte, milho e lúpulo");
		beer.setAlcoholContent("5,5%");
 	    
		assertThatThrownBy(() -> mvc.perform(get(URI + "{beerId}", 1l)).andExpect(status().isBadRequest()));
    }
    
    @Test
    public void shouldReturnBeerGet() throws IOException, Exception {

        mvc.perform(get(URI)).andExpect(status().isOk()).andReturn();
    }
    
    @Test
    public void shouldReturnBeerPatchWithError() throws IOException, Exception {

    	Beer beer = new Beer();
		beer.setIngredients("Água, malte, milho e lúpulo");
		beer.setAlcoholContent("5,5%");
 	    
		assertThatThrownBy(() -> mvc.perform(get(URI + "{beerId}", 1l)).andExpect(status().isBadRequest()));
    }
    
    @Test
    public void shouldReturnBeerPatchWithSucess() throws IOException, Exception {

    	Beer beer = new Beer();
    	beer.setName("Brahma");
    	beer.setCategory("Lager");
    	beer.setPrice(BigDecimal.valueOf(20.00));
    	
        mvc.perform(patch(URI + "{beerId}", 1l).contentType(MediaType.APPLICATION_JSON).
        		content(toJson(beer))).andExpect(status().isOk());
    }
    
    @Test
    public void shouldReturnBeerDeteleWithSucess() throws IOException, Exception {
        mvc.perform(delete(URI + "{beerId}", 1l)).andExpect(status().isNoContent());
    }
    
    @Test
    public void shouldReturnBeerDeteleWithError() throws IOException, Exception {
    	assertThatThrownBy(() -> mvc.perform(delete(URI + "{beerId}", 90l)).andExpect(status().isNotFound()));
    }
    
    static String toJson(Object object) throws IOException {
        Gson toJson = new Gson();
        return toJson.toJson(object);
    }

}