package main;

import java.io.Serializable;

public class Resultado implements Serializable  {

	String identificador;
	String numFamiliares;
	String coordX;
	String coordY;
	String tarifa;
	String e2Total;
	String tarifaEstimada;
	
	public Resultado() {}

	public Resultado(String identificador, String numFamiliares, String coordX, String coordY, String tarifa,
			String e2Total, String tarifaEstimada) {
		super();
		this.identificador = identificador;
		this.numFamiliares = numFamiliares;
		this.coordX = coordX;
		this.coordY = coordY;
		this.tarifa = tarifa;
		this.e2Total = e2Total;
		this.tarifaEstimada = tarifaEstimada;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNumFamiliares() {
		return numFamiliares;
	}

	public void setNumFamiliares(String numFamiliares) {
		this.numFamiliares = numFamiliares;
	}

	public String getCoordX() {
		return coordX;
	}

	public void setCoordX(String coordX) {
		this.coordX = coordX;
	}

	public String getCoordY() {
		return coordY;
	}

	public void setCoordY(String coordY) {
		this.coordY = coordY;
	}

	public String getTarifa() {
		return tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	public String getE2Total() {
		return e2Total;
	}

	public void setE2Total(String e2Total) {
		this.e2Total = e2Total;
	}

	public String getTarifaEstimada() {
		return tarifaEstimada;
	}

	public void setTarifaEstimada(String tarifaEstimada) {
		this.tarifaEstimada = tarifaEstimada;
	}
	
	
}
