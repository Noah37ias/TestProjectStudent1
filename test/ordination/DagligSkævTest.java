package ordination;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class DagligSkævTest {

    //--------------------------------------samletDosis-----------------------------------------------
    
    @Test
    void samletDosis1Dag1Dosering(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30)};
        double[] antalEnheder = {1};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(1, result, 0.0001);
    }

    @Test
    void samletDosis4Dage1Dosering(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30)};
        double[] antalEnheder = {1};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 5), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(4, result, 0.0001);
    }

    @Test
    void samletDosis1Dag2Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30), LocalTime.of(10, 30)};
        double[] antalEnheder = {1, 2};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(3, result, 0.0001);
    }

    @Test
    void samletDosis2Dage2Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30), LocalTime.of(10, 30)};
        double[] antalEnheder = {1, 2};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 3), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(6, result, 0.0001);
    }

    @Test
    void samletDosis2Dage3Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30), LocalTime.of(10, 30), LocalTime.of(13, 30)};
        double[] antalEnheder = {1, 2, 1};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 3), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(8, result, 0.0001);
    }


    @Test
    void samletDosis4Dage6Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30), LocalTime.of(10, 30), LocalTime.of(13, 30),  LocalTime.of(14, 30),LocalTime.of(19, 30),LocalTime.of(20, 30)};
        double[] antalEnheder = {2, 1, 2, 1, 2, 1};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 5), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(36, result, 0.0001);
    }

    @Test
    void samletDosis1DagTomDoseringer(){
        LocalTime[] klokkeSlet = {};
        double[] antalEnheder = {};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(0, result, 0.0001);
    }

    @Test
    void samletDosis1Dag0Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30)};
        double[] antalEnheder = {0};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(0, result, 0.0001);
    }

    @Test
    void samletDosis2Dag05Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30)};
        double[] antalEnheder = {0.5};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 3), null, klokkeSlet, antalEnheder);
        double result = d.samletDosis();
        assertEquals(1, result, 0.0001);
    }

//--------------------------------------døgnDosis-----------------------------------------------

    @Test
    void døgnDosis1Dag1Dosering(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30)};
        double[] antalEnheder = {1};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.døgnDosis();
        assertEquals(1, result, 0.0001);
    }

    @Test
    void døgnDosis4Dage1Dosering(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30)};
        double[] antalEnheder = {1};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 5), null, klokkeSlet, antalEnheder);
        double result = d.døgnDosis();
        assertEquals(1, result, 0.0001);
    }

    @Test
    void døgnDosis1Dag2Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30), LocalTime.of(10, 30)};
        double[] antalEnheder = {1, 2};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.døgnDosis();
        assertEquals(3, result, 0.0001);
    }


    @Test
    void døgnDosis1Dag6Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30), LocalTime.of(10, 30), LocalTime.of(13, 30),  LocalTime.of(14, 30),LocalTime.of(19, 30),LocalTime.of(20, 30)};
        double[] antalEnheder = {2, 1, 2, 1, 2, 1};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.døgnDosis();
        assertEquals(9, result, 0.0001);
    }

    @Test
    void døgnDosis1DagTomDoseringer(){
        LocalTime[] klokkeSlet = {};
        double[] antalEnheder = {};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.døgnDosis();
        assertEquals(0, result, 0.0001);
    }

    @Test
    void døgnDosis1Dag0Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30)};
        double[] antalEnheder = {0};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 2), null, klokkeSlet, antalEnheder);
        double result = d.døgnDosis();
        assertEquals(0, result, 0.0001);
    }

    @Test
    void døgnDosis2Dage05Doseringer(){
        LocalTime[] klokkeSlet = {LocalTime.of(9, 30)};
        double[] antalEnheder = {0.5};
        DagligSkæv d = new DagligSkæv(LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 3), null, klokkeSlet, antalEnheder);
        double result = d.døgnDosis();
        assertEquals(0.5, result, 0.0001);
    }
}