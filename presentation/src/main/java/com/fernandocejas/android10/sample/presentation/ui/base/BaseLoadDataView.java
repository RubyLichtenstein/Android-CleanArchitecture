/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.ui.base;

import android.content.Context;

/**
 * Interface representing a View that will use to load data.
 */
public interface BaseLoadDataView {

  /**
   * Show or hide view with a progress bar indicating a loading process.
   */
  void showLoading(boolean show);

  /**
   * Show an error message
   *
   * @param message A string representing an error.
   */
  void showError(String message);

  /**
   * Get a {@link android.content.Context}.
   */
  Context context();
}
