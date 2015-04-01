package ee.siimplangi.aboutme;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siim on 31.03.2015.
 */
public class XMLParser {

    private static final String ns = null;
    private static final String HYPERLINK_TAG = "hyperlink";
    private static final String TITLE_TAG = "title";
    private static final String SUBTITLE_TAG = "subtitle";
    private static final String TEXT_TAG = "text";
    private static final String IMAGE_TAG = "image";
    private static final String JUSTIFIED_TAG = "justified";
    private static final String LARGE_TITLE_TAG = "largetitle";

    public static List<RowInfo> parseXML(XmlPullParser parser) throws IOException, XmlPullParserException {
          List<RowInfo> rows = new ArrayList<>();
          parser.next();
          parser.next();
          parser.require(XmlPullParser.START_TAG, ns, "data");
          while (parser.next() != XmlPullParser.END_TAG){
              if (parser.getEventType() != XmlPullParser.START_TAG) {
                  continue;
              }
              String name = parser.getName();
              if (name.equals("row")) {
                  rows.add(parseRow(parser));
              } else {
                  skip(parser);
              }
          }
          return rows;
    }

    private static RowInfo parseRow(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "row");
        RowInfo row = new RowInfo();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            switch (name) {
                case TITLE_TAG:
                    row.addInfo(new Info(read(parser, TITLE_TAG), InfoType.TITLE));
                    break;
                case TEXT_TAG:
                    row.addInfo(new Info(read(parser, TEXT_TAG), InfoType.TEXT));
                    break;
                case SUBTITLE_TAG:
                    row.addInfo(new Info(read(parser, SUBTITLE_TAG), InfoType.SUBTITLE));
                    break;
                case HYPERLINK_TAG:
                    row.addInfo(new Info(read(parser, HYPERLINK_TAG), InfoType.HYPERLINK));
                    break;
                case JUSTIFIED_TAG:
                    row.addInfo(new Info(read(parser, JUSTIFIED_TAG), InfoType.JUSTIFIED));
                    break;
                case LARGE_TITLE_TAG:
                    row.addInfo(new Info(read(parser, LARGE_TITLE_TAG), InfoType.LARGE_TITLE));
                    break;
                case IMAGE_TAG:
                    row.addInfo(new Info(read(parser, IMAGE_TAG), InfoType.IMAGE));
                    break;
                default:
                    skip(parser);
                    break;
            }
        }
        return row;
    }

    private static String read(XmlPullParser parser, String requiredTag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, requiredTag);
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        parser.require(XmlPullParser.END_TAG, ns, requiredTag);
        return result;
    }

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
