package br.com.thalesfrigo.devtecnonutrix.view.contract;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.util.LoadMode;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public interface FeedView {
    void startProgress();
    void finishProress();
    void updateFeed(List<Feed> feeds, LoadMode loadMode);
    void setEmptyFeed();
    void showErrorMessage(int messageResourceId);
}
