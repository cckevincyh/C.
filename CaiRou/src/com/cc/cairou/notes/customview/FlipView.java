/*
Copyright 2012 Aphid Mobile

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.cc.cairou.notes.customview;



import com.cc.cairou.R;
import com.cc.cairou.main.MainActivity;
import com.cc.cairou.notes.aty.AtyList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FlipView extends LinearLayout {
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==0){
				getContext().startActivity(new Intent(getContext(),AtyList.class));
				((Activity)getContext()).finish();
			}
		};
	};
	private RelativeLayout root;
	private ImageView ivImage;
	private Button btn;

	public FlipView(Context context,int rootId,int imageId) {
		super(context);
		LayoutInflater.from(getContext()).inflate(R.layout.welcome_item, this);
		root=(RelativeLayout) findViewById(R.id.root_welcome);
		ivImage=(ImageView) findViewById(R.id.welcome_image);
		btn=(Button) findViewById(R.id.welcome_button);

		setButtonBackground(context);
		setRootBackground(rootId);
		setImageBackground(imageId);
	}



	public void setButtonBackground(Context context){
		btn.setBackgroundResource(R.drawable.up);
		btn.startAnimation(AnimationUtils.loadAnimation(context, R.anim.welcome_button));

	}

	public void setRootBackground(final int id){
		root.setBackgroundResource(id);
		if(id==R.drawable.widget_4x_yellow){
			btn.clearAnimation();
			
			btn.setBackgroundResource(R.drawable.home);
			
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Animation animation=AnimationUtils.loadAnimation(getContext(), R.anim.welcome_home);
					btn.startAnimation(animation);
					animation.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation arg0) {
						}
						@Override
						public void onAnimationRepeat(Animation arg0) {
						}
						@Override
						public void onAnimationEnd(Animation arg0) {
							getContext().startActivity(new Intent(getContext(),MainActivity.class));
							((Activity)getContext()).finish();
						}
					});
					
					
				}
			});
		}
	}

	public void setImageBackground(int id){
		ivImage.setBackgroundResource(id);
	}

	public RelativeLayout getRoot() {
		return root;
	}
	public void setRoot(RelativeLayout root) {
		this.root = root;
	}
	public ImageView getIvImage() {
		return ivImage;
	}
	public void setIvImage(ImageView ivImage) {
		this.ivImage = ivImage;
	}
	public Button getBtn() {
		return btn;
	}
	public void setBtn(Button btn) {
		this.btn = btn;
	}



}
