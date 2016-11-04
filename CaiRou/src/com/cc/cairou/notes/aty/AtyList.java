package com.cc.cairou.notes.aty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cc.cairou.R;
import com.cc.cairou.notes.adapter.MyAdapter;
import com.cc.cairou.notes.model.DatabaseHelper;
import com.cc.cairou.notes.model.Notes;
import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonIcon;
import com.gc.materialdesign.widgets.Dialog;

import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class AtyList extends AtyBase implements 
SlideAndDragListView.OnListItemLongClickListener,
SlideAndDragListView.OnDragListener, 
SlideAndDragListView.OnSlideListener,
SlideAndDragListView.OnListItemClickListener, 
SlideAndDragListView.OnMenuItemClickListener,
SlideAndDragListView.OnItemDeleteListener{

	private Menu mMenu;
	private List<Notes> notesList=null;
	private MyAdapter adapter;

	private SlideAndDragListView<Notes> mListView;
	private EditText etSearch;
	private ButtonFloat bfButton;
	private ButtonIcon biButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_list);
		initViews();

	}

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initViews(){
		//绑定资源
		etSearch=(EditText) findViewById(R.id.aty_list_et_search);
//		menu=(SatelliteMenu) findViewById(R.id.menu);
		bfButton=(ButtonFloat) findViewById(R.id.buttonFloat);
		biButton=(ButtonIcon) findViewById(R.id.aty_list_btn_buttonIcon);
		mListView=(SlideAndDragListView) findViewById(R.id.aty_list_listview);
		
		
		//初始化监听器
		bfButton.setOnClickListener(this);
		biButton.setOnClickListener(this);
		etSearch.addTextChangedListener(filterTextWatcher);





		//初始化滑动删除部分
		mMenu = new Menu((int) getResources().getDimension(R.dimen.slv_item_height), new ColorDrawable(Color.LTGRAY), false);

		MenuItem builder1 = new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width)*2)
				.setBackground(new ColorDrawable(Color.RED))
				.setText("删除")
				.setDirection(MenuItem.DIRECTION_RIGHT)
				.setTextColor(Color.WHITE)
				.setTextSize((int) getResources().getDimension(R.dimen.txt_size))
				.build();

		mMenu.addItem(builder1);

		//初始化notesList和adapter,notesList从数据库里取
		getAllDataFromDatabase();
		adapter=new MyAdapter(this,notesList);

		mListView.setMenu(mMenu);
		mListView.setAdapter(adapter);
		mListView.setOnSlideListener(this);
		mListView.setOnListItemLongClickListener(this);
		mListView.setOnListItemClickListener(this);
		mListView.setOnMenuItemClickListener(this);
		mListView.setOnItemDeleteListener(this);

	}

	private TextWatcher filterTextWatcher=new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			startQuery(s.toString());
			adapter.notifyDataSetInvalidated();
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}
		
		@Override
		public void afterTextChanged(Editable s) {
		}
		
	};
	
	private void startQuery(String text){
		List<Notes> tempList=new ArrayList<Notes>();
		List<Notes> lists=DatabaseHelper.getInstance(AtyList.this).queryAll();
		for(Notes notes:lists){
			if(notes.getContent().contains(text)){
				tempList.add(notes);
			}
		}
		notesList.clear();
		notesList.addAll(tempList);
		MyAdapter mAdapter=new MyAdapter(this,notesList);
		mListView.setAdapter(mAdapter);
	}
	
	private void reloadList(){

		notesList.clear();
		getAllDataFromDatabase();
		MyAdapter mAdapter=new MyAdapter(this,notesList);
		mListView.setAdapter(mAdapter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		reloadList();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonFloat:
			addNewNote();
			break;
		case R.id.aty_list_btn_buttonIcon:
			startQuery(etSearch.getText().toString());
			break;
		default:
			break;
		}
	}

	//点击add按钮添加新便签
	private  void addNewNote(){
		Intent intent=new Intent();
		intent.setClass(this, AtyCreate.class);
		startActivity(intent);
	}



	//从数据库里取出所有的数据填充notesList
	private void getAllDataFromDatabase(){
		notesList=DatabaseHelper.getInstance(this).queryAll();
	}

	




	public View getViewByPosition(int pos, SlideAndDragListView<Notes> listView) {
		final int firstListItemPosition = listView.getFirstVisiblePosition();
		final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

		if (pos < firstListItemPosition || pos > lastListItemPosition ) {
			return listView.getAdapter().getView(pos, null, listView);
		} else {
			final int childIndex = pos - firstListItemPosition;
			return listView.getChildAt(childIndex);
		}
	}

	@Override
	public void onItemDelete(View arg0, int position) {

		if(notesList.size()!=0){
			Notes notes=(Notes) notesList.get(position);
			DatabaseHelper.getInstance(AtyList.this).delete(notes);
		}
		reloadList();
	}

	@Override
	public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
		Log.i("sheldon", "onMenuItemClick   " + itemPosition + "   " + buttonPosition + "   " + direction);
		switch (direction) {
		case MenuItem.DIRECTION_RIGHT:
			switch (buttonPosition) {
			case 0://删除该项
				return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
			}
		}
		return Menu.ITEM_NOTHING;
	}
	


	@Override
	public void onListItemLongClick(View view, int position) {
		//	        Toast.makeText(DemoActivity.this, "onItemLongClick   position--->" + position, Toast.LENGTH_SHORT).show();
		Log.i("yuyidong", "onListItemLongClick   " + position);
		//handler.sendEmptyMessage(0);
	}

	@Override
	public void onDragViewStart(int position) {
		//	        Toast.makeText(DemoActivity.this, "onDragViewStart   position--->" + position, Toast.LENGTH_SHORT).show();
		Log.i("yuyidong", "onDragViewStart   " + position);
	}

	@Override
	public void onDragViewMoving(int position) {
		//	        Toast.makeText(DemoActivity.this, "onDragViewMoving   position--->" + position, Toast.LENGTH_SHORT).show();
		Log.i("yuyidong", "onDragViewMoving   " + position);
	}

	@Override
	public void onDragViewDown(int position) {
		//	        Toast.makeText(DemoActivity.this, "onDragViewDown   position--->" + position, Toast.LENGTH_SHORT).show();
		Log.i("yuyidong", "onDragViewDown   " + position);
	}

	@Override
	public void onListItemClick(View v, int position) {
		//	        Toast.makeText(DemoActivity.this, "onItemClick   position--->" + position, Toast.LENGTH_SHORT).show();
		Log.i("yuyidong", "onListItemClick   " + position);
		Intent intent=new Intent(AtyList.this,AtyDetail.class);
		Bundle bundle=new Bundle();
		bundle.putSerializable("notesList", (Serializable) notesList);
		intent.putExtra("bundle", bundle);
		intent.putExtra("position", position);
		startActivity(intent);
	}

	@Override
	public void onSlideOpen(View view, View parentView, int position, int direction) {
		//	        Toast.makeText(DemoActivity.this, "onSlideOpen   position--->" + position + "  direction--->" + direction, Toast.LENGTH_SHORT).show();
		Log.i("yuyidong", "onSlideOpen   " + position);
	}

	@Override
	public void onSlideClose(View view, View parentView, int position, int direction) {
		//	        Toast.makeText(DemoActivity.this, "onSlideClose   position--->" + position + "  direction--->" + direction, Toast.LENGTH_SHORT).show();
		Log.i("yuyidong", "onSlideClose   " + position);
	}
}
