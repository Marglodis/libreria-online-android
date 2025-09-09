package com.abpro_ae4.libreriaonlineapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abpro_ae4.libreriaonlineapp.R;
import com.abpro_ae4.libreriaonlineapp.models.Libro;

import java.util.List;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.LibroViewHolder>{
    private List<Libro> listaLibros;

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
    static class LibroViewHolder extends RecyclerView.ViewHolder {
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
            ivPortada.setImageResource(libro.getImagenResorceId());
            tvTitulo.setText(libro.getTitulo());
            tvDescripcion.setText(libro.getDescripcion());

            btnAgregar.setOnClickListener(v-> {
                // TODO: Lógica para agregar al carrito
            });
        }
    }
}
