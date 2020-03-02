package com.example.login_app_using_firebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artist>{
    private Activity context;
    private List<Artist> artistList;

    public ArtistList(Activity context,List<Artist> artistList)
    {
        super(context,R.layout.list_artist,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_artist,null,true);

        TextView artist_name=(TextView)listViewItem.findViewById(R.id.artist_name);
        TextView artist_type=(TextView)listViewItem.findViewById(R.id.artist_type);

        Artist artist=artistList.get(position);
        artist_name.setText(artist.getArtist_Name());
        artist_type.setText(artist.getArtist_type());

        return  listViewItem;

    }
}
