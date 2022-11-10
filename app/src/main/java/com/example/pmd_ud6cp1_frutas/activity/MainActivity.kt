package com.example.pmd_ud6cp1_frutas.activity

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.pmd_ud6cp1_frutas.R
import com.example.pmd_ud6cp1_frutas.adapter.Adapter
import com.example.pmd_ud6cp1_frutas.model.Fruit


class MainActivity : AppCompatActivity() {
    private val TAG = "miapp"
    private lateinit var fruits: ArrayList<Fruit>
    private lateinit var topAppBar: androidx.appcompat.widget.Toolbar
    private lateinit var listView: ListView
    private lateinit var gridView: GridView
    private lateinit var listAdapter: Adapter
    private lateinit var gridAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init latevars...
        fruits = Fruit.feedDefaultList()
        topAppBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.topAppBar)

        listView = findViewById<android.widget.ListView>(R.id.list_view)
        listAdapter = Adapter(this, R.layout.item_list, fruits)
        listView.adapter = listAdapter

        gridView = findViewById<android.widget.GridView>(R.id.grid_view)
        gridAdapter = Adapter(this, R.layout.item_grid, fruits)
        gridView.adapter = gridAdapter

        registerForContextMenu(listView)
        registerForContextMenu(gridView)


        //set ListView for first view show
        changeToListView()

        //TopAppBar
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    Log.v(TAG, "Top App Bar: add")
                    addNewFruit()
                    true
                }
                R.id.change -> {
                    if (listView.isVisible) {
                        Log.v(TAG, "Top App Bar: change TRUE")
                        menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_list)
                        changeToGridView()
                    } else {
                        Log.v(TAG, "Top App Bar: change FALSE")
                        menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid)
                        changeToListView()
                    }
                    //menuItem.setIcon(R.drawable.ic_add)
                    Log.v(TAG, "Top App Bar: change")
                    true
                }
                else -> false
            }
        }

        //ListView Item Click
        listView.setOnItemClickListener() { adapterView, view, position, id ->
            Log.v(TAG, "setOnItemClickListener")
            val itemAtPos = adapterView.getItemAtPosition(position) as Fruit
            Toast.makeText(
                this,
                "La mejor fruta de ${itemAtPos.from} es la ${itemAtPos.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        //GridView Item Click
        gridView.setOnItemClickListener() { adapterView, view, position, id ->
            Log.v(TAG, "setOnItemClickListener")
            val itemAtPos = adapterView.getItemAtPosition(position) as Fruit
            Toast.makeText(
                this,
                "La mejor fruta de ${itemAtPos.from} es la ${itemAtPos.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //Context Menu
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        menu.setHeaderTitle(fruits[info.position].name)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.delete -> {
                removeFruit(fruits[info.position])
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun changeView() {
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.ic_add)
        topAppBar.setOverflowIcon(drawable)
    }

    private fun addNewFruit(withNonReplace: Boolean) {
        fruits.add(Fruit("Nueva nº${Fruit.lastNewFruitAddedNumber}", R.drawable.ic_new_fruit_playstore, "Nueva Fruta"))
        Fruit.lastNewFruitAddedNumber++
        notifyChangesToAdapters()
    }

    private fun addNewFruit() {

        var isUsedName = true
        var number = 1
        var defaultNewName = ""
        while (isUsedName) {
            defaultNewName = "Nueva nº$number"
            for (fruit in fruits) {
                if (fruit.name.equals(defaultNewName)) {
                    number++
                    isUsedName = true
                    break
                } else {
                    isUsedName = false
                }
            }
        }

        fruits.add(Fruit(defaultNewName, R.drawable.ic_new_fruit_playstore, "Nueva Fruta"))
        notifyChangesToAdapters()
    }

    private fun removeFruit(fruit: Fruit) {
        fruits.remove(fruit)
        notifyChangesToAdapters()
    }

    private fun notifyChangesToAdapters() {
        listAdapter.notifyDataSetChanged()
        gridAdapter.notifyDataSetChanged()
    }

    private fun changeToListView() {
        listView.visibility = View.VISIBLE
        gridView.visibility = View.GONE
    }

    private fun changeToGridView() {
        listView.visibility = View.GONE
        gridView.visibility = View.VISIBLE
    }
}