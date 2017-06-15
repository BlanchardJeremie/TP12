package fr.formation.tp12;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


import fr.formation.tp12.database.modele.User;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

          private List<User> utilisateurs;

        public MyAdapter(List<User> utilisateurs) {
            this.utilisateurs = utilisateurs;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_contente, parent, false);
            return new UtilisateurViewHolder(view);

        }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((UtilisateurViewHolder) holder).mContentView.setText(utilisateurs.get(position).getNom());
    }


        @Override
        public int getItemCount() {
            return utilisateurs.size();
        }

        public class UtilisateurViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final TextView mContentView;

            public UtilisateurViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }

}
