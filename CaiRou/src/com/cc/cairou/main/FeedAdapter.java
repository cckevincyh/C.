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
    private int type;
    public FeedAdapter(Context context,ExplosionField mExplosionField,int type) {
        this.context = context;
       this.mExplosionField = mExplosionField;
       this.type = type;
      
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
    	
    	
    	int []a = {
        		R.drawable.a1,
        		R.drawable.a2,
        		R.drawable.a3,
        		R.drawable.a4,
        		R.drawable.a5,
        		R.drawable.a6,
        		R.drawable.a7,
        		R.drawable.a8,
        		R.drawable.a9,
        		R.drawable.a10,
        		R.drawable.a11,
        		R.drawable.a12,
        		R.drawable.a13,
        		R.drawable.a14,
        		R.drawable.a15,
        		R.drawable.a16,
        		R.drawable.a17,
        		R.drawable.a18,
        		R.drawable.a19,
        		R.drawable.a20,
        		R.drawable.a21,
        		R.drawable.a22,
        		R.drawable.a23,
        		R.drawable.a24,
        		R.drawable.a25,
        		R.drawable.a26,
        		R.drawable.a27,
        		R.drawable.a28,
        		R.drawable.a29,
        		R.drawable.a30,
        		R.drawable.a31,
        		R.drawable.a32,
        		R.drawable.a33,
        		R.drawable.a34,
        		R.drawable.a35,
        		R.drawable.a36,
        		R.drawable.a37,
        		R.drawable.a38,
        		R.drawable.a39,
        		R.drawable.a40,
        		R.drawable.a41,
        		R.drawable.a42,
        		R.drawable.a43,
        		R.drawable.a43,
        		R.drawable.a44,
        		R.drawable.a45,
        		R.drawable.a46,
        		R.drawable.a47,
        		R.drawable.a48,
        		R.drawable.a49
        	};
    	
    	
    	int z[] = {
    			R.drawable.z1,
        		R.drawable.z2,
        		R.drawable.z3,
        		R.drawable.z4,
        		R.drawable.z5,
        		R.drawable.z6,
        		R.drawable.z7,
        		R.drawable.z8,
        		R.drawable.z9,
        		R.drawable.z10,
        		R.drawable.z11,
        		R.drawable.z12,
        		R.drawable.z13,
        		R.drawable.z14,
        		R.drawable.z15,
        		R.drawable.z16,
        		R.drawable.z17,
        		R.drawable.z18,
        		R.drawable.z19,
        		R.drawable.z20,
        		R.drawable.z21,
        		R.drawable.z22,
        		R.drawable.z23,
        		R.drawable.z24,
        		R.drawable.z25,
        		R.drawable.z26,
        		R.drawable.z27,
        		R.drawable.z28,
        		R.drawable.z29,
        		R.drawable.z30,
        		R.drawable.z31,
        		R.drawable.z32,
        		R.drawable.z33,
        		R.drawable.z34,
        		R.drawable.z35,
        		R.drawable.z36,
        		R.drawable.z37,
        		R.drawable.z38,
        		R.drawable.z39,
        		R.drawable.z40,
        		R.drawable.z41,
        		R.drawable.z42,
        		R.drawable.z43,
        		R.drawable.z43,
        		R.drawable.z44,
        		R.drawable.z45,
        		R.drawable.z46,
        		R.drawable.z47,
        		R.drawable.z48,
        		R.drawable.z49,
        		R.drawable.z50,
        		R.drawable.z51,
        		R.drawable.z52,
        		R.drawable.z53,
        		R.drawable.z54,
        		R.drawable.z55,
        		R.drawable.z56,
        		R.drawable.z57,
        		R.drawable.z58,
        		R.drawable.z59,
        		R.drawable.z60,
        		R.drawable.z61,
        		R.drawable.z62,
        		R.drawable.z63,
        		R.drawable.z64,
        		R.drawable.z65,
        		R.drawable.z66,
        		R.drawable.z67,
        		R.drawable.z68,
        		R.drawable.z69,
        		R.drawable.z70,
        		R.drawable.z71,
        		R.drawable.z72,
        		R.drawable.z73,
        		R.drawable.z74,
        		R.drawable.z75,
        		R.drawable.z76,
        		R.drawable.z77,
        		R.drawable.z78,
        		R.drawable.z79,
        		R.drawable.z80,
        		R.drawable.z81,
        		R.drawable.z82,
        		R.drawable.z83,
        		R.drawable.z84,
        		R.drawable.z85,
        		R.drawable.z86,
        		R.drawable.z87,
        		R.drawable.z88,
        		R.drawable.z89,
        		R.drawable.z90,
        		R.drawable.z91,
        		R.drawable.z92,
        		R.drawable.z93,
        		R.drawable.z94,
        		R.drawable.z95,
        		R.drawable.z96,
        		R.drawable.z97,
        		R.drawable.z98,
        		R.drawable.z99,
        		R.drawable.z100,
        		R.drawable.z101,
        		R.drawable.z102,
        		R.drawable.z103,
        		R.drawable.z104	
    	};
    	
    	int d[] = {
    			R.drawable.d01,
        		R.drawable.d02,
        		R.drawable.d03,
        		R.drawable.d04,
        		R.drawable.d05,
        		R.drawable.d06,
        		R.drawable.d09,
        		R.drawable.d10,
        		R.drawable.d11,
        		R.drawable.d12,
        		R.drawable.d14,
        		R.drawable.d15,
        		R.drawable.d16,
        		R.drawable.d17,
        		R.drawable.d18,
        		R.drawable.d20,
        		R.drawable.d21,
        		R.drawable.d22,
        		R.drawable.d23,
        		R.drawable.d24,
        		R.drawable.d25,
        		R.drawable.d26,
        		R.drawable.d27,
        		R.drawable.d29,
        		R.drawable.d30,
        		R.drawable.d31,
        		R.drawable.d32,
        		R.drawable.d34,
        		R.drawable.d35,
        		R.drawable.d36,
        		R.drawable.d37,
        		R.drawable.d38,
        		R.drawable.d39,
        		R.drawable.d40,
        		R.drawable.d41,
        		R.drawable.d42,
        		R.drawable.d43,
        		R.drawable.d43,
        		R.drawable.d44,
        		R.drawable.d45,
        		R.drawable.d46,
        		R.drawable.d47,
    	};
    	
    	
    	int []g = {
    			R.drawable.g1,
        		R.drawable.g2,
        		R.drawable.g3,
        		R.drawable.g4,
        		R.drawable.g5,
        		R.drawable.g6,
        		R.drawable.g7,
        		R.drawable.g8,
        		R.drawable.g9,
        		R.drawable.g10,
        		R.drawable.g11,
        		R.drawable.g12,
        		R.drawable.g13,
        		R.drawable.g14,
        		R.drawable.g15,
        		R.drawable.g16,
        		R.drawable.g17,
        		R.drawable.g18,
        		R.drawable.g19,
        		R.drawable.g20,
        		R.drawable.g21,
        		R.drawable.g22,
        		R.drawable.g23,
        		R.drawable.g24,
        		R.drawable.g25,
        		R.drawable.g26,
        		R.drawable.g27,
        		R.drawable.g28,
        		R.drawable.g29,
        		R.drawable.g30,
        		R.drawable.g31,
        		R.drawable.g32,
        		R.drawable.g33,
        		R.drawable.g34,
        		R.drawable.g35,
        		R.drawable.g36,
        		R.drawable.g37,
        		R.drawable.g38,
        		R.drawable.g39,
        		R.drawable.g40,
        		R.drawable.g41,
        		R.drawable.g42,
        		R.drawable.g43,
        		R.drawable.g43,
        		R.drawable.g44,
        		R.drawable.g45,
        		R.drawable.g46,
        		R.drawable.g47,
        		R.drawable.g48,
        		R.drawable.g49,
        		R.drawable.g50,
        		R.drawable.g51,
        		R.drawable.g52,
        		R.drawable.g53,
        		R.drawable.g54,
        		R.drawable.g55,
        		R.drawable.g56,
        		R.drawable.g58,
        		R.drawable.g59,
        		R.drawable.g60,
        		R.drawable.g61,
        		R.drawable.g62,
        		R.drawable.g63,
        		R.drawable.g64,
        		R.drawable.g65,
        		R.drawable.g66,
        		R.drawable.g67,
        		R.drawable.g68,
        		R.drawable.g69,
        		R.drawable.g70,
        		R.drawable.g71,
        		R.drawable.g72,
        		R.drawable.g73,
        		R.drawable.g74,
        		R.drawable.g75,
        		R.drawable.g76,
        		R.drawable.g77,
        		R.drawable.g78,
        		R.drawable.g79,
        		R.drawable.g80,
        		R.drawable.g81,
        		R.drawable.g82,
        		R.drawable.g83,
        		R.drawable.g84,
        		R.drawable.g85,
        		R.drawable.g86,
        		R.drawable.g87,
        		R.drawable.g88,
        		R.drawable.g89,
        		R.drawable.g90,
        		R.drawable.g91,
        		R.drawable.g92,
        		R.drawable.g93,
        		R.drawable.g94,
        		R.drawable.g95,
        		R.drawable.g96,
        		R.drawable.g97,
        		R.drawable.g98,
        		R.drawable.g99,
        		R.drawable.g100
    	};
    	
    	
    	Random random = new Random();
    	if(type==1){
    	  holder.ivFeedCenter.setImageResource(p[random.nextInt(p.length)]);
    	}
    	if(type==2){
    		holder.ivFeedCenter.setImageResource(a[random.nextInt(a.length)]);
      	}
    	if(type==3){
	    	holder.ivFeedCenter.setImageResource(z[random.nextInt(z.length)]);
    	}
    	if(type==4){
    		holder.ivFeedCenter.setImageResource(d[random.nextInt(d.length)]);
    	}
    	if(type==5){
    		holder.ivFeedCenter.setImageResource(g[random.nextInt(g.length)]);
    	}
    	
    	  holder.ivFeedCenter.setTag(holder);
    	  
    }

    public void updateItems() {
        itemsCount = 8;
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
