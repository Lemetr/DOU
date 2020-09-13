
import scrapers.JsoupScrap;
import scrapers.Scrappable;
import scrapers.SeleniumScrap;

public class Main {

    private static  <T> void write(Writeble w, T sb){ w.writeTo(sb); }
    private static String getScrap(Scrappable s){return s.getScrap();}

    public static void main(String[] args) {

//        write(SaveType::writeToFile, getScrap(new SeleniumScrap()::getScrap));
        write(SaveType::printToConsole, getScrap(new JsoupScrap()::getScrap));

    }

}