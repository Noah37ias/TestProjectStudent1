package controller;

import ordination.Lægemiddel;
import ordination.Ordination;
import ordination.Patient;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @org.junit.jupiter.api.Test
    void opretPNOrdination() {
    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination() {
    }

    @org.junit.jupiter.api.Test
    void opretDagligSkævOrdination() {
    }

    @org.junit.jupiter.api.Test
    void anvendOrdinationPN() {
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDøgn() {
        Lægemiddel para = Controller.opretLægemiddel("Paracetamol","ml",25,50,75);
        Patient p1 = Controller.opretPatient("1604041212","Noah",1);
        double result1 = Controller.anbefaletDosisPrDøgn(p1,para);
        assertEquals(25,result1,0.5);

        Patient p2 = Controller.opretPatient("1604041212","Noah",1000);
        double result2 = Controller.anbefaletDosisPrDøgn(p2,para);
        assertEquals(75,result2,0.5);

        Patient p3 = Controller.opretPatient("1604041212","Noah",120);
        double result3 = Controller.anbefaletDosisPrDøgn(p3,para);
        assertEquals(50,result3,0.5);

        Patient p4 = Controller.opretPatient("1604041212","Noah",25);
        double result4 = Controller.anbefaletDosisPrDøgn(p4,para);
        assertEquals(50,result4,0.5);

        Patient p5 = Controller.opretPatient("1604041212","Noah",24.9);
        double result5 = Controller.anbefaletDosisPrDøgn(p5,para);
        assertEquals(24.9,result5,0.5);

        Patient p6 = Controller.opretPatient("1604041212","Noah",120.1);
        double result6 = Controller.anbefaletDosisPrDøgn(p6,para);
        assertEquals(75,result6,0.5);

        Patient p7 = Controller.opretPatient("1604041212","Noah",35);
        double result7 = Controller.anbefaletDosisPrDøgn(p7,para);
        assertEquals(50,result7,0.5);

        Patient p8 = Controller.opretPatient("1604041212","Noah",-1);
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.anbefaletDosisPrDøgn(p8, para);
        });

        Patient p9 = Controller.opretPatient("1604041212","Noah",1001);
        assertThrows(IllegalArgumentException.class,() -> {
            Controller.anbefaletDosisPrDøgn(p9, para);
        });
    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVægtPrLægemiddel() {
        Lægemiddel para = Controller.opretLægemiddel("Paracetamol","ml",25,50,75);
        Patient p1 = Controller.opretPatient("1604041212","Noah",24.9);
        Patient p2 = Controller.opretPatient("1604041212","Noah",25);
        Patient p3 = Controller.opretPatient("1604041212","Noah",120);
        Patient p4 = Controller.opretPatient("1604041212","Noah",120.1);
        Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2), 5, p1, para);
        Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2), 5, p2, para);
        Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2), 5, p3, para);
        Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2), 5, p4, para);

        int result1 = Controller.antalOrdinationerPrVægtPrLægemiddel(25,120,para);
        assertEquals(2,result1);
    }
}