package fr.imie.travelagencyproject.dao;

import java.util.List;

import fr.imie.travelagencyproject.models.Trip;

public abstract interface TripDao {
	public long createTrip(Trip trip);
	public Trip findTripById(Trip trip);
	public void removeTrip(Trip trip);
	public List<Trip> getAllTrips();
}
