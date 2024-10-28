package Hotel;

/**
 * La clase abstracta Habitacion representa una habitación en el hotel.
 * Define el tipo de habitación y la tarifa base, y obliga a las subclases a implementar
 * el cálculo de los costos adicionales.
 */
abstract class Habitacion {
    protected String tipo;
    protected double tarifaBase;

    /**
     * Constructor de la clase Habitacion.
     *
     * @param tipo el tipo de habitación (Estandar, Ejecutiva, Suite Presidencial)
     * @param tarifaBase la tarifa base de la habitación por noche
     */
    public Habitacion(String tipo, double tarifaBase) {
        this.tipo = tipo;
        this.tarifaBase = tarifaBase;
    }

    /**
     * Calcula el costo adicional por personas y días en la habitación.
     *
     * @param personas el número de personas que se hospedarán
     * @param dias el número de días de la reserva
     * @return el costo adicional basado en el tipo de habitación
     */
    public abstract double calcularCostoAdicional(int personas, int dias);
}

/**
 * La clase HabitacionEstandar representa una habitación estándar en el hotel.
 * No tiene costos adicionales por personas ni días.
 */
class HabitacionEstandar extends Habitacion {

    /**
     * Constructor de la clase HabitacionEstandar.
     * Define la tarifa base de la habitación estándar como 50.
     */
    public HabitacionEstandar() {
        super("Estandar", 50);
    }

    /**
     * Calcula el costo adicional para una habitación estándar.
     * En este caso, no se aplican costos adicionales.
     *
     * @param personas el número de personas (sin efecto en este tipo de habitación)
     * @param dias el número de días (sin efecto en este tipo de habitación)
     * @return siempre 0, ya que no hay costos adicionales
     */
    @Override
    public double calcularCostoAdicional(int personas, int dias) {
        return 0; // Sin costos adicionales
    }
}

/**
 * La clase HabitacionEjecutiva representa una habitación ejecutiva en el hotel.
 * Aplica costos adicionales por personas y días de la reserva.
 */
class HabitacionEjecutiva extends Habitacion {

    /**
     * Constructor de la clase HabitacionEjecutiva.
     * Define la tarifa base de la habitación ejecutiva como 100.
     */
    public HabitacionEjecutiva() {
        super("Ejecutiva", 100);
    }

    /**
     * Calcula el costo adicional para una habitación ejecutiva.
     * Se aplica un costo de 15 por cada persona y 10 por cada día.
     *
     * @param personas el número de personas en la habitación
     * @param dias el número de días de la reserva
     * @return el costo adicional basado en el número de personas y días
     */
    @Override
    public double calcularCostoAdicional(int personas, int dias) {
        return (15 * personas) + (10 * dias);
    }
}

/**
 * La clase SuitePresidencial representa una suite presidencial en el hotel.
 * Aplica costos adicionales basados en los días y un cargo fijo.
 */
class SuitePresidencial extends Habitacion {

    /**
     * Constructor de la clase SuitePresidencial.
     * Define la tarifa base de la suite presidencial como 250.
     */
    public SuitePresidencial() {
        super("Suite Presidencial", 250);
    }

    /**
     * Calcula el costo adicional para una suite presidencial.
     * Se aplica un costo de 50 por cada día y un cargo fijo de 30.
     *
     * @param personas el número de personas (sin efecto en este tipo de habitación)
     * @param dias el número de días de la reserva
     * @return el costo adicional basado en los días y el cargo fijo
     */
    @Override
    public double calcularCostoAdicional(int personas, int dias) {
        return (50 * dias) + 30;
    }
}
