import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    private Kruskal() {
    }

    public static void ejecutar(Grafo grafo) {
        List<Arista> aristasOrdenadas = grafo.getAristas();
        aristasOrdenadas.sort(Comparator.comparingLong(Arista::getPeso));

        ConjuntosDisjuntos conjuntos = new ConjuntosDisjuntos(grafo.getCantidadVertices());
        List<Arista> seleccionadas = new ArrayList<>();
        long costoTotal = 0;

        for (Arista arista : aristasOrdenadas) {
            if (conjuntos.unir(arista.getOrigen(), arista.getDestino())) {
                seleccionadas.add(arista);
                costoTotal += arista.getPeso();
                if (seleccionadas.size() == grafo.getCantidadVertices() - 1) {
                    break;
                }
            }
        }

        System.out.println("\n--- KRUSKAL ---");
        System.out.println("Aristas seleccionadas (en orden de peso, sin ciclos):");
        for (Arista arista : seleccionadas) {
            System.out.println("  " + arista);
        }
        if (seleccionadas.isEmpty()) {
            System.out.println("  No fue necesario seleccionar aristas.");
        }
        if (seleccionadas.size() == grafo.getCantidadVertices() - 1) {
            System.out.println("Peso total del arbol de expansion minima: " + costoTotal);
        } else {
            System.out.println("El grafo no es conexo: no existe un arbol de expansion minima global.");
            System.out.println("Se obtuvo un bosque de expansion minima.");
            System.out.println("Peso total del bosque: " + costoTotal);
        }
    }
}
