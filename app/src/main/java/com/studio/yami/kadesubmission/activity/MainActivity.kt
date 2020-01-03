package com.studio.yami.kadesubmission.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.adapter.MyListAdapter
import com.studio.yami.kadesubmission.entity.ListEntity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_list_league.layoutManager = GridLayoutManager(this, 2)
        rv_list_league.adapter = MyListAdapter(setData()){
            startActivity<DetailActivity>(EXTRA_DATA to it)
        }

        floating_btn_search.setOnClickListener {
            startActivity<SearchActivity>()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.action_favorite){
            startActivity<FavoriteActivity>()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setData(): List<ListEntity>{
        val url = "https://www.thesportsdb.com/images/media/league/badge/"

        val logoArr = resources.getStringArray(R.array.logo)
        val nameArr = resources.getStringArray(R.array.name)
        val idArr = resources.getIntArray(R.array.id)

        val itemArr = mutableListOf<ListEntity>()
        for(i in nameArr.indices){
            val item = ListEntity(
                null,
                idArr[i].toString(),
                url + logoArr[i],
                nameArr[i]
            )
            itemArr.add(item)
        }
        return itemArr
    }

}
