package dk.tec.apiinterfaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dk.tec.apiinterfaceapp.data.remote.PersonService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity{

    EditText edtPersonName;
    EditText edtPersonEmail;

    Button btnCreatePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create3);

        edtPersonName = findViewById(R.id.edtPersonName);
        edtPersonEmail = findViewById(R.id.edtPersonEmail);

        btnCreatePerson = findViewById(R.id.btnCreatePerson);



        btnCreatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonService personService = ServiceBuilder.buildService(PersonService.class);

                String name,email;

                name = edtPersonName.getText().toString();
                email = edtPersonEmail.getText().toString();

                Person person = new Person(name,email);

                Call<Person> request = personService.addPerson(person);

                request.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {

                        finish();
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {

                    }
                });
            }
        });
    }


}