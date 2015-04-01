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

}

class Info {
    String info;
    InfoType infoType;

    Info(String info, InfoType type) {
        this.info = info;
        infoType = type;
        this.info = this.info.replaceAll("\\s+", " ");
        this.info = this.info.replaceAll("^\\s+|\\s+$", "");
    }

    public String getInfo() {
        return info;
    }

    public InfoType getInfoType() {
        return infoType;
    }
}

enum InfoType{
    TITLE,SUBTITLE,TEXT,HYPERLINK,IMAGE,JUSTIFIED,LARGE_TITLE
}

