package com.gabriel.librms.service;
import com.gabriel.librms.model.LibraryTracker;
public interface LibraryTrackerService {
	LibraryTracker[] getAll() throws Exception;
	LibraryTracker get(Integer id) throws Exception;
	LibraryTracker create(LibraryTracker libraryTracker) throws Exception;
	LibraryTracker update(LibraryTracker libraryTracker) throws Exception;
	void delete(Integer id) throws Exception;
}
