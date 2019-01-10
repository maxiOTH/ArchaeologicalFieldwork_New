package org.wit.archaeologicalfieldwork.views.sitelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_site.view.*
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.helpers.readImageFromPath
import org.wit.archaeologicalfieldwork.models.SiteModel

interface SiteListener{
    fun onSiteClick(site:SiteModel)
}

class SiteAdapter constructor(private var sites:List<SiteModel>,
                              private val listener: SiteListener
): RecyclerView.Adapter<SiteAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, vieType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_site,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val site = sites[holder.adapterPosition]
        holder.bind(site, listener)
    }

    override fun getItemCount(): Int = sites.size

    class MainHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(site: SiteModel, listener: SiteListener){
            itemView.siteName.text = site.name
            itemView.description.text = site.description
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context,site.image))
            itemView.setOnClickListener{listener.onSiteClick(site)
            }
        }
    }

}