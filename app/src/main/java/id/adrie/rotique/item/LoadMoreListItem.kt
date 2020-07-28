package id.adrie.rotique.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikepenz.fastadapter.items.AbstractItem
import id.adrie.rotique.R

class LoadMoreListItem: AbstractItem<LoadMoreListItem, LoadMoreListItem.ViewHolder>() {

    override fun getType(): Int = hashCode()

    override fun getLayoutRes(): Int = R.layout.list_item_loadmore

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        if (holder.itemView.layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            val param = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            param.isFullSpan = true
        }
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


}
