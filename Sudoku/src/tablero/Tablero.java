package tablero;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tablero {
	public final static int FILAS = 9;
	public final static int COLUMNAS = 9;
	private List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	private int[][] tablero;

	// CONSTRUCTORES

	public Tablero() {
		this.tablero = new int[FILAS][COLUMNAS];
		generarSudoku(tablero);
		mostrarSudoku();
	}

	// GETTERS

	public int[][] getTablero() {
		return tablero;
	}

	// TOSTRING

	@Override
	public String toString() {
		return "Tablero [tablero=" + tablero + "]";
	}

	// METODOS

	public boolean generarSudoku(int[][] tablero) {
		int fila = -1;
		int columna = -1;
		boolean estaCompleta = true;

		outerloop: for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				if (tablero[i][j] == 0) {
					fila = i;
					columna = j;
					estaCompleta = false;
					break outerloop;
				}
			}
		}

		if (estaCompleta) {
			return true;
		}

		for (int k = 0; k < 9; k++) {
			Collections.shuffle(numeros);

			if (esSegura(fila, columna, numeros.get(k), tablero)) {
				tablero[fila][columna] = numeros.get(k);

				if (generarSudoku(tablero)) {
					return true;
				} else {
					tablero[fila][columna] = 0; 
				}
			}
		}
		return false;
	}

	public boolean resolverSudoku(int[][] tablero) {
		int fila = -1;
		int columna = -1;
		boolean estaCompleta = true;

		outerloop: for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				if (tablero[i][j] == 0) {
					fila = i;
					columna = j;
					estaCompleta = false;
					break outerloop;
				}
			}
		}

		if (estaCompleta) {
			return true;
		}

		for (int k = 1; k <= 9; k++) {

			if (esSegura(fila, columna, k, tablero)) {
				tablero[fila][columna] = k;

				if (resolverSudoku(tablero)) {
					return true;
				} else {
					tablero[fila][columna] = 0; // se reemplaza
				}
			}
		}
		return false;
	}

	public boolean existeEnFila(int fila, int valor, int[][] tablero) {
		for (int j = 0; j < COLUMNAS; j++) {

			if (tablero[fila][j] == valor) {
				return true;
			}
		}
		return false;
	}

	public boolean existeEnColumna(int columna, int valor, int[][] tablero) {
		for (int i = 0; i < FILAS; i++) {

			if (tablero[i][columna] == valor) {
				return true;
			}
		}
		return false;
	}

	public boolean existeEnSubmatriz(int fila, int columna, int valor, int tablero[][]) {
		int sqrt = (int) Math.sqrt(tablero.length);
		int filaInicioSubmatriz = fila - fila % sqrt;
		int columnaInicioSubmatriz = columna - columna % sqrt;

		for (int i = filaInicioSubmatriz; i < filaInicioSubmatriz + sqrt; i++) {
			for (int j = columnaInicioSubmatriz; j < columnaInicioSubmatriz + sqrt; j++) {

				if (tablero[i][j] == valor) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean esSegura(int fila, int columna, int valor, int[][] tablero) {
		if (existeEnFila(fila, valor, tablero) || existeEnColumna(columna, valor, tablero)
				|| existeEnSubmatriz(fila, columna, valor, tablero)) {
			return false;
		} else {
			return true;
		}
	}

	public void mostrarSudoku() {

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				System.out.print("[" + tablero[i][j] + "]");
			}
			System.out.println("");
		}
	}
}
