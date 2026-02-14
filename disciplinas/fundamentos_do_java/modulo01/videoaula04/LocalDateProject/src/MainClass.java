import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainClass {

    public static void main(String[] args) {

        LocalDate dataAtual = LocalDate.now();
        LocalTime horaAtual = LocalTime.now();
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        System.out.println(dataAtual);
        System.out.println(horaAtual);
        System.out.println(dataHoraAtual);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(dataAtual.format(format));

        String dataUsuario = "14/02/2026";
        LocalDate data = LocalDate.parse(dataUsuario,format);

        System.out.println(data);

        DateTimeFormatter outroFormato = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.println(data.format(outroFormato));
    }
}

