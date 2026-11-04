import java.util.ArrayList;
import java.util.List;

public class Grafo {
    public static final int MAX_VERTICES = 100;
    public static final long MAX_PESO = 1_000_000_000_000L;
    public static final long INFINITO = Long.MAX_VALUE / 4;

    private long[][] matriz;
    private int cantidadVertices;
    private int cantidadAristas;

    public Grafo(int numeroVertices) {
        if (numeroVertices < 1 || numeroVertices > MAX_VERTICES) {
            throw new IllegalArgumentException("La cantidad de vertices debe estar entre 1 y " + MAX_VERTICES);
        }
        cantidadVertices = numeroVertices;
        cantidadAristas = 0;
        matriz = crearMatriz(numeroVertices);
    }

    private static long[][] crearMatriz(int n) {
        long[][] nueva = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nueva[i][j] = (i == j) ? 0 : INFINITO;
            }
        }
        return nueva;
    }

    public int agregarVertice() {
        if (cantidadVertices == MAX_VERTICES) {
            throw new IllegalStateException("Se alcanzo el limite de vertices.");
        }
        int nuevoTamano = cantidadVertices + 1;
        long[][] nueva = crearMatriz(nuevoTamano);
        for (int i = 0; i < cantidadVertices; i++) {
            System.arraycopy(matriz[i], 0, nueva[i], 0, cantidadVertices);
        }
        matriz = nueva;
        cantidadVertices = nuevoTamano;
        return cantidadVertices;
    }

    public void agregarArista(int origen, int destino, long peso) {
        if (!existeVertice(origen) || !existeVertice(destino)) {
            throw new IllegalArgumentException("Uno o ambos vertices no existen.");
        }
        if (origen == destino) {
            throw new IllegalArgumentException("No se permiten aristas de un vertice a si mismo.");
        }
        if (peso < 0 || peso > MAX_PESO) {
            throw new IllegalArgumentException("El peso debe estar entre 0 y " + MAX_PESO + ".");
        }
        if (existeArista(origen, destino)) {
            throw new IllegalArgumentException("La arista ya existe. No se permiten duplicados.");
        }
        matriz[origen - 1][destino - 1] = peso;
        matriz[destino - 1][origen - 1] = peso;
        cantidadAristas++;
    }

    public boolean existeVertice(int vertice) {
        return vertice >= 1 && vertice <= cantidadVertices;
    }

    public boolean existeArista(int origen, int destino) {
        return existeVertice(origen) && existeVertice(destino)
                && origen != destino && matriz[origen - 1][destino - 1] != INFINITO;
    }

    public long getPeso(int origen, int destino) {
        if (!existeVertice(origen) || !existeVertice(destino)) {
            throw new IllegalArgumentException("Vertice inexistente.");
        }
        return matriz[origen - 1][destino - 1];
    }

    public int getCantidadVertices() {
        return cantidadVertices;
    }

    public int getCantidadAristas() {
        return cantidadAristas;
    }

    public List<Arista> getAristas() {
        List<Arista> aristas = new ArrayList<>();
        for (int i = 1; i <= cantidadVertices; i++) {
            for (int j = i + 1; j <= cantidadVertices; j++) {
                if (existeArista(i, j)) {
                    aristas.add(new Arista(i, j, getPeso(i, j)));
                }
            }
        }
        return aristas;
    }

    public void mostrar() {
        System.out.println("\nGRAFO NO DIRIGIDO Y PONDERADO");
        System.out.println("Vertices: " + cantidadVertices + " | Aristas: " + cantidadAristas);
        System.out.println("Matriz de adyacencia (INF = sin arista):");
        System.out.printf("%12s", "");
        for (int j = 1; j <= cantidadVertices; j++) {
            System.out.printf("%12d", j);
        }
        System.out.println();
        for (int i = 1; i <= cantidadVertices; i++) {
            System.out.printf("%12d", i);
            for (int j = 1; j <= cantidadVertices; j++) {
                long peso = getPeso(i, j);
                System.out.printf("%12s", peso == INFINITO ? "INF" : Long.toString(peso));
            }
            System.out.println();
        }
        System.out.println("Aristas:");
        List<Arista> aristas = getAristas();
        if (aristas.isEmpty()) {
            System.out.println("(Ninguna)");
        } else {
            for (Arista arista : aristas) {
                System.out.println("  " + arista);
            }
        }
    }
}
