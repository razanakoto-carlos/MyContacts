package mg.carlos.mycontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "ContactDB";
    private static final int DATABASE_VERSION=1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE IF NOT EXISTS contacts("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nom VARCHAR, "
                + "telephone VARCHAR, "
                + "email VARCHAR, "
                + "note VARCHAR);";

        db.execSQL(query);

        db.execSQL("INSERT INTO contacts VALUES(null, 'Carlos', '034000000', 'carlos@email.com', 'Développeur Fullstack');");
        db.execSQL("INSERT INTO contacts VALUES(null, 'Prof de Mobile', '032111111', 'prof@school.com', 'Enseignant SQLite');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
