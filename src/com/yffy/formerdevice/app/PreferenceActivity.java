package com.yffy.formerdevice.app;

import com.yffy.formerdevice.R;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.view.Window;
import android.view.WindowManager;

public class PreferenceActivity extends android.preference.PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,Preference preference) {
	
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
	
}
