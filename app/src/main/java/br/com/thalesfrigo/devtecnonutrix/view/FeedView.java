package br.com.thalesfrigo.devtecnonutrix.view;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public interface FeedView {
    void startProgress();
    void finishProress();
    void updateFeed(List<Feed> feeds);
    void setEmptyFeed();
    void showErrorMessage(String message);
}
