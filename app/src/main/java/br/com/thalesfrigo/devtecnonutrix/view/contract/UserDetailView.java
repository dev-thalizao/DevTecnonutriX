package br.com.thalesfrigo.devtecnonutrix.view.contract;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.User;
import br.com.thalesfrigo.devtecnonutrix.util.LoadMode;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public interface UserDetailView  {
    void startProgress();
    void finishProress();
    void updateFeed(User user, List<Feed> feeds, LoadMode loadMode);
    void setEmptyFeed();
    void showErrorMessage(String message);
}
