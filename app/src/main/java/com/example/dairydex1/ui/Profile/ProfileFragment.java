package com.example.dairydex1.ui.Profile;
import android.content.Intent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dairydex1.R;
import com.example.dairydex1.databinding.FragmentProfileBinding;
import com.example.dairydex1.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    CircleImageView profileImg;
    EditText name,email,password;
    Button update;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage Storage;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        Storage=FirebaseStorage.getInstance();
        profileImg=root.findViewById(R.id.profile_img);
        name=root.findViewById(R.id.name);
        email=root.findViewById(R.id.email_reg);
        password=root.findViewById(R.id.password_reg);
       update=root.findViewById(R.id.reg_btn);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
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

    public  void onActivityResult(int requestCode, int resultCode, @NonNull Intent data){
        super.onActivityResult(requestCode,resultCode,  data);
            if((data).getData()!=null){
                Uri profileUri=data.getData();
                profileImg.setImageURI(profileUri);

                final StorageReference reference=Storage.getReference().child("profile_picture")
                        .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
                reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                    }
                });
            }
    }
}