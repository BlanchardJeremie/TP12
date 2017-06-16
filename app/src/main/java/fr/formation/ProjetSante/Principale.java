package fr.formation.ProjetSante;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;


public class Principale extends FragmentActivity {

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_principale, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste);
        Log.d("PRINCIPALE","Oncreate");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principale.this, ActivityPageSave.class);
                startActivity(intent);
            }
        });


        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ListeUtilisateurFragment listeUtilisateursFragment = new ListeUtilisateurFragment();
            listeUtilisateursFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, listeUtilisateursFragment).commit();
        }
    }

}
