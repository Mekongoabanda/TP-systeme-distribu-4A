
import visidia.simulation.process.algorithm.Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
import visidia.simulation.process.messages.IntegerMessage;
import visidia.simulation.process.messages.StringMessage;
import visidia.simulation.process.algorithm.LC0_Algorithm;

import java.util.Random;

// todo :  la classe abstraite Algorithm nous impose d'implémenter les méthodes clone() et init().
//  La méthode clone() permet de créer des instances de notre class et la méthode init() contient
//  le comportement du noeud.

public class MonAlgo extends Algorithm {


    @Override
    public Object clone() {
        return null;
    }

    //todo: Essayons de demander à nos noeuds d'envoyer un nombre aléatoire entre 1 et 15 et de les envoyer à tous les voisins.
    // Pour cela, nous auront besoin de 3 méthodes :
    //
    //todo: void sendAll(Message m) : Envoi m à tous ses voisins.
    //todo: int getArity() : Renvoi le nombre de noeuds présents.
    //todo : boolean sendTo(int door, Message m) : Envoi m via la porte door.
    //todo: Chaque noeuds possède une à plusieures portes qui mène à son / ses voisins. Si vous désirez que tous vos
    // noeuds envoient un message à leur premier voisin il suffit d'utiliser sendTo(1,Message m) si vous voulez que les
    // noeuds envoient un message à tous les voisins un sendAll(Message m) est préférable.

    @Override
    public void init() {

        Random nb = new Random();
        int nodes = getArity(); //On récupère le nombre de noeuds présents lors de la simulation

        while(true){
            int nbAlea = nb.nextInt(15-1)+1; //On génère un nombre entre 1 et 15
            sendAll(new IntegerMessage(nbAlea)); // On envoie ce nombre à tous les voisins

            for (int i = 0; i < nodes; i++) {

                /* On utilise une boucle pour demander à notre noeud d'attendre un message
                 * provenant de ses voisins. On sait que ces messages sont des entiers on
                 * les stocke donc dans une variable de type IntegerMessage via un cast.
                 */

                IntegerMessage msg = (IntegerMessage)receiveFrom(i);

                /* Taitement du message recu */
                if(msg.value()==7){
                    /* Si le message reçu est 7, on met en évidence la porte (le chemin) qui
                     * mène vers le voisin qui ce voisin, et on lui envoi le message : "Trouvé!"
                     */
                    setDoorState(new MarkedState(true),i); // Mise en évidence de la porte menant vers i
                    sendTo(i,new StringMessage("Trouvé !")); // On envoi "Trouvé !" au voisin i.
                    receiveFrom(i); // On attends que le voisin réponde.
                    setDoorState(new MarkedState(false),i); //On reset la forme de porte
                }

            }
        }


    }
}
