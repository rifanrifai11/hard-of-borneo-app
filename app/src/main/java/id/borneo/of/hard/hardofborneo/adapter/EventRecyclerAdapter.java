package id.borneo.of.hard.hardofborneo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.borneo.of.hard.hardofborneo.R;
import id.borneo.of.hard.hardofborneo.item.EventItem;

public class EventRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<EventItem> itemList;
    private Context context;

    public EventRecyclerAdapter(Context context, List<EventItem> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.titleEvent.setText(itemList.get(position).getTitle());
        holder.descEvent.setText(itemList.get(position).getDesc());
        holder.imageEvent.setImageResource(itemList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
