package com.abpro_ae4.libreriaonlineapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abpro_ae4.libreriaonlineapp.adapters.LibroAdapter;
import com.abpro_ae4.libreriaonlineapp.models.Libro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<Libro> listaLibrosGlobal;

    // Método estático para que otras actividades accedan a la lista
    public static List<Libro> obtenerListaLibrosGlobal() {
        return listaLibrosGlobal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // COnfigurar la Toolbar como ActionBar
        setSupportActionBar(findViewById(R.id.toolbar));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }


        //Inicializar vistas
        RecyclerView rvListaLibros = findViewById(R.id.rvListaLibros);

        //COnfigurar el layoutManager
        rvListaLibros.setLayoutManager(new LinearLayoutManager(this));

        // Crear lista de libros
        listaLibrosGlobal = new ArrayList<>();
        listaLibrosGlobal.add(new Libro("Cien Años de Soledad",
                "Obra maestra de Gabriel García Márquez.",
                R.drawable.libro1,
                BigDecimal.valueOf(16000)));
        listaLibrosGlobal.add(new Libro("1984",
                "Distopía de George Orwell sobre la vigilancia estatal.",
                R.drawable.libro2,
                BigDecimal.valueOf(14000)));
        listaLibrosGlobal.add(new Libro("El Principito",
                "Una historia poética sobre la amistad y la vida.",
                R.drawable.libro3,
                BigDecimal.valueOf(5000)));
        listaLibrosGlobal.add(new Libro("Don Quijote de la Mancha",
                "Clásico de Miguel de Cervantes sobre las aventuras de un caballero soñador.",
                R.drawable.libro4,
                BigDecimal.valueOf(18000)));
        listaLibrosGlobal.add(new Libro("La Odisea",
                "Epopeya griega atribuida a Homero, que narra el viaje de Odiseo.",
                R.drawable.libro5,
                BigDecimal.valueOf(15000)));

        listaLibrosGlobal.add(new Libro("Crimen y Castigo",
                "Novela psicológica de Fiódor Dostoyevski sobre la culpa y la redención.",
                R.drawable.libro6,
                BigDecimal.valueOf(17000)));

        listaLibrosGlobal.add(new Libro("Orgullo y Prejuicio",
                "Romance clásico de Jane Austen que retrata las costumbres sociales de su época.",
                R.drawable.libro7,
                BigDecimal.valueOf(13000)));

        listaLibrosGlobal.add(new Libro("Harry Potter y la Piedra Filosofal",
                "La primera entrega de la saga de J.K. Rowling que sigue las aventuras del joven mago.",
                R.drawable.libro8,
                BigDecimal.valueOf(20000)));


        // Crear y asignar el adaptador
        LibroAdapter adaptador = new LibroAdapter(listaLibrosGlobal);
        rvListaLibros.setAdapter(adaptador);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_carrito) {
            Intent intent = new Intent(this, CarritoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}