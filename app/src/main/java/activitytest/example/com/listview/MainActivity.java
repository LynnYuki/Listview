package activitytest.example.com.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Object> shopname;
    private ArrayList<String> shopDetail;
    private boolean[] isbuy;
    private ArrayList<String>   shopPic;
    private ListView lvshop;
    private Button btnComputing;
    private Button btnSelectAll;
    private Button btnRevertSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataInit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvshop=(ListView)this.findViewById(R.id.lvShopping) ;
        final ArrayAdapter<Object> tt=new MyAdapter(getApplicationContext(),R.drawable.strawberry,shopname);
        lvshop.setAdapter(tt);
        btnComputing = (Button)this.findViewById(R.id.btnComputing);
        btnSelectAll = (Button)this.findViewById(R.id.btnSelectAll);
        btnRevertSelected = (Button)this.findViewById(R.id.btnReserveSelected);
        btnSelectAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i=0;i<shopname.size();i++){
                    isbuy[i] = true;
                }
                tt.notifyDataSetChanged();
            }
        });
        btnRevertSelected.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i=0;i<shopname.size();i++){
                    if(isbuy[i])
                        isbuy[i] = false;
                    else
                        isbuy[i] = true;
                }
                tt.notifyDataSetChanged();
            }
        });
        btnComputing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String re = "已选择的商品\n";
                for(int i=0;i<isbuy.length;i++)
                    if(isbuy[i])
                        re+=shopname.get(i).toString()+"\n";
                Toast.makeText(getApplicationContext(),re,Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    private void dataInit(){
        shopname = new ArrayList<Object>();
        shopname.add("西瓜");
        shopname.add("草莓");
        shopname.add("苹果");
        shopDetail = new ArrayList<String>();
        shopDetail.add("西瓜很好吃！");
        shopDetail.add("草莓也好吃！");
        shopDetail.add("苹果更好吃！");
        isbuy = new boolean[shopname.size()];
        for (int i=0;i<shopname.size();i++){
            isbuy[i] = true;

        }
        shopPic = new ArrayList<String>();
        shopPic.add(String.valueOf(R.drawable.watermelon));
        shopPic.add(String.valueOf(R.drawable.strawberry));
        shopPic.add(String.valueOf(R.drawable.apple));


    }

    public  class MyAdapter extends ArrayAdapter<Object>{
        private Context context;
        private int resource;
        List<Object> objects;
        public MyAdapter(Context context,int resource,List<Object> objects){
            super(context,resource,objects);
            this.context = context;
            this.resource= resource;
            this.objects = objects;
        }
        public  MyAdapter(Context context,int resource){
            super(context,resource);
        }
        public View getView(final int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.listitem,null);
            }
            ImageView iv = (ImageView) convertView.findViewById(R.id.ivShop);
            iv.setImageResource(Integer.parseInt(shopPic.get(position)));
            TextView tv = (TextView)convertView.findViewById(R.id.tvGoodName);
            tv.setText(shopname.get(position).toString());
            Button bt = (Button) convertView.findViewById(R.id.btnShopInfo);
            bt.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v) {
                    Toast.makeText(MainActivity.this," 商品详情\n"+shopDetail.get(position),Toast.LENGTH_SHORT).show();
                }
            });
            CheckBox cb = (CheckBox)convertView.findViewById(R.id.checkSelect);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public  void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                    isbuy[position] = isChecked;
                }

            });
            cb.setChecked(isbuy[position]);
            return  convertView;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
