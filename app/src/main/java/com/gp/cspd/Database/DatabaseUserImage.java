package com.gp.cspd.Database;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DatabaseUserImage {
    private StorageReference mStorageRef;
    public Uri fileUri;
    public DatabaseUserImage(String imageName, Uri uri){
        this.fileUri = null;

        mStorageRef = FirebaseStorage.getInstance().getReference("UserImageFolder");
        StorageReference imageRef = mStorageRef.child(imageName);

        imageRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                        fileUri = downloadUrl;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public Uri getFileUri() {
        return fileUri;
    }
}
