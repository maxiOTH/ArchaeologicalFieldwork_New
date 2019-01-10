package org.wit.archaeologicalfieldwork.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId():Long{
    return lastId++
}


class SiteMemStore : SiteStore , AnkoLogger{
    override fun delete(site: SiteModel) {
        sites.remove(site)
    }

    val sites = ArrayList<SiteModel>()

    override fun findAll(): List<SiteModel> {
        return sites
    }

    override fun create(site: SiteModel) {
        site.id = getId()
        sites.add(site)
        logAll()
    }

    override fun update(site: SiteModel) {
        var foundSite:SiteModel?=sites.find { p->p.id == site.id }
        if (foundSite!=null){
            foundSite.name = site.name
            foundSite.description = site.description
            foundSite.image = site.image
            foundSite.lat = site.lat
            foundSite.lng = site.lng
            foundSite.zoom = site.zoom
            logAll()
        }
    }

    fun logAll(){
        sites.forEach{info("${it}")}
    }

    override fun finById(id: Long): SiteModel? {
        val foundSite:SiteModel?=sites.find{it.id == id}
        return foundSite
    }
}