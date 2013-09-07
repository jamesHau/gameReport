package gameReport

class MatchRecord
{
	Person person
	int playGoals
	int penaltyGoals
	int warnings
	int suspensions
	Boolean recievedRedCard
	Boolean goalKeeper
	Date start
	Date end
	
	static belongsTo = [match:Match]
	
	// Automatically updated by GORM
	Date dateCreated
	
	// Automatically updated by GORM
	Date lastUpdated

	static constraints =
	{
		playGoals min: 0
		penaltyGoals min: 0, validator: { value, obj ->
			obj.playGoals >= penaltyGoals?: false 
		}
		warnings min: 0, max: 2
		suspensions min: 0, max: 2
	}
}
