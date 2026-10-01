package ordination;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@NullMarked
public class DagligSkæv extends Ordination {
    private List<Dosis> doser;

    public DagligSkæv(LocalDate startDato, LocalDate slutDato, Lægemiddel lægemiddel, List<Dosis> doser) {
        this.doser = doser;
    }

    @Override
    public double samletDosis() {
        double sum = 0;
        for (Dosis dosis: doser){
            sum += dosis.getAntal();
        }

        return sum;
    }

    @Override
    public double døgnDosis() {

        return 0;
    }

    @Override
    public String getType() {
        return "Daglig skæv";
    }
}
