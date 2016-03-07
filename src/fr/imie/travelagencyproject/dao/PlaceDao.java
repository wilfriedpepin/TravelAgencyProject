package fr.imie.travelagencyproject.dao;

import java.util.List;

import fr.imie.travelagencyproject.models.Place;

public interface PlaceDao {
	 long createPlace(Place place);
	 Place findPlaceById(Place place);
	 void updatePlace(Place place);
	 void removePlace(Place place);
	 List<Place> getAllPlaces(); 
	
}
