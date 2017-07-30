package com.fernandocejas.android10.sample.presentation.ui.base;

/**
 * Created by Ruby on 7/30/2017.
 */

public interface BasePresenterContract {
  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onResume() method.
   */
  void resume();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onPause() method.
   */
  void pause();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onDestroy() method.
   */
  void destroy();

  void bindViewIntents();
}
