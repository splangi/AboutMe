package ee.siimplangi.aboutme;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Siim on 30.03.2015.
 */
public class CustomListViewItem extends LinearLayout {

    private TextView rowHeading;
    private TextView rowText;

    private LayoutInflater mInflater;
    private Context mContext;

    private final int PADDING_TOP = densityToPixels(5);
    private final int PADDING_BOTTOM = densityToPixels(5);
    private final int PADDING_LEFT = densityToPixels(16);
    private final int PADDING_RIGHT = 0;



    public CustomListViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListViewItem(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        mContext = context;
        setLayoutParameters();
    }

    private void setLayoutParameters(){
        setOrientation(HORIZONTAL);
        // IN Kitkat, it did not like the LinearLayout.LayoutParams
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        setPadding(PADDING_LEFT,PADDING_TOP,PADDING_RIGHT,PADDING_BOTTOM);
        setLayoutParams(params);
    }
    private TextView createSubTitleTextView(String text){
        TextView view = (TextView) mInflater.inflate(R.layout.row_title_textview, this, false);
        view.setText(text);
        return view;
    }

    private TextView createTitleTextView(String text){
        TextView view = createSubTitleTextView(text);
        view.setTypeface(null, Typeface.BOLD);
        return view;
    }

    private TextView createInfoTextView(String text){
        TextView view = (TextView) mInflater.inflate(R.layout.row_info_textview, this, false);
        view.setText(text);
        return view;
    }

    private TextView createHyperlinkTextView(String text){
        TextView view = (TextView) mInflater.inflate(R.layout.row_info_textview, this, false);
        view.setText(Html.fromHtml(text));
        view.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }

    public void setRowInfo(RowInfo rowInfo){
        removeAllViews();
        for (Info info : rowInfo.getRowInformationList()){
            TextView view = null;
            switch(info.getInfoType()){
                case TEXT:
                    view = createInfoTextView(info.getInfo());
                    break;
                case TITLE:
                    view = createTitleTextView(info.getInfo());
                    break;
                case SUBTITLE:
                    view = createSubTitleTextView(info.getInfo());
                    break;
                case HYPERLINK:
                    view = createHyperlinkTextView(info.getInfo());
                    break;
            }
            if (view != null){
                addView(view);
            }
        }

    }

    private int densityToPixels(int dp){
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (dp*scale + 0.5f);
        return dpAsPixels;
    }



}
