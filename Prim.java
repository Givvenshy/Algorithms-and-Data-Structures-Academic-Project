import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {
    private Prim() {
    }

    public static void ejecutar(Grafo grafo) {
        int n = grafo.getCantidadVertices();
        boolean[] visitado = new boolean[n + 1];
        List<Arista> seleccionadas = new ArrayList<>();
        PriorityQueue<Arista> cola = new PriorityQueue<>(Comparator.comparingLong(Arista::getPeso));
        int componentes = 0;
        long costoTotal = 0;

        for (int inicio = 1; inicio <= n; inicio++) {
            if (visitado[inicio]) {
                continue;
            }
            componentes++;
            visitado[inicio] = true;
            agregarCandidatas(grafo, inicio, visitado, cola);

            while (!cola.isEmpty()) {
                Arista mejor = cola.poll();
                int destino = mejor.getDestino();
                if (visitado[destino]) {
                    continue;
                }
                visitado[destino] = true;
                seleccionadas.add(mejor);
                costoTotal += mejor.getPeso();
                agregarCandidatas(grafo, destino, visitado, cola);
            }
        }

        System.out.println("\n--- PRIM ---");
        imprimirResultado(seleccionadas, costoTotal, componentes);
    }

    private static void agregarCandidatas(Grafo grafo, int u, boolean[] visitado,
                                           PriorityQueue<Arista> cola) {
        for (int v = 1; v <= grafo.getCantidadVertices(); v++) {
            if (!visitado[v] && grafo.existeArista(u, v)) {
                cola.add(new Arista(u, v, grafo.getPeso(u, v)));
            }
        }
    }

    private static void imprimirResultado(List<Arista> aristas, long costoTotal, int componentes) {
        for (Arista arista : aristas) {
            System.out.println("  " + arista);
        }
        if (aristas.isEmpty()) {
            System.out.println("  No fue necesario seleccionar aristas.");
        }
        if (componentes == 1) {
            System.out.println("Peso total del arbol de expansion minima: " + costoTotal);
        } else {
            System.out.println("El grafo no es conexo: no existe un arbol de expansion minima global.");
            System.out.println("Se obtuvo un bosque de expansion minima con " + componentes + " componentes.");
            System.out.println("Peso total del bosque: " + costoTotal);
        }
    }
}
