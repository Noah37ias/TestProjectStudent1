package controller;

import ordination.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @org.junit.jupiter.api.Test
    void opretPNOrdination() {
        Lægemiddel para = Controller.opretLægemiddel("Paracetamol", "ml", 25, 50, 75);
        Patient p = Controller.opretPatient("1604041212", "Noah", 67);
        assertEquals(0, p.getOrdinationer().size());

        PN pn1 = Controller.opretPNOrdination(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), 10, p, para);
        assertTrue(p.getOrdinationer().contains(pn1));
        assertNotNull(pn1);
        assertEquals(1, p.getOrdinationer().size());


        PN pn2 = Controller.opretPNOrdination(LocalDate.of(2026, 10, 5), LocalDate.of(2026, 12, 12), 10, p, para);
        assertTrue(p.getOrdinationer().contains(pn2));
        assertNotNull(pn2);
        assertEquals(2, p.getOrdinationer().size());

        //UGYLDIGE DATA


        assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretPNOrdination(LocalDate.of(2026, 10, 3), LocalDate.of(2026, 10, 1), 10, p, para);
        });
        assertEquals(2, p.getOrdinationer().size());

    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination() {
        Lægemiddel para = Controller.opretLægemiddel("Paracetamol", "ml", 25, 50, 75);
        Patient p = Controller.opretPatient("1604041212", "Noah", 67);
        assertEquals(0, p.getOrdinationer().size());

        DagligFast df = Controller.opretDagligFastOrdination(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), 1, 1, 1, 1, p, para);
        assertTrue(p.getOrdinationer().contains(df));
        assertEquals(1, p.getOrdinationer().size());

        Controller.opretDagligFastOrdination(LocalDate.of(2026, 10, 5), LocalDate.of(2026, 12, 12), 1, 1, 1, 1, p, para);
        assertTrue(p.getOrdinationer().contains(df));
        assertEquals(2, p.getOrdinationer().size());

        //UGYLDIGE DATA

        assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretDagligFastOrdination(LocalDate.of(2026, 10, 3), LocalDate.of(2026, 10, 1), 1, 1, 1, 1, p, para);
        });
        assertEquals(2, p.getOrdinationer().size());

    }

    @org.junit.jupiter.api.Test
    void opretDagligSkævOrdination() {

        Lægemiddel para = Controller.opretLægemiddel("Paracetamol", "ml", 25, 50, 75);
        Patient p = Controller.opretPatient("1604041212", "Noah", 67);
        assertEquals(0, p.getOrdinationer().size());
        LocalTime[] tid = {LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1)};
        double[] enheder = {1, 1, 1, 1};

        DagligSkæv ds = Controller.opretDagligSkævOrdination(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), tid, enheder, p, para);
        assertTrue(p.getOrdinationer().contains(ds));
        assertEquals(1, p.getOrdinationer().size());

        DagligSkæv ds1 = Controller.opretDagligSkævOrdination(LocalDate.of(2026, 10, 5), LocalDate.of(2026, 12, 12), tid, enheder, p, para);
        assertTrue(p.getOrdinationer().contains(ds1));
        assertEquals(2, p.getOrdinationer().size());

        //UGYLDIGE DATA :9


        assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretDagligSkævOrdination(LocalDate.of(2026, 10, 3), LocalDate.of(2026, 10, 1), tid, enheder, p, para);
        });

        LocalTime[] tid1 = {LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1)};
        double[] enheder1 = {1, 1, 1, 1, 1, 1};
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretDagligSkævOrdination(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), tid1, enheder1, p, para);
        });

        LocalTime[] tid2 = {LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1), LocalTime.of(1, 1)};
        double[] enheder2 = {1, 1, 1, 1};
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretDagligSkævOrdination(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), tid2, enheder2, p, para);
        });

    }

    @org.junit.jupiter.api.Test
    void anvendOrdinationPN() {
        Lægemiddel para = Controller.opretLægemiddel("Paracetamol", "ml", 25, 50, 75);
        Patient p = Controller.opretPatient("1604041212", "Noah", 67);
        PN pn1 = Controller.opretPNOrdination(LocalDate.of(2026, 9, 2), LocalDate.of(2026, 10, 2), 10, p, para);

        Controller.anvendOrdinationPN(pn1, LocalDate.of(2026, 10, 1));
        assertEquals(1, pn1.antalGangeAnvendt());

        Controller.anvendOrdinationPN(pn1, LocalDate.of(2026, 9, 2));
        assertEquals(2, pn1.antalGangeAnvendt());

        Controller.anvendOrdinationPN(pn1, LocalDate.of(2026, 10, 2));
        assertEquals(3, pn1.antalGangeAnvendt());

        assertThrows(IllegalArgumentException.class, () -> {
            Controller.anvendOrdinationPN(pn1, LocalDate.of(2025, 10, 1));
        });
        assertEquals(3, pn1.antalGangeAnvendt());

        assertThrows(IllegalArgumentException.class, () -> {
            Controller.anvendOrdinationPN(pn1, LocalDate.of(2026, 10, 3));
        });
        assertEquals(3, pn1.antalGangeAnvendt());

    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDøgn() {
        Lægemiddel para = Controller.opretLægemiddel("Paracetamol", "ml", 25, 50, 75);
        Patient p1 = Controller.opretPatient("1604041212", "Noah", 1);
        double result1 = Controller.anbefaletDosisPrDøgn(p1, para);
        assertEquals(25, result1, 0.5);

        Patient p2 = Controller.opretPatient("1604041212", "Noah", 1000);
        double result2 = Controller.anbefaletDosisPrDøgn(p2, para);
        assertEquals(75, result2, 0.5);

        Patient p3 = Controller.opretPatient("1604041212", "Noah", 120);
        double result3 = Controller.anbefaletDosisPrDøgn(p3, para);
        assertEquals(50, result3, 0.5);

        Patient p4 = Controller.opretPatient("1604041212", "Noah", 25);
        double result4 = Controller.anbefaletDosisPrDøgn(p4, para);
        assertEquals(50, result4, 0.5);

        Patient p5 = Controller.opretPatient("1604041212", "Noah", 24.9);
        double result5 = Controller.anbefaletDosisPrDøgn(p5, para);
        assertEquals(24.9, result5, 0.5);

        Patient p6 = Controller.opretPatient("1604041212", "Noah", 120.1);
        double result6 = Controller.anbefaletDosisPrDøgn(p6, para);
        assertEquals(75, result6, 0.5);

        Patient p7 = Controller.opretPatient("1604041212", "Noah", 35);
        double result7 = Controller.anbefaletDosisPrDøgn(p7, para);
        assertEquals(50, result7, 0.5);

        Patient p8 = Controller.opretPatient("1604041212", "Noah", -1);
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.anbefaletDosisPrDøgn(p8, para);
        });

        Patient p9 = Controller.opretPatient("1604041212", "Noah", 1001);
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.anbefaletDosisPrDøgn(p9, para);
        });
    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVægtPrLægemiddel() {
        Lægemiddel para = Controller.opretLægemiddel("Paracetamol", "ml", 25, 50, 75);
        Patient p1 = Controller.opretPatient("1604041212", "Noah", 24.9);
        Patient p2 = Controller.opretPatient("1604041212", "Noah", 25);
        Patient p3 = Controller.opretPatient("1604041212", "Noah", 120);
        Patient p4 = Controller.opretPatient("1604041212", "Noah", 120.1);
        Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2), 5, p1, para);
        Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2), 5, p2, para);
        Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2), 5, p3, para);
        Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2), 5, p4, para);

        int result1 = Controller.antalOrdinationerPrVægtPrLægemiddel(25, 120, para);
        assertEquals(2, result1);
    }
}