package br.com.dogdositio;

import br.com.dogdositio.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void validProductById() {
		Long id = 1L;
		ResponseEntity<Product> response = restTemplate.exchange("/product/" + id, HttpMethod.GET, null, Product.class);
		assert response.getStatusCode().equals(HttpStatus.OK);
		assert response.getBody().getId().equals(id);
	}

	@Test
	public void productListIsNotEmpty() {
		ResponseEntity<List<Product>> response = restTemplate.exchange("/product", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
		assert response.getStatusCode().equals(HttpStatus.OK);
		assert response.getBody().isEmpty() == false;
	}

}
