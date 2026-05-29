package mg.carlos.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private ArrayList<Contact> listeContacts;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeContacts = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chargerContactsDepuisLaBase();

        adapter = new ContactAdapter(listeContacts);
        recyclerView.setAdapter(adapter);

    }
    private void chargerContactsDepuisLaBase() {
        listeContacts.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor curseur = db.rawQuery("SELECT * FROM contacts", null);

        if(curseur.moveToFirst()){
            do{
                int id = curseur.getInt(0);
                String nom = curseur.getString(1);
                String telephone = curseur.getString(2);
                String email = curseur.getString(3);
                String note = curseur.getString(4);

                Contact contact = new Contact(id, nom, telephone, email, note);
                listeContacts.add(contact);
            } while (curseur.moveToNext());
        }
        curseur.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        chargerContactsDepuisLaBase();
    }

}