package id.borneo.of.hard.hardofborneo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.borneo.of.hard.hardofborneo.R;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView titleEvent;
    public TextView descEvent;
    public ImageView imageEvent;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        titleEvent = (TextView)itemView.findViewById(R.id.titleEvent);
        descEvent = (TextView)itemView.findViewById(R.id.descEvent);
        imageEvent = (ImageView)itemView.findViewById(R.id.imgEvent);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}