package com.studio.yami.kadesubmission.fragment.favorite

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.activity.TeamActivity
import com.studio.yami.kadesubmission.adapter.MyListAdapter
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.startActivity

class FavTeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pro_list.visibility = View.GONE

        val factory = activity?.application?.let { ViewModelFactory(it) }
        val model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        model.showTeamFavorite().observe(this, Observer {list ->
            if (list != null) {

                rv_list.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = MyListAdapter(list){
                        startActivity<TeamActivity>(TeamActivity.EXTRA_TEAM to it)
                    }
                }
            }
        })

    }
}