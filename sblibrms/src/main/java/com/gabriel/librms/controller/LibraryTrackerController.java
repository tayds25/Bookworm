package com.gabriel.librms.controller;
import com.gabriel.librms.model.LibraryTracker;
import com.gabriel.librms.service.LibraryTrackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class LibraryTrackerController {
	Logger logger = LoggerFactory.getLogger( LibraryTrackerController.class);
	@Autowired
	private LibraryTrackerService libraryTrackerService;
	@GetMapping("/api/libraryTracker")
	public ResponseEntity<?> listLibraryTracker()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			LibraryTracker[] libraryTracker = libraryTrackerService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(libraryTracker);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/libraryTracker")
	public ResponseEntity<?> add(@RequestBody LibraryTracker libraryTracker){
		logger.info("Input >> " + libraryTracker.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			LibraryTracker newLibraryTracker = libraryTrackerService.create(libraryTracker);
			logger.info("created libraryTracker >> " + newLibraryTracker.toString() );
			response = ResponseEntity.ok(newLibraryTracker);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve libraryTracker with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/libraryTracker")
	public ResponseEntity<?> update(@RequestBody LibraryTracker libraryTracker){
		logger.info("Update Input >> libraryTracker.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			LibraryTracker newLibraryTracker = libraryTrackerService.update(libraryTracker);
			response = ResponseEntity.ok(libraryTracker);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve libraryTracker with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/libraryTracker/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input libraryTracker id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			LibraryTracker libraryTracker = libraryTrackerService.get(id);
			response = ResponseEntity.ok(libraryTracker);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/libraryTracker/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			libraryTrackerService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@PutMapping("api/libraryTracker/{id}")
	public ResponseEntity<?> update(@PathVariable final Integer id, @RequestBody LibraryTracker libraryTrackerDetails) {
		logger.info("Update request for libraryTracker id >> " + id);
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			LibraryTracker existingLibraryTracker = libraryTrackerService.get(id);

			LibraryTracker updatedLibraryTracker = libraryTrackerService.update(existingLibraryTracker);
			response = ResponseEntity.ok().headers(headers).body(updatedLibraryTracker);
		} catch (Exception ex) {
			logger.error("Failed to update libraryTracker with id: {}", id, ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
