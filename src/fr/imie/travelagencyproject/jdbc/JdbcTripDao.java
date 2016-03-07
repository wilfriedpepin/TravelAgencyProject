package fr.imie.travelagencyproject.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.imie.travelagencyproject.dao.TripDao;
import fr.imie.travelagencyproject.models.Place;
import fr.imie.travelagencyproject.models.Trip;

public class JdbcTripDao implements TripDao{

	@Override
	public long createTrip(Trip trip) {
		try{
			PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement
					("INSERT INTO trip(departureFK,destinationFK,price) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, trip.getDeparture().getId());
			stmt.setLong(2, trip.getDestination().getId());
			stmt.setInt(3, trip.getPrice());
			stmt.executeUpdate();
			ResultSet resultat = stmt.getGeneratedKeys();
			resultat.next();
			long id = resultat.getLong(1);
			ConnectionManager.getConnection().commit();
			return id;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public Trip findTripById(Trip trip) {
		Connection connection = ConnectionManager.getConnection();
		JdbcPlaceDao searchPlace = new JdbcPlaceDao();
		try {
			Statement stmt = connection.createStatement();
			ResultSet resultat = stmt.executeQuery("SELECT * FROM trip WHERE id = '"
					+ trip.getId() +"'");
			if(resultat.next()){
				
				Place departure = new Place(resultat.getInt(2),"find");
				departure = searchPlace.findPlaceById(departure);
				Place destination = new Place(resultat.getInt(3),"find");
				destination = searchPlace.findPlaceById(destination);
				
				Trip tripFindById = new Trip(resultat.getInt(1),departure,destination,resultat.getInt(4));
				return tripFindById;
			}else{
				System.out.println("No place have been find!");
				return null;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("COUCOU je suis une erreur!\n",e);
		}
	}

	@Override
	public void removeTrip(Trip trip) {
		Connection connection = ConnectionManager.getConnection();
		try {
			
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM trip WHERE id = " + trip.getId());
			stmt.executeUpdate();
			connection.commit();
			System.out.println("The removal is a sucess!");
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la suppression!\n",e);
		}
		
	}
	public List<Trip> getAllTrips(){
		Connection connection = ConnectionManager.getConnection();
		JdbcPlaceDao searchPlace = new JdbcPlaceDao();
		try{
			Statement stmt = connection.createStatement();
			ResultSet resultat = stmt.executeQuery("SELECT * FROM trip");
			ArrayList<Trip> allTrips = new ArrayList<>();
			while(resultat.next()){
				int resultatId = resultat.getInt(1);
				int resultatIdDeparture = resultat.getInt(2);
				int resultatIdDestination = resultat.getInt(3);
				int resultatPrice = resultat.getInt(4);
				
				Place placeDeparture = new Place(resultatIdDeparture,"find");
				placeDeparture = searchPlace.findPlaceById(placeDeparture);
				
				Place placeDestination = new Place(resultatIdDestination,"find");
				placeDestination = searchPlace.findPlaceById(placeDestination);
				
				Trip trip = new Trip(resultatId,placeDeparture,placeDestination,resultatPrice);
				allTrips.add(trip);
			}
			return allTrips;
		}catch(SQLException e){
			throw new RuntimeException("Erreur lors de la recherche des lieux!\n",e);
		}
	}

}
