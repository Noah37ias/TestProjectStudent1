package ordination;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

@NullMarked
public class DagligSkæv extends Ordination {
    private List<Dosis> doser;

    public DagligSkæv(LocalDate startDato, LocalDate slutDato, @Nullable Lægemiddel lægemiddel ) {
    }

    public List<Dosis> getDoser() {
        return doser;
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
