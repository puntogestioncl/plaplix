package cl.puntogestion.plaplix.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.puntogestion.plaplix.R
import cl.puntogestion.plaplix.db.local.ProductEntity
import cl.puntogestion.plaplix.view.ProductAdapter.ProductViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class ProductAdapter(private var mDataSet: MutableList<ProductEntity>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    var product = MutableLiveData<ProductEntity>()

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.textMarca.text = mDataSet.get(position).name
        holder.textPrice.text = mDataSet.get(position).price.toString()
        Glide.with(holder.itemView.context).load(mDataSet.get(position).image).into(holder.imageViewPhone);

        holder.itemView.setOnClickListener{
            product.postValue(mDataSet.get(position))
        }
    }

    fun updateItems(it: List<ProductEntity>) {
        mDataSet.clear()
        mDataSet.addAll(it)
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewPhone = itemView.imageViewMobile
        var textMarca = itemView.textViewMarca
        var textPrice = itemView.textViewPrice
    }
}