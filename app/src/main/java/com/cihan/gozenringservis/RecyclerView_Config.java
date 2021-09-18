package com.cihan.gozenringservis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private RequestsAdapter mRequestsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Request> requests,
                          List<String> keys){
        mContext = context;
        mRequestsAdapter = new RequestsAdapter(requests,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRequestsAdapter);
    }

    class RequestItemView extends RecyclerView.ViewHolder{

        private TextView mstation1;
        private TextView mstation2;
        private TextView mcount;
        private TextView mtime;

        private String key;

        public RequestItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.driver_arayuz,parent,false));

            mstation1 = (TextView) itemView.findViewById(R.id.txtStation1);
            mstation2 = (TextView) itemView.findViewById(R.id.txtStation2);
            mcount = (TextView) itemView.findViewById(R.id.txtcount);
            mtime = (TextView) itemView.findViewById(R.id.txtKey);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,RequestsDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("station1",mstation1.getText().toString());
                    intent.putExtra("station2",mstation2.getText().toString());
                    intent.putExtra("count",mcount.getText().toString());
                    intent.putExtra("key",mtime.getText().toString());
                    mContext.startActivity(intent);

                }
            });


        }

        public void bind(Request request, String key){
             mstation1.setText(request.getStation1());
             mstation2.setText(request.getStation2());
             mcount.setText(request.getCount());
             mtime.setText(request.getTime());

             this.key  = key;
        }
    }

    class RequestsAdapter extends RecyclerView.Adapter<RequestItemView>{

        private List<Request> mRequestList;
        private List<String> mKeys;

        public RequestsAdapter(List<Request> mRequestList, List<String> mKeys) {
            this.mRequestList = mRequestList;
            this.mKeys = mKeys;
        }

        public RequestsAdapter() {
            super();
        }

        @NonNull
        @Override
        public RequestItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RequestItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RequestItemView holder, int position) {
             holder.bind(mRequestList.get(position),mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mRequestList.size();
        }
    }

}
