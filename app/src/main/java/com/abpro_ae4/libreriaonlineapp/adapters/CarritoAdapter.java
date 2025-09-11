package com.abpro_ae4.libreriaonlineapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abpro_ae4.libreriaonlineapp.R;
import com.abpro_ae4.libreriaonlineapp.models.Libro;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {
    private static List<Libro> listaCarrito;
    private OnCarritoUpdatedListener listener;

    // Interfaz para comunicación con la Activity
    public interface OnCarritoUpdatedListener {
        void onCarritoUpdated();
    }

    //COnstructor
    public CarritoAdapter(List<Libro> listaCarrito, OnCarritoUpdatedListener listener) {
        this.listaCarrito = listaCarrito;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrito, parent, false);
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder holder, int position) {
        Libro libro = listaCarrito.get(position);
        holder.bind(libro);

    }

    @Override
    public int getItemCount() {
        return listaCarrito != null ? listaCarrito.size() : 0;
    }

    class CarritoViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivPortada;
        private TextView tvTitulo;
        private TextView tvCantidad;
        private TextView tvSubtotal;
        private Button btnQuitar;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPortada = itemView.findViewById(R.id.ivPortadaCarrito);
            tvTitulo = itemView.findViewById(R.id.tvTituloCarritoItem);
            tvCantidad = itemView.findViewById(R.id.tvCantidadCarritoItem);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotalCarritoItem);
            btnQuitar = itemView.findViewById(R.id.btnQuitarCarrito);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Libro libro) {
            ivPortada.setImageResource(libro.getImagenResourceId());
            tvTitulo.setText(libro.getTitulo());

            // Formatear números
            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US);

            tvCantidad.setText("Cantidad: " + libro.getCantidadEnCarrito());
            tvSubtotal.setText("Subtotal: " + formatoMoneda.format(libro.calcularSubtotal()));

            btnQuitar.setOnClickListener(v -> {
                libro.eliminarDelCarrito();

                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (libro.getCantidadEnCarrito() == 0) {
                        // Si la cantidad es 0, lo quitamos de la lista
                        listaCarrito.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        // Si aún tiene cantidad, actualizamos el item
                        notifyItemChanged(position);
                    }
                }

                // Notificar al Activity para que actualice el resumen
                if (listener != null) {
                    listener.onCarritoUpdated();
                }

                Toast.makeText(itemView.getContext(),
                        "¡" + libro.getTitulo() + " eliminado del carrito!",
                        Toast.LENGTH_SHORT).show();
            });
        }
    }
}
