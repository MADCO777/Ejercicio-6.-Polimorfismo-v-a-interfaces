package Hotel;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

/**
 * La clase GestorArchivos permite guardar y cargar reservas de un archivo.
 * Facilita el manejo de archivos para almacenar la información de las reservas.
 */
class GestorArchivos {

    /**
     * Guarda la lista de reservas en un archivo.
     *
     * @param reservas la lista de reservas a guardar
     * @param archivo el nombre o ruta del archivo donde se guardarán las reservas
     * @throws IOException si ocurre un error al escribir en el archivo
     */
    public static void guardarReservas(List<Reserva> reservas, String archivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Carga las reservas desde un archivo y las agrega al hotel.
     * Lee cada línea del archivo, crea las reservas basándose en la información leída,
     * y las agrega a la lista de reservas activas del hotel.
     *
     * @param archivo el nombre o ruta del archivo desde el cual se cargarán las reservas
     * @param hotel el hotel al que se agregarán las reservas cargadas
     * @return la lista de reservas activas del hotel después de cargar los datos
     * @throws IOException si ocurre un error al leer el archivo
     */
    public static List<Reserva> cargarReservas(String archivo, Hotel hotel) throws IOException {
        List<Reserva> reservas = hotel.obtenerReservasActivas();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String tipo = partes[0];
                LocalDate fechaInicio = LocalDate.parse(partes[1]);
                int dias = Integer.parseInt(partes[2]);
                int personas = Integer.parseInt(partes[3]);

                Habitacion habitacion;
                switch (tipo) {
                    case "Estandar":
                        habitacion = new HabitacionEstandar();
                        break;
                    case "Ejecutiva":
                        habitacion = new HabitacionEjecutiva();
                        break;
                    case "Suite Presidencial":
                        habitacion = new SuitePresidencial();
                        break;
                    default:
                        continue;
                }
                reservas.add(new Reserva(habitacion, fechaInicio, dias, personas));
            }
        }
        return reservas;
    }
}
