package dev.mah.nassa.roomtestt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentAdapter extends BaseAdapter{
    List<StudentModel> modelList;
    int layout;
    Context context;
    MyViewModel myViewModel;
    RoomBase roomBase;

    public StudentAdapter(List<StudentModel> modelList, int layout, Context context , RoomBase roomBase, MyViewModel myViewModel) {
        this.modelList = modelList;
        this.layout = layout;
        this.context = context;
        this.roomBase=roomBase;
        this.myViewModel=myViewModel;
    }




    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }


    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(layout, null);
        TextView name=view.findViewById(R.id.userName);
        name.setText(modelList.get(position).getName());
        TextView email=view.findViewById(R.id.emailAddress);
        email.setText(modelList.get(position).getEmail());
        ImageView delete = view.findViewById(R.id.deleteBut);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewModel.deletStudentById(roomBase , modelList.get(position).getId() , context);

            }
        });
        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
