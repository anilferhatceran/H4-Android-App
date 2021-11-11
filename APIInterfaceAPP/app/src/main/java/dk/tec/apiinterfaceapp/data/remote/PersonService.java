package dk.tec.apiinterfaceapp.data.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;
import dk.tec.apiinterfaceapp.Person;


public interface PersonService {

    @GET("all_persons")
    Call<List<Person>> getAllPersons();

    @GET("get_person/{id}")
    Call<Person> getPerson(@Path("id") int id);

    @POST("add_person")
    Call<Person> addPerson(@Body Person person);
}
