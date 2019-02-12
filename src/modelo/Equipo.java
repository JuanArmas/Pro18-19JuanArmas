package modelo;

public class Equipo implements Comparable<Equipo> {
	//puntos, victorias, empates, derrotas, golesFavor, golesContra.
	
	private Integer idEquipo;
	private String nombreCortoEquipo;
	private String nombreLargoEquipo;
	private Integer puntos;
	private Integer victorias; 
	private Integer empates;
	private Integer derrotas;
	private Integer golesFavor;
	private Integer golesContra;
	
	public Equipo() {
		super();
	}
	
	public Equipo(Integer idEquipo, String nombreCortoEquipo, String nombreLargoEquipo) {
		super();
		this.idEquipo = idEquipo;
		this.nombreCortoEquipo = nombreCortoEquipo;
		this.nombreLargoEquipo = nombreLargoEquipo;
		
	}
	
	public Equipo(Integer idEquipo, String nombreCortoEquipo, String nombreLargoEquipo, Integer puntos, Integer victorias,
			Integer empates, Integer derrotas, Integer golesFavor, Integer golesContra) {
		super();
		this.idEquipo = idEquipo;
		this.nombreCortoEquipo = nombreCortoEquipo;
		this.nombreLargoEquipo = nombreLargoEquipo;
		this.puntos = puntos;
		this.victorias = victorias;
		this.empates = empates;
		this.derrotas = derrotas;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
	}
	
	public Integer getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getNombreCortoEquipo() {
		return nombreCortoEquipo;
	}
	public void setNombreCortoEquipo(String nombreCortoEquipo) {
		this.nombreCortoEquipo = nombreCortoEquipo;
	}
	public String getNombreLargoEquipo() {
		return nombreLargoEquipo;
	}
	public void setNombreLargoEquipo(String nombreLargoEquipo) {
		this.nombreLargoEquipo = nombreLargoEquipo;
	}
	
	/*@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombreCortoEquipo=" + nombreCortoEquipo + ", nombreLargoEquipo="
				+ nombreLargoEquipo + "]";
	}*/
	
	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getVictorias() {
		return victorias;
	}

	public void setVictorias(Integer victorias) {
		this.victorias = victorias;
	}

	public Integer getEmpates() {
		return empates;
	}

	public void setEmpates(Integer empates) {
		this.empates = empates;
	}

	public Integer getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(Integer derrotas) {
		this.derrotas = derrotas;
	}

	public Integer getGolesFavor() {
		return golesFavor;
	}

	public void setGolesFavor(Integer golesFavor) {
		this.golesFavor = golesFavor;
	}

	public Integer getGolesContra() {
		return golesContra;
	}

	public void setGolesContra(Integer golesContra) {
		this.golesContra = golesContra;
	}

	
	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombreCortoEquipo=" + nombreCortoEquipo + ", nombreLargoEquipo="
				+ nombreLargoEquipo + ", puntos=" + puntos + ", victorias=" + victorias + ", empates=" + empates
				+ ", derrotas=" + derrotas + ", golesFavor=" + golesFavor + ", golesContra=" + golesContra + "]\n";
	}

	@Override
	public int compareTo(Equipo o) {
		if(this.getPuntos() > this.getPuntos()) {		
			return -1;
			
		} else if(this.getPuntos() < this.getPuntos()){ 
			return 1;
			
			}
		return 0;
		
	}

	
}
