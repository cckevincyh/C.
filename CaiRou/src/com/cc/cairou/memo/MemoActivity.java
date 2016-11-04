package com.cc.cairou.memo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.cc.cairou.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
public class MemoActivity extends ActionBarActivity {
	
	private ArrayList<String> typeArray = new ArrayList<String>();
    private ArrayList<String> memoArray = new ArrayList<String>();//默认的第一个的左侧列表的第一个的右列表的项目集合
    private ArrayList<TypeItem> typeItemArrayList = new ArrayList<TypeItem>();//左侧的所有的列表集合
    public static int selectedTypeItemNum = 0;      //默认第一个被选中，左侧，当前选中的左侧项目
    private TypeItemAdapter left_adapter;
    private MemoItemAdapter right_adapter;
	
    //声明相关变量
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
   
    private ListView right_listView;
    
    private  Handler handler = new Handler(){
    	@Override
    	public void handleMessage(Message msg) {
    	   left_adapter.notifyDataSetChanged();//更新左侧
           right_adapter.notifyDataSetChanged();//更新右侧
    	}
    	
    	
    };
    
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        findViews(); //获取控件
        toolbar.setTitle("随手记");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
               
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
             
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        initMemo();
        
        
    }
    private void findViews() {
      
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);//左侧隐藏的列表
        right_listView = (ListView) findViewById(R.id.right_list_view);//右侧的列表ListView
    }
    
    
    
    
    private void initMemo(){
    	//这里是获得解析json的对象
        final Gson gson = new GsonBuilder().serializeNulls().create();
        //读取typeArray文件并转化为ArrayList<String>类
        final String typeArrayFileName = "typeArray";	//类型集合
		//这里把这个文件名为typeArray的读取出来，读取出来的字符串赋值给in，
        String in = loadStringList(typeArrayFileName);//左侧列表的数据作为字符串取出
		//解析json字符串，返回List<T>
        typeArray = gson.fromJson(in, new TypeToken<List<String>>(){}.getType());//解析得到集合


        //读取typeItemArrayList文件并转化为ArrayList<TypeItem>类
        final String typeItemListFileName = "typeItemArrayList";//类型项目集合
        in = loadObject(typeItemListFileName);//也是读取右侧的数据作为字符串取出
		//解析json字符串，返回List<T>
        typeItemArrayList = gson.fromJson(in,new TypeToken<List<TypeItem>>(){}.getType());//解析成为集合

        //将第一个类目的备忘录列表赋给memoArray
        if (typeItemArrayList!=null&&typeItemArrayList.size()>0){
            try {
                memoArray = (ArrayList<String>) deepCopy(typeItemArrayList.get(selectedTypeItemNum).getMemoList());//显示出来的右侧的数据集合，第一个左侧列表对应的右侧列表
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
       
        
        
        //设置菜单列表//左侧的列表ListView数据适配器
        left_adapter = new TypeItemAdapter(MemoActivity.this, R.layout.type_item, typeArray);
        //右侧的列表ListView数据适配器
        right_adapter = new MemoItemAdapter(MemoActivity.this,R.layout.memo_item, memoArray);
        
        
        
        lvLeftMenu.setAdapter(left_adapter);
        right_listView.setAdapter(right_adapter);//设置数据
        //左侧列表的项目点击监听
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(MemoActivity.this, "您点击了 " + position, Toast.LENGTH_SHORT).show();
				 //如果按下了最后一个“加号”item
                if (position == left_adapter.getCount() - 1) {
                    //create dialog for users to input category's name
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MemoActivity.this);//弹出对话框，输入数据
                    dialog.setTitle("请输入类目名称");
                    final EditText typeEditText = new EditText(MemoActivity.this);
                    typeEditText.setHint("请输入类目名称");
                    dialog.setView(typeEditText);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {//确定按钮的事件监听
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                
                            String typeName = typeEditText.getText().toString();	//获得输入的类目名称
                            if (typeName != null && !typeName.isEmpty()) {	//判断数据是否为空
                                typeArray.add(typeArray.size()-1,typeName);	//不为空的话就加入集合中中
                                typeItemArrayList.add(typeItemArrayList.size()-1,new TypeItem(typeName));//左边列表的集合添加
                                typeItemArrayList.get(typeItemArrayList.size() - 2).getMemoList().add("+");	//右侧加入一个+项目
                                selectedTypeItemNum = typeItemArrayList.size() - 2;//当前左侧的列表数目
                                memoArray.clear();//右侧显示集合清空
                                memoArray.addAll(typeItemArrayList.get(selectedTypeItemNum).getMemoList());//显示为当前添加入的集合
                             
                                //更新数据
                                handler.sendEmptyMessage(1);
                                
                                //更新typeArray文件和typeItemArrayList文件
                                save(gson.toJson(typeArray), typeArrayFileName);//保存左侧数据
                                save(gson.toJson(typeItemArrayList), typeItemListFileName);//保存右侧数据
                                //隐藏左侧
                                mDrawerLayout.closeDrawers();
                            }
                        }
                    });
                    dialog.setNegativeButton("取消", null);
                    dialog.show();
                } else {
					//如果不是+号，则展示出右侧内容
                    //按下left_listView的item后，right_listView显示相应TypeItem的memoList
                    selectedTypeItemNum = position;      //记录被选中的类目的位置
                    memoArray.clear();
                    memoArray.addAll(typeItemArrayList.get(position).getMemoList());
                    //更新数据
                    handler.sendEmptyMessage(1);
                    //隐藏左侧
                    mDrawerLayout.closeDrawers();
                }
            }
		
		});
        
      
       

   

        //长按左侧隐藏的item出现对话框，对话框含有删除和修改选项
        lvLeftMenu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position != left_adapter.getCount() - 1) {
                    final String[] dialogItems = {"删除", "修改", "取消"};
                    AlertDialog.Builder longClickDialog = new AlertDialog.Builder(MemoActivity.this);
                    longClickDialog.setAdapter(new ArrayAdapter<String>(MemoActivity.this, R.layout.diag_item, R.id.dialog_item_name, dialogItems),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //点击删除
                                    if (dialogItems[which].equals("删除")) {
                                        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(MemoActivity.this);
                                        deleteDialog.setTitle("注意");
                                        deleteDialog.setMessage("该类目下的所有记录将被删除.");
                                        deleteDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                typeArray.remove(position);
                                                typeItemArrayList.remove(position);
                                                memoArray.clear();
                                                memoArray.addAll(typeItemArrayList.get(0).getMemoList());
                                                //更新数据
                                                handler.sendEmptyMessage(1);
                                                
                                                //更新数据文件
                                                save(gson.toJson(typeArray), typeArrayFileName);
                                                save(gson.toJson(typeItemArrayList), typeItemListFileName);
                                            }
                                        });
                                        deleteDialog.setNegativeButton("取消", null);
                                        deleteDialog.show();
                                    }
                                    //点击修改
                                    if (dialogItems[which].equals("修改")) {
                                        AlertDialog.Builder changeDialog = new AlertDialog.Builder(MemoActivity.this);
                                        changeDialog.setTitle("修改类目名称");
                                        final EditText changeEditText = new EditText(MemoActivity.this);
                                        changeEditText.setHint("请输入类目名称");
                                        changeDialog.setView(changeEditText);
                                        changeDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                final String changedTypeName = changeEditText.getText().toString();
                                                typeArray.set(position, changedTypeName);
                                                typeItemArrayList.get(position).setTypename(changedTypeName);
                                                save(gson.toJson(typeArray), typeArrayFileName);
                                                left_adapter.notifyDataSetChanged();
                                            }
                                        });
                                        changeDialog.setNegativeButton("取消", null);
                                        AlertDialog tempchangeDiag = changeDialog.create();
                                        tempchangeDiag.setCanceledOnTouchOutside(true);
                                        tempchangeDiag.show();
                                    }
                                }
                            });
                    AlertDialog tempLongClickDialog = longClickDialog.create();
                    tempLongClickDialog.setCanceledOnTouchOutside(true);
                    tempLongClickDialog.show();
                }
                return true;
            }
        });

		
		//右侧列表长按
        right_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //如果按下了最后一个“加号”item
                if (position ==memoArray.size() - 1) {
                  
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MemoActivity.this);
                    dialog.setTitle("请输入内容");
                    final EditText editText = new EditText(MemoActivity.this);
                    editText.setHint("请输入内容");
                    dialog.setView(editText);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           
                            String memo = editText.getText().toString();
                            if (memo != null && !memo.isEmpty()) {
                                memoArray.add(memoArray.size()-1,memo);
                                //往被选中类目的记录列表中添加记录
                                typeItemArrayList.get(selectedTypeItemNum).getMemoList()//当前给选中的左侧列表的右侧项目添加
                                        .add(typeItemArrayList.get(selectedTypeItemNum).getMemoList().size()-1,memo);
                                handler.sendEmptyMessage(1);//更新数据
                                save(gson.toJson(typeItemArrayList),typeItemListFileName);//保存右侧的
                            }
                        }
                    });
                    dialog.setNegativeButton("取消", null);
                    dialog.show();
                }
            }

        });

      //右侧列表长按
        right_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position!=memoArray.size()-1){
                    final String[] dialogItems = {"删除", "修改","取消"};
                    AlertDialog.Builder longClickDialog = new AlertDialog.Builder(MemoActivity.this);
                    longClickDialog.setAdapter(new ArrayAdapter<String>(MemoActivity.this, R.layout.diag_item, R.id.dialog_item_name, dialogItems),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //点击删除
                                    if (dialogItems[which].equals("删除")) {
                                        memoArray.remove(position);
                                        typeItemArrayList.get(selectedTypeItemNum).getMemoList().remove(position);
                                        save(gson.toJson(typeItemArrayList), typeItemListFileName);
                                        //更新数据
                                        handler.sendEmptyMessage(1);
                                    }
                                    //点击修改
                                    if (dialogItems[which].equals("修改")) {
                                        AlertDialog.Builder changeDialog = new AlertDialog.Builder(MemoActivity.this);
                                        changeDialog.setTitle("修改内容");
                                        final EditText changeEditText = new EditText(MemoActivity.this);
                                        changeEditText.setHint("请输入内容");
                                        changeDialog.setView(changeEditText);
                                        changeDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                final String changedMemo = changeEditText.getText().toString();
                                                if (!changedMemo.isEmpty()) {
                                                    typeItemArrayList.get(selectedTypeItemNum).getMemoList().set(position, changedMemo);
                                                    memoArray.clear();
                                                    memoArray.addAll(typeItemArrayList.get(selectedTypeItemNum).getMemoList());
                                                    save(gson.toJson(typeItemArrayList), typeItemListFileName);
                                                    //更新数据
                                                    handler.sendEmptyMessage(1);
                                                }
                                            }
                                        });
                                        changeDialog.setNegativeButton("取消", null);
                                        changeDialog.show();
                                    }
                                }
                            });
                    AlertDialog tempLongClickDialog = longClickDialog.create();
                    tempLongClickDialog.setCanceledOnTouchOutside(true);
                    tempLongClickDialog.show();
                }
                return true;
            }
        });

    
    }
    
    
  //保存文件的方法
    public void save(String json,String fileName) {
        FileOutputStream fout;//文件输出流对象
        BufferedWriter writer = null;//字符流对象
        try {
            fout = openFileOutput(fileName, MODE_PRIVATE);//文件流对象
            writer = new BufferedWriter(new OutputStreamWriter(fout));//包装成字符流对象
            writer.write(json);//把json写到文件名为fileName的文件中
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if (writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	
	//加载文件得到字符串
    public String load(String fileName){
        FileInputStream in;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine())!=null){
                content.append(line);//读取文件追加到字符串中
            }
        } catch (FileNotFoundException e) {     //若文件不存在，则新建一个空文件
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString(); //返回文件中得到的字符串
    }

	//读取文件返回字符串
    public String loadStringList(String fileName){
        FileInputStream in;	//文件输入流对象
        BufferedReader reader = null; //字符读取流对象
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(in));//包装成字符读取流对象
            String line;
            while((line = reader.readLine())!=null){
                content.append(line);	//读取文件追加到字符串中
            }
        } catch (FileNotFoundException e) {     //若文件不存在，则新建一个空文件
			//保存文件的方法
            save("[\"+\"]", fileName);
            return loadStringList(fileName);//创建空的文件夹之后重新读取文件
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

	
	
	//读取左侧的列表
    public String loadObject(String fileName){
        FileInputStream in;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        Gson gson = new Gson();//得到解析json的对象
        ArrayList<TypeItem> typeItemArrayList = new ArrayList<TypeItem>();	//类型项目的集合
        typeItemArrayList.add(new TypeItem("+"));	//添加类型项目，把左侧的添加的+放入
        try{
            in = openFileInput(fileName);	//打开文件
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine())!=null){
                content.append(line);	//读取追加到字符串
            }
        } catch (FileNotFoundException e) {     //若文件不存在，则新建一个空文件
            save(gson.toJson(typeItemArrayList), fileName);	//保存json的文件
            return load(fileName);	//返回字符串
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
    
}