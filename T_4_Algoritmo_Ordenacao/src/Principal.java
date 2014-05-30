public class Principal {

	public static void main(String[] args) {
		// METODO MERGE ====================================
		Ordenar mergeSort = new Ordenar();

		mergeSort.setPrintArray(false);
		mergeSort.Print();

		mergeSort.mergeSort(mergeSort.getArrayOrd());
		mergeSort.setPrintArray(true);
		System.out.println("MÉTODO USADO: MERGE SORT");
		mergeSort.Print();
		System.out.println("");
		System.out.println("");

		// METODO SELECTION ====================================
		Ordenar selectionSort = new Ordenar();

		selectionSort.setPrintArray(false);
		selectionSort.Print();

		selectionSort.selectionSort(selectionSort.getArrayOrd());
		selectionSort.setPrintArray(true);
		System.out.println("MÉTODO USADO:SELECTION SORT");
		selectionSort.Print();
		System.out.println("");
		System.out.println("");
	}

}
