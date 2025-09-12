package com.abpro_ae4.libreriaonlineapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.abpro_ae4.libreriaonlineapp.databinding.ActivityDetalleLibroBinding;

public class DetalleLibroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetalleLibroBinding binding = ActivityDetalleLibroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener datos del Intent
        String titulo = getIntent().getStringExtra("TITULO");
        String descripcion = getIntent().getStringExtra("DESCRIPCION");
        int imagenId = getIntent().getIntExtra("IMAGEN_ID", R.drawable.portada_default);

        // Asignar datos a las vistas
        binding.ivPortadaDetalle.setImageResource(imagenId);
        binding.tvTituloDetalle.setText(titulo);
        binding.tvDescripcionDetalle.setText(descripcion);
    }
}