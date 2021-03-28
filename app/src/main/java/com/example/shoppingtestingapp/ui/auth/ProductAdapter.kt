package com.example.shoppingtestingapp.ui.auth

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingtestingapp.R
import com.example.shoppingtestingapp.domain.Product


class ProductAdapter(val context:Context,val list:List<Product>): RecyclerView.Adapter<ProductAdapter.viewHolder>() {

    class  viewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        fun onbind(product: Product, context: Context) {

            val name=itemView.findViewById<TextView>(R.id.name)

            val price=itemView.findViewById<TextView>(R.id.price)
            val vat=itemView.findViewById<TextView>(R.id.vat)

            val image=itemView.findViewById<ImageView>(R.id.imageView)



            name.text=product.name

            price.text=product.price.toString()

            vat.text=product.vat.toString()

            Glide.with(context)
                .load(product.image)

                .fitCenter()
                .into(image);


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val rowView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_layout_item,
                parent,
                false
            )


        return viewHolder(rowView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        var product=list.get(position)

        holder.onbind(product,context)
    }

    override fun getItemCount(): Int {
      return  list.size
    }

}