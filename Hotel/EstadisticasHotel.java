package Hotel;

// src/EstadisticasHotel.java
import java.util.List;

/**
 * La clase EstadisticasHotel contiene métodos para calcular estadísticas
 * sobre la ocupación del hotel, como el porcentaje de ocupación y el
 * número de habitaciones ocupadas por tipo.
 */
class EstadisticasHotel {

    /**
     * Calcula el porcentaje de ocupación del hotel.
     *
     * @param totalHabitaciones el total de habitaciones en el hotel
     * @param habitacionesOcupadas el número de habitaciones ocupadas actualmente
     * @return el porcentaje de ocupación del hotel
     */
    public static double calcularPorcentajeOcupacion(int totalHabitaciones, int habitacionesOcupadas) {
        return ((double) habitacionesOcupadas / totalHabitaciones) * 100;
    }

    /**
     * Muestra las estadísticas de ocupación por tipo de habitación.
     *
     * Este método cuenta cuántas habitaciones de cada tipo están ocupadas
     * (Estandar, Ejecutiva y Suite Presidencial) y muestra el resultado en consola.
     *
     * @param reservas una lista de reservas que contiene la información de las habitaciones ocupadas
     */
    public static void mostrarEstadisticas(List<Reserva> reservas) {
        long estandar = reservas.stream().filter(r -> r.getTipoHabitacion().equals("Estandar")).count();
        long ejecutiva = reservas.stream().filter(r -> r.getTipoHabitacion().equals("Ejecutiva")).count();
        long suite = reservas.stream().filter(r -> r.getTipoHabitacion().equals("Suite Presidencial")).count();

        System.out.println("Habitaciones Estandar ocupadas: " + estandar);
        System.out.println("Habitaciones Ejecutiva ocupadas: " + ejecutiva);
        System.out.println("Suites Presidenciales ocupadas: " + suite);
    }
}
