package com.abpro_ae4.libreriaonlineapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abpro_ae4.libreriaonlineapp.DetalleLibroActivity;
import com.abpro_ae4.libreriaonlineapp.R;
import com.abpro_ae4.libreriaonlineapp.models.Libro;

import java.util.List;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.LibroViewHolder>{
    private static List<Libro> listaLibros;

    public LibroAdapter(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Libro libroActual = listaLibros.get(position);
        holder.bind(libroActual);
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }
    // View Interno (Patrón ViewHolder)
    class LibroViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPortada;
        private TextView tvTitulo;
        private TextView tvDescripcion;
        private Button btnAgregar;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivPortada = itemView.findViewById(R.id.ivPortadaLibro);
            tvTitulo = itemView.findViewById(R.id.tvTituloLibro);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionLibro);
            btnAgregar = itemView.findViewById(R.id.btnAgregarCarrito);
        }

        public void bind(Libro libro) {
            ivPortada.setImageResource(libro.getImagenResourceId());
            tvTitulo.setText(libro.getTitulo());
            tvDescripcion.setText(libro.getDescripcion());

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Libro libroSeleccionado = listaLibros.get(position);
                    // Crear Intent para navegar a DetalleLibroActivity
                    Intent intent = new Intent(itemView.getContext(), DetalleLibroActivity.class);
                    intent.putExtra("TITULO", libroSeleccionado.getTitulo());
                    intent.putExtra("DESCRIPCION", libroSeleccionado.getDescripcion());
                    intent.putExtra("IMAGEN_ID", libroSeleccionado.getImagenResourceId());
                    itemView.getContext().startActivity(intent);
                }
            });

            btnAgregar.setOnClickListener(v-> {
                //Agrega el lirbor al carrito
                libro.agregarAlCarrito();

                //Notifica al adaptadorqu el item ah cambiado
                notifyItemChanged(getAdapterPosition());

                // Feedback al usuario
                Toast.makeText(v.getContext(), "¡" + libro.getTitulo() + " agregado!", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
