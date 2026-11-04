import java.util.Scanner;

public class Entrada {
    private Entrada() {
    }

    public static int leerEntero(Scanner scanner, String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                int numero = Integer.parseInt(linea);
                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }
            } catch (NumberFormatException e) {
                // Se vuelve a solicitar la entrada.
            }
            System.out.println("Entrada invalida. Ingresa un entero entre " + minimo + " y " + maximo + ".");
        }
    }

    public static long leerPeso(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                long numero = Long.parseLong(linea);
                if (numero >= 0 && numero <= Grafo.MAX_PESO) {
                    return numero;
                }
            } catch (NumberFormatException e) {
                // Se vuelve a solicitar la entrada.
            }
            System.out.println("Peso invalido. Ingresa un entero entre 0 y " + Grafo.MAX_PESO + ".");
        }
    }
}
