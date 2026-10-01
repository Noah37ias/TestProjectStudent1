package ordination;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@NullMarked
public class PN extends Ordination {
    private double antalEnheder;
    private Lægemiddel lægemiddel;
    private List<LocalDate> datoer = new ArrayList<>();

    public PN(double antalEnheder, @Nullable Lægemiddel lægemiddel) {
        this.antalEnheder = antalEnheder;
        this.lægemiddel = lægemiddel;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    /** Registrer datoen for en anvendt dosis. */
    public boolean anvendDosis(LocalDate dato) {
        if (!dato.isBefore(getStartDato()) && !dato.isAfter(getSlutDato())) {
            datoer.add(dato);
            return true;
        }
        return false;
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int antalGangeAnvendt() {

        return datoer.size();
    }

    @Override
    public double samletDosis() {

        return døgnDosis() * (ChronoUnit.DAYS.between(datoer.getFirst(), datoer.getLast()) + 1);
    }


    @Override
    public double døgnDosis() {
        if (antalGangeAnvendt() == 0) {
            return 0;
        } else {
            return (antalGangeAnvendt() * antalEnheder) / (ChronoUnit.DAYS.between(datoer.getFirst(), datoer.getLast()) + 1);
        }
    }

    @Override
    public String getType() {
        return "PN";
    }
}
