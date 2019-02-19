package modelo;

public class Equipo implements Comparable<Equipo> {
	//puntos, victorias, empates, derrotas, golesFavor, golesContra.
	
	private Integer idEquipo;
	private String nombreCortoEquipo;
	private String nombreLargoEquipo;
	private Integer victorias; 
	private Integer empates;
	private Integer derrotas;
	private Integer golesFavor;
	private Integer golesContra;
	
	public Integer  calcularPuntos(){
		Integer puntos = 0;	
		puntos = (this.victorias * 3) + this.empates;	
		return puntos;
	}
	
	public Equipo() {
		super();
	}
	
	public Equipo(Integer idEquipo, String nombreCortoEquipo, String nombreLargoEquipo) {
		super();
		this.idEquipo = idEquipo;
		this.nombreCortoEquipo = nombreCortoEquipo;
		this.nombreLargoEquipo = nombreLargoEquipo;
		
	}
	
	public Equipo(Integer idEquipo, String nombreCortoEquipo, String nombreLargoEquipo, Integer victorias,
			Integer empates, Integer derrotas, Integer golesFavor, Integer golesContra) {
		super();
		this.idEquipo = idEquipo;
		this.nombreCortoEquipo = nombreCortoEquipo;
		this.nombreLargoEquipo = nombreLargoEquipo;
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
		return "Equipo [idEquipo=" + this.idEquipo + ", nombreCortoEquipo=" + this.nombreCortoEquipo + ", nombreLargoEquipo="
				+ this.nombreLargoEquipo + ", puntos= " + calcularPuntos()+", victorias=" + this.victorias + ", empates=" + this.empates
				+ ", derrotas=" + this.derrotas + ", golesFavor=" + this.golesFavor + ", golesContra=" + this.golesContra + "]\n";
	}

	@Override
	public int compareTo(Equipo o) {
		if(this.getVictorias() > this.getVictorias()) {		
			return -1;
			
		} else if(this.getVictorias() < this.getVictorias()){ 
			return 1;
			
			}
		return 0;	
	}

	
	
}
