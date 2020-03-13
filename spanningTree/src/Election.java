
import visidia.simulation.process.algorithm.LC0_Algorithm;

public class Election extends LC0_Algorithm {

    private static final long serialVersionUID = 5119095075115871042L;
    private final String lost = "F";
    private final String leader = "E";

    //Notre constructeur
    public Election() {

    }

    //Description de notre algorithme
    public String getDescription() {
        return "TD3: election d'un leader";
    }

    //avant le lancement de notre simulation
    protected void beforeStart() {
        this.setLocalProperty("label", this.vertex.getLabel());
        this.setLocalProperty("arity", this.vertex.degree());
    }

    protected void onStarCenter() {

        //Si il y'a eu un élu on termine la simulation avec une terminaison globale
        if (this.getLocalProperty("label").equals("E")) {
            this.globalTermination();
        }

        if ((Integer)this.getLocalProperty("arity") == 1 && (Integer)this.getNeighborProperty("arity") > 1) {

            this.setLocalProperty("label", "F");
            this.setLocalProperty("arity", (Integer)this.getLocalProperty("arity") - 1);
            this.setNeighborProperty("arity", (Integer)this.getNeighborProperty("arity") - 1);

        } else if ((Integer)this.getLocalProperty("arity") == 1 && (Integer)this.getNeighborProperty("arity") == 1) {
            this.setLocalProperty("label", "E");
            this.setLocalProperty("arity", (Integer)this.getLocalProperty("arity") - 1);
            this.setNeighborProperty("label", "F");
            this.setNeighborProperty("arity", (Integer)this.getNeighborProperty("arity") - 1);
        }

    }

    public Object clone() {
        return new Election();
    }
}