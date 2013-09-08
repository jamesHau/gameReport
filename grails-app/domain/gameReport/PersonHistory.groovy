package gameReport

import java.util.Date;

class PersonHistory {

	static belongsTo = [person: Person]
	
	RoleType roleType
	Date start
	Date end
	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		start shared: "start"
		end shared: "end"
    }
}
