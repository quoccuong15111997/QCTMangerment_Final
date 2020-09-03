package com.example.qctmanagement.common;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.qctmanagement.callback.UploadImageCallback;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

/**
 * Created by Nguyen Quoc Cuong on 8/28/2020.
 */
public class UploadProvider {
    private static UploadProvider instance;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private UploadProvider(){
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://qctshop.appspot.com/");
    }
    public static UploadProvider getInstance(){
        if (instance==null){
            instance= new UploadProvider();
        }
        return instance;
    }
    public void doUploadImage(Bitmap bitmapCamera, String name, UploadImageCallback callback){
        String child = name;
        final StorageReference mountainsRef = storageRef.child(child);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmapCamera.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
               System.out.println(exception.fillInStackTrace());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                System.out.println(taskSnapshot.getError());
            }
        });
        final StorageReference ref = storageRef.child(child);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    System.out.println(task.getException());
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    final Uri downloadUri = task.getResult();
                    String urlImage=downloadUri.toString();
                    callback.onUpLoadSucces(urlImage);

                } else {
                    System.out.println(task.getException());
                    // Handle failures
                    // ...
                }
            }
        });
    }
}
