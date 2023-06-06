import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {
    private static final String ARTICLES = "articles";
    private static final String ARTICLE = "article";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";

    private Storage website;
    private StringBuilder elementValue;
    public ArrayList<Arcticle> articles;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        website = new Storage();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case ARTICLES:
                website.articles = new ArrayList<>();
                break;
            case ARTICLE:
                website.articles.add(new Arcticle());
                break;
            case TITLE:
            case CONTENT:
                elementValue = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case TITLE:
                latestArticle().setTitle(elementValue.toString());
                break;
            case CONTENT:
                latestArticle().setContent(elementValue.toString());
                break;
        }
    }

    private Arcticle latestArticle() {
        return website.articles.get(website.articles.size() - 1);
    }

    public Storage getWebsite() {
        return website;
    }
}
