package com.ranajit.shaadi.ui.homescreen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ranajit.shaadi.R
import com.ranajit.shaadi.ShaadiApplication
import com.ranajit.shaadi.model.MatchesModel
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileListAdapter(
    private val context: Context, var arrayList: ArrayList<MatchesModel>,
    internal var recyclerViewItemClickListener: RecyclerViewItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ContactsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_profile, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as ContactsViewHolder).bindItems(arrayList[p1], p1)
    }

    inner class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("NewApi")
        fun bindItems(profile: MatchesModel, position: Int) {

            itemView.name.text = StringBuilder()
                .append(profile.title)
                .append(" ")
                .append(profile.firstName)
                .append(" ")
                .append(profile.lastName)
                .append(", ")
                .append(profile.dobAge)
                .toString()

            itemView.subtext1.text = StringBuilder()
                .append(profile.city)
                .append(", ")
                .append(profile.state)
                .append("\n")
                .append(profile.country)
                .toString()

            Glide.with(ShaadiApplication.instance)
                .load(profile.pictureLarge)
                .into(itemView.avatar)

            if (profile.isAccepted == 1) {
                itemView.gradient.background = context.getDrawable(R.drawable.gradient_green)
                itemView.subtext2.visibility = View.VISIBLE
                itemView.buttons_2.visibility = View.GONE
                itemView.subtext2.text = context.getString(R.string.accepted)
                itemView.subtext2.setTextColor(context.resources.getColor(R.color.green))
                itemView.subtext2.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    ContextCompat.getDrawable(context, R.drawable.ic_tick_green),
                    null
                )

            } else if (profile.isAccepted == 0) {
                itemView.gradient.background = context.getDrawable(R.drawable.gradient_red)
                itemView.subtext2.visibility = View.VISIBLE
                itemView.buttons_2.visibility = View.GONE
                itemView.subtext2.text = "REJECTED"
                itemView.subtext2.setTextColor(context.resources.getColor(R.color.red))
                itemView.subtext2.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    ContextCompat.getDrawable(context, R.drawable.ic_tick_red),
                    null
                )

            } else {
                itemView.gradient.setBackgroundColor(context.resources.getColor(R.color.white))
                itemView.subtext2.visibility = View.GONE
                itemView.buttons_2.visibility = View.VISIBLE
            }

            itemView.button_decline_2.setOnClickListener {
                recyclerViewItemClickListener.onReject(profile, position)
                notifyItemChanged(position)
            }
            itemView.button_accept_2.setOnClickListener {
                recyclerViewItemClickListener.onAccept(profile, position)
                notifyItemChanged(position)
            }
        }
    }

    interface RecyclerViewItemClickListener {
        fun onAccept(data: MatchesModel, position: Int)
        fun onReject(data: MatchesModel, position: Int)
    }
}

