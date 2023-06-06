import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.Console;

public class SAXXMLParser {
    public static void main(String args[]) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAXHandler saxHandler = new SAXHandler();

            saxParser.parse("Storage01.xml", saxHandler);
            Storage website = saxHandler.getWebsite();
            for (Arcticle currentArcticle : website.articles) {
                System.out.println(currentArcticle.getTitle());
                System.out.println(currentArcticle.getContent());
                System.out.println("=========================================");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }
}
