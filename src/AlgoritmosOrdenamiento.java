// Proyecto académico: Programación y Algoritmos en Java
// Autor: Leonel Sarubbe
// Tema: Algoritmos de ordenamiento

public class AlgoritmosOrdenamiento {

    // Método que implementa el algoritmo de Bubble Sort.
    public static void bubbleSort(int[] arreglo) {

        // Recorremos el arreglo varias veces.
        for (int i = 0; i < arreglo.length - 1; i++) {

            // Comparamos elementos consecutivos.
            for (int j = 0; j < arreglo.length - 1 - i; j++) {

                // Si el elemento actual es mayor que el siguiente,
                // intercambiamos sus posiciones.
                if (arreglo[j] > arreglo[j + 1]) {

                    int auxiliar = arreglo[j];

                    arreglo[j] = arreglo[j + 1];

                    arreglo[j + 1] = auxiliar;
                }
            }
        }
    }

    // Método para mostrar los elementos del arreglo.
    public static void mostrarArreglo(int[] arreglo) {

        for (int i = 0; i < arreglo.length; i++) {

            System.out.print(arreglo[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        // Creamos un arreglo con valores desordenados.
        int[] numeros = {50, 20, 80, 10, 40, 30};

        System.out.println("Arreglo original:");

        mostrarArreglo(numeros);

        // Ordenamos el arreglo utilizando Bubble Sort.
        bubbleSort(numeros);

        System.out.println("Arreglo ordenado:");

        mostrarArreglo(numeros);
    }
}
