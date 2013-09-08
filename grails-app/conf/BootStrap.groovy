import gameReport.*

class BootStrap {

    def init = { servletContext ->
		environments
		{
			development
			{
				log.info("Inserting sample data")
				Season s11 = new Season(2011).save()
				Season s12 = new Season(2012).save()
				Season s13 = new Season(2013).save()
				
				League ldiv2s11 = new League(name:"Division 2", season:s11).save()
				League ldiv3s11= new League(name:"Division 3", season:s11).save()
				League ldiv4s11 = new League(name:"Division 4", season:s11).save()
				
				League ldiv1s12 = new League(name:"Division 1", season:s12).save()
				League ldiv2s12= new League(name:"Division 2", season:s12).save()
				League ldiv3s12 = new League(name:"Division 3", season:s12).save()
				
				League ldiv3s13 = new League(name:"Division 3", season:s13).save()
				League ldiv4s13= new League(name:"Division 4", season:s13).save()
				League ldiv5s13 = new League(name:"Division 5", season:s13).save()
				
				Date d = new Date(1312171200000)
				Tournament t1s11 = new Tournament(name:"Division 2", season:s11, start: d , end: d + 5).save()
				Tournament t2s11= new Tournament(name:"Division 3", season:s11, start: d + 180 , end: d + 183).save()
				Tournament t3s11 = new Tournament(name:"Division 4", season:s11, start: d + 300 , end: d + 320).save()
				
				d += 365
				Tournament t1s12 = new Tournament(name:"Division 1", season:s12, start: d + 15, end: d + 20).save()
				Tournament t2s12= new Tournament(name:"Division 2", season:s12, start: d + 150 , end: d + 155).save()
				Tournament t3s12 = new Tournament(name:"Division 3", season:s12, start: d + 265, end: d + 270).save()
				
				d += 365
				Tournament t1s13 = new Tournament(name:"Division 3", season:s13, start: d + 30 , end: d + 35).save()
				Tournament t2s13= new Tournament(name:"Division 4", season:s13, start: d + 123 , end: d + 128).save()
				Tournament t3s13 = new Tournament(name:"Division 5", season:s13, start: d + 211 , end: 216).save()
				
				Team et1 = new Team(enemyTeam:true, name: "Skuru").save()
				Team et2 = new Team(enemyTeam:true, name: "Handen").save()
				Team et3 = new Team(enemyTeam:true, name: "Haninge").save()
				Team et4 = new Team(enemyTeam:true, name: "Spånga").save()
				Team et5 = new Team(enemyTeam:true, name: "Östermalm").save()
			}
		}
    }
    def destroy = {
    }
}
