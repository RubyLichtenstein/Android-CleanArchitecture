package com.fernandocejas.android10.sample.presentation.ui.main;

import android.os.Bundle;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.ui.base.BaseActivity;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

  //todo to nice welcome screen
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    navigateToUserList();
    finish();
  }

  private void navigateToUserList() {
    this.navigator.navigateToCityList(this);
  }
}
