package fr.formation.ProjetSante;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.formation.ProjetSante.database.datasource.DataSource;
import fr.formation.ProjetSante.database.modele.User;

import static fr.formation.ProjetSante.R.id.viewPager;

/**
 * Created by admin on 16/06/2017.
 */

public class UtilisateurFragment extends Fragment{
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    private ViewPager pager;
    private ListeUtilisateurFragment liste;
    DataSource<User> dataSource;
    private User user ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        user = new User();

        View view = inflater.inflate(R.layout.utilisateur, container, false);
        pager = (ViewPager) view.findViewById(viewPager);
        pager.setAdapter(new UtilisateurPagerAdapter(getChildFragmentManager()));
        pager.setCurrentItem(mCurrentPosition);

        liste = (ListeUtilisateurFragment) getFragmentManager().findFragmentById(R.id.liste_fragment);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("PAGER", "page : " + position);
                //View v = (View) liste.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("PAGER", "page : " + position);
                //liste.getListView().setItemChecked(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        pager.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int position) {
        if (pager != null) {
            pager.setCurrentItem(position);
            Log.d("SELECT PAGE", "" + position);
            //article.setText(Mock.utilisateurs.get(position).getDetail());
            mCurrentPosition = position;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    ViewPager getPager() {
        return pager;
    }

    private class UtilisateurPagerAdapter extends FragmentPagerAdapter {

        public UtilisateurPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            return UtilisateurPagerFragment.newInstance(user.getNom());
          //  Mock.utilisateurs.get(pos).getDetail()
        }

        @Override
        public int getCount() {
                return user.getId();

            //return Mock.utilisateurs.size();
        }
    }

}
