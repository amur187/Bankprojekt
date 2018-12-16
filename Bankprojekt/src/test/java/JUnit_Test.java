import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;


class JUnit_Test {

	Student echterStudi = new Student("Peter", "Lustig", "Am Löwenzahn 3", LocalDate.of(1963, 4, 13), "HTW", "Museumskunde",LocalDate.of(2018, 10, 1), LocalDate.of(2019, 03, 31));
	Student alterStudi =  new Student("Hans", "Glücklich", "Edisonstr. 4", LocalDate.of(1993, 10, 3), "HTW", "AI",LocalDate.of(2016, 5, 1), LocalDate.of(2017, 02, 28));
	Student heuteImmatrikuliert =  new Student("Jens", "Blub", "Zuhause. 2", LocalDate.of(1943, 10, 3), "TH Wildau", "ET",LocalDate.now(), LocalDate.of(2017, 02, 28));
	Student heuteExmatrikuliert =  new Student("Sad", "Bachelor", "Engineerstr. 2", LocalDate.of(1943, 10, 3), "TH Wildau", "ET",LocalDate.of(2017,10,01), LocalDate.now());

	
	
	
	@Test
	public void testImmatrikulationsstatus1() {
		System.out.println("### Test 1 ###");
		System.out.println("Echter Studi: " + echterStudi.immatrikulationAbfragen());
		assertEquals(true, echterStudi.immatrikulationAbfragen());
	}
	
	@Test
	public void testImmatrikulationsstatus2() {

		System.out.println("### Test 2 ###");
		System.out.println("Abgelaufener Studi: " + alterStudi.immatrikulationAbfragen());
		assertEquals(false, alterStudi.immatrikulationAbfragen());
	}
	@Test
	public void testImmatrikulationsstatus3() {

		System.out.println("### Test 3 ###");
		System.out.println("Heute Immatrikuliert: " + heuteImmatrikuliert.immatrikulationAbfragen());
		assertEquals(true, heuteImmatrikuliert.immatrikulationAbfragen());
	}
	@Test
	public void testImmatrikulationsstatus4() {

		System.out.println("### Test 4 ###");
		System.out.println("Heute Exmatrikuliert: " + heuteExmatrikuliert.immatrikulationAbfragen());
		assertEquals(true, heuteImmatrikuliert.immatrikulationAbfragen());
	}
	
	@Test
	public void testNeuImmatrikulieren() {
		System.out.println("### Test 5 ###");
		System.out.println("Abgelaufener Studi: " + alterStudi.immatrikulationAbfragen());
		assertEquals(false, alterStudi.immatrikulationAbfragen());
		System.out.println("Studentenstatus erneuern: ");
		alterStudi.immatrikulationEintragen(LocalDate.now(), LocalDate.now().plusMonths(6));
		System.out.println("Abgelaufener Studi: " + alterStudi.immatrikulationAbfragen());
		assertEquals(true, alterStudi.immatrikulationAbfragen());
	}
	
	
	@Test
	public void testToString() 
	{
	
	}
	

}