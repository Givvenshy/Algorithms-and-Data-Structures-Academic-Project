public class FloydWarshall {
    private FloydWarshall() {
    }

    public static void ejecutar(Grafo grafo) {
        int n = grafo.getCantidadVertices();
        long[][] distancias = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distancias[i][j] = grafo.getPeso(i + 1, j + 1);
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (distancias[i][k] == Grafo.INFINITO) {
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (distancias[k][j] == Grafo.INFINITO) {
                        continue;
                    }
                    long pasandoPorK = distancias[i][k] + distancias[k][j];
                    if (pasandoPorK < distancias[i][j]) {
                        distancias[i][j] = pasandoPorK;
                    }
                }
            }
        }

        System.out.println("\n--- FLOYD-WARSHALL ---");
        System.out.println("Matriz final de distancias minimas (INF = inalcanzable):");
        System.out.printf("%12s", "");
        for (int j = 1; j <= n; j++) {
            System.out.printf("%12d", j);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%12d", i + 1);
            for (int j = 0; j < n; j++) {
                long distancia = distancias[i][j];
                System.out.printf("%12s", distancia == Grafo.INFINITO ? "INF" : Long.toString(distancia));
            }
            System.out.println();
        }
    }
}
