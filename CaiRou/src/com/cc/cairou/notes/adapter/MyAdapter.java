package com.cc.cairou.notes.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cc.cairou.R;
import com.cc.cairou.notes.model.Notes;
import com.cc.cairou.notes.utils.Utils;



import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter implements Filterable{
	private Context context;
	private List<Notes> myforeverlist;//不变
	
	private List<Notes> mFilterArrayList;//过滤
	private List<Notes> myNotesList;//给adapter
	
	public MyAdapter(Context pContext,List<Notes> pList){
		this.context=pContext;
		this.myforeverlist=pList;
		
		this.mFilterArrayList=new ArrayList<Notes>();
		this.myNotesList=new ArrayList<Notes>();
		myNotesList.addAll(myforeverlist);
	}
	
	class Holder{
		ImageView ivRight;
		TextView tvContent,tvTime;
	}
	
	@Override
	public int getCount() {
		return this.myNotesList!=null? this.myNotesList.size(): 0 ;
	}

	@Override
	public Object getItem(int position) {
		return this.myNotesList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Holder holder=null;
		if(view==null){
			Context context=parent.getContext();
			view=LayoutInflater.from(context).inflate(R.layout.list_item, null);
			holder=new Holder();
			holder.ivRight=(ImageView) view.findViewById(R.id.list_item_right);
			holder.tvContent=(TextView) view.findViewById(R.id.list_item_tv_text);
			holder.tvTime=(TextView) view.findViewById(R.id.list_item_time);
			view.setTag(holder);
		}else{
			holder=(Holder) view.getTag();
			
		}
		Notes notes=(Notes) this.myNotesList.get(position);
		holder.ivRight.setBackgroundResource(R.drawable.ic_chevron_right_black_24dp);
		holder.tvContent.setText(notes.getContent());
		holder.tvTime.setText(Utils.getDeltaTime(notes));
		
		return view;
	}

	private NotesFilter notesFilter;
	
	@Override
	public Filter getFilter() {
		if(notesFilter==null){
			notesFilter=new NotesFilter();
		}
		return notesFilter;
	}
	
	class NotesFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			
			FilterResults filterResults=new FilterResults();
			
			if(TextUtils.isEmpty(constraint)){
				mFilterArrayList.clear();
				filterResults.values=myforeverlist;
				return filterResults;
			}else{
				mFilterArrayList.clear();
				for(Notes notes:myforeverlist){
					if(notes.getContent().contains(constraint)||notes.getContent().toUpperCase().startsWith(constraint.toString().toUpperCase())){
						mFilterArrayList.add(notes);
					}
				}
				filterResults.values=mFilterArrayList;
				filterResults.count=mFilterArrayList.size();
				return filterResults;
			}
			
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			myNotesList=(List<Notes>) results.values;
			if(results.count>0){
				notifyDataSetChanged();
			}else{
				notifyDataSetInvalidated();
			}
		}
		
	}

}
