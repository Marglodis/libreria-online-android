package com.abpro_ae4.libreriaonlineapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
    private RecyclerView rvListaCarrito;
    private TextView tvTotalArticulos;
    private TextView tvTotalPrecio;
    private Button btnFinalizarCompra;

    private List<Libro> listaCompletaLibros; // La lista original con todos los libros
    private List<Libro> listaCarrito; // Lista filtrada (solo libros con cantidad > 0)
    private CarritoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCarritoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvListaCarrito = binding.rvListaCarrito;
        tvTotalArticulos = binding.tvTotalArticulos;
        tvTotalPrecio = binding.tvTotalPrecio;
        btnFinalizarCompra = binding.btnFinalizarCompra;

        // Configurar RecyclerView
        rvListaCarrito.setLayoutManager(new LinearLayoutManager(this));

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
        rvListaCarrito.setAdapter(adaptador);

        // Actualizar el resumen inicial (total de artículos y precio)
        actualizarResumen();

        // Configurar el botón de finalizar compra
        btnFinalizarCompra.setOnClickListener(v -> {
            if (listaCarrito.isEmpty()) {
                Toast.makeText(this, "Tu carrito está vacío.", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Aquí iría la lógica para procesar la compra
                Toast.makeText(this, "¡Compra finalizada! Gracias por tu pedido.", Toast.LENGTH_LONG).show();
                // Opcional: Limpiar el carrito después de la compra
                 limpiarCarrito();
            }
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

        tvTotalArticulos.setText("Total Artículos: " + totalArticulos);
        tvTotalPrecio.setText("Total: " + formatoMoneda.format(totalPrecio));

        if(listaCarrito.isEmpty()) {
            binding.ivCarritoVacio.setVisibility(View.VISIBLE);
            binding.tvMensajeCarritoVacio.setVisibility(View.VISIBLE);
            rvListaCarrito.setVisibility(View.GONE);
            btnFinalizarCompra.setVisibility(View.GONE);
            tvTotalArticulos.setVisibility(View.GONE);
            tvTotalPrecio.setVisibility(View.GONE);

        } else {
            binding.ivCarritoVacio.setVisibility(View.GONE);
            binding.tvMensajeCarritoVacio.setVisibility(View.GONE);
            rvListaCarrito.setVisibility(View.VISIBLE);
            btnFinalizarCompra.setVisibility(View.VISIBLE);
            tvTotalArticulos.setVisibility(View.VISIBLE);
            tvTotalPrecio.setVisibility(View.VISIBLE);
        }
    }

    /**
     * (Opcional) Método para limpiar completamente el carrito.
     * Útil si se implementa la finalización de compra.
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