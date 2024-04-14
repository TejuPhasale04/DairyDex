package com.example.dairydex1.ui.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dairydex1.R;
import com.example.dairydex1.databinding.FragmentProfileBinding;
import com.example.dairydex1.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    ImageView profileImg;
    EditText name,email,password;
    Button update;

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        profileImg=root.findViewById(R.id.profile_img);
        name=root.findViewById(R.id.name);
        email=root.findViewById(R.id.email_reg);
        password=root.findViewById(R.id.password_reg);
       update=root.findViewById(R.id.reg_btn);

       profileImg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent();
               intent.setAction(Intent.ACTION_GET_CONTENT);
               intent.setType("image/*");
               startActivityForResult(intent,33);
           }
       });
       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               updateUserProfile();

           }
       });

        return root;
    }

    private void updateUserProfile() {

    }

}