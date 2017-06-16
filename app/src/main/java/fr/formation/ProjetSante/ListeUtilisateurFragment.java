/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.formation.ProjetSante;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import fr.formation.ProjetSante.database.modele.User;

public class ListeUtilisateurFragment extends Fragment {

    private static final String TAG = ListeUtilisateurFragment.class.getCanonicalName();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_utilisateurs, container, false);
        recyclerView = (RecyclerView) view;
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Utilisateurs chargés depuis json
        new ChargerUtilisateurs().execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    RecyclerView.Adapter getAdapter() {
        return adapter;
    }

//   @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == 1) {
//            adapter.notifyDataSetChanged();
//        }
//    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

        int selectedItem;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_contente, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.mContentView.setText("Jeremie"/*Mock.utilisateurs.get(position).getNom()*/);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UtilisateurFragment articleFrag = (UtilisateurFragment)
                            getFragmentManager().findFragmentById(R.id.article_fragment);

                    if (articleFrag != null) {
                        articleFrag.updateArticleView(position);

                    } else {
                        UtilisateurFragment newFragment = new UtilisateurFragment();
                        Bundle args = new Bundle();
                        args.putInt(UtilisateurFragment.ARG_POSITION, position);
                        newFragment.setArguments(args);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }

                    notifyItemChanged(selectedItem);
                    selectedItem = position;
                    notifyItemChanged(selectedItem);
                }
            });

            // Gestion de la surbrillance.
            holder.itemView.setSelected(position == selectedItem);
            if (selectedItem == position) {
                holder.itemView.setBackgroundColor(Color.GREEN);
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }

        }

        @Override
        public int getItemCount() {
            //return Mock.utilisateurs.size();
            return 1;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    private class ChargerUtilisateurs extends AsyncTask<Void, Void, Void> {

        List<User> utilisateurs = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity(), "Chargement des utilisateurs", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
           /* JsonOutils sh = new JsonOutils();
            String url = "https://www.jobsathome.fr/offres/profils/monde/ressources-humaines/json";
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            sh.jsonToUtilisateurs(jsonStr, utilisateurs);
            */
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Mock.utilisateurs.addAll(utilisateurs);
            adapter.notifyDataSetChanged();

            UtilisateurFragment utilisateurFragment = (UtilisateurFragment)
                    getFragmentManager().findFragmentById(R.id.article_fragment);

            if (utilisateurFragment != null) {
                utilisateurFragment.getPager().getAdapter().notifyDataSetChanged();
            }
        }
    }

}