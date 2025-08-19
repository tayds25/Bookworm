package com.gabriel.librms.transform;
import com.gabriel.librms.entity.LibraryTrackerData;
import com.gabriel.librms.model.LibraryTracker;
public interface TransformLibraryTrackerService {
	LibraryTrackerData transform(LibraryTracker libraryTracker);
	LibraryTracker transform(LibraryTrackerData libraryTrackerData);
}
