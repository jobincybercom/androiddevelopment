package com.example.demoform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainForm extends Activity {

	public static final String EXTRA_MESSAGE1 = "com.example.demoform.MESSAGE1";
	public static final String EXTRA_MESSAGE2 = "com.example.demoform.MESSAGE2";
	public static final String EXTRA_MESSAGE3 = "com.example.demoform.MESSAGE3";
	public static final String EXTRA_MESSAGE4 = "com.example.demoform.MESSAGE4";
	
	private EditText editText1 = null;
	private EditText editText2 = null;
	private EditText editText3 = null;
	private EditText editText4 = null;
	private ImageView viewImage;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_form);
	}
	
	public void clearData(View view){
		
		editText1  = (EditText) findViewById(R.id.etxt_firstName);
		editText2 = (EditText) findViewById(R.id.etxt_lastName);
		editText3 = (EditText) findViewById(R.id.etxt_email);
		editText4 = (EditText) findViewById(R.id.etxt_phone);
		editText1.setText("");
		editText2.setText("");
		editText3.setText("");
		editText4.setText("");
		
	}
	
	public void sendData(View view){
		
		System.out.println(view.getId());
		Intent intent = new Intent(this, DisplayFormData.class);
		
		editText1 = (EditText) findViewById(R.id.etxt_firstName);
		editText2 = (EditText) findViewById(R.id.etxt_lastName);
		editText3 = (EditText) findViewById(R.id.etxt_email);
		editText4 = (EditText) findViewById(R.id.etxt_phone);
		
		intent.putExtra(EXTRA_MESSAGE1, editText1.getText().toString());
		intent.putExtra(EXTRA_MESSAGE2, editText2.getText().toString());
		intent.putExtra(EXTRA_MESSAGE3, editText3.getText().toString());
		intent.putExtra(EXTRA_MESSAGE4, editText4.getText().toString());
		
		startActivity(intent);
		
	}
	
	
	public void uploadPic(View view){
		
		/* Intent intent = new Intent();
		  intent.setType("image/*");
		  intent.setAction(Intent.ACTION_GET_CONTENT);		  
		  startActivityForResult(Intent.createChooser(intent, "Select Picture"),0);
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		String path = Environment.getExternalStorageDirectory() + "/photo1.jpg";
		 File file = new File(path);
	        Uri outputFileUri = Uri.fromFile(file);
		    intent.putExtra(MediaStore.EXTRA_OUTPUT,
		            Uri.withAppendedPath(outputFileUri , "profile"));
		    if (intent.resolveActivity(getPackageManager()) != null) {
		        startActivityForResult(intent, 0);
		    }
		
		  */

		  
	        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
	 
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle("Add Photo!");
	        builder.setItems(options, new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int item) {
	                if (options[item].equals("Take Photo"))
	                {
	                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
	                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
	                    startActivityForResult(intent, 1);
	                }
	                else if (options[item].equals("Choose from Gallery"))
	                {
	                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	                    startActivityForResult(intent, 2);
	 
	                }
	                else if (options[item].equals("Cancel")) {
	                    dialog.dismiss();
	                }
	            }
	        });
	        builder.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_capture, menu);
		return true;
	}
	  @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        if (resultCode == RESULT_OK) {
	            if (requestCode == 1) {
	                File f = new File(Environment.getExternalStorageDirectory().toString());
	                for (File temp : f.listFiles()) {
	                    if (temp.getName().equals("temp.jpg")) {
	                        f = temp;
	                        break;
	                    }
	                }
	                try {
	                    Bitmap bitmap;
	                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
	 
	                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
	                            bitmapOptions); 
	                   
	                    viewImage.setImageBitmap(bitmap);
	 
	                    String path = android.os.Environment
	                            .getExternalStorageDirectory()
	                            + File.separator
	                            + "Phoenix" + File.separator + "default";
	                    f.delete();
	                    OutputStream outFile = null;
	                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
	                    try {
	                        outFile = new FileOutputStream(file);
	                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
	                        outFile.flush();
	                        outFile.close();
	                    } catch (FileNotFoundException e) {
	                        e.printStackTrace();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            } else if (requestCode == 2) {
	 
	                Uri selectedImage = data.getData();
	                String[] filePath = { MediaStore.Images.Media.DATA };
	                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
	                c.moveToFirst();
	                int columnIndex = c.getColumnIndex(filePath[0]);
	                String picturePath = c.getString(columnIndex);
	                c.close();
	                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
	                Log.w("path of image from gallery......******************.........", picturePath+"");
	                viewImage.setImageBitmap(thumbnail);
	            }
	        
	    }   
	}
}
