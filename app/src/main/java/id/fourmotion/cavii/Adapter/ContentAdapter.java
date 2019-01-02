package id.fourmotion.cavii.Adapter;


import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import id.fourmotion.cavii.DetailContent;
import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.ListContent;
import id.fourmotion.cavii.Model.Content;
import id.fourmotion.cavii.R;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private ArrayList<Content> dataList;
    private MyDatabase db;

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
    public void onBindViewHolder(final ContentViewHolder holder, final int position) {
        holder.txtJudul.setText(dataList.get(position).getJudul());
        holder.txtJenis.setText(dataList.get(position).getJenis());
        holder.txtHarga.setText(dataList.get(position).getHarga());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        try {
            holder.txtHarga.setText(formatRupiah.format(Double.parseDouble(dataList.get(position).getHarga())));
        } catch (Exception e) {
            //Error: null string
        }
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("assets/image/" + dataList.get(position).getImgPath());
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        holder.imgPath.setImageBitmap(bitmap);
        holder.txtAlamat.setText(dataList.get(position).getAlamat());
        holder.tombol_favorit.setSelected(dataList.get(position).getFav());

        holder.tombol_favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase db = new MyDatabase(ListContent.getContext());
                if (holder.tombol_favorit.isSelected()){
                    holder.tombol_favorit.setSelected(false);
                    try {
                        Log.d("ID ini","ID nya : " + dataList.get(position).get_id());

                        db.setFav(dataList.get(position).get_id(), "false");
                    }catch (Exception e){
                        Log.d("Error Disini", "Ini errornya : " + e);
                    }
                }else {
                    holder.tombol_favorit.setSelected(true);
                    db.setFav(dataList.get(position).get_id(), "true");
                }
            }
        });
    }

    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJudul, txtJenis, txtHarga, txtAlamat;
        private ImageView imgPath;
        LinearLayout layoutFav;
        ImageButton tombol_favorit;

        public ContentViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_konveksi);
            txtJenis = itemView.findViewById(R.id.txt_jenis);
            txtHarga = itemView.findViewById(R.id.txt_harga);
            imgPath = itemView.findViewById(R.id.img_konveksi);
            txtAlamat = itemView.findViewById(R.id.txt_alamat);
            layoutFav = itemView.findViewById(R.id.layout_favorit);
            tombol_favorit = itemView.findViewById(R.id.tombol_fav);

        }
    }
}