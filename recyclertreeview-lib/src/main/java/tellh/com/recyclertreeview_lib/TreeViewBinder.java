package tellh.com.recyclertreeview_lib;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


public abstract class TreeViewBinder<VH extends RecyclerView.ViewHolder> implements LayoutItemType {
    private TreeViewAdapter tvaAdapter;

    public void setAdapter(TreeViewAdapter adapter) {
        tvaAdapter = adapter;
    }
    public TreeViewAdapter getAdapter() {
        return tvaAdapter;
    }

    public abstract VH provideViewHolder(View itemView);

    public abstract void bindView(VH holder, int position, TreeNode node);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TreeViewAdapter tvaAdapter;

        public TreeViewAdapter getAdapter() {
            return tvaAdapter;
        }

        public ViewHolder(View rootView) {
            this(rootView, null);
        }

        public ViewHolder(View rootView, TreeViewAdapter adapter) {
            super(rootView);

            tvaAdapter = adapter;
        }

        protected <T extends View> T findViewById(@IdRes int id) {
            return (T) itemView.findViewById(id);
        }
    }

}