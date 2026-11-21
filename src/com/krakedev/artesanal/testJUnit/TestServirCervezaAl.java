package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestServirCervezaAl {

    private static final double TOLERANCIA = 0.0001;

    // ------------------------------------------------------------------
    // Casos usando el constructor con capacidad personalizada
    // ------------------------------------------------------------------

    /**
     * Valida que al usar el constructor con capacidad personalizada,
     * si hay cerveza suficiente se descuente la cantidad servida
     * y se retorne el valor a pagar correcto (cantidad * precioPorMl).
     */
    @Test
    public void servirConCapacidadSuficienteConstructorConCapacidad() {
        // capacidadMaxima = 5000, precioPorMl = 0.5
        Maquina maquina = new Maquina("IPA", "Cerveza artesanal IPA", 0.5, 5000, "MAQ-001");
        // Cargamos 3000 ml
        maquina.recargarCerveza(3000);

        double valorPagado = maquina.servirCerveza(1000);

        // 1000 ml * 0.5 = 500.0
        assertEquals(500.0, valorPagado, TOLERANCIA,
                "El valor a pagar debe ser cantidad * precioPorMl");
        // cantidadActual debe bajar de 3000 a 2000
        assertEquals(2000.0, maquina.getCantidadActual(), TOLERANCIA,
                "La cantidad actual debe disminuir en la cantidad servida");
    }

    /**
     * Valida que al servir exactamente toda la cerveza disponible
     * (caso límite), se descuente completamente y se retorne el valor correcto.
     */
    @Test
    public void servirExactamenteLaCantidadDisponible() {
        Maquina maquina = new Maquina("Stout", "Cerveza negra", 1.0, 5000, "MAQ-002");
        maquina.recargarCerveza(2000);

        double valorPagado = maquina.servirCerveza(2000);

        assertEquals(2000.0, valorPagado, TOLERANCIA,
                "El valor a pagar debe ser 2000 * 1.0");
        assertEquals(0.0, maquina.getCantidadActual(), TOLERANCIA,
                "La cantidad actual debe quedar en 0 al servir todo lo disponible");
    }

    /**
     * Valida que si se intenta servir más de lo disponible,
     * no se modifica la cantidad actual y se retorna 0 (no sirve nada).
     */
    @Test
    public void noServirSiNoHaySuficienteCervezaConstructorConCapacidad() {
        Maquina maquina = new Maquina("Lager", "Cerveza rubia", 1.0, 5000, "MAQ-003");
        maquina.recargarCerveza(1000);

        double valorPagado = maquina.servirCerveza(2000);

        assertEquals(0.0, valorPagado, TOLERANCIA,
                "Si no hay suficiente cerveza debe retornar 0");
        assertEquals(1000.0, maquina.getCantidadActual(), TOLERANCIA,
                "Si no sirve nada, la cantidad actual no debe cambiar");
    }

    /**
     * Valida el comportamiento cuando la máquina está vacía (cantidadActual = 0).
     * Debe retornar 0 y mantener la cantidad actual en 0.
     */
    @Test
    public void noServirEnMaquinaVacia() {
        Maquina maquina = new Maquina("Pilsner", "Cerveza clara", 0.8, 5000, "MAQ-004");
        // No se recarga: cantidadActual = 0

        double valorPagado = maquina.servirCerveza(100);

        assertEquals(0.0, valorPagado, TOLERANCIA,
                "En máquina vacía debe retornar 0");
        assertEquals(0.0, maquina.getCantidadActual(), TOLERANCIA,
                "La cantidad actual debe permanecer en 0");
    }

    /**
     * Valida el cálculo del valor a pagar usando la capacidad máxima
     * obtenida con llenarMaquina() (capacidadMaxima - 100).
     */
    @Test
    public void servirDespuesDeLlenarMaquinaCalculaValorCorrecto() {
        Maquina maquina = new Maquina("Amber", "Cerveza ámbar", 0.75, 5000, "MAQ-005");
        maquina.llenarMaquina(); // capacidadMaxima - 100 = 4900

        double valorPagado = maquina.servirCerveza(4900);

        // 4900 * 0.75 = 3675.0
        assertEquals(3675.0, valorPagado, TOLERANCIA,
                "El valor a pagar debe ser 4900 * 0.75");
        assertEquals(0.0, maquina.getCantidadActual(), TOLERANCIA,
                "Al servir todo, la cantidad actual debe quedar en 0");
    }

    // ------------------------------------------------------------------
    // Casos usando el constructor con capacidad por defecto (10000)
    // ------------------------------------------------------------------

    /**
     * Valida el servicio exitoso usando el constructor que asigna capacidad 10000 por defecto.
     */
    @Test
    public void servirConCapacidadSuficienteConstructorPorDefecto() {
        // capacidadMaxima = 10000 por defecto, precioPorMl = 0.25
        Maquina maquina = new Maquina("Weiss", "Cerveza de trigo", 0.25, "MAQ-006");
        maquina.recargarCerveza(5000);

        double valorPagado = maquina.servirCerveza(2000);

        // 2000 * 0.25 = 500.0
        assertEquals(500.0, valorPagado, TOLERANCIA,
                "El valor a pagar debe ser cantidad * precioPorMl");
        assertEquals(3000.0, maquina.getCantidadActual(), TOLERANCIA,
                "La cantidad actual debe disminuir en la cantidad servida");
    }

    /**
     * Valida que con el constructor por defecto, si no hay suficiente cerveza,
     * no se sirve nada y la cantidad actual no cambia.
     */
    @Test
    public void noServirSiNoHaySuficienteCervezaConstructorPorDefecto() {
        Maquina maquina = new Maquina("Bock", "Cerveza fuerte", 0.6, "MAQ-007");
        maquina.recargarCerveza(1500);

        double valorPagado = maquina.servirCerveza(1500.01);

        assertEquals(0.0, valorPagado, TOLERANCIA,
                "Si no hay suficiente cerveza debe retornar 0");
        assertEquals(1500.0, maquina.getCantidadActual(), TOLERANCIA,
                "Si no sirve nada, la cantidad actual no debe cambiar");
    }

    /**
     * Valida el valor a pagar usando el constructor por defecto con un precio distinto,
     * verificando que el cálculo cantidad * precioPorMl sea correcto.
     */
    @Test
    public void servirCalculaValorConConstructorPorDefecto() {
        Maquina maquina = new Maquina("Porter", "Cerveza oscura", 0.33, "MAQ-008");
        maquina.recargarCerveza(4000);

        double valorPagado = maquina.servirCerveza(1500);

        // 1500 * 0.33 = 495.0
        assertEquals(495.0, valorPagado, TOLERANCIA,
                "El valor a pagar debe ser 1500 * 0.33");
        assertEquals(2500.0, maquina.getCantidadActual(), TOLERANCIA,
                "La cantidad actual debe ser 4000 - 1500");
    }

    /**
     * Valida que en una máquina recién creada con el constructor por defecto
     * (cantidadActual = 0), cualquier intento de servir retorna 0
     * y no modifica la cantidad actual.
     */
    @Test
    public void noServirEnMaquinaVaciaConstructorPorDefecto() {
        Maquina maquina = new Maquina("Saison", "Cerveza de granja", 0.9, "MAQ-009");

        double valorPagado = maquina.servirCerveza(500);

        assertEquals(0.0, valorPagado, TOLERANCIA,
                "En máquina vacía debe retornar 0");
        assertEquals(0.0, maquina.getCantidadActual(), TOLERANCIA,
                "La cantidad actual debe permanecer en 0");
    }
}
