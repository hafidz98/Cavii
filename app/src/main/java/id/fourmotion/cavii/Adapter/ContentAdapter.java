package id.fourmotion.cavii.Adapter;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.ListContent;
import id.fourmotion.cavii.Model.Content;
import id.fourmotion.cavii.R;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private ArrayList<Content> dataList;

    public ContentAdapter(ArrayList<Content> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_content_capsule, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        holder.txtJudul.setText(dataList.get(position).getJudul());
        holder.txtJenis.setText(dataList.get(position).getJenis());
        holder.txtBahan.setText(dataList.get(position).getBahan());
        holder.txtHarga.setText(dataList.get(position).getHarga());

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("assets/image/" + dataList.get(position).getImgPath());
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        holder.imgPath.setImageBitmap(bitmap);
        //holder.imgPath.getDrawable();
    }

    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJudul, txtJenis, txtBahan, txtHarga;
        private ImageView imgPath;

        public ContentViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_konveksi);
            txtJenis = itemView.findViewById(R.id.txt_jenis);
            txtBahan = itemView.findViewById(R.id.txt_bahan);
            txtHarga = itemView.findViewById(R.id.txt_harga);
            imgPath = itemView.findViewById(R.id.img_konveksi);
        }
    }

}