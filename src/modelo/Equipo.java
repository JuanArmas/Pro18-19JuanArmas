package modelo;

public class Equipo {
	
	private int idEquipo;
	private String nombreCortoEquipo;
	private String nombreLargoEquipo;
	
	public Equipo() {
		super();
	}
	
	public Equipo(int idEquipo, String nombreCortoEquipo, String nombreLargoEquipo) {
		super();
		this.idEquipo = idEquipo;
		this.nombreCortoEquipo = nombreCortoEquipo;
		this.nombreLargoEquipo = nombreLargoEquipo;
	}
	
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
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
	
	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombreCortoEquipo=" + nombreCortoEquipo + ", nombreLargoEquipo="
				+ nombreLargoEquipo + "]";
	}
	
	
	
}
