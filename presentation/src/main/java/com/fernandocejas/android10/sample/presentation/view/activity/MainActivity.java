package com.fernandocejas.android10.sample.presentation.view.activity;

import android.os.Bundle;
import com.fernandocejas.android10.sample.presentation.R;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

  //todo to nice welcome screen
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    navigateToUserList();
  }

  void navigateToUserList() {
    this.navigator.navigateToCityList(this);
  }
}
