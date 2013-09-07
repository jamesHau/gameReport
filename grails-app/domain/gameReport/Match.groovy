package gameReport

import java.util.Date;

class Match {

	Location location
	Team homeTeam
	Team awayTeam
	Date date
	int homeScore
	int halfTimeHomeScore
	int awayScore
	int halfTimeAwayScore
	Person captain
	Person trainer
	Person assistant
	
	static hasMany = [matchRecords:MatchRecord]
	
	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		homeScore min: 0
		awayScore min: 0
		halfTimeHomeScore min: 0, validator: { value, obj ->
			obj.homeScore >= value?: false 
		}
		halfTimeAwayScore min: 0, validator: { value, obj ->
			obj.awayScore >= value?: false 
		}
		matchRecords minSize: 0   
    }
}
