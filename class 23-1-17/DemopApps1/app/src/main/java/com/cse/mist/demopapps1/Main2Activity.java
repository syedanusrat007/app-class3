package com.cse.mist.demopapps1;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.cse.mist.demopapps1.adapter.CustomSpinnerAdapter;
import com.cse.mist.demopapps1.model.Person;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
     private Context context;
     private EditText etName,etEmail,etPassword;
     private Spinner spType;
     private RadioGroup rgBtnGender;
     private RadioButton rBtnMale,rBtnFemail,rBtnOthers;
     private TextView btnTextReg,btnTexLogin,tvResult;
     private CheckBox chkPastBtn;

    private static final int REQUEST_CODE = 900;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        context = this;
        intUi();
    }

    private void intUi() {


         SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
         SharedPreferences.Editor editor = pref.edit();


        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnTextReg = (TextView) findViewById(R.id.btnTextReg);
        btnTexLogin = (TextView) findViewById(R.id.btnTexLogin);
        rgBtnGender = (RadioGroup) findViewById(R.id.rgBtnGender);
        rBtnMale = (RadioButton) findViewById(R.id.rBtnMale);
        rBtnFemail = (RadioButton) findViewById(R.id.rBtnFemail);
        rBtnOthers = (RadioButton) findViewById(R.id.rBtnOthers);
        chkPastBtn = (CheckBox) findViewById(R.id.chkPastBtn);

        tvResult = (TextView) findViewById(R.id.tvResult);

    /*    if (pref.getBoolean("forgetValue",true)){
            chkPastBtn.setChecked(true);
        }else{

            chkPastBtn.setChecked(false);
        }
*/

        spType = (Spinner) findViewById(R.id.spType);

        ArrayList<String> type = new ArrayList<String>();
        type.add("Choose a Type");
        type.add("Islam");
        type.add("Science");
        type.add("Arts");
        type.add("Social");
        type.add("Filem");
        type.add("Health");
        type.add("Beauty");
        type.add("Islam11");
        type.add("Science11");
        type.add("Arts11");
        type.add("Social11");
        type.add("Filem11");
        type.add("Health11");
        type.add("Beauty11");
        type.add("Islam22");
        type.add("Science22");
        type.add("Arts22");
        type.add("Social22");
        type.add("Filem22");
        type.add("Health22");
        type.add("Beauty22");

        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(context,type);
        spType.setAdapter(customSpinnerAdapter);


        btnTexLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*//RadioBtn value
                final String valueGender = ((RadioButton)findViewById(rgBtnGender.getCheckedRadioButtonId() )).getText().toString();
                Toast.makeText(context,"Gender: "+valueGender,Toast.LENGTH_SHORT).show();*/


        /*      //Spinner value
                String spinnerItem =  spType.getItemAtPosition(spType.getSelectedItemPosition()).toString();
                Toast.makeText(context,"Spinner Item: "+spinnerItem,Toast.LENGTH_SHORT).show();*/

                   /*    //CheckBox Check and Value
                        if (chkPastBtn.isChecked()){
                            editor.putBoolean("forgetValue", true);
                            editor.commit();
                            Toast.makeText(context,"CheckBox Checked and value: "+chkPastBtn.getText().toString(),Toast.LENGTH_SHORT).show();
                        }else{
                            editor.putBoolean("forgetValue", false);
                            editor.commit();
                            Toast.makeText(context,"CheckBox not Checked",Toast.LENGTH_SHORT).show();
                        }*/





            }
        });


        btnTextReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()){
                    dataCheck();
                    //Toast.makeText(context,"Network is available",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context,"Network isn't available",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    protected void dataCheck(){
            SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

            boolean isSaved;
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String passWord= etPassword.getText().toString().trim();

           String valueGender = ((RadioButton)findViewById(rgBtnGender.getCheckedRadioButtonId() )).getText().toString();
           String spinnerItem =  spType.getItemAtPosition(spType.getSelectedItemPosition()).toString();


        if (chkPastBtn.isChecked()){
           // editor.putBoolean("forgetValue", true);
           // editor.commit();
           // Toast.makeText(context,"CheckBox Checked and value: "+chkPastBtn.getText().toString(),Toast.LENGTH_SHORT).show();
            isSaved =true;
        }else{
           // editor.putBoolean("forgetValue", false);
           // editor.commit();
            //Toast.makeText(context,"CheckBox not Checked",Toast.LENGTH_SHORT).show();
            isSaved = false;
        }



        if (TextUtils.isEmpty(name)){

            alertDialog.setMessage(getString(R.string.alertName));
            alertDialog.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            alertDialog.show();
            return;
        }else if(!isValidEmail(email)){
            alertDialog.setMessage(getString(R.string.alertEmail));
            alertDialog.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            alertDialog.show();
             return;
        }else if(TextUtils.isEmpty(passWord)){
            alertDialog.setMessage(getString(R.string.alertPassWord));
            alertDialog.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
              alertDialog.show();
              return;
        }else if(spinnerItem.equalsIgnoreCase("Choose a Type")) {

            alertDialog.setMessage(getString(R.string.alertType));
            alertDialog.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            alertDialog.show();
            return;

        }else{

            etName.setText("");
            etEmail.setText("");
            etPassword.setText("");

            Person person = new Person();
            person.setName(name);
            person.setEmail(email);
            person.setPassWord(passWord);
            person.setGender(valueGender);
            person.setType(spinnerItem);
            person.setSave(isSaved);

            Intent intent = new Intent(context,RegActivity.class);
            intent.putExtra("personData",person);
            startActivityForResult(intent, REQUEST_CODE);
          //  startActivity(intent);

        }
    }

    protected  boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==REQUEST_CODE){
            if (resultCode==RESULT_OK){

                Person pd = (Person) data.getSerializableExtra("pdata");
                if (TextUtils.isEmpty(pd.getAddress())){
                    tvResult.setText("");
                }else{
                    tvResult.setText(pd.getAddress());
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
