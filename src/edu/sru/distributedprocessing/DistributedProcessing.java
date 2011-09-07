package edu.sru.distributedprocessing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DistributedProcessing extends Activity {
    private Button truck_btn, company_btn, button3, button4, button5;
    private TextView header_txt;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipping_main);
        
        header_txt = (TextView) findViewById(R.id.header_txt);
        
        truck_btn = (Button) findViewById(R.id.truck_btn);
        truck_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Toast.makeText(DistributedProcessing.this, "Truck Button Selected", Toast.LENGTH_SHORT).show();
				header_txt.setText("Truck Info:");
			}
		});
        
        company_btn = (Button) findViewById(R.id.company_btn);
        company_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Toast.makeText(DistributedProcessing.this, "Company Button Selected", Toast.LENGTH_SHORT).show();
				header_txt.setText("Company Info:");
			}
        });
        
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Toast.makeText(DistributedProcessing.this, "Button 3 Selected", Toast.LENGTH_SHORT).show();
				header_txt.setText("Button3 Info:");
			}
        });
        
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Toast.makeText(DistributedProcessing.this, "Button 4 Selected", Toast.LENGTH_SHORT).show();
				header_txt.setText("Button4 Info:");
			}
        });
        
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Toast.makeText(DistributedProcessing.this, "Button 5 Selected", Toast.LENGTH_SHORT).show();
				header_txt.setText("Button5 Info:");
			}
        });
    }
}