package gameReport

import java.util.Date;

class Location {
	
	String name
	String address
	String postalCode
	String city
	String country

	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		name blank:false, unique:false
		address blank:false, unique:false
		postalCode blank:true
		city blank:false
		country blank:false
    }
	
	@Override
	public String toString() {
		return name
	}
}
