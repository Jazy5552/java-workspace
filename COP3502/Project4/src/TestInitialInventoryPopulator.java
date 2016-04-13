import static org.junit.Assert.*;

import org.junit.Test;

public class TestInitialInventoryPopulator {

	@Test
	public void test() {
		RecordStack rs = new RecordStack();
		InitialInventoryPopulator.getInitialInventory(
				"Lucy_Initial_Inventory.csv",
				rs,rs);
		assertEquals("Artist4",rs.pop().getArtist());
		assertEquals("Title 3", rs.pop().getTitle());
		assertEquals(2,rs.size());
		rs.pop(); 
		assertEquals(2001,rs.pop().getYear());
		assertEquals(true,rs.isEmpty());
	}
	
	@Test
	public void testToString() {
		RecordStack rs = new RecordStack();
		InitialInventoryPopulator.getInitialInventory(
				"Lucy_Initial_Inventory.csv",
				rs,rs);
		assertEquals("LP: Title 4 by Artist4, 2004",
				rs.pop().toString());
		assertEquals("45: Title 3 by Artist3, 2003",
				rs.pop().toString());
	}
}
