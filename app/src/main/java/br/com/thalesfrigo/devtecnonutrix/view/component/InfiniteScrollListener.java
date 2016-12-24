package br.com.thalesfrigo.devtecnonutrix.view.component;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;

import br.com.thalesfrigo.devtecnonutrix.presenter.BasePresenter;

/**
 * Created by thalesfrigo on 12/21/16.
 */

public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private LayoutManager layoutManager;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public InfiniteScrollListener(LayoutManager layoutManager) {
        super();
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();

        if(layoutManager instanceof LinearLayoutManager){
            firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if(layoutManager instanceof GridLayoutManager){
            firstVisibleItem = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            loading = true;
            didReachTheEnd();
        }
    }

    public abstract void didReachTheEnd();
}
