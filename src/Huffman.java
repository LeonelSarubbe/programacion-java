// Proyecto académico: Programación y Algoritmos en Java
// Autor: Leonel Sarubbe
// Tema: Compresión de datos mediante Huffman

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Clase principal del programa.
public class Huffman {

    // Clase Nodo.
    // Representa cada elemento del árbol de Huffman.
    static class Nodo implements Comparable<Nodo> {

        char caracter;
        int frecuencia;

        Nodo izquierdo;
        Nodo derecho;

        // Constructor para un nodo.
        Nodo(char caracter, int frecuencia) {

            this.caracter = caracter;
            this.frecuencia = frecuencia;

            this.izquierdo = null;
            this.derecho = null;
        }

        // Constructor para un nodo interno del árbol.
        Nodo(int frecuencia, Nodo izquierdo, Nodo derecho) {

            this.caracter = '\0';
            this.frecuencia = frecuencia;

            this.izquierdo = izquierdo;
            this.derecho = derecho;
        }

        // Permite ordenar los nodos según su frecuencia.
        @Override
        public int compareTo(Nodo otro) {

            return this.frecuencia - otro.frecuencia;
        }
    }

    // Calcula cuántas veces aparece cada carácter.
    public static Map<Character, Integer> calcularFrecuencias(
            String texto) {

        Map<Character, Integer> frecuencias = new HashMap<>();

        for (char caracter : texto.toCharArray()) {

            if (frecuencias.containsKey(caracter)) {

                frecuencias.put(
                    caracter,
                    frecuencias.get(caracter) + 1
                );

            } else {

                frecuencias.put(caracter, 1);
            }
        }

        return frecuencias;
    }

    // Construye el árbol de Huffman.
    public static Nodo construirArbol(
            Map<Character, Integer> frecuencias) {

        PriorityQueue<Nodo> cola = new PriorityQueue<>();

        // Creamos un nodo por cada carácter.
        for (Map.Entry<Character, Integer> entrada
                : frecuencias.entrySet()) {

            Nodo nodo = new Nodo(
                entrada.getKey(),
                entrada.getValue()
            );

            cola.add(nodo);
        }

        // Combinamos los nodos de menor frecuencia
        // hasta formar el árbol completo.
        while (cola.size() > 1) {

            Nodo izquierdo = cola.poll();
            Nodo derecho = cola.poll();

            Nodo nuevo = new Nodo(
                izquierdo.frecuencia + derecho.frecuencia,
                izquierdo,
                derecho
            );

            cola.add(nuevo);
        }

        return cola.poll();
    }

    // Genera los códigos recorriendo el árbol.
    public static void generarCodigos(
            Nodo nodo,
            String codigo,
            Map<Character, String> codigos) {

        // Si el nodo es una hoja, guardamos su código.
        if (nodo.izquierdo == null
                && nodo.derecho == null) {

            codigos.put(nodo.caracter, codigo);

            return;
        }

        // Al ir hacia la izquierda agregamos 0.
        generarCodigos(
            nodo.izquierdo,
            codigo + "0",
            codigos
        );

        // Al ir hacia la derecha agregamos 1.
        generarCodigos(
            nodo.derecho,
            codigo + "1",
            codigos
        );
    }

    // Codifica el texto utilizando los códigos obtenidos.
    public static String codificar(
            String texto,
            Map<Character, String> codigos) {

        StringBuilder resultado = new StringBuilder();

        for (char caracter : texto.toCharArray()) {

            resultado.append(codigos.get(caracter));
        }

        return resultado.toString();
    }

    public static void main(String[] args) {

        // Texto que queremos codificar.
        String texto = "hola mundo";

        System.out.println("Texto original:");
        System.out.println(texto);

        // Calculamos la frecuencia de cada carácter.
        Map<Character, Integer> frecuencias =
                calcularFrecuencias(texto);

        System.out.println("\nFrecuencias:");

        for (Map.Entry<Character, Integer> entrada
                : frecuencias.entrySet()) {

            System.out.println(
                "'" + entrada.getKey()
                + "' = "
                + entrada.getValue()
            );
        }

        // Construimos el árbol de Huffman.
        Nodo raiz = construirArbol(frecuencias);

        // Creamos un mapa para almacenar los códigos.
        Map<Character, String> codigos = new HashMap<>();

        // Generamos los códigos.
        generarCodigos(
            raiz,
            "",
            codigos
        );

        System.out.println("\nCódigos de Huffman:");

        for (Map.Entry<Character, String> entrada
                : codigos.entrySet()) {

            System.out.println(
                "'" + entrada.getKey()
                + "' = "
                + entrada.getValue()
            );
        }

        // Codificamos el mensaje original.
        String mensajeCodificado =
                codificar(texto, codigos);

        System.out.println("\nMensaje codificado:");
        System.out.println(mensajeCodificado);
    }
}
