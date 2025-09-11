package com.abpro_ae4.libreriaonlineapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

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
    private RecyclerView rvListaLibros;
    private LibroAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // COnfigurar la Toolbar como ActionBar
        setSupportActionBar(findViewById(R.id.toolbar));

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }


        //Inicializar vistas
        rvListaLibros = findViewById(R.id.rvListaLibros);

        //COnfigurar el layoutManager
        rvListaLibros.setLayoutManager(new LinearLayoutManager(this));

        // Crear lista de libros
        listaLibrosGlobal = new ArrayList<>();
        listaLibrosGlobal.add(new Libro("Cien Años de Soledad", "Obra maestra de Gabriel García Márquez.", R.drawable.libro1, BigDecimal.valueOf(16000)));
        listaLibrosGlobal.add(new Libro("1984", "Distopía de George Orwell sobre la vigilancia estatal.", R.drawable.libro2, BigDecimal.valueOf(14000)));
        listaLibrosGlobal.add(new Libro("El Principito", "Una historia poética sobre la amistad y la vida.", R.drawable.libro3, BigDecimal.valueOf(5000)));


        // Crear y asignar el adaptador
        adaptador = new LibroAdapter(listaLibrosGlobal);
        rvListaLibros.setAdapter(adaptador);

    }

    public static List<Libro> obtenerListaLibros() {
        return listaLibrosGlobal;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //Obtener el ite, del carrito
   //     MenuItem itemCarrito = menu.findItem(R.id.action_carrito);
       /* View view = itemCarrito.getActionView();

        //Calcular total de articulos
        int totalArticulos = 0;
        for(Libro libro : listaLibrosGlobal){
            totalArticulos += libro.getCantidadEnCarrito();
        }

        if(totalArticulos > 0) {
            String contador = String.valueOf(totalArticulos);
            SpannableString spannableString = new SpannableString(" " + contador); // espacio para separar del ícono
            spannableString.setSpan(new BackgroundColorSpan(Color.RED), 0, spannableString.length(), 0);
            spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spannableString.length(),0);
            spannableString.setSpan(new RelativeSizeSpan(0.7f), 0, spannableString.length(),0); //Tamaño más pequeño

            // CRear un objeto SpannableStringBuilder para combinar el ícono y el contador
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(itemCarrito.getTitle());
            builder.append(spannableString);

            itemCarrito.setTitle(builder);
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.action_carrito) {
            Intent intent = new Intent(this, CarritoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Método estático para que otras actividades accedan a la lista
    public static List<Libro> obtenerListaLibrosGlobal() {
        return listaLibrosGlobal;
    }
}