package gameReport


class Tournament extends SeasonItem
{
	Date start
	Date end
	
	static constraints = {
		start shared: "start"
		end shared: "end"
	}
	
}
