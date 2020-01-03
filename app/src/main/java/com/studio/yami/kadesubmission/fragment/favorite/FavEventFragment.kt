package com.studio.yami.kadesubmission.fragment.favorite


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.activity.MatchActivity
import com.studio.yami.kadesubmission.activity.MatchActivity.Companion.EXTRA_MATCH
import com.studio.yami.kadesubmission.adapter.MatchAdapter
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.startActivity

class FavEventFragment : Fragment() {

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

        model.showEventFavorite().observe(this, Observer {
            if (it != null) {

                rv_list.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = MatchAdapter(it) { e ->
                        startActivity<MatchActivity>(EXTRA_MATCH to e)
                    }
                }
            }
        })

    }

}
