package br.com.thalesfrigo.devtecnonutrix.view.contract;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;

/**
 * Created by thalesfrigo on 12/22/16.
 */
public interface FeedDetailView {
    void startProgress();
    void finishProress();
    void updateFeed(Feed feed);
    void showErrorMessage(int messageResourceId);
}
