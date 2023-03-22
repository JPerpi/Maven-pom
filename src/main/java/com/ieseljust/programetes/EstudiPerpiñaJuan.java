package com.ieseljust.programetes;

import java.util.ArrayList;

public class EstudiPerpiñaJuan {

    /**
     * Pregunta el numero de jueces y cantantes y crea la matriz
     *
     * @return Devuelve la matriz llena de 0
     */
    static int[][] creaTaulerDeVots() {
        int qjueces = Teclat.lligInt("�Cuantos jueces? ", 1);
        int qcantantes = Teclat.lligInt("�Cuantos cantantes? ", 8);

        int tablaVotos[][] = new int[qjueces + 1][qcantantes + 1];

        return tablaVotos;
    }

    /**
     * Hace un for para obtener la posicion del juez que va a votar
     *
     * @param tablaVotos Es la matriz que hemos realizado con la funci�n
     * creaTaulerDeVots
     */
    static void fesVotacions(int[][] tablaVotos) {

        for (int juez = 1; juez < tablaVotos.length; juez++) {
            votaJutge(tablaVotos, juez);
        }
    }

    /**
     * Muestra el juez que va a votar y llama a la funci�n votaJutgeACantant
     *
     * @param tablaVotos Matriz donde se van a colocar los votos
     * @param numJuez El juez que va a votar
     */
    static void votaJutge(int[][] tablaVotos, int numJuez) {

        System.out.println("El juez " + numJuez);
        for (int orden = 1; orden <= 8; orden++) {
            votaJutgeACantant(tablaVotos, numJuez, orden);
        }
    }

    /**
     * Pregunta para que cantante son los votos y los guarda en la posicion del
     * juez y cantante de la matriz
     *
     * @param tablaVotos Matriz donde se colocaran los votos
     * @param numJuez El juez que est� votando
     * @param orden Es la posici�n que nos indica cuantos puntos va a obtener el
     * cantante
     */
    static void votaJutgeACantant(int[][] tablaVotos, int numJuez, int orden) {

        int cantante = Teclat.lligInt("- " + puntsSegonsOrdre(orden) + " puntos para el cantante: ", 1, tablaVotos[1].length - 1);
        while (tablaVotos[numJuez][cantante] != 0) {
            System.out.println("Ya has votado a este cantante.");
            cantante = Teclat.lligInt("- " + puntsSegonsOrdre(orden) + " puntos para el cantante: ", 1, tablaVotos[1].length - 1);
        }
        tablaVotos[numJuez][cantante] = puntsSegonsOrdre(orden);
    }

    /**
     * Indica los puntos seg�n el parametro int introducido
     *
     * @param orden Parametro que indica el case en el switch
     * @return Devuelve los puntos que corresponden seg�n el parametro
     * introducido
     */
    static int puntsSegonsOrdre(int orden) {

        int puntos = 0;
        switch (orden) {
            case 1:
                puntos = 2;
                break;
            case 2:
                puntos = 4;
                break;
            case 3:
                puntos = 5;
                break;
            case 4:
                puntos = 6;
                break;
            case 5:
                puntos = 7;
                break;
            case 6:
                puntos = 8;
                break;
            case 7:
                puntos = 10;
                break;
            case 8:
                puntos = 12;
                break;
        }
        return puntos;
    }

    /**
     * Muestra la matriz de forma ordenada
     *
     * @param puntuaciones Matriz donde est�n colocados los puntos de los
     * cantantes
     * @param canciones Array de strings que nos indica el nombre de la canci�n
     */
    static void mostraPuntuacions(int[][] puntuaciones, ArrayList<String> canciones) {
        System.out.printf("%-25s", " ");
        for (int i = 1; i < puntuaciones.length; i++) {
            System.out.printf("%-5s", "(" + i + ")");
        }

        System.out.println();

        for (int i = 1; i < puntuaciones[1].length; i++) {
            System.out.printf("%-5s", "(" + i + ")");
            if (canciones.get(i - 1).length() > 20) {
                String grancancion = canciones.get(i - 1);
                System.out.printf("%-20s" + "  ", canciones.get(i - 1).substring(0, 18));
            } else {
                System.out.printf("%-20s", canciones.get(i - 1));
            }

            for (int j = 1; j < puntuaciones.length; j++) {
                System.out.printf("%-5s", puntuaciones[j][i]);
            }

            System.out.println();
        }
    }

    /**
     * Nos va a dar la posici�n del cantante ganador
     *
     * @param tablaVotos matriz donde se encuentran los cantantes y sus puntos
     * @return Posici�n del ganador
     */
    static int guanyador(int[][] tablaVotos) {
        int max = puntsCantant(tablaVotos, 1);
        int ganador = 1;
        for (int cantante = 1; cantante < tablaVotos[1].length; cantante++) {
            if (max < puntsCantant(tablaVotos, cantante)) {
                max = puntsCantant(tablaVotos, cantante);
                ganador = cantante;
            }
        }
        return ganador;
    }

    /**
     * Suma los puntos de cada fila de cantantes
     *
     * @param tablaVotos Matriz donde est�n los puntos de los cantantes
     * @param ncantante Posici�n del cantante en la matriz
     * @return La suma de los puntos de la posici�n del cantante
     */
    static int puntsCantant(int[][] tablaVotos, int ncantante) {
        int puntostotales = 0;
        for (int juez = 1; juez < tablaVotos.length; juez++) {
            puntostotales += tablaVotos[juez][ncantante];
        }
        return puntostotales;
    }

    /**
     * Hace una linia de * igual de larga que los puntos obtenidos por el
     * ganador
     *
     * @param cantidad El n�mero de puntos del ganador
     */
    static void liniaPunts(int cantidad) {

        if (cantidad == 0) {
            System.out.println("");
            System.out.println("");
        } else {
            System.out.print("*");
            liniaPunts(cantidad - 1);
        }
    }

    /**
     * Busca si ha habido alg�n cantante sin votos
     *
     * @param tablaVotos Matriz donde se encuentran los puntos de los cantantes
     * @return Un True si ha encontrado cantantes sin votos y False si todos
     * tienen votos
     */
    static boolean hiHaNoVotats(int[][] tablaVotos) {
        boolean noVotados = false;
        for (int cantante = 1; cantante < tablaVotos[1].length; cantante++) {
            if (puntsCantant(tablaVotos, cantante) == 0) {
                noVotados = true;
            }
        }
        return noVotados;
    }

    /**
     * Nos muestra que tipo de cantante �s seg�n la puntuaci�n
     *
     * @param tablaVotos Matriz donde se encuentran los puntos
     * @param ncantante Posici�n del cantante en la matriz
     * @return Un string seg�n la puntuaci�n obtenida
     */
    static String tipusCantant(int[][] tablaVotos, int ncantante) {

        int puntos = puntsCantant(tablaVotos, ncantante);
        String tipo = "";
        if (puntos < 11) {
            tipo = "Es un mal cantante";
        }
        if (puntos > 10 && puntos < 21) {
            tipo = "Es un cantante regular";
        }
        if (puntos > 20 && puntos < 31) {
            tipo = "Es un buen cantante";
        }
        if (puntos > 30) {
            tipo = "Es un crack";
        }
        return tipo;
    }

    /**
     * Guarda el nombre de las canciones
     *
     * @param ncantants El total de cantantes
     * @return Un Array con las nombres de las canciones
     */
    static ArrayList nombreCanciones(int ncantants) {

        ArrayList<String> canciones = new ArrayList();
        for (int posiciones = 1; posiciones < ncantants; posiciones++) {
            String cancion = Teclat.lligString("�Como se llama la canci�n " + posiciones + "? ");
            canciones.add(cancion);
        }

        return canciones;
    }

    public static void main(String[] args) {

        // Inicializamos la matriz y la llamamos a la funci�n creaTaulerDeVots
        int[][] votaciones;
        votaciones = creaTaulerDeVots();

        /* Obtenemos la cantidad de cantantes y la guardamos en una variable para
        luego pasarselo a la funci�n nombreCanciones */
        int ncantants = votaciones[1].length;
        ArrayList<String> canciones;
        canciones = nombreCanciones(ncantants);

        //Comenzamos la votaci�n
        fesVotacions(votaciones);
        System.out.println("");

        //Mostramos la matriz 
        mostraPuntuacions(votaciones, canciones);
        System.out.println("");

        // Guardamos la posici�n del ganados en una variable
        int gcantant = guanyador(votaciones);

        //Mostramos la canci�n ganadora y los puntos que ha obtenido
        System.out.println("El cantante de " + canciones.get(gcantant) + " ha ganado.");
        System.out.println("");
        System.out.println("El cantante de " + canciones.get(gcantant) + " ha conseguido: " + puntsCantant(votaciones, gcantant) + " puntos.");
        System.out.println("");

        //Hacemos un linia de * igual a la cantidad de puntos del ganador
        liniaPunts(puntsCantant(votaciones, guanyador(votaciones)));

        // Mostramos el tipo de cantante seg�n la puntuaci�n obtenida
        for (int cantant = 1; cantant < votaciones[0].length; cantant++) {
            System.out.println("El cantante de " + canciones.get(cantant - 1) + ": " + tipusCantant(votaciones, cantant));
            System.out.println("");
        }
        //Mostramos si ha habido alg�n cantante sn votos
        if (hiHaNoVotats(votaciones)) {
            System.out.println("Algun cantante no ha recibido votos");
        } else {
            System.out.println("Todos los cantantes han recibido votos");
        }
    }
}
