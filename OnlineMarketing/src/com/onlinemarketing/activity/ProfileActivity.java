package com.onlinemarketing.activity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProfile;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.ProfileVO;

public class ProfileActivity extends BaseActivity implements OnClickListener {

	private int PICK_IMAGE = 0;
	private Bitmap bitmap;
	ProfileVO profile = new ProfileVO();
	Output out;
	ImageView imgAvatar;
	EditText editName, editPhone, editMail, editAdd, editPass, editConfigPass;
	Button btnApprovePhone, btnSave, btnBacklist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_profile);
		super.onCreate(savedInstanceState);
		imgAvatar = (ImageView) findViewById(R.id.imgAvatar_profile);
		editName = (EditText) findViewById(R.id.editName_profile);
		editMail = (EditText) findViewById(R.id.editEmail_profile);
		editAdd = (EditText) findViewById(R.id.editAddress_profile);
		editPass = (EditText) findViewById(R.id.editPass_profile);
		editConfigPass = (EditText) findViewById(R.id.editConfigPass_profile);
		editPhone = (EditText) findViewById(R.id.editPhone_profile);
		btnApprovePhone = (Button) findViewById(R.id.btnApprovePhone_profile);
		btnSave = (Button) findViewById(R.id.btnSave_profile);
		imgAvatar = (ImageView) findViewById(R.id.imgAvatar_profile);
		btnBacklist = (Button) findViewById(R.id.btnBackList);
		btnBacklist.setOnClickListener(this);
		imgAvatar.setOnClickListener(this);
		btnApprovePhone.setOnClickListener(this);
		btnSave.setOnClickListener(this);
		setData(SystemConfig.oOputproduct.getProfileVO());
	}

	public void setData(ProfileVO proVo) {
		// imgAvatar.setImageResource(Integer.parseInt(proVo.getAvatar()));
		editName.setText(proVo.getUsername());
		editPhone.setText(proVo.getPhone());
		editMail.setText(proVo.getEmail());
		editAdd.setText(proVo.getAddress());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSave_profile:
			profile.setUsername(editName.getText().toString());
			profile.setPhone(editPhone.getText().toString());
			profile.setEmail(editMail.getText().toString());
			profile.setAddress(editAdd.getText().toString());
			profile.setPass(editPass.getText().toString());
			profile.setOld_pass(editConfigPass.getText().toString());
			profile.setAvatar("a");
			new UpdateAsystask().execute();
			break;

		case R.id.btnApprovePhone_profile:
			break;
		case R.id.btnBackList:
			startActivity(new Intent(ProfileActivity.this, BackListActivity.class));
			break;
		case R.id.imgAvatar_profile:
			getGallery();
			break;
		}
	}
	

	public ContentResolver getGallery() {
		  Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		  startActivityForResult(i, PICK_IMAGE);
		  return null;
		 }

		 @Override
		 public void onActivityResult(int requestCode, int resultCode, Intent data) {
		  if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK)
		   try {
		    // We need to recyle unused bitmaps
		    if (bitmap != null) {
		     bitmap.recycle();
		    }
		    InputStream stream = this.getContentResolver().openInputStream(data.getData());
		    bitmap = BitmapFactory.decodeStream(stream);
		    stream.close();
		    imgAvatar.setImageBitmap(bitmap);
		   } catch (FileNotFoundException e) {
		    e.printStackTrace();
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		  super.onActivityResult(requestCode, resultCode, data);
		 }

	public class UpdateAsystask extends AsyncTask<String, String, Output> {

		JsonProfile jsonProfile;

		@Override
		protected void onPreExecute() {
			jsonProfile = new JsonProfile();
			super.onPreExecute();
		}

		@Override
		protected Output doInBackground(String... params) {
			out = jsonProfile.postPaserProfile(SystemConfig.user_id,
					SystemConfig.session_id, SystemConfig.device_id, profile);
			return out;
		}

		@Override
		protected void onPostExecute(Output result) {
			Debug.e("bbbbbbbbbbbbbb: " + Constan.getIntProperty("success"));
			Debug.e("aaaaaaaaaaaaaa: " + result.getCode());
			// if (result.getCode() == Constan.getIntProperty("success")) {
			// Debug.showAlert(ProfileActivity.this, result.getMessage());
			// }
			super.onPostExecute(result);
		}

	}
}
