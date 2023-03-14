package dev.mah.nassa.roomtestt;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface Student_Dao {

    @Insert
    Completable insertStudnt(StudentModel student);

    @Update
    Completable updateStudent(StudentModel student);

    @Query("delete from StudentModel where id=:id")
    Completable deleteStudentByID(int id);

    @Delete
    Completable deleteStudent(StudentModel student);

    @Query("SELECT * FROM StudentModel")
    Observable<List<StudentModel>> getAllStudent();

}
