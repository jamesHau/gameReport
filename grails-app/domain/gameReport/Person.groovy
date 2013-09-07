package gameReport

import java.util.Date;

class Person {
	
	String firstName
	String middleName
	String lastName
	String personNumber
	
	static hasMany = [matchRecords: MatchRecord, historyRecords: PersonHistory]
	
	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		firstName blank:false 
		middleName blank:true 
		lastName blank:false 
		personNumber blank:false, unique: true
    }
}
