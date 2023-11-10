package com.example.dashboard_ededdneddy_barangayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class home_activity extends Activity {

	private Button button;
	private View _bg__home_ek2;
	private View _bg__android_status_bar_ek1;
	private View _bg__icons_ek1;
	private ImageView cellular;
	private ImageView wifi;
	private View _bg__battery_ek1;
	private ImageView union;
	private View capacity;
	private TextView _12_30;
	private ImageView setttings_icon_1;
	private ImageView profile_icon_1;
	private View _bg__title_ek1;
	private TextView welcome__user_;
	private View _bg__donate_ek1;
	private View button_ek1;
	private TextView appointment;
	private View _bg__sell_ek1;
	private View button_ek2;
	private View button_ek3;
	private TextView report_suggestion;
	private View _bg__nutritional_guide_ek1;
	private View button_ek4;
	private View button_ek5;
	private TextView locate_brgy__hall;
	private View _bg__nutritional_guide_ek3;
	private View button_ek6;
	private View button_ek7;
	private TextView announcements;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);


		_bg__home_ek2 = (View) findViewById(R.id._bg__home_ek2);
		profile_icon_1 = (ImageView) findViewById(R.id.profile_icon_1);
		_bg__title_ek1 = (View) findViewById(R.id._bg__title_ek1);
		welcome__user_ = (TextView) findViewById(R.id.welcome__user_);
		_bg__donate_ek1 = (View) findViewById(R.id._bg__donate_ek1);
		button_ek1 = (View) findViewById(R.id.button_ek1);
		appointment = (TextView) findViewById(R.id.appointment);
		_bg__sell_ek1 = (View) findViewById(R.id._bg__sell_ek1);
		button_ek2 = (View) findViewById(R.id.button_ek2);
		button_ek3 = (View) findViewById(R.id.button_ek3);
		report_suggestion = (TextView) findViewById(R.id.report_suggestion);
		_bg__nutritional_guide_ek1 = (View) findViewById(R.id._bg__nutritional_guide_ek1);
		button_ek4 = (View) findViewById(R.id.button_ek4);
		button_ek5 = (View) findViewById(R.id.button_ek5);
		locate_brgy__hall = (TextView) findViewById(R.id.locate_brgy__hall);
		_bg__nutritional_guide_ek3 = (View) findViewById(R.id._bg__nutritional_guide_ek3);
		button_ek6 = (View) findViewById(R.id.button_ek6);
		button_ek7 = (View) findViewById(R.id.button_ek7);
		announcements = (TextView) findViewById(R.id.announcements);
	
		
		//custom code goes here
		button = (Button) findViewById(R.id.appointmentButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openactivity_appointment();
			}
		});
	}
	public void openactivity_appointment(){
		Intent intent;
		intent = new Intent(this, Appointment.class);
		startActivity(intent);


	}
}
	
	