package Hotel;

// src/Reserva.java
import java.time.LocalDate;

/**
 * La clase Reserva representa una reserva de una habitación en el hotel.
 * Contiene detalles de la habitación, fecha de inicio, número de días y personas.
 */
class Reserva {
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private int dias;
    private int personas;

    /**
     * Constructor de la clase Reserva.
     *
     * @param habitacion la habitación reservada
     * @param fechaInicio la fecha de inicio de la reserva
     * @param dias el número de días de la reserva
     * @param personas el número de personas que se hospedarán
     */
    public Reserva(Habitacion habitacion, LocalDate fechaInicio, int dias, int personas) {
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.dias = dias;
        this.personas = personas;
    }

    /**
     * Calcula el costo total de la reserva, incluyendo costos adicionales.
     *
     * @return el costo total de la reserva
     */
    public double calcularCostoTotal() {
        return (habitacion.tarifaBase * dias) + habitacion.calcularCostoAdicional(personas, dias);
    }

    /**
     * Obtiene el tipo de habitación reservada.
     *
     * @return el tipo de la habitación (por ejemplo, Estandar, Ejecutiva, Suite)
     */
    public String getTipoHabitacion() {
        return habitacion.tipo;
    }

    /**
     * Obtiene la fecha de inicio de la reserva.
     *
     * @return la fecha de inicio de la reserva
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Obtiene el número de días de la reserva.
     *
     * @return el número de días de la reserva
     */
    public int getDias() {
        return dias;
    }

    /**
     * Devuelve una representación en cadena de la reserva, que incluye tipo de habitación,
     * fecha de inicio, duración, número de personas y costo total.
     *
     * @return una cadena con los detalles de la reserva
     */
    @Override
    public String toString() {
        return habitacion.tipo + "," + fechaInicio + "," + dias + "," + personas + "," + calcularCostoTotal();
    }
}
