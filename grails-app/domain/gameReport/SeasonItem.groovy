package gameReport

import groovy.beans.Bindable;

import java.util.Date;

class SeasonItem {
	String name
	Season season
	
	static belongsTo = [season: Season]
	static hasMany = [matches: Match]
	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
		
    static constraints = {
		name blank: false
    }
	
	@Override
	public String toString() {
		return name
	}
}
