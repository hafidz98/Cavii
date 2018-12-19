package id.fourmotion.cavii.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import id.fourmotion.cavii.Model.Content;
import id.fourmotion.cavii.R;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private ArrayList<Content> dataList;

    public ContentAdapter(ArrayList<Content> dataList){
        this.dataList = dataList;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_content_capsule, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position){
        holder.txtJudul.setText(dataList.get(position).getJudul());
        holder.txtJenis.setText(dataList.get(position).getJenis());
        holder.txtBahan.setText(dataList.get(position).getBahan());
        holder.txtHarga.setText(dataList.get(position).getHarga());
    }

    public int getItemCount(){
        return (dataList !=null ) ? dataList.size() : 0;
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder{
        private TextView txtJudul, txtJenis, txtBahan, txtHarga;

        public ContentViewHolder(View itemView){
            super(itemView);
            txtJudul = (TextView) itemView.findViewById(R.id.txt_judul_konveksi);
            txtJenis = (TextView) itemView.findViewById(R.id.txt_jenis);
            txtBahan = (TextView) itemView.findViewById(R.id.txt_bahan);
            txtHarga = (TextView) itemView.findViewById(R.id.txt_harga);

        }
    }

}