package com.example.spring.boot.demo.spring.demo;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.boot.demo.spring.demo.models.Bond;
import com.example.spring.boot.demo.spring.demo.persistence.BondRepository;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BondControllerTest {
    
    private static final String BASE_PATH = "/bonds";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private BondRepository bondRepository;
    
    private Bond getNewBond() {
	Bond bond = new Bond("LVMH 19/23", "FR0013405347", 560d, 64.16d);
	bond.setId(1L);
	return bond;
    }

    @Test
    public void getBondByIdTest() throws JSONException {
	when(bondRepository.findById(1L)).thenReturn(Optional.of(getNewBond()));
	
	ResponseEntity<String> response = restTemplate.withBasicAuth("jimmy", "pass").getForEntity(BASE_PATH+"/1", String.class);
	assertTrue(response.getBody() != null);
	assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
	JSONAssert.assertEquals("{id:1}", response.getBody(), false);
	JSONAssert.assertEquals("{isin:FR0013405347}", response.getBody(), false);
    }
    
    @Test
    public void newBondTest() throws JSONException {
	Bond bond = getNewBond();
	when(bondRepository.save(bond)).thenReturn(bond);
	
	ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "pass").postForEntity(BASE_PATH, getNewBond(), String.class);
	assertTrue(response.getBody() != null);
	assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
	JSONAssert.assertEquals("{id:1}", response.getBody(), false);
	JSONAssert.assertEquals("{isin:FR0013405347}", response.getBody(), false);
    }
}
