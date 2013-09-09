package gameReport

import java.util.Date;

class Location {
	
	String name
	String address
	String postalCode
	String city
	String country
	String googleMapsLink

	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		name unique:false
		address unique:false
		postalCode blank:true
		googleMapsLink nullable:true, url:true
    }
	
	@Override
	public String toString() {
		return name
	}
}
