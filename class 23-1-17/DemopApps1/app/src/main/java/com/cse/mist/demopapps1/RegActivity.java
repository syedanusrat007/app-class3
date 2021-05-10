package com.cse.mist.demopapps1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cse.mist.demopapps1.model.Person;

public class RegActivity extends AppCompatActivity {
    private Context context;
    private TextView tvOutPut;
    private EditText etAddress;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        context =this;
        intUi();
    }

    private void intUi() {

        tvOutPut = (TextView) findViewById(R.id.tvOutPut);
        tvOutPut.setMovementMethod(new ScrollingMovementMethod());
        etAddress = (EditText) findViewById(R.id.etAddress);
        btnSave = (Button) findViewById(R.id.btnSave);

        Intent intent = getIntent();
        Person pdata = (Person)intent.getSerializableExtra("personData");

        tvOutPut.setText("Name: "+pdata.getName()+"\n Email: "+pdata.getEmail()+"\n Password: "+pdata.getPassWord()
        +"\n Gender: "+pdata.getGender()+"\n Type: "+pdata.getType()+"\n isSaved: "+pdata.isSave());



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });
    }

    protected void checkData(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        String address = etAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)){

            alertDialog.setMessage(getString(R.string.alertAddress));
            alertDialog.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            alertDialog.show();
            return;
        }else{
            Intent intent = getIntent();
            etAddress.setText("");
            Person person = new Person();
            person.setAddress(address);
            intent.putExtra("pdata",person);
            setResult(RESULT_OK,intent);
        }
    }

}
