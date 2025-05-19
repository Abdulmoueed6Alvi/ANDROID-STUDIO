package com.example.a233536_lab_mid_mobile_computing_question_2
import com.example.a233536_lab_mid_mobile_computing_question_2.Adapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), Adapter.OnProductClickListener {

    private val cartList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.items)
        val products = listOf(
            Product("Bottle", "Price: Rs 150", R.drawable.download),
            Product("Chair", "Price: Rs 1200", R.drawable.down),
            Product("Table", "Price: Rs 2500", R.drawable.table),
            Product("Lamp", "Price: Rs 900", R.drawable.lamp),
            Product("Notebook", "Price: Rs 100", R.drawable.notebook),
            Product("Pen", "Price: Rs 20", R.drawable.pen),
            Product("Bag", "Price: Rs 800", R.drawable.bag),
            Product("Shoes", "Price: Rs 2500", R.drawable.shoes),
            Product("Watch", "Price: Rs 5000", R.drawable.watch),
            Product("Mirror", "Price: Rs 700", R.drawable.mirror),
            Product("Pillow", "Price: Rs 400", R.drawable.pillow),
            Product("Blanket", "Price: Rs 1500", R.drawable.blanket),
            Product("Headphones", "Price: Rs 3500", R.drawable.headphones),
            Product("Charger", "Price: Rs 600", R.drawable.charger),
            Product("Glasses", "Price: Rs 2000", R.drawable.glasses),
            Product("Keyboard", "Price: Rs 2500", R.drawable.keyboard),
            Product("Mouse", "Price: Rs 700", R.drawable.mouse),
            Product("Wallet", "Price: Rs 1500", R.drawable.wallet)
        )


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(products, this)
    }

    override fun onProductClick(product: Product) {
        cartList.add(product)
        Toast.makeText(this, "${product.name} added to cart!", Toast.LENGTH_SHORT).show()
    }
}
