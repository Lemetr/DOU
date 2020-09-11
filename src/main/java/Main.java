
import scrapers.JsoupScrap;
import scrapers.Scrappable;
import scrapers.SeleniumScrap;

public class Main {

    private <T> void write(Writeble w, T sb){ w.writeTo(sb); }
    private String getScrap(Scrappable s){return s.getScrap();}

    public static void main(String[] args) {

        Main m = new Main();

//        m.write(SaveType::printToConsole, new SeleniumScrap().getScrap());
        m.write(SaveType::printToConsole, m.getScrap(JsoupScrap::getScrap));

    }

}