package com.cc.cairou.main;

import java.util.Random;

import tyrantgit.explosionfield.ExplosionField;

import com.cc.cairou.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;

//中间的适配器
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	
    private Context context;
    private int itemsCount = 0;
    private ExplosionField mExplosionField;
    public FeedAdapter(Context context,ExplosionField mExplosionField) {
        this.context = context;
       this.mExplosionField = mExplosionField;
      
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
     
        return new CellFeedViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final CellFeedViewHolder holder = (CellFeedViewHolder) viewHolder;
        
        bindDefaultFeedItem(position, holder);
        holder.ivFeedCenter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  mExplosionField.explode(v);
                  v.setOnClickListener(null);
			}
		});
    }

    private void bindDefaultFeedItem(int position, CellFeedViewHolder holder) {
    	
    	int []p = {
    		R.drawable.p1,
    		R.drawable.p2,
    		R.drawable.p3,
    		R.drawable.p4,
    		R.drawable.p5,
    		R.drawable.p6,
    		R.drawable.p7,
    		R.drawable.p8,
    		R.drawable.p9,
    		R.drawable.p10,
    		R.drawable.p11,
    		R.drawable.p12,
    		R.drawable.p13,
    		R.drawable.p14,
    		R.drawable.p15,
    		R.drawable.p16,
    		R.drawable.p17,
    		R.drawable.p18,
    		R.drawable.p19,
    		R.drawable.p20,
    		R.drawable.p21,
    		R.drawable.p22,
    		R.drawable.p23,
    		R.drawable.p24,
    		R.drawable.p25,
    		R.drawable.p26,
    		R.drawable.p27,
    		R.drawable.p28,
    		R.drawable.p29,
    		R.drawable.p30,
    		
    	};
    	Random random = new Random();
    	  holder.ivFeedCenter.setImageResource(p[random.nextInt(30)]);
    	  holder.ivFeedCenter.setTag(holder);
    	  
    }

    public void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        
    	ImageView ivFeedCenter;
        public CellFeedViewHolder(View view) {
            super(view);

            ivFeedCenter = (ImageView) view.findViewById(R.id.imge);
        }
    } 
    
    
    
 

  
   
 


  
    
}
