import constants.Constants;
import scrapers.SeleniumScrap;

public class Main {

    private static <T> void write(Writeble w, T sb){ w.writeTo(sb); }

    public static void main(String[] args) { Main.write(SaveType::printToConsole, new SeleniumScrap().getScrap()); }

}