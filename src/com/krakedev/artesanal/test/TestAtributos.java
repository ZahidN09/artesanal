package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestAtributos {

	public static void main(String[] args) {
		
		Maquina rubia = new Maquina("Pilsener","Cerveza Rubia",0.02,100000,"0001");
		
		rubia.imprimir();
		rubia.setNombreCerveza("Golden Ales");
		rubia.setDescripcion("Cerveza con Aroma más intenso");
		rubia.imprimir();
	}

}
