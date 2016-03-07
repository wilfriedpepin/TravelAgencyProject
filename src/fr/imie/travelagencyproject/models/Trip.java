package fr.imie.travelagencyproject.models;

public class Trip {
	private int id;
	private Place departure;
	private Place destination;
	private int price;
	
	public Trip(int id, Place departure, Place destination, int price) {
		super();
		this.id = id;
		this.departure = departure;
		this.destination = destination;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Place getDeparture() {
		return departure;
	}

	public void setDeparture(Place departure) {
		this.departure = departure;
	}

	public Place getDestination() {
		return destination;
	}

	public void setDestination(Place destination) {
		this.destination = destination;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", departure=" + departure + ", destination=" + destination + ", price=" + price
				+ "]";
	}
	
	
	
	
	
}
