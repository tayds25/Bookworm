package com.gabriel.librms.transform;
import com.gabriel.librms.entity.LibraryTrackerData;
import com.gabriel.librms.model.LibraryTracker;
import org.springframework.stereotype.Service;
@Service
public class TransformLibraryTrackerServiceImpl implements TransformLibraryTrackerService {
	@Override
	public LibraryTrackerData transform(LibraryTracker libraryTracker){
		LibraryTrackerData libraryTrackerData = new LibraryTrackerData();
		libraryTrackerData.setId(libraryTracker.getId());
		libraryTrackerData.setTitle(libraryTracker.getTitle());
		libraryTrackerData.setAuthor(libraryTracker.getAuthor());
		libraryTrackerData.setISBN(libraryTracker.getISBN());
		libraryTrackerData.setBorrowerName(libraryTracker.getBorrowerName());
		libraryTrackerData.setBorrowerEmail(libraryTracker.getBorrowerEmail());
		libraryTrackerData.setBorrowerPhoneNumber(libraryTracker.getBorrowerPhoneNumber());
		libraryTrackerData.setBorrowDate(libraryTracker.getBorrowDate());
		libraryTrackerData.setReturnDate(libraryTracker.getReturnDate());
		return libraryTrackerData;
	}
	@Override

	public LibraryTracker transform(LibraryTrackerData libraryTrackerData){;
		LibraryTracker libraryTracker = new LibraryTracker();
		libraryTracker.setId(libraryTrackerData.getId());
		libraryTracker.setTitle(libraryTrackerData.getTitle());
		libraryTracker.setAuthor(libraryTrackerData.getAuthor());
		libraryTracker.setISBN(libraryTrackerData.getISBN());
		libraryTracker.setBorrowerName(libraryTrackerData.getBorrowerName());
		libraryTracker.setBorrowerEmail(libraryTrackerData.getBorrowerEmail());
		libraryTracker.setBorrowerPhoneNumber(libraryTrackerData.getBorrowerPhoneNumber());
		libraryTracker.setBorrowDate(libraryTrackerData.getBorrowDate());
		libraryTracker.setReturnDate(libraryTrackerData.getReturnDate());
		libraryTracker.setCreated(libraryTrackerData.getCreated());
		libraryTracker.setLastUpdated(libraryTrackerData.getLastUpdated());
		return libraryTracker;
	}
}
