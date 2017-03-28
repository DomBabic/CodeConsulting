package hr.etfos.d1babic.codeconsulting.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hr.etfos.d1babic.codeconsulting.model.Item;
import hr.etfos.d1babic.codeconsulting.R;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public class ListViewAdapter extends BaseAdapter{

    private final List<Item> itemsList = new ArrayList<>();

    public void setAdapterItems(List<Item> items) {
        itemsList.clear();
        itemsList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_items, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        if(viewHolder != null) {

            if(!itemsList.get(i).getPagemap().getCseThumbnail().isEmpty()) {
                Picasso.with(view.getContext()).load(itemsList.get(i).getPagemap().getCseThumbnail().first().getSrc()
                ).into(viewHolder.image);
            }

            viewHolder.title.setText(itemsList.get(i).getTitle());
            viewHolder.link.setText(itemsList.get(i).getLink());
            viewHolder.snippet.setText(itemsList.get(i).getSnippet());
        }

        return view;
    }

}