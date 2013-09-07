package gameReport

import java.util.Date;

class Team {
	
	Boolean enemyTeam
	String name
	Boolean active = true
	Person trainer
	Person captain
	
	static hasMany = [activePlayers: Person, personRecords: PersonHistory]

	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		name blank: false, unique: true
    }
}
