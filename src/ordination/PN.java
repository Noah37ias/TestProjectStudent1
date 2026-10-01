package ordination;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NullMarked
public class PN extends Ordination{
    private double antalEnheder;
    private List<LocalDate> datoer = new ArrayList<>();

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
        return 0;
    }

    @Override
    public double døgnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return "PN";
    }
}
