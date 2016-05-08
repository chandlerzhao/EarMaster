package org.neo.earmaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ExpandableListView listView;
	private String[] funcType = {"音程练习", "和弦练习","听音练习","绝对音准"};
	private String[][] func ={{"音程比较","音程辨认","连续音程辨认","转调音程"},
							  {"三和弦辨认","七和弦辨认","三和弦转位","七和弦转位",
								"三和弦综合","七和弦综合","大调和弦辨认","转调和弦"},
							  {"绝对音准视唱","绝对音准练耳"}
							  };
	private static final String ACTIVITY_TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ExpandableListView) this.findViewById(R.id.expandableListView1);
		MyExpandableListAdapter adapter = new MyExpandableListAdapter();
		listView.setAdapter(adapter);
		int groupCount = listView.getCount();
//		for(int i = 0; i < groupCount; i++){
//			listView.expandGroup(i);
//		}
		listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				switch(groupPosition){
				case 0:
					switch(childPosition){
					case 0:
						Intent intent00 = new Intent();
						intent00.setClass(MainActivity.this, IntervalCompare.class);
						startActivity(intent00);
						break;
					case 1:
						Intent intent01 = new Intent();
						intent01.setClass(MainActivity.this, IntervalReco.class);
						startActivity(intent01);
						break;
					case 2:
						Intent intent02 = new Intent();
						intent02.setClass(MainActivity.this, CIntervalReco.class);
						startActivity(intent02);
						break;
					case 3:
						Intent intent03 = new Intent();
						intent03.setClass(MainActivity.this, IntervalGamut.class);
						startActivity(intent03);
						break;
					default:
						Log.e(ACTIVITY_TAG, "Select Error");
					}
					break;
				case 1:
					switch(childPosition){
					case 0:
						Intent intent10 = new Intent();
						intent10.setClass(MainActivity.this, ChordReco3.class);
						startActivity(intent10);
						break;
					case 1:
						Intent intent11 = new Intent();
						intent11.setClass(MainActivity.this, ChordReco7.class);
						startActivity(intent11);
						break;
					case 2:
						Intent intent12 = new Intent();
						intent12.setClass(MainActivity.this, ChordTrans3.class);
						startActivity(intent12);
						break;
					case 3:
						Intent intent13 = new Intent();
						intent13.setClass(MainActivity.this, ChordTrans7.class);
						startActivity(intent13);
						break;
					case 4:
						Intent intent14 = new Intent();
						intent14.setClass(MainActivity.this, ChordComp7.class);
						startActivity(intent14);
						break;
					case 5:
						Intent intent15 = new Intent();
						intent15.setClass(MainActivity.this, ChordComp7.class);
						startActivity(intent15);
						break;
					case 6:
						Intent intent16 = new Intent();
						intent16.setClass(MainActivity.this, ChordRecoMaj.class);
						startActivity(intent16);
						break;
					case 7:
						Intent intent17 = new Intent();
						intent17.setClass(MainActivity.this, ChordGamut.class);
						startActivity(intent17);
						break;
					default:
						Log.e(ACTIVITY_TAG, "Select Error");
					}
					break;
				case 2:
					switch(childPosition){
					case 0:
						Intent intent20 = new Intent();
						intent20.setClass(MainActivity.this, AbsoluteSing.class);
						startActivity(intent20);
						break;
					case 1:
						Intent intent21 = new Intent();
						intent21.setClass(MainActivity.this, AbsoluteSing.class);
						startActivity(intent21);
						break;
					default:
						Log.e(ACTIVITY_TAG, "Select Error");
					}
					break;
				default:
					Log.e(ACTIVITY_TAG, "Select Error");
				}
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	class MyExpandableListAdapter implements ExpandableListAdapter{
		
		
		@Override
		public void registerDataSetObserver(DataSetObserver observer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void unregisterDataSetObserver(DataSetObserver observer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return funcType.length;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return func[groupPosition].length;
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return funcType[groupPosition];
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return func[groupPosition][childPosition];
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			String name = funcType[groupPosition];
			TextView textView=getTextView();
			textView.setText(name);
			return textView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			String name = func[groupPosition][childPosition];
			TextView textView = getSubTextView();
			textView.setText(name);
			return textView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean areAllItemsEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onGroupExpanded(int groupPosition) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGroupCollapsed(int groupPosition) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public long getCombinedChildId(long groupId, long childId) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getCombinedGroupId(long groupId) {
			// TODO Auto-generated method stub
			return 0;
		}

		private TextView getTextView(){
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			TextView textView = new TextView(MainActivity.this);
			textView.setLayoutParams(lp);
			textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
			textView.setPadding(96, 0, 0, 0);
			textView.setTextSize(30);
			
			return textView;
		}
		private TextView getSubTextView(){
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			TextView textView = new TextView(MainActivity.this);
			textView.setLayoutParams(lp);
			textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
			textView.setPadding(128, 0, 0, 0);
			textView.setTextSize(25);
			return textView;
		}
	}
}
