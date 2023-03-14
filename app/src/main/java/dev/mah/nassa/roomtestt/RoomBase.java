package dev.mah.nassa.roomtestt;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StudentModel.class} , version = 2 )
abstract class RoomBase extends RoomDatabase {
    public static RoomBase INSTACE;
    public abstract Student_Dao student_dao();

    public static synchronized RoomBase getInstance(Context context) {
        if(INSTACE == null){
            INSTACE=Room.databaseBuilder(context , RoomBase.class , "st_data_base")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTACE;

    }
}
