package com.valkyria.trebusz;

/**
 * Created by Kensaj on 28.10.2017.
 */

import java.util.List;

import com.valkyria.trebusz.DatabaseInterface;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TrebuszTaskAdapter extends ArrayAdapter<DatabaseInterface> {
    private Activity context;
    private List<DatabaseInterface> tasks;

    public TrebuszTaskAdapter(Activity context, List<DatabaseInterface> tasks) {
        super(context, R.layout.fragment_databsetest, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    static class ViewHolder {
        public TextView tvTrebuszDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.fragment_databsetest, null, true);
            viewHolder = new ViewHolder();
            viewHolder.tvTrebuszDate = (TextView) rowView.findViewById(R.id.DBTestbutton);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        DatabaseInterface task = tasks.get(position);
        viewHolder.tvTrebuszDate.setText(task.getDate());
        return rowView;
    }
}
