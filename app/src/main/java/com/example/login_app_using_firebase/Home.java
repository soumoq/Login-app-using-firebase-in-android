package com.example.login_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    TextView user_info_email;
    EditText user_artist;
    Spinner user_artist_type;
    ListView artist_listview;
    List<Artist> artistList;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("user");
        user_info_email=(TextView)findViewById(R.id.user_info_email);
        artist_listview=(ListView)findViewById(R.id.artist_listview);
        artistList=new ArrayList<>();

        String email=getIntent().getStringExtra("email");
        user_info_email.setText("Enter your favourite artist");

        user_artist=(EditText)findViewById(R.id.user_artist);
        user_artist_type=(Spinner)findViewById(R.id.user_artist_type);


    }
    public void add_Artist(View view)
    {
        String artist=user_artist.getText().toString();
        String artist_type=user_artist_type.getSelectedItem().toString();

        if(!artist.isEmpty())
        {
            String id=databaseReference.push().getKey();
            Artist artist1=new Artist(id,artist,artist_type);
            databaseReference.push().setValue(artist1);
            Toast.makeText(this,"Artist added",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(Home.this,"Enter a name",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistList.clear();
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren())
                {
                    Artist artist=artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }

                ArtistList adopter=new ArtistList(Home.this,artistList);
                artist_listview.setAdapter(adopter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
