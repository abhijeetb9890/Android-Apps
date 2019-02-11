package com.sdhkcnasdc.hp.todolist;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText task_edit;
    EditText descr_edit;
    EditText id_edit;
    dbhelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("To Do List");
        mydb=new dbhelper(MainActivity.this);

        task_edit = (EditText)findViewById(R.id.editText1);
        descr_edit = (EditText)findViewById(R.id.editText2);
        id_edit = (EditText) findViewById(R.id.editText3);
    }

    public void addtask(View view){
        long isinserted = mydb.insertData(task_edit.getText().toString(),descr_edit.getText().toString());
        int count=0;
        Cursor cursor= mydb.getAllData();
        while(cursor.moveToNext()) {
            count++;
        }
        if (isinserted != -1)
            Toast.makeText(MainActivity.this,"Task no."+count+" Inserted!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Task not inserted..",Toast.LENGTH_LONG).show();
    }

    public void viewtasks(View view){
        int count=0;
        Cursor cursor= mydb.getAllData();
        StringBuffer buffer = new StringBuffer();
        if(cursor.getCount()==0)
            Toast.makeText(MainActivity.this,"There're no pending tasks!",Toast.LENGTH_LONG).show();
        else{
            while(cursor.moveToNext()){
                count++;
                buffer.append("SrNo: #"+count+"\n");
                buffer.append("TASK: "+cursor.getString(1)+"\n");
                buffer.append("DESCR: "+cursor.getString(2)+"\n");
                buffer.append("ID: "+cursor.getString(0)+"\n\n\n");
            }
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Here they are!");
            builder.setMessage(buffer.toString());
            builder.show();

        }

    }

    public void updatetask(View view){
             long isupdate = mydb.updateData(id_edit.getText().toString(),task_edit.getText().toString(),descr_edit.getText().toString());
             if (isupdate!=0)
                 Toast.makeText(this,isupdate+" task updated with ID:"+id_edit.getText().toString()+"!",Toast.LENGTH_LONG).show();
             else
                 Toast.makeText(this,"Task not updated..",Toast.LENGTH_LONG).show();
    }

    public void deletetask(View view){
        int res = mydb.deleteData(id_edit.getText().toString());
        Toast.makeText(this,res+" tasks deleted!",Toast.LENGTH_LONG).show();
    }
}
