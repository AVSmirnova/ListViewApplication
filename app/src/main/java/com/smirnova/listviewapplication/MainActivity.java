package com.smirnova.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<State> states = new ArrayList<State>();
    ListView countriesList;
    GridView gridview;

    // создаем переменные для работы с БД
    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DBHelper(getApplicationContext());
// начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
      countriesList = findViewById(R.id.countriesList);
       // gridview = findViewById(R.id.gridview);
        // создаем адаптер
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        // устанавливаем адаптер
       countriesList.setAdapter(stateAdapter);
        //gridview.setAdapter(stateAdapter);
        // устанавливаем слушатель
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // получаем выбранный пункт
                State selectedState = (State)adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getName(),
                        Toast.LENGTH_SHORT).show();
            }


        };
        countriesList.setOnItemClickListener(itemListener);
       // gridview.setOnItemClickListener(itemListener);

    }
    private void setInitialData(){

        if(states!=null)  states.clear();
        // открываем подключение
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DBHelper.TABLE, null);

        while(userCursor.moveToNext()){
            String country = userCursor.getString(1);
            String capital= userCursor.getString(2);
            int flagResource = userCursor.getInt(3);
            states.add(new State(country,capital,flagResource));
        }
        userCursor.close();






       /* states.add(new State ("Россия", "Москва", R.drawable.flag_of_russia));
        states.add(new State ("Франция", "Париж", R.drawable.france));
        states.add(new State ("Германия", "Берлин", R.drawable.germany));
        states.add(new State ("Италия", "Рим", R.drawable.italy));
        states.add(new State ("Китай", "Пекин", R.drawable.knr));
        states.add(new State ("Великобритания", "Лондон", R.drawable.velikobritaniya));
        states.add(new State ("Япония", "Токио", R.drawable.yapan));*/


    }
}