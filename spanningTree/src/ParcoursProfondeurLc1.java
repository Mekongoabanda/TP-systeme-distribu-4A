//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompil
import java.util.Iterator;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class ParcoursProfondeurLc1 extends LC1_Algorithm {
    private final String ANode = "A";
    private final String NNode = "N";

    public ParcoursProfondeurLc1() {
    }

    public String getDescription() {
        return "TP4: Etoiles ouvertes, arbre recouvrant dans un graphe quelconque";
    }

    protected void beforeStart() {
        this.setLocalProperty("label", this.vertex.getLabel());
    }

    protected void onStarCenter() {
        int doorA = -1;
        if (this.getLocalProperty("label").equals("N")) {
            Iterator var3 = this.getActiveDoors().iterator();

            while(var3.hasNext()) {
                int door = (Integer)var3.next();
                if (this.getNeighborProperty(door, "label").equals("A")) {
                    doorA = door;
                }
            }

            if (doorA != -1) {
                this.setDoorState(new MarkedState(true), doorA);
                this.setLocalProperty("label", "A");
            }
        }

    }

    public Object clone() {
        return new ParcoursProfondeurLc1();
    }
}
