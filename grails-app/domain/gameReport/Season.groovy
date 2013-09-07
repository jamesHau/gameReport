package gameReport

import java.util.Date;

class Season {

	int year
	static hasMany = [leagues: League, tournaments: Tournament]
	
	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		year min: 1900, max:2100, nullable: false
    }
}
