package com.gabriel.librms.service;
import com.gabriel.librms.model.LibraryTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LibraryTrackerService {
	Logger logger = LoggerFactory.getLogger(LibraryTrackerService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/libraryTracker";

	protected static LibraryTrackerService service= null;
	public static LibraryTrackerService getService(){
		if(service == null){
			service = new LibraryTrackerService();
		}
		return service;
	}

	RestTemplate restTemplate = null;
	public RestTemplate getRestTemplate() {
		if(restTemplate == null) {
		restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
			messageConverters.add(converter);
			restTemplate.setMessageConverters(messageConverters);
		}
		return restTemplate;
	}

	public LibraryTracker get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<LibraryTracker> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, LibraryTracker.class);
		return response.getBody();
	}

	public LibraryTracker[] getAll() {
		String url = endpointUrl;
		logger.info("getLibraryTrackers: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<LibraryTracker[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, LibraryTracker[].class);
		LibraryTracker[] libraryTrackers = response.getBody();
		return libraryTrackers;
	}

	public LibraryTracker create(LibraryTracker libraryTracker) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<LibraryTracker> request = new HttpEntity<>(libraryTracker, headers);
		final ResponseEntity<LibraryTracker> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, LibraryTracker.class);
		return response.getBody();
	}
	public LibraryTracker update(LibraryTracker libraryTracker) {
		logger.info("update: " + libraryTracker.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<LibraryTracker> request = new HttpEntity<>(libraryTracker, headers);
		final ResponseEntity<LibraryTracker> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, LibraryTracker.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<LibraryTracker> request = new HttpEntity<>(null, headers);
		final ResponseEntity<LibraryTracker> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, LibraryTracker.class);
	}
}
