package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    @Test
    void anvendDosisDatoFørPeriode() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 8), 1, null);
        LocalDate dato1 = LocalDate.of(2026, 10, 1);
        assertEquals(false, pn.anvendDosis(dato1));

    }

    @Test
    void anvendDosisDatoIPeriode() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 8), 1, null);
        LocalDate dato2 = LocalDate.of(2026, 10, 6);
        assertEquals(true, pn.anvendDosis(dato2));
    }

    @Test
    void anvendDosisDatoEfterPeriode() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 8), 1, null);
        LocalDate dato3 = LocalDate.of(2026, 10, 10);
        assertEquals(false, pn.anvendDosis(dato3));
    }


    @Test
    void antalGangeAnvendtÉnEnhed() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 8), 1, null);

        pn.anvendDosis(LocalDate.of(2026, 10, 2));
        assertEquals(1, pn.antalGangeAnvendt());
    }

    @Test
    void antalGangeAnvendtTreEnheder() {

        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 8), 1, null);
        pn.anvendDosis(LocalDate.of(2026, 10, 2));
        pn.anvendDosis(LocalDate.of(2026, 10, 4));
        pn.anvendDosis(LocalDate.of(2026, 10, 6));
        assertEquals(3, pn.antalGangeAnvendt());
    }

    @Test
    void samletDosisÉnEnhedÉnGang() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 8), 1, null);
        pn.anvendDosis(LocalDate.of(2026, 10, 2));
        assertEquals(1, pn.getAntalEnheder() * pn.antalGangeAnvendt());
    }

    @Test
    void samletDosisÉnEnhedTreGange() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 8), 1, null);
        pn.anvendDosis(LocalDate.of(2026, 10, 2));
        pn.anvendDosis(LocalDate.of(2026, 10, 4));
        pn.anvendDosis(LocalDate.of(2026, 10, 6));
        assertEquals(3, pn.getAntalEnheder() * pn.antalGangeAnvendt());

    }


    @Test
    void samletDosisToEnhederFireGange() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 8), 2, null);
        pn.anvendDosis(LocalDate.of(2026, 10, 2));
        pn.anvendDosis(LocalDate.of(2026, 10, 4));
        pn.anvendDosis(LocalDate.of(2026, 10, 6));
        pn.anvendDosis(LocalDate.of(2026, 10, 7));
        assertEquals(8, pn.getAntalEnheder() * pn.antalGangeAnvendt());
    }

    @Test
    void samletDosisOtteEnhederSyvGange() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 14), 8, null);
        pn.anvendDosis(LocalDate.of(2026, 10, 2));
        pn.anvendDosis(LocalDate.of(2026, 10, 4));
        pn.anvendDosis(LocalDate.of(2026, 10, 6));
        pn.anvendDosis(LocalDate.of(2026, 10, 7));
        pn.anvendDosis(LocalDate.of(2026, 10, 9));
        pn.anvendDosis(LocalDate.of(2026, 10, 10));
        pn.anvendDosis(LocalDate.of(2026, 10, 12));
        assertEquals(56, pn.getAntalEnheder() * pn.antalGangeAnvendt());
    }

    @Test
    void døgnDosisAntalGangeAnvendtNul() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 14), 0, null);
        assertEquals(0, pn.døgnDosis());
    }

    @Test
    void døgnDosisAntalGangeAnvendtÉnOgEnhedÉn() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), 1, null);
        pn.anvendDosis(LocalDate.of(2026,10,2));
        assertEquals(1, pn.døgnDosis());
    }

    @Test
    void døgnDosisAntalGangeAnvendtFireDageAnvendtToEnhedÉn() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 5), 1, null);
        pn.anvendDosis(LocalDate.of(2026,10,2));
        pn.anvendDosis(LocalDate.of(2026,10,5));
        assertEquals(0.5, pn.døgnDosis());
    }

    @Test
    void døgnDosisAntalGangeAnvendtFireDageAnvendtTreEnhedÉn() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 5), 1, null);
        pn.anvendDosis(LocalDate.of(2026,10,2));
        pn.anvendDosis(LocalDate.of(2026,10,3));
        pn.anvendDosis(LocalDate.of(2026,10,5));
        assertEquals(0.75, pn.døgnDosis());
    }

    @Test
    void døgnDosisAntalGangeAnvendtFireDageAnvendtFireEnhedTo() {
        PN pn = new PN(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 5), 2, null);
        pn.anvendDosis(LocalDate.of(2026,10,2));
        pn.anvendDosis(LocalDate.of(2026,10,3));
        pn.anvendDosis(LocalDate.of(2026,10,4));
        pn.anvendDosis(LocalDate.of(2026,10,5));
        assertEquals(2, pn.døgnDosis());
    }

    @Test
    void døgnDosisTestAfSortering() {
        PN pn = new PN(LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 10), 2, null);
        pn.anvendDosis(LocalDate.of(2026,10,12));
        pn.anvendDosis(LocalDate.of(2026,10,3));
        pn.anvendDosis(LocalDate.of(2026,10,2));
        pn.anvendDosis(LocalDate.of(2026,10,5));
        IO.println(pn.døgnDosis());
    }
}