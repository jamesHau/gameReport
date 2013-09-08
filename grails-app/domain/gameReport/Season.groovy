package gameReport

import java.util.Date;

class Season {

	String name
	int year
	static hasMany = [leagues: League, tournaments: Tournament]
	
	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated
	
	public Season(int year)
	{
		this.year = year
		this.name = year.toString()[2..3] + "/" + (year+1).toString()[2..3]
	}
	
	public Season(String name, int year)
	{
		this.name = name
		this.year = year
	}
	
    static constraints = {
		year min: 1900, max:2100, nullable: false
    }
	
	@Override
	public String toString() {
		return name
	}
}
