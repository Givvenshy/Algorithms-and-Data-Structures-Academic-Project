public class ConjuntosDisjuntos {
    private final int[] padre;
    private final int[] rango;

    public ConjuntosDisjuntos(int cantidadVertices) {
        padre = new int[cantidadVertices + 1];
        rango = new int[cantidadVertices + 1];
        for (int i = 1; i <= cantidadVertices; i++) {
            padre[i] = i;
        }
    }

    public int encontrar(int vertice) {
        if (padre[vertice] != vertice) {
            padre[vertice] = encontrar(padre[vertice]);
        }
        return padre[vertice];
    }

    public boolean unir(int a, int b) {
        int raizA = encontrar(a);
        int raizB = encontrar(b);
        if (raizA == raizB) {
            return false;
        }
        if (rango[raizA] < rango[raizB]) {
            padre[raizA] = raizB;
        } else if (rango[raizA] > rango[raizB]) {
            padre[raizB] = raizA;
        } else {
            padre[raizB] = raizA;
            rango[raizA]++;
        }
        return true;
    }
}
