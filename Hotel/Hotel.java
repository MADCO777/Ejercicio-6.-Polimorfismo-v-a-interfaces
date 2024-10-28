package Hotel;

// Hotel.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Hotel representa un hotel con una lista de reservas.
 * Permite agregar reservas y obtener estadísticas de ingresos por tipo de habitación y por mes.
 */
class Hotel {
    private List<Reserva> reservas;

    /**
     * Constructor de la clase Hotel.
     * Inicializa la lista de reservas.
     */
    public Hotel() {
        reservas = new ArrayList<>();
    }

    /**
     * Agrega una reserva a la lista de reservas del hotel.
     *
     * @param reserva la reserva a agregar
     */
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    /**
     * Obtiene la lista de reservas activas en el hotel.
     *
     * @return una lista de reservas activas
     */
    public List<Reserva> obtenerReservasActivas() {
        return reservas;
    }

    /**
     * Calcula los ingresos de un tipo específico de habitación en un mes y año dados.
     *
     * @param tipo el tipo de habitación (Estandar, Ejecutiva, Suite Presidencial)
     * @param mes el mes en el que se desea calcular los ingresos (1 a 12)
     * @param año el año en el que se desea calcular los ingresos
     * @return los ingresos totales generados por el tipo de habitación en el mes y año indicados
     */
    public double calcularIngresosPorTipoYMes(String tipo, int mes, int año) {
        return reservas.stream()
                .filter(r -> r.getTipoHabitacion().equals(tipo))
                .filter(r -> r.getFechaInicio().getMonthValue() == mes
                        && r.getFechaInicio().getYear() == año)
                .mapToDouble(Reserva::calcularCostoTotal)
                .sum();
    }

    /**
     * Determina el tipo de habitación que generó más ingresos en un mes y año específicos.
     *
     * @param mes el mes en el que se desea encontrar el tipo de habitación con mayor ingresos (1 a 12)
     * @param año el año en el que se desea encontrar el tipo de habitación con mayor ingresos
     * @return el tipo de habitación (Estandar, Ejecutiva, Suite Presidencial) con mayores ingresos
     */
    public String tipoHabitacionMayorIngresosMes(int mes, int año) {
        double ingresosEstandar = calcularIngresosPorTipoYMes("Estandar", mes, año);
        double ingresosEjecutiva = calcularIngresosPorTipoYMes("Ejecutiva", mes, año);
        double ingresosSuite = calcularIngresosPorTipoYMes("Suite Presidencial", mes, año);

        if (ingresosEstandar > ingresosEjecutiva && ingresosEstandar > ingresosSuite) {
            return "Estandar";
        } else if (ingresosEjecutiva > ingresosSuite) {
            return "Ejecutiva";
        } else {
            return "Suite Presidencial";
        }
    }
}
