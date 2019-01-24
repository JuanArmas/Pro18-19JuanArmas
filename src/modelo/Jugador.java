package modelo;

public class Jugador extends Persona {

	private int idJugador;
	private int dorsal;
	private int idEquipo;
	
	
	public Jugador() {
		
	}


	public Jugador(int idJugador, String nif, String nombre, int longitudPaso, String fecha_nac, char sexo, int dorsal, int idEquipo) {
		super(nif, nombre, longitudPaso, fecha_nac, sexo);
		this.idJugador = idJugador;
		this.dorsal = dorsal;
		this.idEquipo = idEquipo;
		
	}

	public Jugador(String nif, String nombre, int longitudPaso, String fecha_nac, char sexo) {
		super(nif, nombre, longitudPaso, fecha_nac, sexo);
		// TODO Auto-generated constructor stub
	}


	public Jugador(String nif, String nombre, String fecha_nac) {
		super(nif, nombre, fecha_nac);
		// TODO Auto-generated constructor stub
	}


	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	@Override
	public String toString() {
		return "Jugador [idJugador=" + idJugador + ", dorsal=" + dorsal + ", idEquipo=" + idEquipo + "]";
	}

}
