package gameReport

import java.util.Date;

class Team {
	
	Boolean enemyTeam = false
	String name
	Boolean active = true
	Person trainer = null
	Person captain = null
	
	static hasMany = [activePlayers: Person, personRecords: PersonHistory]

	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		name unique: true
		trainer nullable: true, validator: {val, obj ->
			obj.enemyTeam? val == null:val != null
		}
		captain nullable: true, validator: {val, obj ->
			obj.enemyTeam? val == null:val != null
		}
    }
	
	@Override
	public String toString() {
		return name
	}
}
