package fr.imie.travelagencyproject.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.imie.travelagencyproject.dao.PlaceDao;
import fr.imie.travelagencyproject.models.Place;



public class JdbcPlaceDao implements PlaceDao{

	@Override
	public  long createPlace(Place place) {
		try{
			PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement
					("INSERT INTO place(name) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, place.getName());
			stmt.executeUpdate();
			ResultSet resultat = stmt.getGeneratedKeys();
			resultat.next();
			Long id = resultat.getLong(1);
			ConnectionManager.getConnection().commit();
			return id;
		}catch(SQLException e){
			throw new RuntimeException("Erreur lors de l'ajout du lieu\n", e);
		}
	}

	@Override
	public Place findPlaceById(Place place) {
		Connection connection = ConnectionManager.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet resultat = stmt.executeQuery("SELECT * FROM place WHERE id = '"
					+ place.getId() +"'");
			if(resultat.next()){
				String name = resultat.getString(2);
				Place placeFindById = new Place(place.getId(),name);
				return placeFindById;
			}else{
				System.out.println("No place have been find!");
				return null;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("COUCOU je suis une erreur!\n",e);
		}
		
		
	}

	@Override
	public void updatePlace(Place place) {
		Connection connection = ConnectionManager.getConnection();
		try {
			
			PreparedStatement stmt = connection.prepareStatement("UPDATE place SET name = '" + place.getName() + "' WHERE"
					+ " id = " + place.getId());
			stmt.executeUpdate();
			connection.commit();
			System.out.println("The changement is a sucess!");
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la modification\n",e);
		}
		
	}

	@Override
	public void removePlace(Place place) {
		Connection connection = ConnectionManager.getConnection();
		try {
			
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM place WHERE id = " + place.getId());
			stmt.executeUpdate();
			connection.commit();
			System.out.println("The removal is a sucess!");
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la suppression!\n",e);
		}
		
		
	}
	public List<Place> getAllPlaces(){
		Connection connection = ConnectionManager.getConnection();
		try{
			Statement stmt = connection.createStatement();
			ResultSet resultat = stmt.executeQuery("SELECT * FROM place");
			ArrayList<Place> allPlaces = new ArrayList<>();
			while(resultat.next()){
				int resultatId = resultat.getInt(1);
				String resultatName = resultat.getString(2);
				Place place = new Place(resultatId,resultatName);
				allPlaces.add(place);
			}
			return allPlaces;
		}catch(SQLException e){
			throw new RuntimeException("Erreur lors de la recherche des lieux!\n",e);
		}
		
		
		
	}


}
