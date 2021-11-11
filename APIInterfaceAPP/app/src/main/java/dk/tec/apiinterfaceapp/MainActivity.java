package dk.tec.apiinterfaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dk.tec.apiinterfaceapp.data.remote.PersonService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView personListView;

    TextView personName;

    PersonService service;

    Button btnAddPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        service = ServiceBuilder.buildService(PersonService.class);

        personName = findViewById(R.id.txtPersonName);

        personListView = findViewById(R.id.personList);

        btnAddPerson = findViewById(R.id.btnAddPerson);

        getAllPersons();

        getPersonById(3, personName);

        btnAddPerson.setOnClickListener(this);
    }

    public Call<List<Person>> getAllPersons()
    {
        Call<List<Person>> request = service.getAllPersons();

        request.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response)
            {
                List<Person> personList = response.body();

                Log.d("Person List", personList.toString());

                ArrayList<String> nameList = new ArrayList<String>();

                for (int i = 0; i < personList.size(); i++)
                {
                    nameList.add(personList.get(i).getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nameList);

                personListView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t)
            {
                Log.d("Log failed:","Fail");
            }
        });
        return request;
    }
    public void getPersonById(int id, TextView personName){
        Call<Person> request = service.getPerson(id);



        request.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person;
                person = response.body();

                personName.setText(person.getName());

                Log.d("Name By Id", person.getName());

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v)
    {

        Intent intent = new Intent(this, CreateActivity.class);

        startActivity(intent);

    }
}