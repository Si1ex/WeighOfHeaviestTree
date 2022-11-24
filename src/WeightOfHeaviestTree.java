import fi.uef.cs.tra.*;

import java.util.Collections;
import java.util.LinkedList;

public class WeightOfHeaviestTree {

    public float painavimmanPuunPaino(DiGraph G) {

        LinkedList<Vertex> solmutLista = new LinkedList<>();
        LinkedList<Float> painoLista = new LinkedList<>();

        float PuunPaino = 0;

        for (Vertex solmu : G.vertices()) {     //Lisätään kaikki verkon solmut listaan
            solmutLista.add(solmu);
        }

        for (Edge kaari : G.edges()) {  //Käydään kaikki verkon kaaret läpi ja poistetaan ne solmut joihin menee kaaria. Lopuksi jää vain juurisolmut.
            solmutLista.remove(kaari.getEndPoint());
        }

        if (solmutLista.size() == 0) {      //Tarkistetaan poikkeustapaus
            return Float.NaN;
        }

        for (Vertex vertex : solmutLista) {     //Käydään solmujen listaa läpi (sisältää vain juuri) yksi kerrallaan.
            dfs(vertex);    //Käydään naapurit läpi syvyyssuuntaisella läpikäynnillä
            for (Vertex solmu : G.vertices()) {     //Jokaiselle solmulle tarkistetaan onko se väritetty mustaksi (solmut oli väritetty dfs-metodissa)
                if (solmu.getColor() == Graph.BLACK) {      //Jos solmu on väritetty niin käydään sen kaaret läpi
                    for (Edge edge : solmu.edges()) {
                        if (edge.getColor() != Graph.BLACK)     //Tarkistetaan onko kaaressa käyty
                            PuunPaino += edge.getWeight();      //Lisätään kaaren paino muuttujaan
                        edge.setColor(Graph.BLACK);         //Vaihdetaan kaaren väriä
                    }
                }
            }
            painoLista.add(PuunPaino);      //Lisätään paino listaan
            varita(G);      //Väritetään puu
            PuunPaino = 0;      //Nollataan paino-muuttuja seuraava juurisolmua varten
        }
        Collections.sort(painoLista);   //Järjestetään painolista kasvujärjestykseen
        return painoLista.get(painoLista.size()-1);     //Otetaan lopusta painavin puu
    }

    void dfs(Vertex v) {        //Metodi syvyyssuuntaiselle läpikäynnille
        v.setColor(Graph.BLACK);
        for (Vertex vertex : v.neighbors()) {
            if (vertex.getColor() != Graph.BLACK) {
                dfs(vertex);
            }
        }
    }
    void varita(AbstractGraph g) {      //Metodi väritykselle
        for (Vertex v : g.vertices()) {
            v.setColor(AbstractGraph.WHITE);
            for (Edge e : v.edges()) {
                e.setColor(AbstractGraph.WHITE);
            }
        }
    }
}




//LUONNOKSIA:


/**
 * float painavin = 0.0f;
 *         if (G.size() != 0) {
 *             for(Vertex vertex : G.vertices()){
 *                 solmutJoihinEiMeneKaaria.add(vertex);
 *                 for(Vertex v : G.vertices()) {
 *                     if(vertex.getEdge(v) != null && !luvut.contains(vertex)){
 *                         painavin = painavin + vertex.getEdge(v).getWeight();
 *                         luvut.add(vertex);
 *                     }
 *                 }
 *             }
 *             solmutJoihinEiMeneKaaria.removeAll(luvut);
 *             for (Vertex juuri : solmutJoihinEiMeneKaaria) {
 *                 for (Edge juurenKaari : juuri.edges()) {
 *                     painavin = painavin + juurenKaari.getWeight();
 *                 }
 *             }
 *             return painavin;
 *         } else {
 *             painavin = Float.NaN;
 *             return  painavin;
 *         }
 *     }
 * }
 */



/**     Algoritmi laskee kaikkien puiden painot yhteen
 float painavin = 0;
 if (G.size() != 0) {
 for (Vertex solmu : G.vertices()) {
 for (Edge kaari : solmu.edges()) {
 if (solmu == kaari.)
 }
 }
 } else {
 painavin = Float.NaN;
 }
 return painavin;
 }
 }

 */

/**
 * ArrayList<Vertex> solmut = new ArrayList<>();
 *         ArrayList<Vertex> luvut = new ArrayList<>();
 *         for(Vertex vertex : G.vertices()){
 *             solmut.add(vertex);
 *             for(Vertex v : G.vertices()){
 *                 if(vertex.getEdge(v) != null && !luvut.contains(vertex)){
 *                     luvut.add(vertex);
 *                 }
 *             }
 *         }
 *         return 0;
 */



/**
 * varita(G, Graph.WHITE);
 *         painoLista.clear();
 *         int maara = 0;
 *         for (Vertex v : G.vertices()) {
 *             if (v.getColor() == Graph.WHITE) {
 *                 maara++;
 *                 float paino2 = 0.0f;
 *                 for (Vertex w : v.neighbors()) {
 *                     for (Edge e : w.edges()) {
 *                         paino2 += e.getWeight();
 *                     }
 *                 }
 *                 painoLista.add(paino2);
 *             }
 *         }
 *         float paino = 0.0f;
 *         for (int i = 0; i < painoLista.size(); i++) {
 *             Float suurin = painoLista.get(i);
 *             if (suurin > paino) {
 *                 paino = suurin;
 *             }
 *         }
 *         if (maara == 0) {
 *             return Float.NaN;
 *         }
 *         return paino;
 *     }
 *     void varita(AbstractGraph g, int c) {
 *         for (Vertex v : g.vertices())
 *             v.setColor(c);
 *     }
 * if (e.getEndPoint(x) == null) {
 *                     for (Vertex u : x.neighbors()) {
 *                         if (u.getColor() == Graph.WHITE) {
 *                             maara++;
 *                         }
 *                     }
 *                 }
 *                 ArrayList<Vertex> lista1 = new ArrayList<>();
 *         ArrayList<Vertex> luvut = new ArrayList<>();
 *
 *         for(Vertex vertex : G.vertices()){
 *             lista1.add(vertex);
 *             for(Vertex v : G.vertices()){
 *                 if(vertex.getEdge(v) != null && !luvut.contains(vertex)){
 *                     luvut.add(vertex);
 *
 *                 }
 *             }}
 *
 *float painavin = 0;
 *         for (Vertex y : G.vertices()) {
 *                   for (Vertex naapuri : y.neighbors()) {
 *                       for (Edge E : naapuri.edges()) {
 *                           painavin += E.getWeight();
 *                       }
 *                   }
 *                   painoLista.add(painavin);
 *               }
 *               float paino = 0.0f;
 *               for (int i = 0; i < painoLista.size(); i++) {
 *                   Float suurin = painoLista.get(i);
 *                   if (suurin > paino) {
 *                       paino = suurin;
 *                   }
 *               }
 *               if (G.size() == 0) {
 *                   paino = Float.NaN;
 *               }
 *               return paino;
 *           }
 *       }
 */
