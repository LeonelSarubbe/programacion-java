// Proyecto académico: Programación y Algoritmos en Java
// Autor: Leonel Sarubbe
// Tema: Algoritmos de búsqueda

public class Busqueda {

    // Búsqueda lineal.
    // Recorre los elementos uno por uno hasta encontrar el valor.
    public static int busquedaLineal(int[] arreglo, int valor) {

        for (int i = 0; i < arreglo.length; i++) {

            if (arreglo[i] == valor) {
                return i;
            }
        }

        return -1;
    }

    // Búsqueda binaria.
    // Para utilizarla, el arreglo debe estar ordenado.
    public static int busquedaBinaria(int[] arreglo, int valor) {

        int inicio = 0;
        int fin = arreglo.length - 1;

        while (inicio <= fin) {

            int medio = (inicio + fin) / 2;

            if (arreglo[medio] == valor) {
                return medio;
            }

            if (arreglo[medio] < valor) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return -1;
    }

    // Método para mostrar el resultado de una búsqueda.
    public static void mostrarResultado(String metodo, int posicion) {

        if (posicion != -1) {

            System.out.println(
                metodo + ": valor encontrado en la posición " + posicion
            );

        } else {

            System.out.println(
                metodo + ": valor no encontrado"
            );
        }
    }

    public static void main(String[] args) {

        // Arreglo ordenado.
        int[] numeros = {10, 20, 30, 40, 50, 60, 70, 80};

        int valorBuscado = 50;

        // Realizamos una búsqueda lineal.
        int resultadoLineal =
                busquedaLineal(numeros, valorBuscado);

        // Realizamos una búsqueda binaria.
        int resultadoBinario =
                busquedaBinaria(numeros, valorBuscado);

        // Mostramos los resultados.
        mostrarResultado(
                "Búsqueda lineal",
                resultadoLineal
        );

        mostrarResultado(
                "Búsqueda binaria",
                resultadoBinario
        );
    }
}
