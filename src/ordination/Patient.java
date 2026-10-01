package ordination;

import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;

@NullMarked
public class Patient {
    private String cprNr;
    private String navn;
    private double vægt;
    private List<Ordination> ordinationer;

    public Patient(String cprNr, String navn, double vægt) {
        this.cprNr = cprNr;
        this.navn = navn;
        this.vægt = vægt;
    }

    public double getVægt() {
        return vægt;
    }

    @Override
    public String toString() {
        return navn + "  " + cprNr;
    }
}
