package com.abpro_ae4.libreriaonlineapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalleLibroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);

        // Obtener referencia a las vistas
        ImageView ivPortada = findViewById(R.id.ivPortadaDetalle);
        TextView tvTitulo = findViewById(R.id.tvTituloDetalle);
        TextView tvDescripcion = findViewById(R.id.tvDescripcionDetalle);

        // Obtener datos del Intent
        String titulo = getIntent().getStringExtra("TITULO");
        String descripcion = getIntent().getStringExtra("DESCRIPCION");
        int imagenId = getIntent().getIntExtra("IMAGEN_ID", R.drawable.portada_default);

        // Asignar datos a las vistas
        ivPortada.setImageResource(imagenId);
        tvTitulo.setText(titulo);
        tvDescripcion.setText(descripcion);
    }
}