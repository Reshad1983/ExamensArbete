package dv606.lecture2;

import java.util.Random;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UsingMenus extends ListActivity {

    private MyAdapter adapter;  // My own adapter class
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* Setup ListAdapter. Each row is just a TextView */    
        adapter = new MyAdapter(this,R.layout.list_item);
        setListAdapter(adapter);
        
        /* Attach context menu on list */
        registerForContextMenu(getListView());
    }
    
    /*
     *  Adding two option menus 
     */
	public static final int COLOR_MENU = 0;
	public static final int NUMBER_MENU = 1;
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        MenuItem m1 = menu.add(0, COLOR_MENU, 0, "Random Color");  // Create menu 1
        m1.setIcon(android.R.drawable.ic_input_add);        // Attach icon
        
        MenuItem m2 = menu.add(0, NUMBER_MENU, 0, "Random Integer");  // Adds a second menu
        m2.setIcon(android.R.drawable.ic_input_add);
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case COLOR_MENU:
        	MyColor color = MyColor.randomColor();
        	adapter.add(color); // Add new color to adapter
            return true;
        case NUMBER_MENU:
        	int n = new Random().nextInt(1000);
        	adapter.add(new Integer(n));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }           
    }
        
    
    
    /*
     *  Adding three context menus 
     */
	public static final int RESTORE = 0;
	public static final int FIVE = 1;
	public static final int TEN = 2;
	
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, 
    		                        ContextMenu.ContextMenuInfo menuInfo) {  
   	 	 menu.setHeaderTitle("Select List Row Separation");	
    	 menu.add(0, FIVE, 0, "5 pixels");
    	 menu.add(0, TEN, 0, "10 pixels");
    	 menu.add(0, RESTORE, 0, "Restore originals");
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {  	
    	 switch (item.getItemId()) {
    	 case RESTORE:
          	getListView().setDividerHeight(1);
            return true;
         case FIVE:
         	getListView().setDividerHeight(5);
            return true;
         case TEN:
         	getListView().setDividerHeight(10);
            return true;
         default:
             return super.onContextItemSelected(item);
    	 }
    }
    
    
    
    /* Used for debugging */
    private void showToast(String msg) {
    	Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    
    /*
     * Adapter to populate list with colors and integers 
     */
    class MyAdapter extends ArrayAdapter<Object> {
    	
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
    		}
    		return tv;		
    	}
    }
}