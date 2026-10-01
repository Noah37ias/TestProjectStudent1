package ordination;

import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public class DagligSkæv extends Ordination{
    private List<Dosis> doser;

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
        return "";
    }
}
