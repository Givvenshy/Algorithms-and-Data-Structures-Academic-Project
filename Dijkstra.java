import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    private static class Estado {
        int vertice;
        long distancia;

        Estado(int vertice, long distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }
    }

    private Dijkstra() {
    }

    public static void ejecutar(Grafo grafo, int origen) {
        if (!grafo.existeVertice(origen)) {
            throw new IllegalArgumentException("El vertice de origen no existe.");
        }

        int n = grafo.getCantidadVertices();
        long[] distancias = new long[n + 1];
        int[] anterior = new int[n + 1];
        Arrays.fill(distancias, Grafo.INFINITO);
        Arrays.fill(anterior, -1);
        distancias[origen] = 0;

        PriorityQueue<Estado> cola = new PriorityQueue<>(Comparator.comparingLong(e -> e.distancia));
        cola.add(new Estado(origen, 0));

        while (!cola.isEmpty()) {
            Estado actual = cola.poll();
            int u = actual.vertice;
            if (actual.distancia != distancias[u]) {
                continue;
            }

            for (int v = 1; v <= n; v++) {
                if (!grafo.existeArista(u, v)) {
                    continue;
                }
                long peso = grafo.getPeso(u, v);
                long nuevaDistancia = distancias[u] + peso;
                if (nuevaDistancia < distancias[v]) {
                    distancias[v] = nuevaDistancia;
                    anterior[v] = u;
                    cola.add(new Estado(v, nuevaDistancia));
                }
            }
        }

        System.out.println("\n--- DIJKSTRA ---");
        System.out.println("Vertice de origen: " + origen);
        for (int destino = 1; destino <= n; destino++) {
            if (distancias[destino] == Grafo.INFINITO) {
                System.out.println("Hacia " + destino + ": no existe ruta.");
            } else {
                System.out.println("Hacia " + destino + " | Distancia: " + distancias[destino]
                        + " | Ruta: " + construirRuta(anterior, destino));
            }
        }
    }

    private static String construirRuta(int[] anterior, int destino) {
        List<Integer> ruta = new ArrayList<>();
        for (int v = destino; v != -1; v = anterior[v]) {
            ruta.add(v);
        }
        Collections.reverse(ruta);
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < ruta.size(); i++) {
            if (i > 0) {
                texto.append(" -> ");
            }
            texto.append(ruta.get(i));
        }
        return texto.toString();
    }
}
