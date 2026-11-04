import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Grafo grafo;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = Entrada.leerEntero(SCANNER, "Selecciona una opcion: ", 1, 10);
            switch (opcion) {
                case 1:
                    crearGrafo();
                    break;
                case 2:
                    agregarVertices();
                    break;
                case 3:
                    agregarArista();
                    break;
                case 4:
                    if (hayGrafo()) grafo.mostrar();
                    break;
                case 5:
                    ejecutarDijkstra();
                    break;
                case 6:
                    if (hayGrafo()) FloydWarshall.ejecutar(grafo);
                    break;
                case 7:
                    if (hayGrafo()) Prim.ejecutar(grafo);
                    break;
                case 8:
                    if (hayGrafo()) Kruskal.ejecutar(grafo);
                    break;
                case 9:
                    grafo = null;
                    System.out.println("Grafo borrado. Puedes crear uno nuevo.");
                    break;
                case 10:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    break;
            }
        } while (opcion != 10);
    }

    private static void mostrarMenu() {
        System.out.println("\n========== MENU DE GRAFOS ==========");
        System.out.println("1. Crear un nuevo grafo");
        System.out.println("2. Agregar vertices");
        System.out.println("3. Agregar arista con peso");
        System.out.println("4. Mostrar grafo");
        System.out.println("5. Ejecutar Dijkstra");
        System.out.println("6. Ejecutar Floyd-Warshall");
        System.out.println("7. Ejecutar Prim");
        System.out.println("8. Ejecutar Kruskal");
        System.out.println("9. Reiniciar / borrar grafo");
        System.out.println("10. Salir");
        if (grafo != null) {
            System.out.println("Grafo actual: " + grafo.getCantidadVertices() + " vertices, "
                    + grafo.getCantidadAristas() + " aristas.");
        }
    }

    private static boolean hayGrafo() {
        if (grafo == null) {
            System.out.println("Primero debes crear un grafo (opcion 1).");
            return false;
        }
        return true;
    }

    private static void crearGrafo() {
        int n = Entrada.leerEntero(SCANNER,
                "Numero de vertices (1 a " + Grafo.MAX_VERTICES + "): ",
                1, Grafo.MAX_VERTICES);
        Grafo nuevo = new Grafo(n);
        int maximoAristas = n * (n - 1) / 2;
        int cantidad = Entrada.leerEntero(SCANNER,
                "Numero de aristas iniciales (0 a " + maximoAristas + "): ", 0, maximoAristas);
        for (int i = 1; i <= cantidad; i++) {
            System.out.println("Arista " + i + " de " + cantidad + ":");
            if (!solicitarArista(nuevo)) {
                i--;
            }
        }
        grafo = nuevo;
        System.out.println("Grafo creado correctamente.");
    }

    private static void agregarVertices() {
        if (!hayGrafo()) {
            return;
        }
        int disponibles = Grafo.MAX_VERTICES - grafo.getCantidadVertices();
        if (disponibles == 0) {
            System.out.println("Ya se alcanzo el maximo de vertices.");
            return;
        }
        int cantidad = Entrada.leerEntero(SCANNER,
                "Cuantos vertices deseas agregar (1 a " + disponibles + ")? ", 1, disponibles);
        for (int i = 0; i < cantidad; i++) {
            int nuevo = grafo.agregarVertice();
            System.out.println("Vertice " + nuevo + " agregado.");
        }
    }

    private static void agregarArista() {
        if (!hayGrafo()) {
            return;
        }
        int n = grafo.getCantidadVertices();
        if (grafo.getCantidadAristas() == n * (n - 1) / 2) {
            System.out.println("El grafo ya tiene todas las aristas posibles.");
            return;
        }
        solicitarArista(grafo);
    }

    private static boolean solicitarArista(Grafo destino) {
        if (destino.getCantidadVertices() < 2) {
            System.out.println("Se necesitan al menos dos vertices para agregar una arista.");
            return false;
        }
        int n = destino.getCantidadVertices();
        int origen = Entrada.leerEntero(SCANNER, "Vertice de origen (1 a " + n + "): ", 1, n);
        int fin = Entrada.leerEntero(SCANNER, "Vertice de destino (1 a " + n + "): ", 1, n);
        if (origen == fin) {
            System.out.println("No puedes conectar un vertice consigo mismo.");
            return false;
        }
        if (destino.existeArista(origen, fin)) {
            System.out.println("Esa arista ya existe. No se permiten duplicados.");
            return false;
        }
        long peso = Entrada.leerPeso(SCANNER, "Peso de la arista: ");
        destino.agregarArista(origen, fin, peso);
        System.out.println("Arista agregada: " + origen + " -- " + fin + " (peso " + peso + ")");
        return true;
    }

    private static void ejecutarDijkstra() {
        if (!hayGrafo()) {
            return;
        }
        int origen = Entrada.leerEntero(SCANNER,
                "Vertice inicial para Dijkstra (1 a " + grafo.getCantidadVertices() + "): ",
                1, grafo.getCantidadVertices());
        Dijkstra.ejecutar(grafo, origen);
    }
}
