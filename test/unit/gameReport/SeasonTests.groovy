package gameReport



import gameReport.Season;
import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Season)
class SeasonTests {
	
    void testYear() {
       Season season = new Season(1500)
	   assertFalse season.validate()
	   season.year = 2000
	   assertTrue season.validate()
	   season.year = 2200
	   assertFalse season.validate()
    }
	
	
	void testName()
	{
		Season season = new Season(2000)
		assertTrue season.name == "00/01"
		
		String expected = "test.test"
		season = new Season([name: expected, year: 2000])
		assertTrue season.validate()
		assertTrue season.name == expected	
	} 
}
