package modelo;

public class Partido {

	private Integer idPartido;
	private Integer jornada;
	private String nombreCortoEquipoLocal;
	private Integer golesLocal;
	private String nombreCortoEquipoVisitante;
	private Integer golesVisitantes;
	
	public Partido() {
		super();
	}

	public Partido(Integer idPartido, Integer jornada, String nombreCortoEquipoLocal, Integer golesLocal, String nombreCortoEquipoVisitante,
			Integer golesVisitantes) {
		super();
		this.idPartido = idPartido;
		this.jornada = jornada;
		this.nombreCortoEquipoLocal = nombreCortoEquipoLocal;
		this.golesLocal = golesLocal;
		this.nombreCortoEquipoVisitante = nombreCortoEquipoVisitante;
		this.golesVisitantes = golesVisitantes;
	}

	
	public Integer getIdPartido() {
		return idPartido;
	}


	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}


	public Integer getJornada() {
		return jornada;
	}


	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}


	public String getNombreCortoEquipoLocal() {
		return nombreCortoEquipoLocal;
	}


	public void setNombreCortoEquipoLocal(String nombreCortoEquipoLocal) {
		this.nombreCortoEquipoLocal = nombreCortoEquipoLocal;
	}


	public Integer getGolesLocal() {
		return golesLocal;
	}


	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}


	public String getNombreCortoEquipoVisitante() {
		return nombreCortoEquipoVisitante;
	}


	public void setNombreCortoEquipoVisitante(String nombreCortoEquipoVisitante) {
		this.nombreCortoEquipoVisitante = nombreCortoEquipoVisitante;
	}


	public Integer getGolesVisitantes() {
		return golesVisitantes;
	}


	public void setGolesVisitantes(Integer golesVisitantes) {
		this.golesVisitantes = golesVisitantes;
	}


	@Override
	public String toString() {
		return "Partido [idPartido=" + idPartido + ", jornada=" + jornada + ", nombreCortoEquipoLocal=" + nombreCortoEquipoLocal
				+ ", golesLocal=" + golesLocal + ", nombreCortoEquipoVisitante=" + nombreCortoEquipoVisitante + ", golesVisitantes="
				+ golesVisitantes + "]";
	}
}
