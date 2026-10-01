package ordination;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.time.LocalTime;

@NullMarked
public class DagligFast extends Ordination {
    private Dosis[] doser = new Dosis[4];

    private DagligFast(LocalDate startDato, LocalDate slutDato, Lægemiddel lægemiddel,
                       double morgenTal, double middagsTal, double aftenTal, double natTal) {

        setLægemiddel(lægemiddel);
        doser[0] = new Dosis(LocalTime.of(6, 0), morgenTal);
        doser[1] = new Dosis(LocalTime.of(12, 0), middagsTal);
        doser[2] = new Dosis(LocalTime.of(18, 0), aftenTal);
        doser[3] = new Dosis(LocalTime.of(22, 0), natTal);

    }

    @Override
    public double samletDosis() {
        return døgnDosis() * antalDage();
    }

    @Override
    public double døgnDosis() {
        return doser[0].getAntal() + doser[1].getAntal() + doser[2].getAntal() + doser[3].getAntal();
    }

    @Override
    public String getType() {
        return "Daglig Fast";
    }
}
