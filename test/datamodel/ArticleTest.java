package datamodel;

import org.junit.Test;

import org.junit.FixMethodOrder;

import static org.junit.Assert.*;


/**
 * 
 * JUnit4 test code for Article class.
 * 
 * Use of assertions, see:
 *   https://junit.org/junit4/javadoc/latest/org/junit/Assert.html
 * 
 * @author sgra64
 */
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ArticleTest {

	/*
	 * Test fixtures - objects needed to perform the tests
	 */
	private final String aToaster_id = "SKU-868682";
	private final String aToaster_description = "Toaster";
	private final long aToaster_unitPrice = 2499;
	private final int aToaster_unitsInStore = 1200;

	private final Article aToaster = new Article( aToaster_id, aToaster_description,
			aToaster_unitPrice, aToaster_unitsInStore );

	/*
	 * Test constructor, regular case.
	 */
	@Test
	public void test001_RegularConstructor() {
		Article a = new Article( aToaster_id, aToaster_description, aToaster_unitPrice, aToaster_unitsInStore );
		assertEquals( a.getId(), aToaster_id );	// assert that correct id is returned
		assertSame( a.getId(), aToaster_id );		// "==" - equivalent
		assertEquals(a.getDescription(), aToaster_description); //description is returned as provided in the constructor
		assertEquals(a.getUnitPrice(), aToaster_unitPrice); //unit price is returned as provided in the constructor
		assertEquals(aToaster_unitsInStore, aToaster_unitsInStore); //units-in-store are returned as provided in the constructor

	}

	/*
	 * Test constructor, special case with empty String and 0 - arguments.
	 */
	@Test
	public void test002_EmptyArgumentConstructor() {
		Article a = new Article("", "",0,0);
		assertSame(a.getId(),""); //id "" is returned
		assertEquals(a.getDescription(),""); //description "" is returned
		assertEquals(a.getUnitPrice(), 0); //unit price 0 is returned
		assertEquals(a.getUnitsInStore(), 0); //units-in-store 0 is returned
	}

	/*
	 * Test constructor, Test special case with null and < 0 - arguments.
	 */
	@Test
	public void test003_NullArgumentConstructor() {
		Article a = new Article(null, null,-1,-1);
		assertNull(a.getId()); //id null is returned
		assertEquals(a.getDescription(),""); //description "" is returned (null for description is not allowed)
		assertEquals(a.getUnitPrice(), 0); //unit price 0 is returned (negative unit prices are not allowed)
		assertEquals(a.getUnitsInStore(), 0); //units-in-store 0 is returned (negative inventory is not allowed)
	}

	@Test
	public void test010_SetDescription() {
		 // test method: setDescription( String descr );
		final String description = "Super Toaster Model XRC-2484698";
		aToaster.setDescription(description);
		assertEquals(aToaster.getDescription(), description); //String description is returned by getDescription()	(regular case)
		aToaster.setDescription("");
		assertEquals(aToaster.getDescription(), ""); //"" is returned for setDescription( "" )				(corner case)
		aToaster.setDescription(null);
		assertEquals(aToaster.getDescription(), ""); //"" is returned for setDescription( null )				(irregular case)
	}

	@Test
	public void test011_SetUnitPrice() {
		 // test method: setUnitPrice( long price );
		final long price = 100L;
		aToaster.setUnitPrice(price);
		assertSame(aToaster.getUnitPrice(), price); //price = 100L is returned by getUnitPrice()			(regular case)
		aToaster.setUnitPrice(0);
		assertEquals(aToaster.getUnitPrice(), 0); //0 is returned for setUnitPrice( 0 )					(corner case)
		aToaster.setUnitPrice(Long.MAX_VALUE);
		assertEquals(aToaster.getUnitPrice(), 0); //0 is returned for setUnitPrice( Long.MAX_VALUE )		(corner case)
		aToaster.setUnitPrice(-1);
		assertEquals(aToaster.getUnitPrice(), 0); //0 is returned for setUnitPrice( -1 )					(irregular case)
		aToaster.setUnitPrice(Long.MIN_VALUE);
		assertEquals(aToaster.getUnitPrice(), 0); //0 is returned for setUnitPrice( Long.MIN_VALUE )		(irregular case)
		//aToaster.setUnitPrice( price );					// regular case
	}

	@Test
	public void test012_SetUnitsInStore() {
		 // test method: setUnitsInStore( int number );
		final int units = 100;
		aToaster.setUnitInStore(units);
		assertSame(aToaster.getUnitsInStore(), units); //units = 100L is returned by getUnitsInStore()				(regular case)
		aToaster.setUnitInStore(0);
		assertEquals(aToaster.getUnitsInStore(),0); //0 is returned for setUnitsInStore( 0 )					(corner case)
		aToaster.setUnitInStore(Integer.MAX_VALUE);
		assertEquals(aToaster.getUnitsInStore(),0); //0 is returned for setUnitsInStore( Integer.MAX_VALUE )	(corner case)
		aToaster.setUnitInStore(-1);
		assertEquals(aToaster.getUnitsInStore(),0); //0 is returned for setUnitsInStore( -1 )					(irregular case)
		aToaster.setUnitInStore(Integer.MIN_VALUE);
		assertEquals(aToaster.getUnitsInStore(),0); //0 is returned for setUnitsInStore( Integer.MIN_VALUE )	(irregular case)
		//aToaster.setUnitsInStore( units );				// regular case
	}

}
