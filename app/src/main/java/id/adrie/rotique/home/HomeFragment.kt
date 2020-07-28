package id.adrie.rotique.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import id.adrie.rotique.R
import id.adrie.rotique.item.LoadMoreListItem
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val itemAdapter = ItemAdapter<IItem<*, *>>()
    private val fastAdapter: FastAdapter<IItem<*, *>> = FastAdapter.with(itemAdapter)
    private var loadMoreListItem: LoadMoreListItem = LoadMoreListItem()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = null
            adapter = fastAdapter
            setHasFixedSize(true)
        }

        swipeRefreshLayout.apply {
            isRefreshing = true
            setOnRefreshListener {
                // load from cache
            }
        }

        itemAdapter.add(loadMoreListItem)
    }

    private fun removeLoadMore() {
        if (itemAdapter.adapterItems.contains(loadMoreListItem)) {
            itemAdapter.remove(itemAdapter.getAdapterPosition(loadMoreListItem))
        }
    }

    private fun stopRefreshing() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

}