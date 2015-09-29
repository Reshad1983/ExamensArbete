package dv606.lecture2;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class ActionBars extends Activity {
	private ListView listView;
    private MyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_bar);


        /* Manual Enabling Home Navigation */
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        listView = (ListView)findViewById(R.id.listView);
        adapter = new MyAdapter(this,R.layout.list_item);
        
        /* Attach context menu on list */
        registerForContextMenu(listView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// An XML based menu specification.
    	// See res/menu/action_menu.xml for details
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.add_color: 
        	MyColor color = MyColor.randomColor();
            adapter.add(color); // Add new color to data list
            listView.setAdapter(adapter);   // Forces ListView update
            return true;
        case R.id.add_integer:
        	int n = new Random().nextInt(1000);
        	adapter.add(new Integer(n));  // Add new integer to data list
        	listView.setAdapter(adapter);   // Forces ListView update
            return true;
        case android.R.id.home:  // Predefined icon ID
            // app icon in action bar clicked ==>  go home
            Intent intent = new Intent(this, MainList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        } 
    }
    
    /*
     *  Adding three context menus 
     */
	public static final int RESTORE = 0;
	public static final int TEN = 1;
	public static final int TWENTY = 2;
	
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, 
    		                        ContextMenu.ContextMenuInfo menuInfo) {  
   	 	 menu.setHeaderTitle("Select List Row Separation");	
    	 menu.add(0, TEN, 0, "10 pixels");
    	 menu.add(0, TWENTY, 0, "20 pixels");
    	 menu.add(0, RESTORE, 0, "Restore originals");
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {  	
    	 switch (item.getItemId()) {
    	 case RESTORE:
          	listView.setDividerHeight(1);
            return true;
         case TEN:
         	listView.setDividerHeight(10);
            return true;
         case TWENTY:
         	listView.setDividerHeight(20);
            return true;
         default:
             return super.onContextItemSelected(item);
    	 }
    }
        
        /*
         * Adapter to populate list with colors and integers 
         */
        class MyAdapter  extends ArrayAdapter<Object> {
        	
        	public MyAdapter(Context context, int textViewResourceId) {
        		super(context,textViewResourceId);
        	}
        	
        	@Override   // Called when updating the ListView
        	public View getView(int position, View convertView, ViewGroup parent) {
        		/* Reuse super handling ==> A TextView from R.layout.list_item */
        		TextView tv = (TextView) super.getView(position,convertView,parent); 
        		
        		/* Find corresponding entry */
        		Object obj = getItem(position);
        		
        		if (obj instanceof MyColor) { 
        			/* Update TextView with color information */
        			MyColor col = (MyColor) obj;
        			tv.setText(col.getColorString());
        			tv.setBackgroundColor(col.getColorCode());
        			tv.setTextColor(0xff000000);  // Black
        		}
        		else {
        			/* Update TextView with integer information */
        			tv.setText("Integer: "+obj.toString());
        			tv.setBackgroundColor(0xffffffff);
        			tv.setTextColor(0xff000000);  // Black
        		}
        		return tv;		
        	}
        }
}
