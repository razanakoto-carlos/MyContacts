package mg.carlos.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditerActivity extends AppCompatActivity {
    private EditText editNom, editTelephone, editEmail, editNote;
    private TextView textTitreEcran;
    private DatabaseHelper dbHelper;
    private int contactId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer);

        editNom = findViewById(R.id.editNom);
        editTelephone = findViewById(R.id.editTelephone);
        editEmail = findViewById(R.id.editEmail);
        editNote = findViewById(R.id.editNote);

        Button btnEnregistrer = findViewById(R.id.btnEnregistrer);
        textTitreEcran = findViewById(R.id.textTitreEcran);
        Button btnAnnuler = findViewById(R.id.btnAnnuler);
        ImageButton btnRetour = findViewById(R.id.btnRetour);

        dbHelper = new DatabaseHelper(this);

        contactId = getIntent().getIntExtra("CONTACT_ID", -1);

        if (contactId != -1) {
            if (textTitreEcran != null) textTitreEcran.setText("Modifier le contact");
            preRemplirChamps(contactId);
        }else {
            if (textTitreEcran != null) textTitreEcran.setText("Nouveau contact");
        }

        btnRetour.setOnClickListener(v -> finish());
        btnAnnuler.setOnClickListener(v -> finish());

        btnEnregistrer.setOnClickListener(v -> appliquerModification());
    }

    private void preRemplirChamps(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor curseur = db.rawQuery("SELECT * FROM contacts WHERE id = ?", new String[]{String.valueOf(id)});

        if (curseur != null && curseur.moveToFirst()) {
            editNom.setText(curseur.getString(1));
            editTelephone.setText(curseur.getString(2));
            editEmail.setText(curseur.getString(3));
            editNote.setText(curseur.getString(4));
        }
        if (curseur != null) curseur.close();
    }

    private void appliquerModification() {
        String nouveauNom = editNom.getText().toString().trim();
        String nouveauTel = editTelephone.getText().toString().trim();
        String nouvelEmail = editEmail.getText().toString().trim();
        String nouvelleNote = editNote.getText().toString().trim();

        if (nouveauNom.isEmpty() || nouveauTel.isEmpty()) {
            Toast.makeText(this, "Le nom et le téléphone sont obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues valeurs = new ContentValues();
        valeurs.put("nom", nouveauNom);
        valeurs.put("telephone", nouveauTel);
        valeurs.put("email", nouvelEmail);
        valeurs.put("note", nouvelleNote);

        if (contactId != -1) {
            db.update("contacts", valeurs, "id = ?", new String[]{String.valueOf(contactId)});
            Toast.makeText(this, "Contact modifié !", Toast.LENGTH_SHORT).show();
        } else {
            db.insert("contacts", null, valeurs);
            Toast.makeText(this, "Contact enregistré !", Toast.LENGTH_SHORT).show();
        }

        db.close();
        finish();
    }
}