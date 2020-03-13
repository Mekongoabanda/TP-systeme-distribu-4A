//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.util.Iterator;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class SpanningTreeLc2 extends LC2_Algorithm {
    private static final long serialVersionUID = 1048869088690879900L;
    private final String ANode = "A";
    private final String NNode = "N";

    public SpanningTreeLc2() {
    }

    public String getDescription() {
        return "Programme d'un calcul d'un arbre recouvrant avec étoile fermée.\n";
    }

    protected void beforeStart() {
        this.setLocalProperty("label", this.vertex.getLabel());
    }

    protected void onStarCenter() {
        int nbN = 0;
        int doorA = -1;
        Iterator var4 = this.getActiveDoors().iterator();

        int door;
        while(var4.hasNext()) {
            door = (Integer)var4.next();
            if (this.getNeighborProperty(door, "label").toString().equals("N")) {
                ++nbN;
            }

            if (this.getNeighborProperty(door, "label").toString().equals("A")) {
                doorA = door;
            }
        }

        if (this.getLocalProperty("label").equals("N") && doorA != -1) {
            this.setDoorState(new MarkedState(true), doorA);
            this.setLocalProperty("label", "A");
        }

        if (this.getLocalProperty("label").equals("A") && nbN != 0) {
            for(var4 = this.getActiveDoors().iterator(); var4.hasNext(); this.setNeighborProperty(door, "label", "A")) {
                door = (Integer)var4.next();
                if (this.getNeighborProperty(door, "label").toString().equals("N")) {
                    this.setDoorState(new MarkedState(true), door);
                }
            }
        }

    }

    public Object clone() {
        return new SpanningTreeLc2();
    }
}
