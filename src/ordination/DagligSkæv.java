package ordination;

import org.jspecify.annotations.NullMarked;
import java.time.LocalDate;
import java.util.List;

@NullMarked
public class DagligSkæv extends Ordination {
    private List<Dosis> doser;

    public DagligSkæv(LocalDate startDato, LocalDate slutDato, Lægemiddel lægemiddel, List<Dosis> doser) {
        this.doser = doser;
    }

    @Override
    public double samletDosis() {
        return døgnDosis() * antalDage();
    }

    @Override
    public double døgnDosis() {
        double sum = 0;
        for (Dosis dosis: doser){
            sum += dosis.getAntal();
        }

        return sum;
    }

    @Override
    public String getType() {
        return "Daglig skæv";
    }
}
