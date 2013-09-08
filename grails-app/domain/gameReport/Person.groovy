package gameReport

import java.util.Date;

class Person {
	
	String firstName
	String middleName
	String lastName
	String personNumber
	String email
	
	static hasMany = [matchRecords: MatchRecord, historyRecords: PersonHistory]
	
	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
    static constraints = {
		middleName blank:true 
		personNumber unique: true
		email email:true
    }
	
	@Override
	public String toString() {
		return firstName + middleName?:"" + lastName
	}
}
