package com.abpro_ae4.libreriaonlineapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abpro_ae4.libreriaonlineapp.adapters.LibroAdapter;
import com.abpro_ae4.libreriaonlineapp.models.Libro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListaLibros;
    private LibroAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar vistas
        rvListaLibros = findViewById(R.id.rvListaLibros);

        //COnfigurar el layoutManager
        rvListaLibros.setLayoutManager(new LinearLayoutManager(this));

        // Crear lista de libros
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro("Cien Años de Soledad", "Obra maestra de Gabriel García Márquez.", R.drawable.libro1));
        libros.add(new Libro("1984", "Distopía de George Orwell sobre la vigilancia estatal.", R.drawable.libro2));
        libros.add(new Libro("El Principito", "Una historia poética sobre la amistad y la vida.", R.drawable.libro3));

        // Crear y asignar el adaptador
        adaptador = new LibroAdapter(libros);
        rvListaLibros.setAdapter(adaptador);

    }
}