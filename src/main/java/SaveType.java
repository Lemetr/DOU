import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveType {

    public static <T> void writeToFile(T str){
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss")) + ".csv";
        try {
            BufferedWriter bw = new BufferedWriter(
                    new PrintWriter(fileName, System.getProperty("file.encoding")));
            bw.write(String.valueOf(str));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void printToConsole(T sb){
        System.out.println(sb);
    }

}
