package com.school.jazy;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SchoolXMLGetterActivity extends Activity implements
		OnClickListener {
	/** Called when the activity is first created. */
	static Button _refresh_button;
	static TextView _main_text;
	static boolean updating = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		_refresh_button = (Button) this.findViewById(R.id.refreshButton);
		_main_text = (TextView) this.findViewById(R.id.editText1);

		_refresh_button.setOnClickListener(this);

	}

	public void onClick(View arg0) {
		if (updating)
			return;
		updating = true; //TODO DONT USE FILE CHANGE TO ANDROID BUILT IN METHODS!!!
		String host_name = getString(R.string.host_name);
		int host_port = Integer.parseInt(getString(R.string.host_port));
		ServerConnection sc = new ServerConnection("http",host_name,host_port,"JazyXML.xml",this);
		File xmlfile = sc.getXMLFile();
		if (xmlfile==null) {
			_main_text.setText("Error getting file...");
			Log.d("JAZY","xmlFile is null");
			return;
		}
		processXMLFile pc = new processXMLFile(xmlfile,this);
		pc.parse();
		
		_main_text.setText(pc.toString());
	}

}