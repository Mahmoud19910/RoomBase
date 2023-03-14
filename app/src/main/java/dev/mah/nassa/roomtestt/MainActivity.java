package dev.mah.nassa.roomtestt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import dev.mah.nassa.roomtestt.databinding.ActivityMainBinding;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RoomBase roomBase;
    MyViewModel myViewModel;
    List<StudentModel> list;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


       myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
       roomBase=RoomBase.getInstance(getApplicationContext());

        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                roomBase.student_dao().insertStudnt(new StudentModel(binding.editName.getText().toString() , binding.editEmail.getText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(MainActivity.this, "Success Full", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });




            }
        });



                myViewModel.getAllStudent(roomBase);
                myViewModel.mutableLiveData.observe(MainActivity.this, new Observer<List<StudentModel>>() {
                    @Override
                    public void onChanged(List<StudentModel> studentModels) {
                        list=studentModels;
                        StudentAdapter studentAdapter = new StudentAdapter(studentModels , R.layout.layout_salary , MainActivity.this ,roomBase , myViewModel);
                        binding.listView.setAdapter(studentAdapter);

                    }
                });

                binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });





    }
}