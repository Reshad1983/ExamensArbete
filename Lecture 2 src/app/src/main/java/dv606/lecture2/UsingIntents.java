package dv606.lecture2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UsingIntents extends Activity {
	private Activity main_activity;
	private TextView name_display;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents);
        
        main_activity = this;  // Simplifies Intent handling
        
        /* Assign listener to button */
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new ButtonClick());        
        Button name_button = (Button)findViewById(R.id.name_button);
        name_button.setOnClickListener(new NameButtonClick());
        
        /* Find name display */
        name_display = (TextView) findViewById(R.id.name_display);
    }
    
    private class ButtonClick implements View.OnClickListener {
    	public void onClick(View v) {
    		/* Start new Activity */
    		Intent intent = new Intent(main_activity,Hello.class);
    		main_activity.startActivity(intent);
        }
    }
    
    private class NameButtonClick implements View.OnClickListener {
    	public void onClick(View v) {
    		/* Start new Activity that returns a result */
    		Intent intent = new Intent(main_activity,ReadName.class);
    		main_activity.startActivityForResult(intent,0);
        }
    }
    
    /** Called when the activity receives a results. */
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent result) {
    	if (resultCode == RESULT_OK) {
    		String name = result.getStringExtra("result");
    		name_display.setText(name);
    	}
    }
}