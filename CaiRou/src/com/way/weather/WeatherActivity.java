package com.way.weather;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.cc.cairou.R;
import com.google.gson.Gson;
import com.way.apapter.WeatherPagerAdapter;
import com.way.app.Application;
import com.way.bean.City;
import com.way.bean.HeWeatherdataserviceBean;
import com.way.bean.TmpBean;
import com.way.bean.WeatherInfoBean;
import com.way.bean.WindBean;
import com.way.db.CityDB;
import com.way.fragment.FirstWeatherFragment;
import com.way.fragment.SecondWeatherFragment;
import com.way.indicator.CirclePageIndicator;
import com.way.util.ConfigCache;
import com.way.util.IphoneDialog;
import com.way.util.L;
import com.way.util.NetUtil;
import com.way.util.SharePreferenceUtil;
import com.way.util.T;
import com.way.util.TimeUtil;

public class WeatherActivity extends FragmentActivity implements
		Application.EventHandler, OnClickListener {
	public static final String UPDATE_WIDGET_WEATHER_ACTION = "com.way.action.update_weather";
	public static final String WEATHER_URL = "https://api.heweather.com/x3/weather?cityid=";
	public static final String KEY = "??????????????????????????????????";//我的秘钥，需要删除的
	private static final String WEATHER_INFO_FILENAME = "_weather.json";
	private static final String SIMPLE_WEATHER_INFO_FILENAME = "_simple_weather.json";
	private static final String PM2D5_INFO_FILENAME = "_pm2d5.json";
	private static final int LOACTION_OK = 0;	
	private static final int ON_NEW_INTENT = 1;
	private static final int UPDATE_EXISTS_CITY = 2;
	private static final int GET_WEATHER_RESULT = 3;
	private LocationClient mLocationClient;
	private CityDB mCityDB;
	private SharePreferenceUtil mSpUtil;
	private Application mApplication;
	private City mCurCity;
	private WeatherInfoBean mWeatherInfoBean;	//天气信息

	private ImageView mCityManagerBtn, mUpdateBtn, mLocationBtn, mShareBtn;
	private ProgressBar mUpdateProgressBar;
	private TextView mTitleTextView;
	private City mNewIntentCity;
	private WeatherPagerAdapter mWeatherPagerAdapter;

	private TextView cityTv, timeTv, humidityTv, weekTv, pmDataTv, pmQualityTv,
			temperatureTv, climateTv, windTv;
	private ImageView weatherImg, pmImg;;
	private ViewPager mViewPager;
	private List<Fragment> fragments;
	private TextView mWeatherMess;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOACTION_OK:

				String cityName = (String) msg.obj;
				L.i("cityName = " + cityName);
				mCurCity = mCityDB.getCity(cityName);
				L.i(mCurCity.toString());
				mSpUtil.setCity(mCurCity.getCity_area());
				cityTv.setText(mCurCity.getCity_area());
				updateWeather(true);
				break;
			case ON_NEW_INTENT:
				mCurCity = mNewIntentCity;
				mSpUtil.setCity(mCurCity.getCity_area());
				cityTv.setText(mCurCity.getCity_area());
				updateWeather(true);
				break;
			case UPDATE_EXISTS_CITY:
				String sPCityName = mSpUtil.getCity();
				mCurCity = mCityDB.getCity(sPCityName);
				updateWeather(false);
				break;
			case GET_WEATHER_RESULT:
				updateWeatherInfo();
				updatePm2d5Info();
				updateWidgetWeather();
				mUpdateBtn.setVisibility(View.VISIBLE);
				mUpdateProgressBar.setVisibility(View.GONE);
				break;
			default:
				break;
			}
		}

	};

	/**
	 * 更新窗体小部件
	 */
	private void updateWidgetWeather() {
		sendBroadcast(new Intent(UPDATE_WIDGET_WEATHER_ACTION));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_main);
		initData();
		initView();
	}

	//启动选择城市界面，将会返回选择的城市的结果
	private void startActivityForResult() {
		Intent i = new Intent(this, SelectCtiyActivity.class);
		startActivityForResult(i, 0);
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		mCityManagerBtn = (ImageView) findViewById(R.id.title_city_manager);
		mUpdateBtn = (ImageView) findViewById(R.id.title_update_btn);
		mShareBtn = (ImageView) findViewById(R.id.title_share);
		mLocationBtn = (ImageView) findViewById(R.id.title_location);
		mCityManagerBtn.setOnClickListener(this);
		mUpdateBtn.setOnClickListener(this);
		mShareBtn.setOnClickListener(this);
		mLocationBtn.setOnClickListener(this);
		mShareBtn.setVisibility(View.GONE);
		mUpdateProgressBar = (ProgressBar) findViewById(R.id.title_update_progress);
		mTitleTextView = (TextView) findViewById(R.id.title_city_name);

		cityTv = (TextView) findViewById(R.id.city);
		timeTv = (TextView) findViewById(R.id.time);
		timeTv.setText(TimeUtil.getDay(mSpUtil.getTimeSamp())
				+ mSpUtil.getTime() + "发布");
		humidityTv = (TextView) findViewById(R.id.humidity);
		weekTv = (TextView) findViewById(R.id.week_today);
		weekTv.setText("今天 " + TimeUtil.getWeek(0, TimeUtil.XING_QI));
		pmDataTv = (TextView) findViewById(R.id.pm_data);
		pmQualityTv = (TextView) findViewById(R.id.pm2_5_quality);
		pmImg = (ImageView) findViewById(R.id.pm2_5_img);
		temperatureTv = (TextView) findViewById(R.id.temperature);
		climateTv = (TextView) findViewById(R.id.climate);
		windTv = (TextView) findViewById(R.id.wind);
		weatherImg = (ImageView) findViewById(R.id.weather_img);
		fragments = new ArrayList<Fragment>();
		fragments.add(new FirstWeatherFragment());
		fragments.add(new SecondWeatherFragment());
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mWeatherMess = (TextView) findViewById(R.id.weatherMessage);
		mWeatherPagerAdapter = new WeatherPagerAdapter(
				getSupportFragmentManager(), fragments);
		mViewPager.setAdapter(mWeatherPagerAdapter);
		((CirclePageIndicator) findViewById(R.id.indicator))
				.setViewPager(mViewPager);
		if (TextUtils.isEmpty(mSpUtil.getCity())) {
			if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
				mLocationClient.start();
				mLocationClient.requestLocation();
				T.showShort(this, "正在定位...");
				mUpdateBtn.setVisibility(View.GONE);
				mUpdateProgressBar.setVisibility(View.VISIBLE);
			} else {
				T.showShort(this, R.string.net_err);
			}
		} else {
			mHandler.sendEmptyMessage(UPDATE_EXISTS_CITY);
		}
	}

	
	/**
	 * 初始化数据
	 */
	private void initData() {
		Application.mListeners.add(this);
		mApplication = Application.getInstance();
		mSpUtil = mApplication.getSharePreferenceUtil();
		mLocationClient = mApplication.getLocationClient();

		mLocationClient.registerLocationListener(mLocationListener);
		mCityDB = mApplication.getCityDB();
		
	}

	/**
	 * 更新天气
	 * @param isRefresh
	 */
	private void updateWeather(final boolean isRefresh) {
		if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE && isRefresh) {
			T.showLong(this, R.string.net_err);
			return;
		}
		if (mCurCity == null) {
			T.showLong(mApplication, "未找到此城市,请重新定位或选择...");
			return;
		}
		// T.showShort(this, "正在刷新天气...");
		timeTv.setText("同步中...");
		mTitleTextView.setText(mCurCity.getCity_area() + "天气");
		mUpdateBtn.setVisibility(View.GONE);
		mUpdateProgressBar.setVisibility(View.VISIBLE);
		// 启动线程获取天气信息
		new Thread() {
			@Override
			public void run() {
				super.run();
				getWeatherInfo(isRefresh);

				if (mWeatherInfoBean != null)
					L.i(mWeatherInfoBean.toString());
				
				mHandler.sendEmptyMessage(GET_WEATHER_RESULT);
			}

		}.start();
	}

	/**
	 * 得到天气信息(重点)
	 * @param isRefresh
	 */
	private void getWeatherInfo(boolean isRefresh) {
		//得到url地址，去获取天气信息
		String url = WEATHER_URL + mCurCity.getCity_id() + KEY;
		String result;
		if (!isRefresh) {//是否是新刷新的，不是的话直接读取已经有的天气信息
			if (mApplication.getmWeatherInfoBean() != null) {// 读取内存中的信息
				mWeatherInfoBean = mApplication.getmWeatherInfoBean();//设置为当前天气信息
				L.i("get the weather info from memory");
				return;// 直接返回，不继续执行
			}
			// result = getInfoFromFile(WEATHER_INFO_FILENAME);// 文件中的信息
			//如果内存中没有天气信息，读取缓存中的天气信息字符串
			result = ConfigCache.getUrlCache(url);
			//若读取出来的缓存信息不为空，则开始解析
			if (!TextUtils.isEmpty(result)) {
				//解析天气信息
				//参数信息:url是请求天气的地址
				//result:从缓存读取出来的天气信息字符串
				//false:这个时候是没有刷新的情况
				parseWeatherInfo(url, result, false);
				L.i("get the weather info from file");
				return;
			}
		}

		//以下是重新刷新，重新请求服务器获取天气信息
		// L.i("weather url: " + url);
		//请求服务器，返回json字符串
		String weatherResult = request(url);
		if (TextUtils.isEmpty(weatherResult))
			//如果请求不到，则从文件中获取
			weatherResult = getInfoFromFile(WEATHER_INFO_FILENAME);
		//解析天气信息
		parseWeatherInfo(url, weatherResult, true);
	}


	
	/**
	 * 解析天气信息
	 * @param url 
	 * @param result 
	 * @param isRefreshWeather
	 */
	private void parseWeatherInfo(String url, String result,
			boolean isRefreshWeather) {
		mWeatherInfoBean = null;//当前天气信息为空
		mApplication.setmWeatherInfoBean(null);
		if (!TextUtils.isEmpty(result)) {
			// L.i(result);
			WeatherInfoBean weather = new Gson().fromJson(result, WeatherInfoBean.class);
			mWeatherInfoBean = weather;
			// L.i(mCurWeatherinfo.toString());
		} else {
			result = "";
		}
		if (isRefreshWeather && !TextUtils.isEmpty(result))
			// save2File(result, WEATHER_INFO_FILENAME);
			ConfigCache.setUrlCache(result, url);
	}

	
	
	// 把信息保存到文件中
	private boolean save2File(String result, String fileName) {
		try {
			FileOutputStream fos = WeatherActivity.this.openFileOutput(fileName,
					MODE_PRIVATE);
			fos.write(result.toString().getBytes());
			fos.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 从文件中获取信息
	 * 
	 * @param fileName
	 * @return
	 */
	private String getInfoFromFile(String fileName) {
		String result = "";
		try {
			FileInputStream fis = openFileInput(fileName);
			byte[] buffer = new byte[fis.available()];// 本地文件可以实例化buffer，网络文件不可行
			fis.read(buffer);
			result = new String(buffer);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 更新天气界面
	 */
	private void updateWeatherInfo() {
		if (mWeatherInfoBean != null) {
			mApplication.setmWeatherInfoBean(mWeatherInfoBean);// 保存到全局变量中
			HeWeatherdataserviceBean heWeatherdataserviceBean = mWeatherInfoBean.getHeWeatherdataservice().get(0);
			TmpBean tmp = heWeatherdataserviceBean.getDaily_forecast().get(0).getTmp();
			temperatureTv.setText(tmp.getMin()+"°~"+tmp.getMax()+"°");//设置温度
			cityTv.setText(heWeatherdataserviceBean.getBasic().getCity());
			mWeatherMess.setText("今日降雨概率:"+heWeatherdataserviceBean.getDaily_forecast().get(0).getPop()+"%\r\n\r\n"
			+"天气信息:"+heWeatherdataserviceBean.getSuggestion().getComf().getTxt()+"\r\n\r\n"+
					"穿衣信息:"+heWeatherdataserviceBean.getSuggestion().getDrsg().getTxt()+"\r\n\r\n"
					+"感冒信息:"+heWeatherdataserviceBean.getSuggestion().getFlu().getTxt()+"\r\n\r\n"
					+"紫外线信息:"+heWeatherdataserviceBean.getSuggestion().getUv().getTxt());
			WindBean wind2 = heWeatherdataserviceBean.getNow().getWind();
			String wind = wind2.getDir()+" "+wind2.getSc();//风的信息
			if (wind.contains("转")) {
				String[] strs = wind.split("转");
				wind = strs[0];
			}
			windTv.setText(wind);
		
			String climate = heWeatherdataserviceBean.getDaily_forecast().get(0).getCond().getTxt_d();
					
			climateTv.setText("白天:"+heWeatherdataserviceBean.getDaily_forecast().get(0).getCond().getTxt_d()
					+"/夜间:"+heWeatherdataserviceBean.getDaily_forecast().get(0).getCond().getTxt_n());
			mSpUtil.setSimpleClimate(climate);
		
			if (mApplication.getWeatherIconMap().containsKey(climate)) {
				int iconRes = mApplication.getWeatherIconMap().get(climate);
				weatherImg.setImageResource(iconRes);
			} else {
				// do nothing 没有这样的天气图片

			}
			if (mWeatherInfoBean != null) {
				if (!heWeatherdataserviceBean.getBasic().getUpdate().getLoc().equals(mSpUtil.getTime())) {
					mSpUtil.setTime(heWeatherdataserviceBean.getBasic().getUpdate().getLoc());
					mSpUtil.setTimeSamp(System.currentTimeMillis());// 保存一下更新的时间戳
				}
				mSpUtil.setSimpleTemp(tmp.getMin()+"°~"+tmp.getMax()+"°");
				timeTv.setText(TimeUtil.getDay(mSpUtil.getTimeSamp())
						+ heWeatherdataserviceBean.getBasic().getUpdate().getLoc() + "发布");
				humidityTv.setText("湿度:" + heWeatherdataserviceBean.getNow().getHum()+"%");//设置湿度
			}
			if (fragments.size() > 0) {
				((FirstWeatherFragment) mWeatherPagerAdapter.getItem(0))
						.updateWeather(mWeatherInfoBean);
				((SecondWeatherFragment) mWeatherPagerAdapter.getItem(1))
						.updateWeather(mWeatherInfoBean);
			}
		} else {
			temperatureTv.setText("N/A");
			cityTv.setText(mCurCity.getCity_area());
			windTv.setText("N/A");
			climateTv.setText("N/A");
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_qing);
			T.showLong(mApplication, "获取天气信息失败");
		}
	}

	/**
	 * 更新pm2.5界面
	 */
	private void updatePm2d5Info() {
		if (mWeatherInfoBean.getHeWeatherdataservice().get(0).getAqi()!= null) {
			
			mApplication.setmWeatherInfoBean(mWeatherInfoBean);
			HeWeatherdataserviceBean heWeatherdataserviceBean = mWeatherInfoBean.getHeWeatherdataservice().get(0);
			pmQualityTv.setText(heWeatherdataserviceBean.getAqi().getCity().getQlty());//设置空气质量
			pmDataTv.setText(heWeatherdataserviceBean.getAqi().getCity().getPm25());
			int pm2_5 = Integer.parseInt(heWeatherdataserviceBean.getAqi().getCity().getPm25());
			int pm_img = R.drawable.biz_plugin_weather_0_50;
			if (pm2_5 > 300) {
				pm_img = R.drawable.biz_plugin_weather_greater_300;
			} else if (pm2_5 > 200) {
				pm_img = R.drawable.biz_plugin_weather_201_300;
			} else if (pm2_5 > 150) {
				pm_img = R.drawable.biz_plugin_weather_151_200;
			} else if (pm2_5 > 100) {
				pm_img = R.drawable.biz_plugin_weather_101_150;
			} else if (pm2_5 > 50) {
				pm_img = R.drawable.biz_plugin_weather_51_100;
			} else {
				pm_img = R.drawable.biz_plugin_weather_0_50;
			}

			pmImg.setImageResource(pm_img);
		} else {
			pmQualityTv.setText("N/A");
			pmDataTv.setText("N/A");
			pmImg.setImageResource(R.drawable.biz_plugin_weather_0_50);
			T.showLong(mApplication, "未获取到PM2.5数据");
		}
	}

	/**
	 *  请求服务器，获取返回数据，请求url地址，返回字符串
	 * @param httpUrl
	 * @return
	 */
	public  String request(String httpUrl) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
			try {
				URL url = new URL(httpUrl);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				InputStream is = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead); sbf.append("\r\n");
				}
				reader.close();
				sbf.deleteCharAt(11);
				sbf.deleteCharAt(15);
				sbf.delete(22,26);
				result = sbf.toString();
			} catch (Exception e) {
			   e.printStackTrace();
			 }
		}
			return result;
	} 
	

	BDLocationListener mLocationListener = new BDLocationListener() {

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// do nothing
		}

		@Override
		public void onReceiveLocation(BDLocation location) {
			// mActionBar.setProgressBarVisibility(View.GONE);
			mUpdateBtn.setVisibility(View.VISIBLE);
			mUpdateProgressBar.setVisibility(View.GONE);
			if (location == null || TextUtils.isEmpty(location.getCity())) {
				// T.showShort(getApplicationContext(), "location = null");
				final Dialog dialog = IphoneDialog.getTwoBtnDialog(
						WeatherActivity.this, "定位失败", "是否手动选择城市?");
				((Button) dialog.findViewById(R.id.ok))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								startActivityForResult();
								dialog.dismiss();
							}
						});
				dialog.show();
				return;
			}
			String cityName = location.getCity();
			mLocationClient.stop();
			Message msg = mHandler.obtainMessage();
			msg.what = LOACTION_OK;
			msg.obj = cityName;
			mHandler.sendMessage(msg);// 更新天气
		}
	};

	/**
	 * 返回所选择的城市
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == RESULT_OK) {
			mNewIntentCity = (City) data.getSerializableExtra("city");
			mHandler.sendEmptyMessage(ON_NEW_INTENT);
		}
	}

	@Override
	public void onCityComplite() {
		// do nothing
	}

	@Override
	public void onNetChange() {
		//网络情况，如果网络不给力，弹出吐司说明网络不给力
		if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE)
			T.showLong(this, R.string.net_err);
		// else if (!TextUtils.isEmpty(mSpUtil.getCity())) {
		// String sPCityName = mSpUtil.getCity();
		// mCurCity = mCityDB.getCity(sPCityName);
		// getWeatherInfo(true, true);
		// }
	}

	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_city_manager:
			startActivityForResult();
			break;
		case R.id.title_location:
			if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
				if (!mLocationClient.isStarted())
					mLocationClient.start();
				mLocationClient.requestLocation();
				T.showShort(this, "正在定位...");
			} else {
				T.showShort(this, R.string.net_err);
			}
			break;
		case R.id.title_share:
			if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
				// do something
			} else {
				T.showShort(this, R.string.net_err);
			}
			break;
		case R.id.title_update_btn:
			if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
				if (TextUtils.isEmpty(mSpUtil.getCity())) {
					T.showShort(this, "请先选择城市或定位！");
				} else {
					String sPCityName = mSpUtil.getCity();
					mCurCity = mCityDB.getCity(sPCityName);
					updateWeather(true);
				}
			} else {
				T.showShort(this, R.string.net_err);
			}
			break;

		default:
			break;
		}
	}

}
