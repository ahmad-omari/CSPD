package com.gp.cspd.mainFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gp.cspd.IdCard.idCardForms;
import com.gp.cspd.R;
import com.gp.cspd.birthCertificate.birthForms;
import com.gp.cspd.deathCertificate.deathForms;
import com.gp.cspd.familyBook.familyBookForms;
import com.gp.cspd.otherServices.otherServicesForms;
import com.gp.cspd.passport.passportForms;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class services extends Fragment implements View.OnClickListener {

    ImageView idCard,passport,birthCertificate,deathCertificate,familBook,otherServices;
    SliderLayout sliderLayout;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_services,container,false);

        view.findViewById(R.id.btn_id_card).setOnClickListener(this);
        view.findViewById(R.id.btn_passport).setOnClickListener(this);
        view.findViewById(R.id.btn_birth_certificate).setOnClickListener(this);
        view.findViewById(R.id.btn_death_certificate).setOnClickListener(this);
        view.findViewById(R.id.btn_family_book).setOnClickListener(this);
        view.findViewById(R.id.btn_other_services).setOnClickListener(this);

        sliderLayout=view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);

        setSliderViews();

        return view;
    }

    private void setSliderViews() {
        for (int i = 0; i <= 2; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.image_slide_1);

                    break;

                case 1:
                    sliderView.setImageDrawable(R.drawable.image_slide_2);

                    break;

                case 2:
                    sliderView.setImageDrawable(R.drawable.image_slide_3);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);

        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_id_card:
                startActivity(new Intent(getActivity(), idCardForms.class));
                getActivity().finish();
                break;
            case R.id.btn_passport:
                startActivity(new Intent(getActivity(), passportForms.class));
                getActivity().finish();
                break;
            case R.id.btn_birth_certificate:
                startActivity(new Intent(getActivity(), birthForms.class));
                getActivity().finish();
                break;
            case R.id.btn_death_certificate:
                startActivity(new Intent(getActivity(), deathForms.class));
                getActivity().finish();
                break;
            case R.id.btn_family_book:
                startActivity(new Intent(getActivity(), familyBookForms.class));
                getActivity().finish();
                break;
            case R.id.btn_other_services:
                startActivity(new Intent(getActivity(), otherServicesForms.class));
                getActivity().finish();
                break;
        }
    }
}
