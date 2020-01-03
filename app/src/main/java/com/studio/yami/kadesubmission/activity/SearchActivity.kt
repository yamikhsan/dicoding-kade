package com.studio.yami.kadesubmission.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.activity.MatchActivity.Companion.EXTRA_MATCH
import com.studio.yami.kadesubmission.activity.TeamActivity.Companion.EXTRA_TEAM
import com.studio.yami.kadesubmission.adapter.MatchAdapter
import com.studio.yami.kadesubmission.adapter.MyListAdapter
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var model: MainViewModel
    private var strSearch: String? = null

    private val event = "EVENT"
    private val team = "TEAM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val factory = ViewModelFactory(application)
        model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        rb_event.isChecked = true
        strSearch = event
        rbListen()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun rbListen(){
        rg_parent.setOnCheckedChangeListener { _, i ->
            strSearch = when(i){
                R.id.rb_event -> event
                R.id.rb_team -> team
                else -> null
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.action_search -> {
                val search = item.actionView as SearchView
                search.queryHint = getString(R.string.search)
                search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if(query != null){
                            when(strSearch){
                                event -> onSearchEvent(query)
                                team -> onSearchTeam(query)
                            }
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })
            }

            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onSearchEvent(query: String){
        tv_not_found.visibility = View.GONE
        rv_search.visibility = View.GONE
        pro_search.visibility = View.VISIBLE

        model.searchEvents(query).observe(this, Observer {
            if(it != null){
                pro_search.visibility = View.GONE

                if(it.events == null){
                    tv_not_found.visibility = View.VISIBLE
                }else{
                    rv_search.apply {
                        visibility = View.VISIBLE
                        layoutManager = LinearLayoutManager(context)
                        adapter = MatchAdapter(it.events){e ->
                            startActivity<MatchActivity>(EXTRA_MATCH to e)
                        }
                    }
                }
            }
        })
    }

    fun onSearchTeam(query: String){
        tv_not_found.visibility = View.GONE
        rv_search.visibility = View.GONE
        pro_search.visibility = View.VISIBLE

        model.searchTeam(query).observe(this, Observer {
            if(it != null){
                pro_search.visibility = View.GONE

                if(it.teams == null){
                    tv_not_found.visibility = View.VISIBLE
                }else{
                    rv_search.apply {
                        visibility = View.VISIBLE
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = MyListAdapter(it.getLists()){t ->
                            startActivity<TeamActivity>(EXTRA_TEAM to t)
                        }
                    }
                }
            }
        })
    }

}
