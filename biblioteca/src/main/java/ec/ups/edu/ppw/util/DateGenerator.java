package ec.ups.edu.ppw.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase utilitaria para la generación de fechas.
 */
public class DateGenerator {

    /**
     * Crea una fecha a partir de los valores especificados de año, mes y día.
     * @param year El año de la fecha.
     * @param month El mes de la fecha (1 para enero, 2 para febrero, etc.).
     * @param day El día del mes.
     * @return Un objeto {@link Date} con la fecha especificada.
     */
    public static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Enero es 0 en Calendar
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
