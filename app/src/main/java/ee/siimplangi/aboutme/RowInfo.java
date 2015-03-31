package ee.siimplangi.aboutme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siim on 31.03.2015.
 */
public class RowInfo {

    List<Info> rowInformationList = new ArrayList<>();

    public void addInfo(Info information){
        rowInformationList.add(information);
    }

    public List<Info> getRowInformationList() {
        return rowInformationList;
    }
    /*
    String title;
    String subtitle;
    String text;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
    */
}

class Info {
    String info;
    InfoType infoType;

    Info(String info, InfoType type) {
        this.info = info;
        infoType = type;
    }

    public String getInfo() {
        return info;
    }

    public InfoType getInfoType() {
        return infoType;
    }
}

enum InfoType{
    TITLE,SUBTITLE,TEXT,HYPERLINK,IMAGE
}

