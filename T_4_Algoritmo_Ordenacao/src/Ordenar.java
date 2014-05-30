public class Ordenar {
	private final int tam = 30;
	private final int randomico = 20;
	private int[] array = new int[tam];
	private int[] arrayOrd = new int[tam];
	private boolean printArrayOrd;
	private int compara;

	public boolean printArray() {
		return printArrayOrd;
	}

	public void setPrintArray(boolean printArrayOrd) {
		this.printArrayOrd = printArrayOrd;
	}

	public int[] getArray() {
		return array;
	}

	public int[] getArrayOrd() {
		return arrayOrd;
	}

	public Ordenar() {
		for (int i = 0; i < tam; i++) {
			array[i] = (int) (Math.random() * randomico);
			arrayOrd[i] = array[i];
		}
		printArrayOrd = false;
		compara = 0;
	}

	public void Print() {
		for (int i = 0; i < tam; i++) {
			if (printArrayOrd) {
				if (i == 0)
					System.out.println("ARRAY ORDENADO:");

				System.out.print(arrayOrd[i]);
			} else {
				if (i == 0)
					System.out.println("ARRAY DESORDENADO:");
				System.out.print(array[i]);
			}

			if (i < (tam - 1))
				System.out.print(" - ");
		}

		System.out.println("");
		if (printArrayOrd)
			System.out.println("Nº de Comparações: " + compara);
		System.out.println("");
	}

	// METODO MERGE
	public void mergeSort(int[] array) {
		if (array.length > 1) {
			int[] esquerda = metadeEsquerda(array);
			int[] direita = metadeDireita(array);

			mergeSort(esquerda);
			mergeSort(direita);

			merge(array, esquerda, direita);
		}
	}

	private int[] metadeEsquerda(int[] array) {
		int meio = array.length / 2;
		int[] esquerda = new int[meio];

		for (int i = 0; i < meio; i++) {
			esquerda[i] = array[i];
		}

		return esquerda;
	}

	private int[] metadeDireita(int[] array) {
		int meio1 = array.length / 2;
		int meio2 = array.length - meio1;
		int[] direita = new int[meio2];

		for (int i = 0; i < meio2; i++) {
			direita[i] = array[i + meio1];
		}

		return direita;
	}

	private void merge(int[] resultado, int[] esquerda, int[] direita) {
		int i1 = 0;
		int i2 = 0;

		for (int i = 0; i < resultado.length; i++) {
			compara++;
			if (i2 >= direita.length
					|| (i1 < esquerda.length && esquerda[i1] <= direita[i2])) {
				resultado[i] = esquerda[i1];
				i1++;
			} else {
				resultado[i] = direita[i2];
				i2++;
			}
		}
	}

	// METODO SELECTION
	public void selectionSort(int[] array) {
		int menor, i_Menor;

		for (int i = 0; i < array.length - 1; i++) {
			menor = array[i];
			i_Menor = i;

			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < menor) {
					compara++;
					menor = array[j];
					i_Menor = j;
				}
			}

			array[i_Menor] = array[i];
			array[i] = menor;
		}
	}

}
