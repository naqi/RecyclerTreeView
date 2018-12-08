package tellh.com.recyclertreeview.viewbinder;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tellh.com.recyclertreeview.R;
import tellh.com.recyclertreeview.bean.Dir;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Created by tlh on 2016/10/1 :)
 */

public class DirectoryNodeBinder extends TreeViewBinder<DirectoryNodeBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView, getAdapter());
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {
        holder.ivArrow.setRotation(0);
        holder.ivArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_black_18dp);
        int rotateDegree = node.isExpand() ? 90 : 0;
        holder.ivArrow.setRotation(rotateDegree);
        Dir dirNode = (Dir) node.getContent();
        holder.tvName.setText(dirNode.dirName);
        if (node.isLeaf())
            holder.ivArrow.setVisibility(View.INVISIBLE);
        else holder.ivArrow.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dir;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {
        private ImageView ivArrow;
        private TextView tvName;
        private ImageView ivMore;

        private static class ClickListener implements View.OnClickListener {
            private ViewHolder vhHolder;
            private TreeViewAdapter tvaAdapter;

            public ClickListener(ViewHolder holder, TreeViewAdapter adapter) {
                this.vhHolder = holder;
                this.tvaAdapter = adapter;
            }

            @Override
            public void onClick(View v) {
                tvaAdapter.processClickEvent(vhHolder);
            }
        }

        public ViewHolder(final View rootView, TreeViewAdapter adapter) {
            super(rootView, adapter);
            this.ivArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
            this.ivMore = (ImageView) rootView.findViewById(R.id.iv_more);

            this.ivArrow.setOnClickListener(new ClickListener(this, getAdapter()));
            this.tvName.setOnClickListener(new ClickListener(this, getAdapter()));

            this.ivMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                    alertDialogBuilder.setMessage("\"More\" icon was clicked for folder \"" +
                            tvName.getText() + "\".");
                    alertDialogBuilder.setPositiveButton("OK",null);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        }

        public ImageView getIvArrow() {
            return ivArrow;
        }

        public TextView getTvName() {
            return tvName;
        }
    }
}
