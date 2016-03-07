package fr.imie.travelagencyproject.launcher;

import java.util.List;
import java.util.Scanner;

import fr.imie.travelagencyproject.jdbc.ConnectionManager;
import fr.imie.travelagencyproject.jdbc.JdbcPlaceDao;
import fr.imie.travelagencyproject.jdbc.JdbcTripDao;
import fr.imie.travelagencyproject.models.Place;
import fr.imie.travelagencyproject.models.Trip;

public class Launcher {
	
	public static void main(String[] args) {
		
		ConnectionManager.getConnection();
		JdbcPlaceDao newPlace = new JdbcPlaceDao();
		JdbcTripDao newTrip = new JdbcTripDao();
		Scanner scanner = new Scanner(System.in);
		
		boolean next = true;
		
		while(next){
			System.out.println("What do you want to do?\n1-Add a place\n2-Find a place\n"
					+ "3-Edit a place\n4-remove a place\n5-Add a trip\n6-Find a trip\n7-"
					+ "Remove a trip\n8-Liste des lieux\n9-Liste des voyages\n10-QUIT");
			String ch = scanner.nextLine();
			int choix = Integer.parseInt(ch);
			
			switch(choix){
				case 1:
					System.out.println("Quel est le nom de la ville?");
					String placeName = scanner.nextLine();
					Place place = new Place(72,placeName);
					newPlace.createPlace(place);
					break;
					
				case 2:
					System.out.println("What is the id of place??");
					String id = scanner.nextLine();
					int id_place = Integer.parseInt(id);
					Place placeFindById = new Place(id_place,"find");
					Place placeFindName = newPlace.findPlaceById(placeFindById);
					System.out.println("Le résultat est: " + placeFindName.getName());
					break;
					
				case 3:
					System.out.println("Quel est l'ID du lieu a modifier?\n");
					String idUpdate = scanner.nextLine();
					int id_placeUpdate = Integer.parseInt(idUpdate);
					System.out.println("Quel est le nouveau nom du lieu?\n");
					String nameUpdate = scanner.nextLine();
					Place placeUpdate = new Place(id_placeUpdate,nameUpdate);
					newPlace.updatePlace(placeUpdate);
					break;
				case 4:
					System.out.println("Quel est l'id du lieu à supprimer?\n");
					String idRemove = scanner.nextLine();
					int id_placeRemove = Integer.parseInt(idRemove);
					Place placeRemove = new Place(id_placeRemove,"remove");
					newPlace.removePlace(placeRemove);
					break;
					
				case 5:
					
					System.out.println("Departure:Please enter the id of the place:");
					String idCreateTripDeparture = scanner.nextLine();
					int id_departureCreateTrip = Integer.parseInt(idCreateTripDeparture);
					
					Place departureFind= new Place(id_departureCreateTrip,"find");
					departureFind = newPlace.findPlaceById(departureFind);
					String departureName = departureFind.getName();	
					System.out.println("Departure: " + departureName);
					
					
					System.out.println("Destination:Please enter the id of the place:");
					String idCreateTripDestination = scanner.nextLine();
					int id_destinationCreateTrip = Integer.parseInt(idCreateTripDestination);
					
					Place destinationFind= new Place(id_destinationCreateTrip,"find");
					destinationFind = newPlace.findPlaceById(destinationFind);
					String destinationName = destinationFind.getName();
					System.out.println("Destination: " + destinationName);
					
					
					System.out.println("What is the price of the trip?");
					String idCreateTripPrice = scanner.nextLine();
					int value_priceCreateTrip = Integer.parseInt(idCreateTripPrice);
					
					
					Place departure = new Place(id_departureCreateTrip,departureName);
					Place destination = new Place(id_destinationCreateTrip,destinationName);
					Trip tripCreate = new Trip(69,departure,destination,value_priceCreateTrip);
					long idTrip = newTrip.createTrip(tripCreate);
					if(idTrip != 0){
						System.out.println("L'insertion a marché!");
					}else{
						System.out.println("L'insertion a échoué");
					}
					break;
					
				case 6:
					System.out.println("What is the ID of the trip?");
					String idFindTripById = scanner.nextLine();
					int id_tripFind = Integer.parseInt(idFindTripById);
					Place fakePlaceDeparture = new Place(1,"find");
					Place fakePlaceDestination = new Place(5,"find");
					Trip tripFindById = new Trip(id_tripFind,fakePlaceDeparture,fakePlaceDestination,900);
					Trip findTrip = newTrip.findTripById(tripFindById);
					System.out.println("La départ est à " + findTrip.getDeparture().getName());
					System.out.println("La destination est à " + findTrip.getDestination().getName());
					System.out.println("Le prix est de " + findTrip.getPrice());
					break;
					
				case 7:
					System.out.println("Quel est l'id du voyage à supprimer?\n");
					String idTripRemove = scanner.nextLine();
					int id_tripRemove = Integer.parseInt(idTripRemove);
					Place departureRemove = new Place(69,"remove");
					Place destinationRemove = new Place(666,"remove");
					Trip tripRemove = new Trip(id_tripRemove,departureRemove,destinationRemove,999);
					newTrip.removeTrip(tripRemove);
					break;
					
				case 8:
					List<Place> listPlace = newPlace.getAllPlaces();
					System.out.println(listPlace.toString());
					break;
					
				case 9:
					List<Trip> listTrip = newTrip.getAllTrips();
					System.out.println(listTrip.toString());
					break;
					
				case 10:
					next = false;
					break;
				default:
					System.out.println("Erreur lors de la selection!\n");
					break;
			}
		}
		if(!next){
			scanner.close();
			System.out.println("En revoir!");
		}
	}
	
	
}





