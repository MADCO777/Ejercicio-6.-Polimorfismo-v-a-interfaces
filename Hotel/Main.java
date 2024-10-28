package Hotel;
// src/Main.java
// Main.java
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Hotel hotel;
    private static Scanner scanner;
    private static final String ARCHIVO_RESERVAS = "reservas.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        hotel = new Hotel();
        scanner = new Scanner(System.in);
        cargarReservasGuardadas();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    registrarNuevaReserva();
                    break;
                case 2:
                    mostrarReservasActivas();
                    break;
                case 3:
                    mostrarIngresosPorTipoEnMes();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE RESERVAS DE HOTEL ===");
        System.out.println("1. Registrar nueva reserva");
        System.out.println("2. Mostrar reservas activas");
        System.out.println("3. Ver ingresos por tipo de habitación en un mes");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static void registrarNuevaReserva() {
        System.out.println("\n=== REGISTRO DE NUEVA RESERVA ===");

        // Seleccionar tipo de habitación
        System.out.println("Seleccione tipo de habitación:");
        System.out.println("1. Estándar ($50/noche)");
        System.out.println("2. Ejecutiva ($100/noche + servicios adicionales)");
        System.out.println("3. Suite Presidencial ($250/noche + servicios adicionales)");

        Habitacion habitacion = null;
        while (habitacion == null) {
            int tipoHabitacion = leerOpcion();
            switch (tipoHabitacion) {
                case 1:
                    habitacion = new HabitacionEstandar();
                    break;
                case 2:
                    habitacion = new HabitacionEjecutiva();
                    break;
                case 3:
                    habitacion = new SuitePresidencial();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
            }
        }

        // Leer fecha de inicio
        LocalDate fechaInicio = null;
        while (fechaInicio == null) {
            System.out.print("Ingrese fecha de inicio (dd/MM/yyyy): ");
            try {
                String fecha = scanner.nextLine();
                fechaInicio = LocalDate.parse(fecha, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use dd/MM/yyyy");
            }
        }

        // Leer cantidad de días
        System.out.print("Ingrese cantidad de días: ");
        int dias = Integer.parseInt(scanner.nextLine());

        // Leer cantidad de personas
        System.out.print("Ingrese cantidad de personas: ");
        int personas = Integer.parseInt(scanner.nextLine());

        // Crear y agregar la reserva
        Reserva reserva = new Reserva(habitacion, fechaInicio, dias, personas);
        hotel.agregarReserva(reserva);

        // Mostrar costo total
        System.out.println("\nReserva registrada exitosamente");
        System.out.printf("Costo total: $%.2f%n", reserva.calcularCostoTotal());

        // Guardar reservas en archivo
        guardarReservas();
    }

    private static void mostrarReservasActivas() {
        System.out.println("\n=== RESERVAS ACTIVAS ===");
        List<Reserva> reservas = hotel.obtenerReservasActivas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            return;
        }

        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            System.out.printf("%d. Tipo: %s, Fecha: %s, Días: %d, Costo: $%.2f%n",
                    i + 1,
                    r.getTipoHabitacion(),
                    r.getFechaInicio().format(formatter),
                    r.getDias(),
                    r.calcularCostoTotal());
        }
    }

    private static void mostrarIngresosPorTipoEnMes() {
        System.out.println("\n=== INGRESOS POR TIPO DE HABITACIÓN ===");
        System.out.print("Ingrese mes (MM) y año (yyyy) a consultar (MM/yyyy): ");

        try {
            String[] partes = scanner.nextLine().split("/");
            int mes = Integer.parseInt(partes[0]);
            int año = Integer.parseInt(partes[1]);

            double ingresosEstandar = hotel.calcularIngresosPorTipoYMes("Estandar", mes, año);
            double ingresosEjecutiva = hotel.calcularIngresosPorTipoYMes("Ejecutiva", mes, año);
            double ingresosSuite = hotel.calcularIngresosPorTipoYMes("Suite Presidencial", mes, año);

            System.out.printf("Habitación Estándar: $%.2f%n", ingresosEstandar);
            System.out.printf("Habitación Ejecutiva: $%.2f%n", ingresosEjecutiva);
            System.out.printf("Suite Presidencial: $%.2f%n", ingresosSuite);

            String tipoMayor = hotel.tipoHabitacionMayorIngresosMes(mes, año);
            System.out.println("\nTipo de habitación con mayores ingresos: " + tipoMayor);
        } catch (Exception e) {
            System.out.println("Error al procesar la fecha. Use el formato MM/yyyy");
        }
    }

    private static void cargarReservasGuardadas() {
        try {
            GestorArchivos.cargarReservas(ARCHIVO_RESERVAS, hotel);
            System.out.println("Reservas cargadas exitosamente.");
        } catch (IOException e) {
            System.out.println("No se encontraron reservas guardadas.");
        }
    }

    private static void guardarReservas() {
        try {
            GestorArchivos.guardarReservas(hotel.obtenerReservasActivas(), ARCHIVO_RESERVAS);
        } catch (IOException e) {
            System.out.println("Error al guardar las reservas: " + e.getMessage());
        }
    }
}