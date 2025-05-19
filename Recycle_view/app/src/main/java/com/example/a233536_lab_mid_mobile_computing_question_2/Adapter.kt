package com.example.a233536_lab_mid_mobile_computing_question_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val items: List<Product>,
    private val onAddToCartClick: OnProductClickListener
) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = items[position]
        holder.bind(product)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.textView)
        private val txtDescription: TextView = itemView.findViewById(R.id.textView2)
        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val button: Button = itemView.findViewById(R.id.button)

        fun bind(product: Product) {
            txtTitle.text = product.name
            txtDescription.text = product.price
            image.setImageResource(product.imageResId)

            button.setOnClickListener {
                onAddToCartClick.onProductClick(product)
            }
        }
    }
}
