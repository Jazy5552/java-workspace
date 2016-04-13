import static org.junit.Assert.*;

import org.junit.Test;

public class TestInitialInventoryPopulator {

	@Test
	public void test() {
		RecordStack rs = InitialInventoryPopulator.getInitialInventory(
				"/users/kristy/3502inclass/Lucy_Initial_Inventory.csv");
		assertEquals("Artist4",rs.pop().getArtist());
		assertEquals("Title 3", rs.pop().getTitle());
		assertEquals(2,rs.size());
		rs.pop(); 
		assertEquals(2001,rs.pop().getYear());
		assertEquals(true,rs.isEmpty());
	}
	
	@Test
	public void testToString() {
		RecordStack rs = InitialInventoryPopulator.getInitialInventory(
				"/users/kristy/3502inclass/Lucy_Initial_Inventory.csv");
		assertEquals("LP: Title 4 by Artist4, 2004",
				rs.pop().toString());
		assertEquals("45: Title 3 by Artist3, 2003",
				rs.pop().toString());
	}
}
