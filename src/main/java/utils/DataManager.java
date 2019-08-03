package utils;
//This class is made by Liudmila Sanchez QA developer test
//Esta clase se encarga de escribir y leer en el excel
public class DataManager {

	XLSHandler excel;
        //Declaramos un constructor en el que se obtiene el formulario con el que se desea trabajar
        //hay que indicarle la direccion donde se encuentra
	public DataManager() {
		excel = new XLSHandler("src\\main\\java\\utils\\formulario.xls");
		
	}
        //Con este metodo dada una posicion y el nombre de la columna se obtiene el dado.
	public String getDato(String columName, int pos) {

		String[] columna = excel.DevArregloDatos(1, columName);
		System.out.println(columna[pos].toString());
		return columna[pos].toString();
	}
        //Con este metodo con un dato de tipo string y conociendo la columna y la posicion se escribe en el fichero
	public void printDato(String dato, int columna, int pos) throws Exception {
		excel.InsertarDato("dato", columna, pos);
	}

}
