package id.fourmotion.cavii.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import id.fourmotion.cavii.DetailContent;
import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.ListContent;
import id.fourmotion.cavii.Model.Content;
import id.fourmotion.cavii.R;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {

    private ArrayList<Content> dataList;
    private LayoutInflater inflater;
    private Context context;


    public ContentAdapter(Context context, ArrayList<Content> dataList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int postion) {
        View view = inflater.inflate(R.layout.activity_content_capsule, parent, false);
        MyViewHolder holder = new MyViewHolder(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtJudul.setText(dataList.get(position).getJudul());
        holder.txtJenis.setText(dataList.get(position).getJenis());
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
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    /*
        @Override
        public void onBindViewHolder(final ContentViewHolder holder, final int position) {
            id = dataList.get(position).get_id();
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

            final MyDatabase db = new MyDatabase(ListContent.getContext());
            holder.tombol_favorit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.tombol_favorit.isSelected()) {
                        holder.tombol_favorit.setSelected(false);
                        try {
                            db.setFav(dataList.get(position).get_id(), "false");
                        } catch (Exception e) {
                            Log.d("Error Disini", "Ini errornya : " + e);
                        }
                    } else {
                        holder.tombol_favorit.setSelected(true);
                        db.setFav(dataList.get(position).get_id(), "true");
                    }
                }
            });

            holder.imgPath.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idData = dataList.get(position).get_id();
                }
            });
        }

        public int getItemCount() {
            return (dataList != null) ? dataList.size() : 0;
        }
    */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        private TextView txtJudul, txtJenis, txtHarga, txtAlamat;
        private ImageView imgPath;
        ImageButton tombol_favorit;

        private MyViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            txtJudul = itemView.findViewById(R.id.txt_judul_konveksi);
            txtJenis = itemView.findViewById(R.id.txt_jenis);
            txtHarga = itemView.findViewById(R.id.txt_harga);
            imgPath = itemView.findViewById(R.id.img_konveksi);
            txtAlamat = itemView.findViewById(R.id.txt_alamat);
            tombol_favorit = itemView.findViewById(R.id.tombol_fav);
            tombol_favorit.setOnClickListener(this);
            imgPath.setOnClickListener(this);
        }

        // onClick Listener for view
        @Override
        public void onClick(View view) {

            MyDatabase db = new MyDatabase(context);

            if (view.getId() == tombol_favorit.getId()) {

                if (tombol_favorit.isSelected()) {
                    tombol_favorit.setSelected(false);
                    db.setFav(dataList.get(getAdapterPosition()).get_id(), "false");

                } else {
                    tombol_favorit.setSelected(true);
                    db.setFav(dataList.get(getAdapterPosition()).get_id(), "true");
                }

            } else if (view.getId() == imgPath.getId()) {
                Intent toDetailContent = new Intent(context, DetailContent.class);
                toDetailContent.putExtra("trans_ID()", dataList.get(getAdapterPosition()).get_id());
                context.startActivity(toDetailContent);
                ((Activity) context).overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_right);
            }
        }


    }

}