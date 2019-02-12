package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import modelo.Equipo;
import modelo.Estudiante;
import modelo.Partido;
import modelo.Persona;

public class Ejercicios {
	
	public ArrayList<Equipo> creaClasificacionEquipo(String rutaFicheroPartidos, HashMap<String, Equipo> nombreEquipos){
		ArrayList<Equipo> listadoEquipos = new ArrayList<Equipo>();
		
		HashMap<String, Equipo> hashMapEquipo = new HashMap<String, Equipo>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFicheroPartidos));
			String registro; // 1#1#GIR#0#RVA#0
			while ((registro = fichero.readLine()) != null) {
				String[] arrayRegistro = registro.split("#");
				if (arrayRegistro[3].equals("")) {
					break;
				}
				
				Integer idPartido = Integer.parseInt(arrayRegistro[0]);
				Integer jornada = Integer.parseInt(arrayRegistro[1]);
				String nombreCortoLocal = arrayRegistro[2];
				Integer idEquipoLocal = nombreEquipos.get(nombreCortoLocal).getIdEquipo();
				String nombreLargoEquipoLocal = nombreEquipos.get(nombreCortoLocal).getNombreLargoEquipo();
				Integer golLocal = Integer.parseInt(arrayRegistro[3]);
				
				String nombreCortoVisitante = arrayRegistro[4];
				Integer idEquipoVisitante = nombreEquipos.get(nombreCortoVisitante).getIdEquipo();
				String nombreLargoEquipoVisitante = nombreEquipos.get(nombreCortoVisitante).getNombreLargoEquipo();
				Integer golVisitante = Integer.parseInt(arrayRegistro[5]);
					
				if (!hashMapEquipo.containsKey(nombreCortoLocal)) { // inicializar el hashmap para añadir el key
					Equipo unEquipo = new Equipo(idEquipoLocal, nombreCortoLocal, nombreLargoEquipoLocal,0,0,0,0,0,0);
					hashMapEquipo.put(nombreCortoLocal, unEquipo);
				}
				
				if (!hashMapEquipo.containsKey(nombreCortoVisitante)) { // inicializar el hashmap para añadir el key
					Equipo unEquipo = new Equipo(idEquipoVisitante, nombreCortoVisitante, nombreLargoEquipoVisitante,0,0,0,0,0,0);
					hashMapEquipo.put(nombreCortoVisitante, unEquipo);
				}
				
				Integer golesFavor = hashMapEquipo.get(nombreCortoLocal).getGolesFavor() + golLocal;
				hashMapEquipo.get(nombreCortoLocal).setGolesFavor(golesFavor);
				Integer golesContra = hashMapEquipo.get(nombreCortoLocal).getGolesContra() + golVisitante;
				hashMapEquipo.get(nombreCortoLocal).setGolesContra(golesContra);

				golesFavor = hashMapEquipo.get(nombreCortoVisitante).getGolesFavor() + golLocal;
				hashMapEquipo.get(nombreCortoVisitante).setGolesFavor(golesFavor);
				golesContra = hashMapEquipo.get(nombreCortoVisitante).getGolesContra() + golVisitante;
				hashMapEquipo.get(nombreCortoVisitante).setGolesContra(golesContra); 
				
				Integer victoria = 0; // en el indice 0 del arrayList es donde se almacenan las victorias
				Integer empate = 0; // en el indice 1 del arrayList es donde se almacenan los empates
				Integer derrota = 0;
				Integer puntos = 0 ;
				
				if (golLocal > golVisitante) {
					victoria = hashMapEquipo.get(nombreCortoLocal).getVictorias() + 1;
					hashMapEquipo.get(nombreCortoLocal).setVictorias(victoria);
					derrota = hashMapEquipo.get(nombreCortoVisitante).getDerrotas() + 1;
					hashMapEquipo.get(nombreCortoVisitante).setDerrotas(derrota);
					puntos = hashMapEquipo.get(nombreCortoLocal).getPuntos() + 3;
					hashMapEquipo.get(nombreCortoLocal).setPuntos(puntos);
					
					
					
				} else if (golLocal < golVisitante) {
					victoria = hashMapEquipo.get(nombreCortoVisitante).getVictorias() + 1;
					hashMapEquipo.get(nombreCortoVisitante).setVictorias(victoria);
					derrota = hashMapEquipo.get(nombreCortoLocal).getDerrotas() + 1;
					hashMapEquipo.get(nombreCortoLocal).setDerrotas(derrota);
					puntos = hashMapEquipo.get(nombreCortoVisitante).getPuntos() +3;
					hashMapEquipo.get(nombreCortoVisitante).setPuntos(puntos);
					
					
				} else {
					empate = hashMapEquipo.get(nombreCortoLocal).getEmpates() + 1;
					hashMapEquipo.get(nombreCortoLocal).setEmpates(empate);
					empate = hashMapEquipo.get(nombreCortoVisitante).getEmpates() + 1;
					hashMapEquipo.get(nombreCortoVisitante).setEmpates(empate);
					puntos = hashMapEquipo.get(nombreCortoLocal).getPuntos() + 1;
					hashMapEquipo.get(nombreCortoLocal).setPuntos(puntos);
					puntos = hashMapEquipo.get(nombreCortoVisitante).getPuntos() + 1;
					hashMapEquipo.get(nombreCortoVisitante).setPuntos(puntos);
				}
				System.out.println();
			}
			
			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero. \n");
		} catch (FileNotFoundException e) {
			System.out.println("SALTO EXCEPCION: FileNotFoundException... \n");
		} catch (IOException e) {
			System.out.println("SALTO EXCEPCION: IO Exception... \n");
		} catch (NumberFormatException e) {
			System.out.println("SALTO EXCEPCION: NumberFormatException... \n");
		}
		listadoEquipos = convertirHashEquipo(hashMapEquipo);
		// Collections.sort(listadoEquipos,(o2, o1) -> o1.getPuntos().compareTo(o2.getPuntos()));
		
		Collections.sort( listadoEquipos, new Comparator<Equipo>(){
	        public int compare( Equipo o1, Equipo o2 ) {
	            return (o2.getPuntos().compareTo( o1.getPuntos()));
	        }

        } );
		
		System.out.println(listadoEquipos);
		
		return listadoEquipos;
	}
	

	
	public ArrayList<Equipo> convertirHashEquipo( HashMap<String, Equipo> hashMapEquipo){
		ArrayList<Equipo> arrayListEquipos = new ArrayList<Equipo>();
		
		Set<String> clavesMapaEquipos = hashMapEquipo.keySet();
		for( String clave : clavesMapaEquipos) {
			Equipo unEquipo = hashMapEquipo.get(clave);
			arrayListEquipos.add(unEquipo);
		}
		return arrayListEquipos;
	}
	
	
	
	//12 febrero 2019
	// ordenar el metodo creaClasificacion por puntos 
	// usar el jtable de swing para mostrarlo
	
	/*
	public void ordenarMapaPuntosEquipos(HashMap<String,ArrayList<Integer>> puntosEquipos) {
				
		Set<Entry<String, ArrayList<Integer>>> set = puntosEquipos.entrySet();
        List<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<Map.Entry<String, ArrayList<Integer>>>(set);
        
        //Expresion Lambda //curiosidad por ahora...
        //Collections.sort(list,(o1, o2) -> o1.getValue().compareTo(o2.getValue())); 
         Collections.sort( list, new Comparator<Map.Entry<String, ArrayList<Integer>>>()
        {
	        public int compare( Map.Entry<String, ArrayList<Integer>> o1, Map.Entry<String, ArrayList<Integer>> o2 )
	        {
	            return (o2.getValue().get(3)).compareTo( o1.getValue().get(3));
	        }
        } );
        
        for(Map.Entry<String, ArrayList<Integer>> entry:list){
        	System.out.println(entry.getKey() +" ==== "+ entry.getValue());
        						
        }
	}
	 * */
	
	// 07 febrero 2019
	
	// devolver el nombre largo del equipo seguido de todos sus valores.
	// sacar el nombre largo de los equipos de una coleccion dada (acceder al metodo obtencionListaEquipo)
	// hashmap clave (nomLargoEquipo) valor (resto de datos, puntos, Victorias.Empates.Derrotas, golesAfavor, golesContra.)
	// acumuladore goles a favor goles en contra.
	// HM<String,AL<Integer>> devolverlo.
	// obtener datos para la clasificacion.  
	
	public HashMap<String,ArrayList<Integer>> creaClasificacion(String rutaFicheroPartidos, String rutaFicheroEquipos){
		HashMap<String,ArrayList<Integer>> clasificacion = new HashMap<String,ArrayList<Integer>>();
		HashMap<String, Equipo> nombreEquipos = this.obtencionMapaEquipos(rutaFicheroEquipos);
		HashMap<String, ArrayList<Integer>> vicDerrEmpEquipos = this.resultadoLigaEquipos(rutaFicheroPartidos);
		HashMap<String, Integer> puntosEquipos = this.generaPuntosEquipos(vicDerrEmpEquipos);
		HashMap<String, ArrayList<Integer>> golesFavorContraEquipos = this.sumarGolesEquipos(rutaFicheroPartidos);
	
		Set<String> conjuntoClavesNombreCorto = nombreEquipos.keySet();
		for( String claveEquipo : conjuntoClavesNombreCorto) {
			Equipo unEquipo = nombreEquipos.get(claveEquipo);
			String nombreLargoUnEquipo = unEquipo.getNombreLargoEquipo();
			// cada celda ira por este orden -> puntos, victorias, empates, derrotas, golesFavor, golesContra.
			
			ArrayList<Integer> listadoClasificacion = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0)); 
			clasificacion.put(nombreLargoUnEquipo, listadoClasificacion);
		
			Set<Entry<String, Equipo>> conjuntoClavesNombreEquipos = nombreEquipos.entrySet();		
			for(  Entry<String, Equipo> claveNombresEquipo : conjuntoClavesNombreEquipos) {
							
				String clave = claveNombresEquipo.getKey();
				String valor = claveNombresEquipo.getValue().getNombreLargoEquipo();
				
					if(nombreLargoUnEquipo.equals(valor)) {
						Integer puntosUnEquipo = puntosEquipos.get(clave);
						listadoClasificacion.set(0, puntosUnEquipo);
						Integer victorias = vicDerrEmpEquipos.get(clave).get(0);
						Integer empates = vicDerrEmpEquipos.get(clave).get(1);
						Integer derrotas = vicDerrEmpEquipos.get(clave).get(2);
						listadoClasificacion.set(1, victorias);
						listadoClasificacion.set(2, empates);
						listadoClasificacion.set(3, derrotas);
						Integer golesFavor = golesFavorContraEquipos.get(clave).get(0);
						Integer golesContra = golesFavorContraEquipos.get(clave).get(1);
						listadoClasificacion.set(4, golesFavor);
						listadoClasificacion.set(5, golesContra);
					}
			}
		}
		System.out.println(clasificacion);
		return clasificacion;
	}
	
	public HashMap<String, ArrayList<Integer>> sumarGolesEquipos(String rutaFichero) {
		HashMap<String, ArrayList<Integer>> golesEquipos = new HashMap<String, ArrayList<Integer>>();		
		ArrayList<Integer> golesFavorContra;
		
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro; // 1#1#GIR#0#RVA#0
			while ((registro = fichero.readLine()) != null) {
				String[] arrayRegistro = registro.split("#");
				
				if (arrayRegistro[3].equals("")) {
					break;
				}
				//int idPartido = Integer.parseInt(arrayRegistro[0]);
				//int jornada = Integer.parseInt(arrayRegistro[1]);
				String equipoLocal = arrayRegistro[2];
				Integer golLocal = Integer.parseInt(arrayRegistro[3]);
				String equipoVisitante = arrayRegistro[4];
				Integer golVisitante = Integer.parseInt(arrayRegistro[5]);

				if (!golesEquipos.containsKey(equipoLocal)) { // inicializar el hashmap para añadir el key
					// el Arrays.asList convierte un array pasado por parametro a arrayList
					// AL celda 0 goles a favor; celda 1 goles contra
					golesFavorContra = new ArrayList<Integer>(Arrays.asList(0, 0));
					golesEquipos.put(equipoLocal, golesFavorContra);
				}
				if (!golesEquipos.containsKey(equipoVisitante)) { // se crea el arrayList
					golesFavorContra = new ArrayList<Integer>(Arrays.asList(0, 0));
					golesEquipos.put(equipoVisitante, golesFavorContra);
				}

				ArrayList<Integer> golesEquipoLocal = golesEquipos.get(equipoLocal);
				
				Integer golesAFavorEquipoLocal = golesEquipoLocal.get(0);
				Integer sumatoriaGolesAFavorEquipoLocal = golesAFavorEquipoLocal + golLocal;
				Integer golesEnContraEquipoLocal = golesEquipoLocal.get(1);	
				Integer sumatoriaGolesEnContraEquipoLocal = golesEnContraEquipoLocal + golVisitante;
				golesEquipoLocal.set(0, sumatoriaGolesAFavorEquipoLocal);
				golesEquipoLocal.set(1, sumatoriaGolesEnContraEquipoLocal);
				
				ArrayList<Integer> golesEquipoVisitante = golesEquipos.get(equipoVisitante);
				
				Integer golesAFavorEquipoVisitante = golesEquipoVisitante.get(0);
				Integer sumatoriaGolesAFavorEquipoVisitante = golesAFavorEquipoVisitante + golVisitante;
				Integer golesEnContraEquipoVisistante = golesEquipoVisitante.get(1);
				Integer sumatoriaGolesEnContraEquipoVisitante = golesEnContraEquipoVisistante + golLocal;
				golesEquipoVisitante.set(0, sumatoriaGolesAFavorEquipoVisitante);
				golesEquipoVisitante.set(1, sumatoriaGolesEnContraEquipoVisitante);
				
			}
			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero. \n");
		} catch (FileNotFoundException e) {
			System.out.println("SALTO EXCEPCION: FileNotFoundException... \n");
		} catch (IOException e) {
			System.out.println("SALTO EXCEPCION: IO Exception... \n");
		} catch (NumberFormatException e) {
			System.out.println("SALTO EXCEPCION: NumberFormatException... \n");
		}
		return golesEquipos;
	}
	
	
	
	
	//06 febrero 2019
	// ejercicio propuesto:
	// obtener listado ordenado nombres largos equipos y a la derecha los partidos jugados
	// a partir del metodo obtenciónListaEquipos;
	
	public ArrayList<Equipo> equiposListaOrdenadaNombre() {
		ArrayList<Equipo> lista = obtencionListaEquipos("ficheros/equipos.txt");
		
	
		/*
		 //ordenar por nombreLargo
		
		 lista.sort(new Comparator<Equipo>() {
			@Override
			public int compare(Equipo equi1, Equipo equi2) {
				
				return (equi2.getNombreLargoEquipo().compareTo(equi1.getNombreLargoEquipo()));
			}	
		});
		*/
		
		
		/*
		// ordenar por idEquipo comparando primitivo int
		 lista.sort(new Comparator<Equipo>() {
			@Override
			public int compare(Equipo equi1, Equipo equi2) {
				
				if(equi1.getIdEquipo() > equi2.getIdEquipo()) {
					return -1;					
				} else if(equi1.getIdEquipo() < equi2.getIdEquipo()){ 
					return 1;
					}else
					 	return 0;
			}	
		});
		*/
		/*
		// ordenar por idEquipo comparando objetos Integer
		 lista.sort(new Comparator<Equipo>() {
				@Override
				public int compare(Equipo equi1, Equipo equi2) {
					
					return (((Integer)equi1.getIdEquipo()).compareTo(((Integer)equi2.getIdEquipo())));
				}	
			});
		 */
		 lista.sort(null); 
		 
		return lista;
		
	}
	
	
	//hacer la ordenacion por goles a favor y goles en contra tambien
	
	//intentar generar un nuevo objeto ordenado sin perder el que nos dan desordenado  ¿?
	public void ordenarMapaPuntosEquipos(HashMap<String,ArrayList<Integer>> puntosEquipos) {
				
		Set<Entry<String, ArrayList<Integer>>> set = puntosEquipos.entrySet();
        List<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<Map.Entry<String, ArrayList<Integer>>>(set);
        
        //Expresion Lambda //curiosidad por ahora...
        //Collections.sort(list,(o1, o2) -> o1.getValue().compareTo(o2.getValue())); 
        
        
        Collections.sort( list, new Comparator<Map.Entry<String, ArrayList<Integer>>>(){
	        public int compare( Map.Entry<String, ArrayList<Integer>> o1, Map.Entry<String, ArrayList<Integer>> o2 )
	        {
	            return (o2.getValue().get(3)).compareTo( o1.getValue().get(3));
	        }
        } );
        
        for(Map.Entry<String, ArrayList<Integer>> entry:list){
        	System.out.println(entry.getKey()+" ==== "+entry.getValue());
        }
	}
	
	
	
	public HashMap<String, Integer> generaPuntosEquipos(HashMap<String, ArrayList<Integer>> partidos_GEP){
		HashMap<String, Integer> mapaPuntos= new HashMap<String, Integer>();
		
		for (String clave : partidos_GEP.keySet()) { //recorrer el mapa por key
			ArrayList<Integer> datos = partidos_GEP.get(clave);
			Integer puntos = datos.get(0) * 3 + datos.get(1);	
			mapaPuntos.put(clave, puntos);
		}	
		return mapaPuntos;
	}
	
	
	// 31 enero 2019
	// ordenar HashMap por valor
	// por medio de la interfaz java.lang.comparable ---> compareTo
	
	public void muestraPuntosOrdenadosEquipos(HashMap<String, ArrayList<Integer>> resultados) {
		//recorrer el hashmap previamente ordenado por puntos
		HashMap<String, Integer>mapaOrdenadoPuntos = new HashMap<String, Integer>();
		//obrtenemos la lista de claves
		for (String clave : resultados.keySet()) { //recorrer el mapa por key
			ArrayList<Integer> datos = resultados.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);
			// se alimenta el hashmap 
			mapaOrdenadoPuntos.put(clave, puntos);
		}
		//ahora se ordena
		
		ArrayList<Integer> valoresPuntos = new ArrayList<Integer>(mapaOrdenadoPuntos.values());
		Collections.sort(valoresPuntos); //ordena listas
		
		
	}
	
	
	
	// 30 enero 2019
	// ejemplo app de escritorio, pruebal SWING (MVC)
	public void pruebaSWING() {
		JFrame ventana =new JFrame("Mi Primer SWING"); //JFrame clase de javax.swing donde se almacenan las librerias¿?
		JButton boton = new JButton("Dame caña nino!"); // crear boton
		JPanel panel = new JPanel(); // crear panel, ventana
		ventana.add(panel);	
		/*ArrayList<Equipo> equipos = this.obtencionListaEquipos("ficheros/equipos.txt");
		Equipo[] arrayEquipos = equipos.toArray(new Equipo[equipos.size()]);
		JComboBox lista = new JComboBox(arrayEquipos); // 
		panel.add(lista);
		panel.add(boton);
		boton.addActionListener(new ActionListener() { // evento para el botón al pulsar
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(lista.getSelectedItem()); //imprime el dato seleccionado al pulsar el boton
				
			}
		});*/
		
		String[] nombreEquipos = {
				"Nombre Equipo",
				"Puntos",
				"Victorias",
				"Empates",
				"Derrotas",
				"Goles a favor",
				"Goles en Contra"
		};
		
		HashMap<String, ArrayList<Integer>> datosClasificacion = creaClasificacion("ficheros/partidos.txt", "ficheros/equipos.txt");
		
		JTable tablaClasificacion;
		//= new JTable(datosClasificacion,nombreEquipos);
		
		Vector<ArrayList<Integer>>unVector = new Vector<>();
		//alimentar el vector con los datos del arraylist con el for
		
		//tablaClasificacion = new JTable
		ventana.pack(); // 
		ventana.setVisible(true); //  
	}
	
	
	// 29 enero 2019
	
	public void muestraPuntosEquipos(HashMap<String, ArrayList<Integer>> resultados) {
		for (String clave : resultados.keySet()) { //recorrer el mapa por key
			ArrayList<Integer> datos = resultados.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);
			System.out.println(clave + " => " + puntos);
			Collections.sort(datos);
		}
	}
	
	
	// 24 enero 2019

	// metodo que devuelva victorias, empates y derrotas por cada equipo
	// por cada equipo habrá una lista de contadores que representen VICTORIAS,
	// EMPATES, DERROTAS
	//
	public HashMap<String, ArrayList<Integer>> resultadoLigaEquipos(String rutaFichero) {
		HashMap<String, ArrayList<Integer>> resultadoEquipo = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> resultados;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro; // 1#1#GIR#0#RVA#0
			while ((registro = fichero.readLine()) != null) {
				String[] arrayRegistro = registro.split("#");
				if (arrayRegistro[3].equals("")) {
					break;
				}
				int idPartido = Integer.parseInt(arrayRegistro[0]);
				int jornada = Integer.parseInt(arrayRegistro[1]);
				String equipoLocal = arrayRegistro[2];
				int golLocal = Integer.parseInt(arrayRegistro[3]);
				String equipoVisitante = arrayRegistro[4];
				int golVisitante = Integer.parseInt(arrayRegistro[5]);

				if (!resultadoEquipo.containsKey(equipoLocal)) { // inicializar el hashmap para añadir el key
					// el Arrays.asList convierte un array pasado por parametro a arrayList
					resultados = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
					resultadoEquipo.put(equipoLocal, resultados);
				}
				if (!resultadoEquipo.containsKey(equipoVisitante)) { // se crea el arrayList
					resultados = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
					resultadoEquipo.put(equipoVisitante, resultados);
				}

				int victoria = 0; // en el indice 0 del arrayList es donde se almacenan las victorias
				int empate = 0; // en el indice 1 del arrayList es donde se almacenan los empates
				int derrota = 0;

				if (golLocal > golVisitante) {
					victoria = resultadoEquipo.get(equipoLocal).get(0) + 1;
					resultadoEquipo.get(equipoLocal).set(0, victoria);
					derrota = resultadoEquipo.get(equipoVisitante).get(2) + 1;
					resultadoEquipo.get(equipoVisitante).set(2, derrota);

				} else if (golLocal < golVisitante) {
					victoria = resultadoEquipo.get(equipoVisitante).get(0) + 1;
					resultadoEquipo.get(equipoVisitante).set(0, victoria);
					derrota = resultadoEquipo.get(equipoLocal).get(2) + 1;
					resultadoEquipo.get(equipoLocal).set(2, derrota);
				} else {
					empate = resultadoEquipo.get(equipoLocal).get(1) + 1;
					resultadoEquipo.get(equipoLocal).set(1, empate);
					empate = resultadoEquipo.get(equipoVisitante).get(1) + 1;
					resultadoEquipo.get(equipoVisitante).set(1, empate);
				}
			}
			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero. \n");
		} catch (FileNotFoundException e) {
			System.out.println("SALTO EXCEPCION: FileNotFoundException... \n");
		} catch (IOException e) {
			System.out.println("SALTO EXCEPCION: IO Exception... \n");
		} catch (NumberFormatException e) {
			System.out.println("SALTO EXCEPCION: NumberFormatException... \n");
		}
		return resultadoEquipo;
	}

	// 23 enero 2019

	// cogiendo el fichero partidos, crear metodo para calcular los puntos de cada
	// equipo (190partidos jugados)
	// metodo detectar cuantos partidos se han jugado
	// recorrer el fichero partidos.txt hasta que se encuentre el primer partido no
	// jugado (el que no tiene numero en goles)

	public void mostrarNumeroPartidosJugados(String rutaFichero) {
		try {
			ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			int cont = 0;
			while ((registro = fichero.readLine()) != null) { // plantilla para la lectura de todos los ficheros de txts
				String[] unaLinea = registro.split("#");

				if (!unaLinea[3].equals("")) { // el equals compara objetos y cadenas
					Integer.parseInt(unaLinea[3]);
					cont++;
				} else {
					break;
				}
				System.out.println(cont);
			}

			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero.");
		} catch (FileNotFoundException e) {
			System.out.println("FICHERO NO ENCONTRADO...");
		} catch (IOException e) {
			System.out.println("IO Exception");
		} /*
			 * catch (NumberFormatException e) {
			 * System.out.println("FORMATO DE NUMERO INCORRECTO..."); }
			 */
	}
	/***************************************** metodos propios ***********************************************/

	public static HashMap<String, Integer> conteoPuntosPorEquipo(String rutaFichero) {
		HashMap<String, Integer> resultados = new HashMap<String, Integer>();
		try {

			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] arrayRegistro = registro.split("#");
				if (arrayRegistro[3].equals("")) {
					break;
				}
				String idPartido = arrayRegistro[0];
				String jornada = arrayRegistro[1];
				String equipoLocal = arrayRegistro[2];
				int golLocal = Integer.parseInt(arrayRegistro[3]);
				String equipoVisitante = arrayRegistro[4];
				int golVisitante = Integer.parseInt(arrayRegistro[5]);

				if (!resultados.containsKey(equipoLocal)) { // inicializar el hashmap para añadir el key
					resultados.put(equipoLocal, 0);
				}
				if (!resultados.containsKey(equipoVisitante)) {
					resultados.put(equipoVisitante, 0);
				}
				
				int sumatoriaPuntosLocal;
				int sumatoriaPuntosVisitante;
				if (golLocal > golVisitante) {
					sumatoriaPuntosLocal = resultados.get(equipoLocal) + 3;
					sumatoriaPuntosVisitante = resultados.get(equipoVisitante);
				} else if (golLocal < golVisitante) {
					sumatoriaPuntosLocal = resultados.get(equipoLocal);
					sumatoriaPuntosVisitante = resultados.get(equipoVisitante) + 3;
				} else {
					sumatoriaPuntosLocal = resultados.get(equipoLocal) + 1;
					sumatoriaPuntosVisitante = resultados.get(equipoVisitante) + 1;
				}
				resultados.put(equipoLocal, sumatoriaPuntosLocal);
				resultados.put(equipoVisitante, sumatoriaPuntosVisitante);
			}

			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero.");

		} catch (FileNotFoundException e) {
			System.out.println("SALTO EXCEPCION DE: FileNotFoundException...");
		} catch (IOException e) {
			System.out.println("SALTO EXCEPCION DE: IO Exception...");
		} catch (NumberFormatException e) {
			System.out.println("SALTO EXCEPCION DE: NumberFormatException...");
		}

		return resultados;
	}

	/*******************************************/

	// crear metodo devolver partidos jugados en una jornada determinada

	public static ArrayList<Partido> devolverResultadoJornada(String rutaFichero, int mostrarJornada) {
		ArrayList<Partido> resultadoJornadaEquipos = new ArrayList<Partido>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] arrayRegistro = registro.split("#");

				// cuando llegue a un registro vacio, finaliza la lectura y evita los errores
				if (arrayRegistro[3].equals("")) {
					break;
				}

				int idPartido = Integer.parseInt(arrayRegistro[0]);
				int jornada = Integer.parseInt(arrayRegistro[1]);
				String equipoLocal = arrayRegistro[2];
				int golLocal = Integer.parseInt(arrayRegistro[3]);
				String equipoVisitante = arrayRegistro[4];
				int golVisitante = Integer.parseInt(arrayRegistro[5]);

				if (jornada == mostrarJornada) {
					Partido unPartido = new Partido(idPartido, jornada, equipoLocal, golLocal, equipoVisitante,
							golVisitante);
					resultadoJornadaEquipos.add(unPartido);
				}
			}
			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero.");

		} catch (FileNotFoundException e) {
			System.out.println("SALTO EXCEPCION: FileNotFoundException... \n");
		} catch (IOException e) {
			System.out.println("SALTO EXCEPCION: IO Exception... \n");
		} catch (NumberFormatException e) {
			System.out.println("SALTO EXCEPCION: NumberFormatException... \n");
		}
		return resultadoJornadaEquipos;
	}

	/*******************************************/

	// metodo que devuelva victorias, empates y derrotas por cada equipo
	// por cada equipo habrá una lista de contadores que representen VICTORIAS,
	// EMPATES, DERROTAS
	//
	public HashMap<String, ArrayList<Integer>> resultadoLigaEquipos2(String rutaFichero) {
		HashMap<String, ArrayList<Integer>> resultadoEquipo = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> resultados;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] arrayRegistro = registro.split("#");

				if (arrayRegistro[3].equals("")) {
					break;
				}

				int idPartido = Integer.parseInt(arrayRegistro[0]);
				int jornada = Integer.parseInt(arrayRegistro[1]);
				String equipoLocal = arrayRegistro[2];
				int golLocal = Integer.parseInt(arrayRegistro[3]);
				String equipoVisitante = arrayRegistro[4];
				int golVisitante = Integer.parseInt(arrayRegistro[5]);

				if (!resultadoEquipo.containsKey(equipoLocal)) { // inicializar el hashmap para añadir el key
					// el Arrays.asList convierte un array pasado por parametro a arrayList
					resultados = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
					resultadoEquipo.put(equipoLocal, resultados);
				}
				if (!resultadoEquipo.containsKey(equipoVisitante)) { // se crea el arrayList
					resultados = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
					resultadoEquipo.put(equipoVisitante, resultados);
				}

				int indiceLocal = 0; // en el indice 0 del arrayList es donde se almacenan las victorias
				int indiceVisitante = 0; // en el indice 0 del arrayList es donde se almacenan las victorias

				if (golLocal > golVisitante) {
					indiceLocal = 0; // accede al indice de victoria del arrayList si gana el local
					indiceVisitante = 2;
				} else if (golLocal < golVisitante) {
					indiceVisitante = 0;
					indiceLocal = 2;
				} else {
					indiceVisitante = 1;
					indiceLocal = 1;
				}
				int incrementoLocal = resultadoEquipo.get(equipoLocal).get(indiceLocal) + 1;
				int incrementoVisitante = resultadoEquipo.get(equipoVisitante).get(indiceVisitante) + 1;
				resultadoEquipo.get(equipoLocal).set(indiceLocal, incrementoLocal);
				resultadoEquipo.get(equipoVisitante).set(indiceVisitante, incrementoVisitante);
			}
			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero. \n");
		} catch (FileNotFoundException e) {
			System.out.println("SALTO EXCEPCION: FileNotFoundException... \n");
		} catch (IOException e) {
			System.out.println("SALTO EXCEPCION: IO Exception... \n");
		} catch (NumberFormatException e) {
			System.out.println("SALTO EXCEPCION: NumberFormatException... \n");
		}
		return resultadoEquipo;
	}

	/************************************** fin metodos propios ***********************************************/
	// coger el fichero equipos, crear una clase equipo
	// crear arrayList de todos los equipos
	public ArrayList<Equipo> obtencionListaEquipos(String rutaFichero) {

		try {
			ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			Equipo unEquipo;
			while ((registro = fichero.readLine()) != null) { // plantilla para la lectura de todos los ficheros de txts
				String[] unaLinea = registro.split("#");
				
				String nombCortoEquipo = unaLinea[1];
				String nombrLargoEquipo = unaLinea[2];
				Integer idEquipo = Integer.parseInt(unaLinea[0]);
				unEquipo = new Equipo(idEquipo, nombCortoEquipo, nombrLargoEquipo);
				listaEquipos.add(unEquipo);
			}
			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero.");
			return listaEquipos;
		} catch (FileNotFoundException e) {
			System.out.println("FICHERO NO ENCONTRADO...");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		return null;
	}

	// la clave sera el nombre corto
	public HashMap<String, Equipo> obtencionMapaEquipos(String rutaFichero) {

		try {
			HashMap<String, Equipo> mapaEquipos = new HashMap<String, Equipo>();
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			Equipo unEquipo;
			while ((registro = fichero.readLine()) != null) { // plantilla para la lectura de todos los ficheros de txts
				// System.out.println(registro);
				String[] unaLinea = registro.split("#");
				
				String nombCortoEquipo = unaLinea[1];
				String nombrLargoEquipo = unaLinea[2];
				int idEquipo = Integer.parseInt(unaLinea[0]);
				unEquipo = new Equipo(idEquipo, nombCortoEquipo, nombrLargoEquipo);
				mapaEquipos.put(nombCortoEquipo, unEquipo);
			}

			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero.");
			return mapaEquipos;
		} catch (FileNotFoundException e) {
			System.out.println("FICHERO NO ENCONTRADO...");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		return null;
	}

	// 22 enero 2019

	public HashMap<String, Integer> comprobarPartidos(String rutaFichero) {

		try {
			HashMap<String, Integer> mapaEquipos = new HashMap<String, Integer>();
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			int cont = 0;
			while ((registro = fichero.readLine()) != null) {
				// System.out.println(registro);
				String[] arrayRegistro = registro.split("#");
				String nombreEquipoLocal = arrayRegistro[2];
				String nombreEquipoVisitante = arrayRegistro[4];

				// .containsKey mira si la clave entre parentesis existe y esta operativa
				if (!mapaEquipos.containsKey(nombreEquipoLocal)) {
					mapaEquipos.put(nombreEquipoLocal, 0);
				}
				if (!mapaEquipos.containsKey(nombreEquipoVisitante)) {
					mapaEquipos.put(nombreEquipoVisitante, 0);
				}
				int contador = (int) mapaEquipos.get(nombreEquipoLocal);
				contador++;
				mapaEquipos.replace(nombreEquipoLocal, contador); // reemplaza
				contador = (int) mapaEquipos.get(nombreEquipoVisitante);
				contador++;
				mapaEquipos.replace(nombreEquipoVisitante, contador);
			}
			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero.");
			return mapaEquipos;
		} catch (FileNotFoundException e) {
			System.out.println("FICHERO NO ENCONTRADO...");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

		return null;
	}

	// 18 diciembre 18

	public void introListas() {

		ArrayList<Persona> listaPersonas;

		listaPersonas = new ArrayList<Persona>();

		listaPersonas.add(new Persona());

		listaPersonas.add(new Persona("44321654F", "Pepe", 145, "14/01/1989", 'M'));

		listaPersonas.add(1, new Persona("nuevoNif", "Pepe", 145, "15/12/2015", 'M'));

		for (Object elemento : listaPersonas) { // for abreviado. el tipo y donde se va a almacenar : donde va a iterar
			System.out.println(elemento.toString());
		}
		// ambos for hacen lo mismo, pero el de arriba es más efectivo por ser de poco
		// código
		for (int i = 0; i < listaPersonas.size(); i++) {
			System.out.println(listaPersonas.get(i));
		}

		System.out.println(listaPersonas.get(1).getNombre());

		System.out.println("fin listas");
	}

	// 09/01/2019 intoduccion a los maps

	public void introMapas() {
		// declarar un mapa (HashMap) que almacena
		// objetos de clase persona, la clave es el nif...
		// inicializar el mapa
		// insertar una serie de Estudiantes

		Estudiante nuevoEstudiante2 = new Estudiante("2", "Juanita Rodriguez", 45, "14/01/1989", 'M');
		nuevoEstudiante2.getNif();

		HashMap<String, Estudiante> mapaPersona = new HashMap<String, Estudiante>();
		mapaPersona.put("1", new Estudiante());
		Estudiante nuevoEstudiante = new Estudiante();
		nuevoEstudiante.getNif();
		mapaPersona.put(nuevoEstudiante.getNif(), nuevoEstudiante);
		mapaPersona.put(nuevoEstudiante2.getNif(), nuevoEstudiante2);

		mapaPersona.put("4", new Estudiante("1", "Pepito Perez", 12, "24/041/1815", 'H'));

		// si no existe el nif inserta ese nif
		if (!mapaPersona.containsKey(nuevoEstudiante.getNif())) {
			mapaPersona.put(nuevoEstudiante.getNif(), nuevoEstudiante);
		}

		// recorrer mapas
		//

		mapaPersona.get(nuevoEstudiante.getNif()).getNombre(); // forma para acceder al nombre

		// forma para acceder a los conjuntos () //devolvera un set (interfaz)
		Set<String> clavesMapa = mapaPersona.keySet();

		// recorre clavesMapa y cada vez que se entre en un elemento habrá una clave de
		// tipo string
		for (String unaClave : clavesMapa) { // habrá que entender estas lineas
			System.out.println(mapaPersona.get(unaClave).getNombre());
		}

		System.out.println("FIN DEL MAPA");

	}

	// 10/01/2019

	public void leerFichero(String rutaFichero) {

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));

			String registro;

			while ((registro = fichero.readLine()) != null) { // plantilla para la lectura de todos los ficheros de txts
				System.out.println(registro);
			}

			fichero.close();
			System.out.println("Lectura Exitosa. Fin de la lectura del fichero.");

		} catch (FileNotFoundException e) {
			System.out.println("FICHERO NO ENCONTRADO...");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

	}

	// 15/01/2019 //verificar ejercicio, no devuelve lo que debiera.

	public ArrayList<Persona> generarListaPersonaDesdeFichero(String rutaFichero, String separador) {
		ArrayList<Persona> ArrayListaPersona = new ArrayList<Persona>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));

			String unFichero;
			while ((unFichero = fichero.readLine()) != null) {
				// la variable "separador" será definida cuando toque diciendole cual será
				String[] campos = unFichero.split(separador);

				for (int i = 0; i < campos.length; i++) {
					System.out.print(campos[i] + ", ");
				}
				System.out.println("");

				int cantPasos = Integer.parseInt(campos[2]);
				// crear objeto de la clase persona
				Persona unaPersona = new Persona(campos[0], campos[1], cantPasos, campos[3], campos[4].charAt(0));
				ArrayListaPersona.add(unaPersona);
			}

			fichero.close();
			return ArrayListaPersona;
		} catch (FileNotFoundException e) {
			System.out.println("Entrada al primer catch");
		} catch (IOException e) {
			System.out.println("Entrada al segundo catch");
		}

		return null;
	}

	// 13 dic 2018 Intro a la Herencia

	// crear un Estudiante

	public void crearEstudiante() {

		Estudiante estudiante = new Estudiante("43781230V", "Pedro Garcia", 153, "12/12/1212", 'M');

	}

	private Persona[] personas;

	/*
	 * public void hijosPersonas() { for (int i = 0; i < personas.length; i++) {
	 * Persona[] hijos = personas[i].getHijosBiologicos();
	 * System.out.println("PROGENITOR -> " + personas[i].getNombre()); if ( hijos !=
	 * null) { for (int j = 0; j < hijos.length; j++) {
	 * System.out.println("Hijo -> " + hijos[j].getNombre()); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */
	// 27 noviembre 18

	public int[] matrizToArrayOrdenado(int[][] matriz) {
		int[] result = new int[1];
		ordenaFilasMatriz(matriz);
		for (int i = 0; i < matriz.length; i++)
			// Se crea un nuevo array con la unión de matriz[i] y result
			// Y se guarda en result
			result = mezclaListasOrdenadas(result, matriz[i]);
		return result;

	}

	// 21 noviembre 2018

	public void ordenaFilasMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++)
			ordenaArrayNumeros(matriz[i]);
	}

	// 20 noviembre 2018

	// Ejercicios con arrays

	// 1. Dada una lista (array) de enteros (int), obtener la lista INVERTIDA sobre
	// si misma

	public void invertirLista(int[] lista) {
		int aux;
		for (int i = 0; i < lista.length / 2; i++) {
			aux = lista[i];
			lista[i] = lista[lista.length - i - 1];
			lista[lista.length - i - 1] = aux;

		}

	}

	public int[] invertirLista2(int[] lista) {
		int[] resultado = new int[lista.length];
		for (int i = 0; i < lista.length; i++)
			resultado[lista.length - 1 - i] = lista[i];
		return resultado;
	}

	// 2. Dadas dos listas PREVIAMENTE ORDENADAS, se pide obtener la lista MEZCLA de
	// ambas

	public int[] mezclaListasOrdenadas(int[] l1, int[] l2) {

		int[] resultado = new int[l1.length + l2.length];
		int i = 0;
		int j = 0;
		int k = 0;

		while (k < l1.length + l2.length) {
			// while (i < l1.length || j < l2.length) {
			try {
				if (l1[i] < l2[j]) {
					resultado[k] = l1[i];
					i++;
				} else {
					resultado[k] = l2[j];
					j++;
				}
				k++;

			} catch (ArrayIndexOutOfBoundsException excepcion) {

				if (i == l1.length) // fin de l1..
					l1[--i] = Integer.MAX_VALUE;
				else
					l2[--j] = Integer.MAX_VALUE;
			}

		}
		return resultado;

	}

	// 3. Dada una cadena , obtener la cadena INVIRTIENDO sus caracteres (char)
	// usar charAt() o toCharArray()

	public String ivertirCaracteres(String cadena) {
		return cadena;

	}

	// 14 noviembre 2018
	public int[] sumaColumnasMatrizHeterogenea(int[][] matriz) {

		int numColMAX = 0;
		for (int i = 0; i < matriz.length; i++)
			if (matriz[i].length > numColMAX)
				numColMAX = matriz[i].length;

		int[] resultado = new int[numColMAX];
		// recorrer matriz por COLUMNAS

		for (int j = 0; j < numColMAX; j++) {
			for (int i = 0; i < matriz.length; i++)
				try {
					resultado[j] += matriz[i][j];
				} catch (ArrayIndexOutOfBoundsException e) {

				}
		}
		return resultado;
	}

	public void ordenaArrayCadenas(String[] cadenas) {
		for (int i = 0; i < cadenas.length - 1; i++)
			for (int j = i + 1; j < cadenas.length; j++)
				if (cadenas[i].compareTo(cadenas[j]) < 0) {
					String aux = cadenas[i];
					cadenas[i] = cadenas[j];
					cadenas[j] = aux;
				}
	}

	// 13 nov 2018
	public void ordenaArrayNumeros(int[] a) {
		for (int i = 0; i < a.length - 1; i++)
			for (int j = i + 1; j < a.length; j++)
				if (a[i] > a[j]) {
					int aux = a[i];
					a[i] = a[j];
					a[j] = aux;
				}
	}

	public void listaDesgloseMoneda(int cantidad) {
		System.out.println("CANTIDAD : " + cantidad);
		int[] monedas = { 500, 200, 100, 50, 20, 10, 5, 2, 1 };
		int[] resultado = desgloseMoneda(cantidad);
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] != 0)
				System.out.println(monedas[i] + " -> " + resultado[i]);
		}

	}

	public int[] desgloseMoneda(int cantidad) {
		int[] monedas = { 500, 200, 100, 50, 20, 10, 5, 2, 1 };
		int[] resultado = new int[monedas.length];
		// bucle
		int indice = 0;
		while (cantidad % monedas[indice] != 0) {
			resultado[indice] = cantidad / monedas[indice];
			cantidad = cantidad % monedas[indice];
			indice++;
		}

		// Para que cuando el resto sea 0 coja el valor
		resultado[indice] = cantidad / monedas[indice];

		return resultado;
	}

	// 8 noviembre 2018

	public boolean esPrimo(int n) {
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;
		return true;
	}

	public void listarPrimos(int desde, int hasta) {

		for (int i = desde; i < hasta; i++) {
			if (esPrimo(i))
				System.out.print(i + ", ");
		}
	}

	public void listarPrimos2(int desde, int cuantos) {
		int contador = 0;
		while (contador < cuantos)
			if (esPrimo(desde++)) {
				System.out.print(desde + ", ");
				contador++;
			}
	}

	public int[] listarPrimos3(int desde, int cuantos) {
		int[] resultado = new int[cuantos];
		int contador = 0;
		while (contador < cuantos)
			if (esPrimo(desde)) {
				resultado[contador] = desde++;
				contador++;
			}
		return resultado;
	}

	// 7 noviembre 2018
	// 3 bucles anidados for , un reloj

	public void mostrarHMS() {
		for (int h = 0; h < 1; h++)
			for (int m = 0; m < 60; m++)
				for (int s = 0; s < 60; s++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(h + " : " + m + " : " + s);
				}
	}

	// 25/10/18

	public void pruebaCadenas() {

		String nombre = "LAS PALMAS DE GRAN CANARIA";
		// iterar en la cadena mostrando sus caracteres
		// for (int i = 0; i < nombre.length(); i++)
		// System.out.println(nombre.charAt(i));

		String c1 = "abcd";
		String c2 = "abcdaaaaaaaaa";
		System.out.println(c1.compareTo(c2));

	}

	public float calcularSaldoFinal(float saldoInicial, float[] movimientos) {

		float saldoFinal = saldoInicial;
		// recorrer el array movimientos
		// sumando dichas cantidades a saldoFinal.
		for (int i = 0; i < movimientos.length; i++)
			saldoFinal += movimientos[i];
		return saldoFinal;
	}

	// 23/10/18

	// metodo para devolver una lista de n pèrsonas

	Persona[] creaListaPersonas(int n) {
		Persona[] lista = null;

		lista = new Persona[n];
		for (int i = 0; i < lista.length; i++)
			lista[i] = new Persona();
		return lista;
	}

	// --------------- 04/10/2018 ACTIVIDAD: BUSCAR EN LA API
	// -------------------------------

	public void pruebasAPI() {
		/*
		 * 1. imprime por consola el valor de 2^3 - Usa la clase estática Math y su
		 * método pow (Cuando es estático no hace falta el new)
		 * 
		 * 2. Imprime un valor aleatorio entre 1 y 100 - Usa la clase java.util.Random y
		 * el método rnd 3. En la cadena "LAS PALMAS DE GRAN CANARIA", imprime "GRAN"
		 * -Usa el método substring de la clase java.lang.String
		 */

		// 1.
		System.out.println("1. " + Math.pow(2, 3));

		// 2.
		Random rnd = new Random();
		System.out.println("2. " + rnd.nextInt(100));

		// 3.
		System.out.println("3. " + "LAS PALMAS DE GRAN CANARIA".substring(14, 18));
	}

	// ACTIVIDAD: Mostrar por consola los números comprendidos entre dos
	// enteros, a y b

	// 1Parte método:. prototipo o cabecera
	public void listaIntervaloEnteros(int primero, int ultimo) {
		// 2Parte método: cuerpo o implementación del método
		/*
		 * int i=primero; while(i < ultimo) System.out.println(++i); i++; i=i+1;
		 */
		for (int j = primero; j <= ultimo; j++)
			System.out.println(j++);

		// true /false
		boolean condicion = true;
		if (condicion) {
			// en caso de verdadero
		} else {
			// la otra opción, falso
		}

	}

	// ---------------------- ACTIVIDAD
	// ----------------------------------------------------- 10/10/2018
	/*
	 * Dado una “cadena” (String) devolver el valor en forma de número entero
	 * correspondiente. Ejempo String: “ab95f1”
	 */

	// String cadena ="ab95f1";
	/*
	 * public static int converString(String cadena) { int numeros =
	 * Integer.parseInt(cadena); return numeros; }
	 */

	// ---------------------- ACTIVIDAD : Serie Fibonacci
	// --------------------------------------- 11/10/2018

	public static void serieFibonacci(int cuantos) {
		int num1 = 0;
		int num2 = 1;
		int total = 1;

		System.out.print(num1 + " ," + num2);
		for (int i = 1; i < cuantos; i++) {
			total = num1 + num2;
			System.out.print(" ," + total);
			num1 = num2;
			num2 = total;
		}
	}

	// ---------------------- ACTIVIDAD : Factorial (1.-Deberes
	// Casa)-------------------------------- 11/10/2018

	public static void factorial(int numFact) {
		int num1 = 1;
		if (numFact == 0 | numFact == 1) {
			System.out.println("El factorial de " + "'" + numFact + "'" + " es :  1");
		} else {
			for (int i = 2; i <= numFact; i++) {
				num1 = num1 * i;
			}
			System.out.println("El factorial de " + "'" + numFact + "'" + " es :" + num1);
		}
	}

	// ---------------------- ACTIVIDAD : Devolver el número menor de tres
	// (2.-Deberes Casa)----------- 11/10/2018

	public int calNumMenor(int x, int y, int z) {
		if (x < y)
			if (x < z)
				return x;
			else
				return z;
		else if (y < z)
			return y;
		else
			return z;

	}

	public int calNumMenor(int x, int y, int z, int w) {
		if (x < y)
			if (x < z)
				if (x < w)
					return x;
				else
					return w;

			else if (z < w)
				return z;
			else
				return w;

		else if (y < z)
			if (y < w)
				return y;
			else
				return w;
		else if (z < w)
			return z;
		else
			return w;

	}

	// IMPRIME POR CONSOLA n números enteros aleatorios
	// entre inferior y superior

	public void imprimeAleatorios(int n, int inferior, int superior) // n,
																		// cuantos
																		// numeros
	{
		Random rnd = new Random();
		int numero = 0;
		for (int i = 0; i < n; i++) {
			numero = inferior + rnd.nextInt(superior - inferior + 1);
			System.out.println(i + 1 + ".- " + numero);
		}
	}

	// Devuelve un array de números enteros aleatorios, basandonos en el método
	// anterior
	public int[] generaListaAleatorios(int n, int inferior, int superior) {
		int[] resultado; // DECLARACION EN LA TABLA DE SIMBOLOS
		resultado = new int[n]; // INICIALIAZACION
		Random rnd = new Random();
		int numero = 0;
		for (int i = 0; i < n; i++)
			resultado[i] = inferior + rnd.nextInt(superior - inferior + 1);
		return resultado;
	}

	// 30 octubre 2018
	public int sumaAleatorios(int n, int inferior, int superior) {
		int resultado; // DECLARACION EN LA TABLA DE SIMBOLOS
		resultado = 0; // INICIALIAZACION
		Random rnd = new Random();
		int numero = 0;
		for (int i = 0; i < n; i++)
			resultado += inferior + rnd.nextInt(superior - inferior + 1);
		return resultado / n;
	}

	public int[] generaEstadisticaAparicion(int n, int inferior, int superior) {
		{
			int[] resultado; // DECLARACION EN LA TABLA DE SIMBOLOS
			resultado = new int[superior - inferior + 1]; // INICIALIAZACION
			Random rnd = new Random();
			int numero = 0;
			for (int i = 0; i < n; i++) {
				numero = inferior + rnd.nextInt(superior - inferior + 1);
				resultado[numero - 1]++;
			}
			return resultado;
		}
	}

	public float[] resumenVendedor(float[][] ventas) {
		// float acumVendedor = 0;
		float[] resultado = new float[ventas.length];
		// iterar por filas/columnas acumulando ventas de cada vendedor en un
		// año
		for (int i = 0; i < ventas.length; i++) {
			// acumVendedor = 0;
			for (int j = 0; j < ventas[i].length; j++)
				resultado[i] += ventas[i][j]; // acumVendedor += ventas[i][j];;
			// resultado[i] = acumVendedor;
		}
		return resultado;
	}

	public void mostrarVentasVendedor() {
		String[] nombresVendedor = { "JUAN CARLOS", "ISABEL", "MARTA" };
		float[][] ventasYear = {
				// hay 3 filas (vendedores) y 12 columnas (meses)
				{ 12.5f, 13.5f, 8.5f, 5.0f, 10.5f, 9.5f, 20.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f },
				{ 10.5f, 15.5f, 3.5f, 2.0f, 10.5f, 15.5f, 2.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f },
				{ 11.5f, 10.5f, 4.5f, 1.0f, 9.5f, 5.5f, 3.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f }

		};
		float[] ventas = resumenVendedor(ventasYear);
		for (int i = 0; i < ventas.length; i++)
			System.out.println(nombresVendedor[i] + " : " + ventas[i]);

	}

	public float[] resumenVentasPorMes(float[][] ventas) {
		// float acumVendedor = 0;
		float[] resultado = new float[ventas[0].length];
		// iterar por filas/columnas acumulando ventas de cada vendedor en un
		// año
		for (int j = 0; j < ventas[0].length; j++) {
			// acumVendedor = 0;
			for (int i = 0; i < ventas.length; i++)
				resultado[j] += ventas[i][j]; // acumVendedor += ventas[i][j];;

		}
		return resultado;
	}

	public int[] convierteCadenas(String[] cadenas) {
		// el array de salida tiene el MISMO numero
		// de elementos que el de entrada
		// si un numero no es valido, se pone -1.
		int[] resultado = new int[cadenas.length];
		for (int i = 0; i < cadenas.length; i++)
			try {
				resultado[i] = Integer.parseInt(cadenas[i]);
				System.out.println("LLega aqui");
			} catch (NumberFormatException e) {
				resultado[i] = -1;
			}

		return resultado;
	}

	public static void main(String[] args) {

		Ejercicios ejercicios = new Ejercicios();

		// ejercicios.introListas();
		// ejercicios.introMapas();
		// ejercicios.leerFichero("ficheros/datos.txt");
		// ArrayList<Persona> listaPersona = ejercicios.generarListaPersonaDesdeFichero("ficheros/ficheroPersona.txt", "##");
		// HashMap<String, Integer> numPartidosEquipo = ejercicios.comprobarPartidos("ficheros/partidos.txt");
		// ArrayList<Equipo> listaEquipos = ejercicios.obtencionListaEquipos("ficheros/equipos.txt");
		// System.out.println(listaEquipos);
		 HashMap<String, Equipo> mapaEquipos = ejercicios.obtencionMapaEquipos("ficheros/equipos.txt");
		// System.out.println(mapaEquipos);
		// ejercicios.mostrarNumeroPartidosJugados("ficheros/partidos.txt");
		
		// HashMap<String, ArrayList<Integer>> clasificacion = ejercicios.creaClasificacion("ficheros/partidos.txt", "ficheros/equipos.txt");
		// System.out.println(clasificacion);
		/******************************/
		// HashMap<String, ArrayList<Integer>> resultadoEquipos = ejercicios.resultadoLigaEquipos("ficheros/partidos.txt");
		//ejercicios.muestraPuntosEquipos(resultadoEquipos);		
		// HashMap<String, Integer> nuevosPuntosEquipos = ejercicios.generaPuntosEquipos(resultadoEquipos);		
		// ejercicios.ordenarMapaPuntosEquipos(nuevosPuntosEquipos);
		
		// ArrayList<Equipo> ordenacionPorNombre = ejercicios.equiposListaOrdenadaNombre();
		
		HashMap<String, Equipo> nombreEquipos;
		// HashMap<String, ArrayList<Integer>> totalGoles = ejercicios.sumarGolesEquipos("ficheros/partidos.txt");
		// HashMap<String, ArrayList<Integer>> crearClasificacion = ejercicios.creaClasificacion("ficheros/partidos.txt", "ficheros/equipos.txt");
		ArrayList<Equipo> crearClasificacionEquipo = ejercicios.creaClasificacionEquipo("ficheros/partidos.txt", mapaEquipos);
		
		
/******************************/		
		
		//ejercicios.pruebaSWING();
		
		
		/******************************** metodos propios ***********************************************/

		// HashMap<String, Integer> nuevoConteoPuntos = ejercicios.conteoPuntosPorEquipo("ficheros/partidos.txt");
		// System.out.println(nuevoConteoPuntos);
	
		/* ArrayList<Partido> partidosJornada =
		 ejercicios.devolverResultadoJornada("ficheros/partidos.txt", 5);
		 System.out.println("JORNADA: " + 5); 
		 for (int i = 0; i < partidosJornada.size(); i++) { //System.out.println(partidosJornada.get(i));
			System.out.println(partidosJornada.get(i).getIdPartido() +" - " +
			partidosJornada.get(i).getJornada() +" - " +
			partidosJornada.get(i).getNombreCortoEquipoLocal() +" - " +
			partidosJornada.get(i).getGolesLocal() +" - " +
			partidosJornada.get(i).getNombreCortoEquipoVisitante() +" - " +
			partidosJornada.get(i).getGolesVisitantes()
			); 
		}*/
		

		/**************************************
		 * fin metodos propios
		 ***********************************************/

		 System.exit(0);

		int[] lista1 = { 2, 4, 8, 9 };
		int[] lista2 = { 1, 3, 5, 23, 45, 67, 89, 123 };

		// int [] listasMezcladas = ejercicios.mezclaListasOrdenadas(lista1, lista2);
		// ejercicios.invertirLista(lista2);
		// int[] listaInvertida = ejercicios.invertirLista2(lista2);
		ejercicios.crearEstudiante();

		int[][] datos = { { 7, 4, 6 }, { 6 }, { 5, 2, 3 }, { 4, 2, 5, 1, 9, 0, 3 }, { 5, 6, 1, 3 } };

		int[] vector = ejercicios.matrizToArrayOrdenado(datos);
		// ejercicios.ordenaFilasMatriz(datos);

		int[] acumuladoColumnas = ejercicios.sumaColumnasMatrizHeterogenea(datos);

		// int[] x = {78,34,1,34,54,29,3};
		String[] nombres = { "carlos", "maria", "luis", "nuria", "roberto", "alejandro" };
		// ejercicios.ordenaArrayCadenas(nombres);
		int[] lista = ejercicios.generaListaAleatorios(1000, 1, 5000);
		// ejercicios.ordenaArrayNumeros(lista);
		// int[] monedas = ejercicios.desgloseMoneda(1234);
		// ejercicios.listaDesgloseMoneda(3);
		// System.out.println(ejercicios.esPrimo(99525));

		int inicio = 500;
		int cuantos = 5;
		// ejercicios.listarPrimos2(inicio, cuantos);

		// int[] numeros = ejercicios.listarPrimos3(inicio, cuantos);
		// ejercicios.mostrarHMS();
		// String[] datos = { "123", "8k8", "9811", "xyz" };
		// int[] numeros = ejercicios.convierteCadenas(datos);

		float[][] ventasYear = {
				// hay 3 filas (vendedores) y 12 columnas (meses)
				{ 12.5f, 13.5f, 8.5f, 5.0f, 10.5f, 9.5f, 20.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f },
				{ 10.5f, 15.5f, 3.5f, 2.0f, 10.5f, 15.5f, 2.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f },
				{ 11.5f, 10.5f, 4.5f, 1.0f, 9.5f, 5.5f, 3.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f }

		};
		float[] resumenVendedor;
		// resumenVendedor = ejercicios.resumenVendedor(ventasYear);
		// float[] reumenPorMes = ejercicios.resumenVentasPorMes(ventasYear);
		// crear e inicializar un array, varias opciones.
		float[] movs = { 10.0f, -5.0f, 20.0f, -10.0f };
		float saldoI = 100.0f;
		// ejercicios.pruebaCadenas();
		// System.out.println("Saldo final :"
		// + ejercicios.calcularSaldoFinal(saldoI, movs));
		// String[] diasSemana = {"LUNES", "MARTES"};
		/*
		 * movs = new float[4];
		 * 
		 * movs[0] = 10.0f; movs[1] = -5.0f; movs[2] = 20.5f; movs[3] = -10.5f;
		 */

		int a = 10, b = 17, c = 14, d = 11;
		// int menor= Ejercicios.calNumMenor(a, b, c, d);
		int menor = ejercicios.calNumMenor(a, b, c, d);
		// System.out.println("el menor es : " + menor);

		// ejercicios.imprimeAleatorios(6, 1, 6);
		int[] estadisticaDados = ejercicios.generaEstadisticaAparicion(1000, 1, 6);

		Persona[] listaPersonas = ejercicios.creaListaPersonas(10);
		// factorial(5);

		// serieFibonacci(8);

		// Act: 10/10/2018
		// System.out.println(converString("8fgf"));

		/*
		 * Ejercicios ejercicios = new Ejercicios(); int x=10; int y= 30;
		 * ejercicios.listaIntervaloEnteros(x, y);
		 */

		// Ejercicios.pruebasAPI();
		// new Ejercicios().pruebasAPI();

		// Persona juan = new Persona();
		// int pasos = juan.caminar(20);

		// Persona juan;
		// juan = new Persona("4545455X", "Juan Luis", 47,null);
		// Persona persona1 = new Persona();

		System.out.println("Fin del programa");
	}
}
