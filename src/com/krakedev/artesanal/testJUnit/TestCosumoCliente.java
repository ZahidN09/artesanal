package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;

public class TestCosumoCliente {

	@Test
	public void probarConsumo() {
		
		Maquina maquinaA = new Maquina("Pilsener","Cerveza Rubia",0.002,8000,"0001");
		Negocio barDeMoe = new Negocio("Bar de Moe",maquinaA);
		Cliente cliente = new Cliente("Andres","00000");
		
		barDeMoe.cargarMaquina();
		
		barDeMoe.consumirCervezaMaquina(cliente,100);
		
		assertEquals(7700, maquinaA.getCantidadActual(),0.0001);
		assertEquals(0.2, cliente.getTotalConsumido(),0.0001);
		
		barDeMoe.consumirCervezaMaquina(cliente, 200);
		assertEquals(7500, maquinaA.getCantidadActual(),0.0001);
		assertEquals(0.6, cliente.getTotalConsumido(),0.0001);
		
	}
}
