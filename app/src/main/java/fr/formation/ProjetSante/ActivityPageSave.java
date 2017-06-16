package fr.formation.ProjetSante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import fr.formation.ProjetSante.database.modele.User;

public class ActivityPageSave extends AppCompatActivity {

    private EditText nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_save);
        nom = (EditText) findViewById(R.id.nom);
    }

    public void sauvegarder(View v) {

        User utilisateur = new User();
        utilisateur.setNom(nom.getText().toString());
        // Transformation en JSON :
        String flux = (new Gson()).toJson(utilisateur);
        Log.d("Utilisateur en JSON", flux);

        // On dépose notre utilisateur jsonné dans l'intent
        Intent resultIntent = new Intent();
        resultIntent.putExtra("utilisateur", flux);
        setResult(2, resultIntent);

        // Bye l'activité
        finish();
    }
}
