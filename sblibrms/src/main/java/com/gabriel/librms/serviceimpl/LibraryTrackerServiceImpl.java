package com.gabriel.librms.serviceimpl;
import com.gabriel.librms.entity.LibraryTrackerData;
import com.gabriel.librms.model.LibraryTracker;
import com.gabriel.librms.repository.LibraryTrackerDataRepository;
import com.gabriel.librms.service.LibraryTrackerService;
import com.gabriel.librms.transform.TransformLibraryTrackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class LibraryTrackerServiceImpl implements LibraryTrackerService {
	Logger logger = LoggerFactory.getLogger(LibraryTrackerServiceImpl.class);
	@Autowired
	LibraryTrackerDataRepository libraryTrackerDataRepository;
	@Autowired
	TransformLibraryTrackerService tansformLibraryTrackerService;
	@Override
	public LibraryTracker[] getAll() {
		List<LibraryTrackerData> libraryTrackersData = new ArrayList<>();
		List<LibraryTracker> libraryTrackers = new ArrayList<>();
		libraryTrackerDataRepository.findAll().forEach(libraryTrackersData::add);
		Iterator<LibraryTrackerData> it = libraryTrackersData.iterator();
		while(it.hasNext()) {
			LibraryTrackerData libraryTrackerData = it.next();
			LibraryTracker libraryTracker = tansformLibraryTrackerService.transform(libraryTrackerData);
			libraryTrackers.add(libraryTracker);
		}
		LibraryTracker[] array = new LibraryTracker[libraryTrackers.size()];
		for  (int i=0; i<libraryTrackers.size(); i++){
			array[i] = libraryTrackers.get(i);
		}
		return array;
	}
	@Override
	public LibraryTracker create(LibraryTracker libraryTracker) {
		logger.info(" add:Input " + libraryTracker.toString());
		LibraryTrackerData libraryTrackerData = tansformLibraryTrackerService.transform(libraryTracker);
		libraryTrackerData = libraryTrackerDataRepository.save(libraryTrackerData);
		logger.info(" add:Input " + libraryTrackerData.toString());
			LibraryTracker newLibraryTracker = tansformLibraryTrackerService.transform(libraryTrackerData);
		return newLibraryTracker;
	}

	@Override
	public LibraryTracker update(LibraryTracker libraryTracker) {
		LibraryTracker updatedLibraryTracker = null;
		int id = libraryTracker.getId();
		Optional<LibraryTrackerData> optional  = libraryTrackerDataRepository.findById(libraryTracker.getId());
		if(optional.isPresent()){
			LibraryTrackerData originalLibraryTrackerData = tansformLibraryTrackerService.transform(libraryTracker);
			originalLibraryTrackerData.setCreated(optional.get().getCreated());
			LibraryTrackerData libraryTrackerData = libraryTrackerDataRepository.save(originalLibraryTrackerData);
			updatedLibraryTracker = tansformLibraryTrackerService.transform(libraryTrackerData);
		}
		else {
			logger.error("LibraryTracker record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedLibraryTracker;
	}

	@Override
	public LibraryTracker get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		LibraryTracker libraryTracker = null;
		Optional<LibraryTrackerData> optional = libraryTrackerDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			libraryTracker = tansformLibraryTrackerService.transform(optional.get());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return libraryTracker;
	}
	@Override
	public void delete(Integer id) {
		LibraryTracker libraryTracker = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<LibraryTrackerData> optional = libraryTrackerDataRepository.findById(id);
		if( optional.isPresent()) {
			LibraryTrackerData libraryTrackerDatum = optional.get();
			libraryTrackerDataRepository.delete(optional.get());
			logger.info(" Successfully deleted LibraryTracker record with id: " + Integer.toString(id));
			libraryTracker = tansformLibraryTrackerService.transform(optional.get());
		}
		else {
			logger.error(" Unable to locate libraryTracker with id:" +  Integer.toString(id));
		}
	}
}
