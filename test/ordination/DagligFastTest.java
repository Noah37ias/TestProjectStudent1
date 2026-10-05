package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void samletDosisNulPiller() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,5), 0,0,0,0, null);
        assertEquals(0,df.samletDosis());
    }
    @Test
    void samletDosisÉnPåAlle() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,5), 1,0,0,0, null);
        assertEquals(1,df.samletDosis());
    }

    @Test
    void samletDosisToPillerToDage() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,6), 1,0,0,1, null);
        assertEquals(4,df.samletDosis());
    }

    @Test
    void samletDosisSeksPillerTreDage() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,7), 1,2,1,2, null);
        assertEquals(18,df.samletDosis());
    }

    @Test
    void samletDosisOttePillerSyvDage() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,11), 2,2,2,2, null);
        assertEquals(56,df.samletDosis());
    }

    @Test
    void døgnDosisNulPiller() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,9), 0,0,0,0, null);
        assertEquals(0,df.døgnDosis());
    }

    @Test
    void døgnDosisÈnPilleMorgen() {
        DagligFast df1 = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,9), 1,0,0,0, null);
        assertEquals(1,df1.døgnDosis());
    }
    @Test
    void døgnDosisÉnPilleMorgenOgNat() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,9), 1,0,0,1, null);
        assertEquals(2,df.døgnDosis());
    }

    @Test
    void døgnDosisÈnToÈnTo() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,9), 1,2,1,2, null);
        assertEquals(6,df.døgnDosis());
    }

    @Test
    void døgnDosisToPåAlle() {
        DagligFast df = new DagligFast(LocalDate.of(2026,10,5),
                LocalDate.of(2026,10,9), 2,2,2,2, null);
        assertEquals(8,df.døgnDosis());
    }
}