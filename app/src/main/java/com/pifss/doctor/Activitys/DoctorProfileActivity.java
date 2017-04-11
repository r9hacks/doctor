package com.pifss.doctor.Activitys;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pifss.doctor.Model.Doctor;
import com.pifss.doctor.R;
import com.pifss.doctor.RequestQueueSingleTon;
import com.pifss.doctor.links;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.R.attr.name;
import static com.pifss.doctor.R.mipmap.email;

public class DoctorProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle(R.string.Myprofile);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btnnEdit = (Button) findViewById(R.id.btnRegister);



        btnnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorProfileActivity.this,EditDoctorProfileActivity.class);
                startActivity(i);
            }
        });


        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        TextView txtName = (TextView) findViewById(R.id.txtDrNameView);
        txtName.setText(doctor.getFirstName() + " " + doctor.getLastName());


        ImageView img = (ImageView) findViewById(R.id.imageViewLogo);
        Picasso.with(DoctorProfileActivity.this).load(doctor.getImageUrl()).placeholder(R.mipmap.profile_image).into(img);

        EditText txtEmail =(EditText) findViewById(R.id.txtDrEmail);
        txtEmail.setText(doctor.getEmail());
        txtEmail.setEnabled(false);

        EditText txtCivilID = (EditText) findViewById(R.id.txtDrPhone);
        txtCivilID.setText(doctor.getPhoneNumber());
        txtCivilID.setEnabled(false);

        EditText txtSpecialty = (EditText) findViewById(R.id.txtDrSpeciality);
        txtSpecialty.setText(doctor.getSpecialityId());
        txtSpecialty.setEnabled(false);

        EditText txtLocation = (EditText) findViewById(R.id.txtDrLocation);
        txtLocation.setText(doctor.getLocation());
        txtLocation.setEnabled(false);


        TextView editImageText = (TextView) findViewById(R.id.textViewEditImage);
        editImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALLARY = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView img = (ImageView) findViewById(R.id.imageViewLogo);
        Bitmap imageBitmap = null;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }

        if (requestCode == REQUEST_IMAGE_GALLARY && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
           // Toast.makeText(this, "Path: "+getRealPathFromURI(selectedImage), Toast.LENGTH_SHORT).show();
            //System.out.println("Base64:"+convert(rotateBitmapOrientation(getRealPathFromURI(selectedImage))));
            imageBitmap = rotateBitmapOrientation(getRealPathFromURI(selectedImage));
            img.setImageBitmap(imageBitmap);
            //img.setImageURI(selectedImage);
        }

        final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(DoctorProfileActivity.this);
        final ProgressDialog progressDialog = new ProgressDialog(DoctorProfileActivity.this);

        try {

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("appID","doctor");
            jsonBody.put("imgData",convert(imageBitmap));
            System.out.println(jsonBody.toString());

            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, links.UploadImage, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progressDialog.hide();
                    System.out.println("response: "+response.toString());
                    try {
                        if (response.getString("errorMsgEn").equalsIgnoreCase("Done")){
                            Toast.makeText(DoctorProfileActivity.this, "Upload successfully", Toast.LENGTH_SHORT).show();
                            if (response.has("imgPath")){
                                String imgPath = response.getString("imgPath");
                                updateDoctorProfileWithImage(imgPath);
                            }
                            System.out.println("response: "+response.toString());
                        }else{
                            Toast.makeText(DoctorProfileActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                        }

                        //Toast.makeText(RegisterActivity.this, "on response: "+response.toString(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("error: "+error.toString());
                    //show message
                    Toast.makeText(DoctorProfileActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();

                    progressDialog.hide();

                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=UTF-8";
                }
            };


            progressDialog.setMessage("Uploading...");
            progressDialog.show();
            queue.add(jsonObjRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void updateDoctorProfileWithImage(String imgPath){

        final RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(DoctorProfileActivity.this);
        final ProgressDialog progressDialog = new ProgressDialog(DoctorProfileActivity.this);

        SharedPreferences preference = getSharedPreferences("settings",MODE_PRIVATE);
        String doctorProfile = preference.getString(links.PrefDoctorProfile,"notfound");
        final Doctor doctor = new Gson().fromJson(doctorProfile,Doctor.class);

        doctor.setImageUrl(imgPath);
        JSONObject jsonBody = null;
        try {
            jsonBody = doctor.getJSONDoctor();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String URL =links.Doctor + "/" + doctor.getDrId();
        System.out.println("body:"+jsonBody.toString());
        System.out.println("link:"+URL);
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.hide();
                try {
                    if (response.getString("errorMsgEn").equalsIgnoreCase("Done")){

                        Toast.makeText(DoctorProfileActivity.this, R.string.ProfileUpdated, Toast.LENGTH_SHORT).show();

                        SharedPreferences preference = getSharedPreferences("settings", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preference.edit();
                        editor.putString(links.PrefDoctorProfile, doctor.getJSONDoctor().toString());
                        editor.commit();
                    }else{
                        Toast.makeText(DoctorProfileActivity.this, R.string.Errortryagainlater, Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("response: "+response.toString());

                //Toast.makeText(EditDoctorProfileActivity.this, "response: "+response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
            }
        });

        progressDialog.setMessage("Updating Profile...");
        progressDialog.show();
        queue.add(jsonObjRequest);
    }

    public Bitmap convert(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public String convert(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(DoctorProfileActivity.this);
        builder.setTitle("Upload Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }else if (options[item].equals("Choose from Gallery"))
                {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , REQUEST_IMAGE_GALLARY);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public Bitmap rotateBitmapOrientation(String photoFilePath) {
        // Create and configure BitmapFactory
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFilePath, bounds);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
        // Read EXIF Data
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(photoFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
        // Return result
        return rotatedBitmap;
    }


    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
