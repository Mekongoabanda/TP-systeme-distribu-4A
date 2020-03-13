//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.util.Iterator;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class SpanningTreeLc2 extends LC2_Algorithm {
    //Noeud étant à l'Etat "A"
    private final String ANode = "A";
    //Noeud étant a l'état "N"
    private final String NNode = "N";
    int nodes = getArity(); //On récupère le nombre de noeuds présents lors de la simulation
    int arret = 0;

    public SpanningTreeLc2() {
    }

    public String getDescription() {
        return "Programme d'un calcul d'un arbre recouvrant avec étoile fermée, si le centre de l'étoile est à l'Etat A" +
                " et qu'il possède des voisins N, il demande à ces derniers de passer à l'Etat A et marque les liens qui" +
                "le connectent à ces voisins .\n";
    }

    protected void beforeStart() {
        this.setLocalProperty("label", this.vertex.getLabel());
    }

    protected void onStarCenter() {
        int nbN = 0;
        int doorA = -1;
        //Numéro de port d'un voisin
        Iterator var4 = this.getActiveDoors().iterator();

        int door;
        while(var4.hasNext()) {
            door = (Integer)var4.next();
            setLocalTerminaison();
            //Si le port "door" est à N
            if (this.getNeighborProperty(door, "label").toString().equals("N")) {
                ++nbN;
            }
            //Si le voisin est à A
            if (this.getNeighborProperty(door, "label").toString().equals("A")) {
                doorA = door;
            }

        }

        //Si noeud est à N et et "doorA" différent de -1
        if (this.getLocalProperty("label").equals("N") && doorA != -1) {
            this.setDoorState(new MarkedState(true), doorA);
            this.setLocalProperty("label", "A");
            setLocalTerminaison();
        }

        if (this.getLocalProperty("label").equals("A") && nbN != 0) {
            for(var4 = this.getActiveDoors().iterator(); var4.hasNext(); this.setNeighborProperty(door, "label", "A")) {
                door = (Integer)var4.next();
                if (this.getNeighborProperty(door, "label").toString().equals("N")) {
                    this.setDoorState(new MarkedState(true), door);
                }
            }
            setLocalTerminaison();
        }

    }

    public void setLocalTerminaison(){
        arret = 0;
        for (int i = 0; i < nodes; i++) {

            if (arret < nodes) {
                if (this.getNeighborProperty(i, "label").toString().equals("A")) {
                    arret++;
                }
            }else if( arret == nodes){

                localTermination();
            }
        }
    }

    public Object clone() {
        return new SpanningTreeLc2();
    }
}
