package ee.siimplangi.aboutme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Siim on 31.03.2015.
 */
public class CustomListAdapter extends BaseAdapter {

    Context mContext;
    List<RowInfo> rowInfoList;

    public CustomListAdapter(Context mContext, List<RowInfo> rowInfoList) {
        this.mContext = mContext;
        this.rowInfoList = rowInfoList;
    }

    @Override
    public int getCount() {
        return rowInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return rowInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CustomListViewItem viewItem;
        if (view != null && view instanceof CustomListViewItem){
            viewItem = (CustomListViewItem) view;
        }
        else{
            viewItem = new CustomListViewItem(mContext);
        }
        viewItem.setRowInfo(rowInfoList.get(i));
        return viewItem;
    }
}
