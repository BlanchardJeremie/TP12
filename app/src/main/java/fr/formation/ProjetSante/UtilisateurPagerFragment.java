package fr.formation.ProjetSante;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class UtilisateurPagerFragment extends Fragment{

    public static UtilisateurPagerFragment newInstance(String text) {
        UtilisateurPagerFragment f = new UtilisateurPagerFragment();
        Bundle b = new Bundle();
        b.putString("contenu", text);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.utilisateurpager, container, false);
        TextView tv = (TextView) v.findViewById(R.id.article);
        tv.setText(getArguments().getString("contenu"));
        return v;
    }
}
