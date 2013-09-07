package gameReport

enum RoleType {
	Trainer('Trainer'),
	Captain('Captain'),
	Assistant('Assistant'),
	MaterialKeeper('Material Keeper'),
	Volunteer('Volunteer'),
	Player('Player'),
	GoalKeeper('GoalKeeper')
	
	String type
	
	RoleType(String type)
	{
		this.type = type
	}
}
