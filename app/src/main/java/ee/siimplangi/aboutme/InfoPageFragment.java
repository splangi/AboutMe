package ee.siimplangi.aboutme;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import ee.siimplangi.aboutme.CustomListViewItem;
import ee.siimplangi.aboutme.R;
import ee.siimplangi.aboutme.RowInfo;
import ee.siimplangi.aboutme.XMLParser;

/**
 * Created by Siim on 30.03.2015.
 */
public class InfoPageFragment extends Fragment{

    public static final String ARG_DATA_RESOURCE_ID = "data_id";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int dataResourceId = getArguments().getInt(ARG_DATA_RESOURCE_ID);

        XmlPullParser xmlParser = getResources().getXml(dataResourceId);
        List<RowInfo> rowInfoList;
        try {
            rowInfoList = XMLParser.parseXML(xmlParser);
        } catch (IOException | XmlPullParserException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_aboutme, container, false);
        if (rootView.getChildCount() == 0){
            throw new IllegalStateException();
        }
        ViewGroup contentWindow = (ViewGroup) rootView.getChildAt(0);
        Context context = getActivity();
        for (RowInfo rowInfo: rowInfoList){
            CustomListViewItem childView = new CustomListViewItem(context);
            childView.setRowInfo(rowInfo);
            contentWindow.addView(childView);
        }

        return rootView;
    }

}

