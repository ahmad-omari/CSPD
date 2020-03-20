package com.gp.cspd.mainFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gp.cspd.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends Fragment {

    View view;
    CircleImageView profilePicture;
    TextView address,phone,ssn,email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.activity_profile,container,false);

        profilePicture=view.findViewById(R.id.profile_pic);

        address=view.findViewById(R.id.address);
        phone=view.findViewById(R.id.telephone);
        ssn=view.findViewById(R.id.ssn);
        email=view.findViewById(R.id.email);




        return view;
    }



}
