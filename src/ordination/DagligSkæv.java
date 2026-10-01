package ordination;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NullMarked
public class DagligSkæv extends Ordination {
    private List<Dosis> doser;

    public DagligSkæv(LocalDate startDato, LocalDate slutDato, @Nullable Lægemiddel lægemiddel,
                      LocalTime[] klokkeSlet, double[] antalEnheder ) {
        setLægemiddel(lægemiddel);
        this.doser = new ArrayList<>();

    }

    public List<Dosis> getDoser() {
        return doser;
    }
    public void addDoser(Dosis dosis){
        doser.add(dosis);
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
