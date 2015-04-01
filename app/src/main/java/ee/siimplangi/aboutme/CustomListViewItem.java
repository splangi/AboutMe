package ee.siimplangi.aboutme;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;

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
    private final int PADDING_RIGHT = densityToPixels(16);



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

    private TextView createLargeTitleTextView(String text){
        TextView view = createTitleTextView(text);
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.largeTextSize));
        return view;
    }

    private TextView createInfoTextView(String text){
        TextView view = (TextView) mInflater.inflate(R.layout.row_info_textview, this, false);
        view.setText(text);
        return view;
    }

    private DocumentView createJustifiedTextView(String text){
        DocumentView view = (DocumentView) mInflater.inflate(R.layout.justified_textview, this, false);
        view.setText(text);
        return view;
    }

    private ImageView createImageView(String filename){
        int resourceId = getResources().getIdentifier(filename, "drawable", mContext.getPackageName());
        Drawable image = getResources().getDrawable(resourceId);
        ImageView view = (ImageView) mInflater.inflate(R.layout.row_image_textview, this, false);
        view.setImageDrawable(image);
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
            View view = null;
            String text = info.getInfo();
            switch(info.getInfoType()){
                case TEXT:
                    view = createInfoTextView(text);
                    break;
                case TITLE:
                    view = createTitleTextView(text);
                    break;
                case SUBTITLE:
                    view = createSubTitleTextView(text);
                    break;
                case HYPERLINK:
                    view = createHyperlinkTextView(text);
                    break;
                case JUSTIFIED:
                    view = createJustifiedTextView(text);
                    break;
                case LARGE_TITLE:
                    view = createLargeTitleTextView(text);
                    break;
                case IMAGE:
                    view = createImageView(text);
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
