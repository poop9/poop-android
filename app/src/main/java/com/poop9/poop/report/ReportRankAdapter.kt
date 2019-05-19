package com.poop9.poop.report

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.poop9.poop.R
import com.poop9.poop.vo.RankData
import com.romainpiel.shimmer.ShimmerTextView
import com.romainpiel.shimmer.Shimmer



class ReportRankAdapter(private val context: Context,
                        private val dataSource: List<RankData>) : BaseAdapter() {
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.item_top10, parent, false)

        val top10Medal = rowView.findViewById(R.id.item_top10_medal) as ImageView
        val top10Rank = rowView.findViewById(R.id.item_top10_rank) as TextView
        val top10Nick = rowView.findViewById(R.id.item_top10_nick) as TextView
        val top10Count = rowView.findViewById(R.id.item_top10_count) as TextView
        val top10NickShimmer = rowView.findViewById(R.id.item_top10_nick_shimmer) as ShimmerTextView

        val data = getItem(position) as RankData
        when(position){
            0->{
                top10Medal.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.gold_medal))
                val shimmer = Shimmer()
                top10Nick.visibility = View.INVISIBLE
                top10NickShimmer.visibility = View.VISIBLE
                top10NickShimmer.text = data.nickname
                top10NickShimmer.textSize = 20f
                shimmer.start(top10NickShimmer)
            }
            1->{
                top10Medal.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.silver_medal))
                val shimmer = Shimmer()
                top10Nick.visibility = View.INVISIBLE
                top10NickShimmer.visibility = View.VISIBLE
                top10NickShimmer.text = data.nickname
                shimmer.start(top10NickShimmer)
            }
            2->{
                top10Medal.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bronze_medal))
                val shimmer = Shimmer()
                top10Nick.visibility = View.INVISIBLE
                top10NickShimmer.visibility = View.VISIBLE
                top10NickShimmer.text = data.nickname
                shimmer.start(top10NickShimmer)
            }
            else -> {
                top10Medal.visibility = View.INVISIBLE
                top10Rank.visibility = View.VISIBLE
                top10Rank.text = Integer.toString(data.rank)
            }
        }
        top10Rank.text = String.format("%s", position+1)
        top10Nick.text = data.nickname
        top10Count.text = String.format("(%s회)", data.count)
        return rowView
    }

    private val inflater: LayoutInflater
        = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}