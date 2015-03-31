package ee.siimplangi.aboutme.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import ee.siimplangi.aboutme.CustomListAdapter;
import ee.siimplangi.aboutme.R;
import ee.siimplangi.aboutme.RowInfo;
import ee.siimplangi.aboutme.XMLParser;

/**
 * Created by Siim on 30.03.2015.
 */
public class InfoPageFragment extends Fragment{

    public static final String ARG_TITLE = "title";
    public static final String ARG_DATA_RESOURCE_ID = "data_id";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String title = getArguments().getString(ARG_TITLE);
        int dataResourceId = getArguments().getInt(ARG_DATA_RESOURCE_ID);

        getActivity().setTitle(title);

        XmlPullParser xmlParser = getResources().getXml(dataResourceId);
        List<RowInfo> rowInfoList;
        try {
            rowInfoList = XMLParser.parseXML(xmlParser);
        } catch (IOException | XmlPullParserException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        ListView contentWindow = (ListView) inflater.inflate(R.layout.fragment_aboutme, container, false);
        contentWindow.setAdapter(new CustomListAdapter(getActivity(), rowInfoList));

        return contentWindow;
    }

}

