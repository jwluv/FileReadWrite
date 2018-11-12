package com.example.edu.filereadwrite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFileName, editTextInput;
    Button buttonFileRead, buttonFileWrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = findViewById(R.id.editTextFileName);
        editTextInput = findViewById(R.id.editTextInput);
        buttonFileRead = findViewById(R.id.buttonFileRead);
        buttonFileWrite = findViewById(R.id.buttonFileWrite);

        buttonFileRead.setOnClickListener(this);
        buttonFileWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        String strFileName = "";
        switch (v.getId()) {
            case R.id.buttonFileRead:
                strFileName = editTextFileName.getText().toString();
                try {
                    fileInputStream = openFileInput(strFileName);
                } catch (Exception e) {
                    Log.e("file_error", "openFileInput()");
                }

                try {
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    editTextInput.setText(new String(buffer));
                    fileInputStream.close();
                } catch (Exception e) {
                    Log.e("file_error", "read()");
                }

                break;
            case R.id.buttonFileWrite:
                strFileName = editTextFileName.getText().toString();
                try {
                    fileOutputStream = openFileOutput(strFileName, Context.MODE_PRIVATE);
                    fileOutputStream.write(editTextInput.getText().toString().getBytes());
                    editTextInput.setText("");
                    fileOutputStream.close();
                } catch (Exception e) {
                    Log.e("file_error", "openFileOutput()");
                }

                break;
        }
    }
}
