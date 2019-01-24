package modelo;

import java.time.LocalDate;

public class Estudiante extends Persona {

	private  String curso;
	private String matricula;
	private char turno; // 'M', 'T', 'N'
	
	public Estudiante() {
		super();
	}

	public Estudiante(String nif, String nombre, int longitudPaso, String fecha_nac, char sexo) {
		super(nif, nombre, longitudPaso, fecha_nac, sexo);
		this.curso = curso;
		this.matricula = matricula;
		this.turno = turno;
		
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Estudiante [curso=" + curso + ", matricula=" + matricula + ", turno=" + turno + "]";
	}

	
}
