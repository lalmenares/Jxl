
package utils;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
//This class is made by @Michel Guerrero java software developer.

public class XLSHandler {
	// Aqui declaro un objeto de tipo Workbook para acceder a la informacion
	// del excel pero solo para su visualizacion
	Workbook xls;

	// El objeto WritableWorkbook es muy parecido al objeto anterior
	// la diferencia radica en que este sera el que modifique el excel
	WritableWorkbook wxls;

	// Aqui se declara la hoja de excel tipo lectura
	Sheet sheet;
	// Aqui se declara la hoja de excel que permite modificar su conteniedo.
	WritableSheet wsheet;
	// Url del fichero excel
	String file;

	/**
	 * En el constructor de la clase el �nico parametro necesario es la url del
	 * fichero excel, importante: no debe tener http, solo la cadena con la
	 * ubicacion en el ordenador
	 * 
	 * @param file
	
	public XLSHandler(URI uri) {
		this.file = uri;
		try {
			xls = Workbook.getWorkbook(new File(uri));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */
	
	public XLSHandler(String file) {
		this.file = file;
		try {
			xls = Workbook.getWorkbook(new File(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public XLSHandler(URL resource) {
		this(resource.getPath());
	}

	/**
	 * Este metodo me devuelve un objeto del tipo hoja dado el indice de dicha
	 * hoja
	 * 
	 * @param hoja
	 * @return
	 */
	private Sheet getHoja(int hoja) {
		sheet = xls.getSheet(hoja);
		return sheet;
	}

	/**
	 * Este metodo el una sobreesctriruta del anterior, devolviendo la Hoja,
	 * dado su nombre
	 * 
	 * @param hoja
	 *            Hoja del documento excel
	 * @return
	 */
	private Sheet getHoja(String hoja) {
		sheet = xls.getSheet(hoja);
		return sheet;
	}

	/**
	 * El metodo getColum, devuelve la pocision de la columna de una hoja
	 * determinada, dado el texto que tiene en la primera fila considerandolo su
	 * nombre.
	 * 
	 * @param hoja:
	 *            Hoja del documento excel
	 * @param nomColumn:
	 *            Texto que aparece en la primera celda de la columna
	 * @ return
	 */
	public int getColum(int hoja, String nomColumn) {
		sheet = getHoja(hoja);
		int columnPost = 0;
		for (int i = 0; i < sheet.getColumns(); i++) {
			Cell a1 = sheet.getCell(i, 0);
			String label = a1.getContents();
			if (label.equalsIgnoreCase(nomColumn)) {
				columnPost = i;
			}
		}
		return columnPost;
	}

	/**
	 * Este metodo devuelve un arreglo de cadenas de caracteres de la columna
	 * especificado por el parametro columna que en este caso sera el texto que
	 * aparece en la primera celda de la columna ademas hay que especificar la
	 * hoja del que que se desea extraer la informacion
	 * 
	 * @param hoja
	 * @param columna
	 * @return
	 */
	public String[] DevArregloDatos(int hoja, String columna) {
		int pos = getColum(0, columna);
		Cell[] celdas = sheet.getColumn(pos);
		String[] datos = new String[celdas.length - 1];

		for (int i = 1; i < datos.length + 1; i++) {
			datos[i - 1] = celdas[i].getContents().toString();

		}
		return datos;
	}

	/**
	 * Este metodo se encarga de insertar un dato usando el Workbook que creamos
	 * que permite modificar el documento excel, a este metodo se le pasara como
	 * parametros, el valor que desee insertar, asi como la fila y la columna de
	 * donde se quiere insertar.
	 * 
	 * @param valor
	 * @param columna
	 * @param fila
	 * @throws Exception
	 */
	public void InsertarDato(String valor, int columna, int fila) throws Exception {
		wxls = Workbook.createWorkbook(new File(file), xls);
		xls.close();
		wsheet = wxls.getSheet(0);
		jxl.write.Label number = new jxl.write.Label(columna, fila, valor);
		wsheet.addCell(number);
		wxls.write();
		wxls.close();

	}

	/**
	 * Este metodo crea un encabezado justo despues de la ultima columna, fue
	 * pensado para crear por ejemplo la etiqueta de la columna flag o de
	 * cualquier otra columna que se desee crear, este metodo hace uso del
	 * metodo anterior.
	 * 
	 * @param nFlag
	 * @throws Exception
	 */
	public void CrearLblFlag(int hoja, String nFlag) throws Exception {
		sheet = getHoja(hoja);
		if (getColumnByValue(hoja, nFlag) == -1)
			InsertarDato(nFlag, sheet.getColumns(), 0);
	}

	/**
	 * Este metodo devuelve el �nndice de una columna dado un dato cualesquiera
	 * que se encuentre en la hoja de excel que también se dara como parametro.
	 * Nota: en caso de que no se encuentre dicho dato, el m�todo devolvera el
	 * valor -1
	 * 
	 * @param valor
	 * @return
	 */
	public int getColumnByValue(int Hoja, String valor) {
		sheet = getHoja(Hoja);
		int columnPost = -1;
		for (int i = 0; i < sheet.getColumns(); i++) {
			Cell[] fila = sheet.getColumn(i);
			for (int j = 0; j < fila.length; j++) {
				Cell a1 = sheet.getCell(i, j);
				String label = a1.getContents();
				if (label.equalsIgnoreCase(valor)) {
					columnPost = i;
				}
			}
		}
		return columnPost;
	}

	/**
	 * Este m�todo devuelve el �ndice de una fila dado un dato cualesquiera que
	 * se encuentre en la hoja de excel que tambi�n se dar� como parametro.
	 * Nota: en caso de que no se encuentre dicho dato, el m�todo devolver� el
	 * valor -1
	 * 
	 * @param valor
	 * @return
	 */
	public int getFilaByValue(String valor) {
		sheet = getHoja(0);
		int columnPost = 0;
		for (int i = 0; i < sheet.getColumns(); i++) {
			Cell[] fila = sheet.getColumn(i);
			for (int j = 0; j < fila.length; j++) {
				Cell a1 = sheet.getCell(i, j);
				String label = a1.getContents();
				if (label.equalsIgnoreCase(valor)) {
					columnPost = j;
				}
			}
		}
		return columnPost;
	}

	/**
	 * Este m�todo devuelve un arreglo bidimencional de todos los datos de una
	 * hoja dada
	 * 
	 * @param hoja
	 * @return
	 */
	public String[][] DevTodosDatos(int hoja) {
		sheet = getHoja(hoja);
		String[][] arrDatos = new String[sheet.getRows()][sheet.getColumns()];
		for (int i = 0; i < sheet.getRows(); i++) {
			for (int j = 0; j < sheet.getColumns(); j++) {
				Cell a1 = sheet.getCell(i, j);
				String label = a1.getContents();
				arrDatos[i][j] = label;
			}
		}
		return arrDatos;
	}

}
