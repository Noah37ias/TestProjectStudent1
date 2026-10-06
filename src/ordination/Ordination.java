package ordination;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@NullMarked
public abstract class Ordination {
    private LocalDate startDato;
    private LocalDate slutDato;
    private @Nullable Lægemiddel lægemiddel;

    public Ordination(LocalDate startDato, LocalDate slutDato) {
        this.startDato = startDato;
        this.slutDato = slutDato;
    }

    public void setLægemiddel(Lægemiddel lægemiddel) {
        this.lægemiddel = lægemiddel;
    }

    public void removeLægemiddel() {
        this.lægemiddel = null;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public Lægemiddel getLægemiddel() {
        return lægemiddel;
    }

    /**
     * Returner antal dage mellem startdato og slutdato
     * (begge dage inklusive).
     */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDato, slutDato) + 1;
    }

    @Override
    public String toString() {
        return startDato.toString();
    }

    /** Returner den totale dosis, der er givet i den periode, ordinationen er gyldig. */
    public abstract double samletDosis();

    /** Returner den gennemsnitlige dosis givet per dag. */
    public abstract double døgnDosis();

    /** Returner ordinations typen som en String (f.eks. "PN"). */
    public abstract String getType();
}
