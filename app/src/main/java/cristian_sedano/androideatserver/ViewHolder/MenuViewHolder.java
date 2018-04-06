package cristian_sedano.androideatserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cristian_sedano.androideatserver.Common.Common;
import cristian_sedano.androideatserver.Interface.ItemClickListener;
import cristian_sedano.androideatserver.R;

/**
 * Created by Christian on 01/04/2018.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMenuName = (TextView) itemView.findViewById(R.id.menu_name);
        imageView = (ImageView) itemView.findViewById(R.id.menu_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        contextMenu.setHeaderTitle("Select the action");

        contextMenu.add(0,0, getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,1, getAdapterPosition(), Common.DELETE);
    }
}
