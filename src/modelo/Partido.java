package modelo;

public class Partido {

	private int idPartido;
	private int jornada;
	private String nombreCortoEquipoLocal;
	private int golesLocal;
	private String nombreCortoEquipoVisitante;
	private int golesVisitantes;
	
	public Partido() {
		
	}

	public Partido(int idPartido, int jornada, String nombreCortoEquipoLocal, int golesLocal, String nombreCortoEquipoVisitante,
			int golesVisitantes) {
		super();
		this.idPartido = idPartido;
		this.jornada = jornada;
		this.nombreCortoEquipoLocal = nombreCortoEquipoLocal;
		this.golesLocal = golesLocal;
		this.nombreCortoEquipoVisitante = nombreCortoEquipoVisitante;
		this.golesVisitantes = golesVisitantes;
	}

	
	public int getIdPartido() {
		return idPartido;
	}


	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}


	public int getJornada() {
		return jornada;
	}


	public void setJornada(int jornada) {
		this.jornada = jornada;
	}


	public String getNombreCortoEquipoLocal() {
		return nombreCortoEquipoLocal;
	}


	public void setNombreCortoEquipoLocal(String nombreCortoEquipoLocal) {
		this.nombreCortoEquipoLocal = nombreCortoEquipoLocal;
	}


	public int getGolesLocal() {
		return golesLocal;
	}


	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}


	public String getNombreCortoEquipoVisitante() {
		return nombreCortoEquipoVisitante;
	}


	public void setNombreCortoEquipoVisitante(String nombreCortoEquipoVisitante) {
		this.nombreCortoEquipoVisitante = nombreCortoEquipoVisitante;
	}


	public int getGolesVisitantes() {
		return golesVisitantes;
	}


	public void setGolesVisitantes(int golesVisitantes) {
		this.golesVisitantes = golesVisitantes;
	}


	@Override
	public String toString() {
		return "Partido [idPartido=" + idPartido + ", jornada=" + jornada + ", nombreCortoEquipoLocal=" + nombreCortoEquipoLocal
				+ ", golesLocal=" + golesLocal + ", nombreCortoEquipoVisitante=" + nombreCortoEquipoVisitante + ", golesVisitantes="
				+ golesVisitantes + "]";
	}
}
