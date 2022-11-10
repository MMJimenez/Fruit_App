package com.example.pmd_ud6cp1_frutas.model
import com.example.pmd_ud6cp1_frutas.R

class Fruit (val name: String, val icon: Int, val from: String) {
    var isNew = true

    private constructor(name: String, icon: Int, from: String, isNew: Boolean) : this(name, icon, from) {
        this.isNew = isNew
    }

    companion object {
        var lastNewFruitAddedNumber = 1
        fun feedDefaultList() : ArrayList<Fruit> {
            val fruits = ArrayList<Fruit>()
            fruits.add(Fruit("Banana", R.drawable.ic_banana_playstore, "Gran Canaria", false))
            fruits.add(Fruit("Manzana", R.drawable.ic_manzana_playstore, "Madrid", false))
            fruits.add(Fruit("Cereza", R.drawable.ic_cereza_playstore, "Galicia", false))
            fruits.add(Fruit("Naranja", R.drawable.ic_naranja_playstore, "Valencia", false))
            fruits.add(Fruit("Pera", R.drawable.ic_pera_playstore, "Zaragoza", false))
            fruits.add(Fruit("Fresa", R.drawable.ic_fresa_playstore, "Huelva", false))
            fruits.add(Fruit("Frambuesa", R.drawable.ic_frambuesa_playstore, "Barcelona", false))
            return fruits
        }
    }
}
