package ordination;

import org.jspecify.annotations.NullMarked;

@NullMarked
public class DagligFast extends Ordination{
    private Dosis[] doser = new Dosis[4];

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
