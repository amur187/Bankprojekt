import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/** TODO
 *
 *
 *
 */


public class Banktest {
    Kunde k1 = new Kunde("Peter", "Lustig", "Am Löwenzahn 3", LocalDate.of(1963, 4, 13));
    Kunde knull = null;
    Bank bank = new Bank(10050000);

    @Test
    public void testBankErstellen() {
        Bank bank = new Bank(10050000);
        assertEquals(bank.getBankleitzahl(), 10050000);
    }

    @Test
    public void testFalscheBankErstellen() {
        Bank bank = new Bank(-10);
    }


    @Test
    public void testBankGirokontoErstellenEchterKunde() {
        assertEquals(1000000001L, bank.giroKontoErstellen(k1));
    }


    @Test
    public void testBankSparbuchErstellenEchterKunde() {
        bank.sparbuchErstellen(k1);
        assertEquals(1000000002L, bank.sparbuchErstellen(k1));
    }

    @Test
    public void testGetAlleKontonummern() {
        bank.sparbuchErstellen(k1);
        bank.sparbuchErstellen(k1);
        bank.sparbuchErstellen(k1);
        bank.sparbuchErstellen(k1);
        assertEquals(4, bank.getAlleKontonummern().size());
    }

    @Test
    public void testGeldEinzahlen() {
        Kunde k1 = new Kunde("Peter", "Lustig", "Am Löwenzahn 3", LocalDate.of(1963, 4, 13));
        bank.giroKontoErstellen(k1);
        bank.geldEinzahlen(1000000001L, 100);
        assertEquals(bank.getKontostand(1000000001L), 100);
    }


    @Test
    public void testGeldAbhebenUngesperrt() throws GesperrtException {
        bank.giroKontoErstellen(k1);
        bank.geldEinzahlen(1000000001L, 101);
        bank.geldAbheben(1000000001L, 100);
        assertEquals(bank.getKontostand(1000000001L), 1);
    }

    @Test
    public void testGeldAbhebenZuviel() throws GesperrtException {
        bank.giroKontoErstellen(k1);
        bank.geldEinzahlen(1000000001L, 101);
        assertFalse(bank.geldAbheben(1000000001L, 10000));
    }

    @Test
    public void testGeldAbhebenNegativ() throws GesperrtException {
        bank.giroKontoErstellen(k1);
        assertFalse(bank.geldAbheben(1000000001L, -10000));
    }

    @Test
    public void testGeldAbhebenKeinKontoDerBank() throws GesperrtException {
        assertFalse(bank.geldAbheben(1000023451L, 100));
    }

    @Test
    public void testKontoLoeschenExistent() {
        bank.giroKontoErstellen(k1);
        assertTrue(bank.kontoLoeschen(1000000001L));
    }

    @Test
    public void testKontoLoeschenNichtExistent() {
        assertFalse(bank.kontoLoeschen(999900001L));
    }


    @Test
    public void testPleitegeierSperren() throws GesperrtException {
        Kunde k1 = new Kunde("Peter", "Lustig", "Am Löwenzahn 3", LocalDate.of(1963, 4, 13));
        Kunde k2 = new Kunde("Zeter", "Kustig", "Am Löwenzahn 3", LocalDate.of(1923, 4, 13));
        bank.giroKontoErstellen(k1);
        bank.giroKontoErstellen(k2);
        bank.geldAbheben(1000000001L,50);
        bank.pleitegeierSperren();


    }

}

