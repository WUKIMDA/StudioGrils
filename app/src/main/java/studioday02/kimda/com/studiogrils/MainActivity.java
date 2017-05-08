package studioday02.kimda.com.studiogrils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    @BindView(R.id.lv_grils)
    ListView mLv;
    GrilsAdapter mAdapter;
    List<GrilsBean.ResultsBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        firstLoad();

        mAdapter = new GrilsAdapter();
        mLv.setAdapter(mAdapter);

        initEvent();
    }

    private void initEvent() {
        mLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //当前状态是停止并且是最后一条
                if (scrollState == SCROLL_STATE_IDLE && mLv.getLastVisiblePosition() == mList.size()-1){
                    loadData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    /**第一次加载*/
    private void firstLoad() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                GrilsBean grilsBean = gson.fromJson(string, GrilsBean.class);

                mList.addAll(grilsBean.getResults());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    private void loadData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/"+mList.size()/10+1).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                GrilsBean grilsBean = gson.fromJson(string, GrilsBean.class);

                mList.addAll(grilsBean.getResults());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }


    private class GrilsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.layout, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            GrilsBean.ResultsBean bean = mList.get(position);
            String url = bean.getUrl();
            Glide.with(MainActivity.this).load(url).bitmapTransform(new CropTransformation(MainActivity.this), new CropCircleTransformation(MainActivity.this)).into(holder.ivImg);
            return convertView;
        }
    }

    private class ViewHolder {
        ImageView ivImg;

        public ViewHolder(View view) {
            ivImg = (ImageView) view.findViewById(R.id.iv_img);
        }
    }
}
