package com.abpro_ae4.libreriaonlineapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.abpro_ae4.libreriaonlineapp.adapters.CarritoAdapter;
import com.abpro_ae4.libreriaonlineapp.databinding.ActivityCarritoBinding;
import com.abpro_ae4.libreriaonlineapp.models.Libro;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Actividad que muestra los libros agregados al carrito.
 * Permite al usuario ver los items, quitarlos y ver el resumen de la compra.
 */
public class CarritoActivity extends AppCompatActivity implements CarritoAdapter.OnCarritoUpdatedListener {

    ActivityCarritoBinding binding;
    private List<Libro> listaCompletaLibros; // La lista original con todos los libros
    private List<Libro> listaCarrito; // Lista filtrada (solo libros con cantidad > 0)
    private CarritoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCarritoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar RecyclerView
        binding.rvListaCarrito.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista completa de libros desde MainActivity (solución temporal)
        listaCompletaLibros = MainActivity.obtenerListaLibrosGlobal();
        if (listaCompletaLibros == null) {
            listaCompletaLibros = new ArrayList<>();
            Toast.makeText(this, "No se encontró la lista de libros.", Toast.LENGTH_SHORT).show();
        }

        // Crear una nueva lista solo con los libros que tienen cantidad > 0
        listaCarrito = new ArrayList<>();
        for (Libro libro : listaCompletaLibros) {
            if (libro.getCantidadEnCarrito() > 0) {
                listaCarrito.add(libro);
            }
        }

        // Crear y asignar el adaptador, pasando 'this' como listener
        adaptador = new CarritoAdapter(listaCarrito, this);
        binding.rvListaCarrito.setAdapter(adaptador);

        // Actualizar el resumen inicial (total de artículos y precio)
        actualizarResumen();

        // Configurar el botón de finalizar compra
        binding.btnFinalizarCompra.setOnClickListener(v -> {

                Toast.makeText(this, "¡Compra finalizada! Gracias por tu pedido.", Toast.LENGTH_LONG).show();

                 limpiarCarrito();
                 new AlertDialog.Builder(this)
                         .setTitle("¡Compra exitosa!")
                         .setMessage("Gracias por tu compra. Tu pedido ha sido recibido.")
                         .setPositiveButton("Aceptar", (dialog, which) -> {
                             // Volver al MainActivity
                             Intent intent = new Intent(CarritoActivity.this, MainActivity.class);
                             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                             startActivity(intent);
                             finish();
                         })
                         .setCancelable(false)
                         .show();
        });
    }

    /**
     * Método del listener OnCarritoUpdatedListener.
     * Se llama desde el adaptador cuando un item es eliminado o modificado.
     */
    @Override
    public void onCarritoUpdated() {
        actualizarResumen();
    }

    /**
     * Actualiza los TextViews del resumen: total de artículos y precio total.
     */
    private void actualizarResumen() {
        int totalArticulos = 0;
        BigDecimal totalPrecio = BigDecimal.ZERO;

        for (Libro libro : listaCarrito) {
            totalArticulos += libro.getCantidadEnCarrito();
            totalPrecio = totalPrecio.add(libro.calcularSubtotal());
        }

        // Formatear el precio total como moneda
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US);

        binding.tvTotalArticulos.setText("Total Artículos: " + totalArticulos);
        binding.tvTotalPrecio.setText("Total: " + formatoMoneda.format(totalPrecio));

        if(listaCarrito.isEmpty()) {
            binding.ivCarritoVacio.setVisibility(View.VISIBLE);
            binding.tvMensajeCarritoVacio.setVisibility(View.VISIBLE);
            binding.llResumen.setVisibility(View.GONE);

        } else {
            binding.ivCarritoVacio.setVisibility(View.GONE);
            binding.tvMensajeCarritoVacio.setVisibility(View.GONE);
            binding.llResumen.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Método para limpiar completamente el carrito.
     */
    @SuppressLint("NotifyDataSetChanged")
    private void limpiarCarrito() {
        for (Libro libro : listaCompletaLibros) {
             libro.setCantidadEnCarrito(0);
        }
        listaCarrito.clear();
        adaptador.notifyDataSetChanged();
        actualizarResumen();
    }
}