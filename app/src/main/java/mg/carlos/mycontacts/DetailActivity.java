package mg.carlos.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    private TextView textNom, textTelephone, textEmail,textNote,textDetailSousTitre;
    private DatabaseHelper dbHelper;
    private int contactId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textNom = findViewById(R.id.textDetailNom);
        textTelephone = findViewById(R.id.textDetailTelephone);
        textEmail = findViewById(R.id.textDetailEmail);
        textNote = findViewById(R.id.textDetailNote);
        textDetailSousTitre = findViewById(R.id.textDetailSousTitre);
        ImageButton btnRetour = findViewById(R.id.btnRetour);

        LinearLayout btnAppeler = findViewById(R.id.btnAppeler);
        LinearLayout btnSms = findViewById(R.id.btnSMS);
        LinearLayout btnEmail = findViewById(R.id.btnEmail);
        LinearLayout btnSupprimer = findViewById(R.id.btnSupprimer);

        btnAppeler.setOnClickListener(v -> lancerAppel());
        btnSms.setOnClickListener(v -> envoyerSms());
        btnEmail.setOnClickListener(v -> envoyerEmail());
        btnSupprimer.setOnClickListener(v -> supprimerContact());
        btnRetour.setOnClickListener(v -> finish());

        dbHelper = new DatabaseHelper(this);



        contactId = getIntent().getIntExtra("CONTACT_ID", -1);

        if (contactId != -1) {
            chargerDetailsContact(contactId);
        }
    }

    private void chargerDetailsContact(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor curseur = db.rawQuery("SELECT * FROM contacts WHERE id = ?", new String[]{String.valueOf(id)});

        if (curseur != null && curseur.moveToFirst()) {
            String nom = curseur.getString(1);
            String telephone = curseur.getString(2);
            String email = curseur.getString(3);
            String note = curseur.getString(4);
            String date = curseur.getString(5);

            textNom.setText(nom);
            textTelephone.setText(telephone);
            textEmail.setText(email);
            textNote.setText(note);
            String dateAffichee = formaterDate(date);
            textDetailSousTitre.setText("Amie · Ajouté le " + dateAffichee);
        }

        if (curseur != null) {
            curseur.close();
        }
    }

    private void lancerAppel() {
        String num = textTelephone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + num));
        startActivity(intent);
    }

    private void envoyerSms() {
        String num = textTelephone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + num));
        startActivity(intent);
    }

    private void envoyerEmail() {
        String adresseEmail = textEmail.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + adresseEmail));
        startActivity(intent);
    }

    private void supprimerContact() {
        // 1. On crée le constructeur de la boîte de dialogue
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);

        builder.setTitle("Suppression");
        builder.setMessage("Voulez-vous vraiment supprimer ce contact ?");

        builder.setPositiveButton("Oui, supprimer", (dialog, which) -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int lignesSupprimees = db.delete("contacts", "id = ?", new String[]{String.valueOf(contactId)});

            if (lignesSupprimees > 0) {
                Toast.makeText(this, "Contact supprimé", Toast.LENGTH_SHORT).show();
                finish();
            }
            db.close();
        });

        builder.setNegativeButton("Annuler", (dialog, which) -> {
            dialog.dismiss();
        });

        builder.show();
    }
    private String formaterDate(String dateIso) {
        if (dateIso == null || dateIso.isEmpty()) return "";
        try {
            SimpleDateFormat formatEntree = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat formatSortie = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
            java.util.Date date = formatEntree.parse(dateIso);
            if (date == null) return dateIso;
            String resultat = formatSortie.format(date);
            return Character.toUpperCase(resultat.charAt(0)) + resultat.substring(1);

        } catch (ParseException e) {
            return dateIso;
        }
    }
}