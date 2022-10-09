import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmerapp.R
import com.example.farmerapp.models.HomeContent
import kotlinx.android.synthetic.main.content_card.view.*

class HomeContentAdapter(
    var content : List<HomeContent>,
    private val listner: HomeContentClicked
) : RecyclerView.Adapter<HomeContentAdapter.HomeContentViewHolder>() {

    inner class HomeContentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_card,parent,false)

        val viewHolder = HomeContentViewHolder(view)

        view.setOnClickListener {
            listner.onItemClick(content[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: HomeContentViewHolder, position: Int) {

        holder.itemView.apply {
            tvHead.text = content[position].title
            tvDiscription.text = content[position].discription
            val image : ImageView = findViewById(R.id.ivImage)

            Glide.with(this).load(content[position].imageUrl).into(image)
        }

    }

    override fun getItemCount(): Int {
        return content.size
    }

}


interface HomeContentClicked{
    fun onItemClick(item: HomeContent)
}