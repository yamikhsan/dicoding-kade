package com.studio.yami.kadesubmission.fragment.league


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
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment: Fragment() {

    companion object {
        private const val EXTRA_ID = "EXTRA ID"
        @JvmStatic
        fun newInstance(id: Int): MatchFragment {
            return MatchFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_ID, id)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = activity?.application?.let { ViewModelFactory(it) }
        val model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        val id = arguments?.getInt(EXTRA_ID, 0)

        model.eventNextLeague(id.toString()).observe(this, Observer {list ->
            if(list != null){
                prog_next_league.visibility = View.GONE

                rv_next_league.apply {
                    visibility = View.VISIBLE
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = MatchAdapter(list){
                        startActivity<MatchActivity>(EXTRA_MATCH to it)
                    }
                }
            }
        })

        model.eventPastLeague(id.toString()).observe(this, Observer { list ->
            if(list != null){
                pro_list.visibility = View.GONE

                rv_list.apply {
                    visibility = View.VISIBLE
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = MatchAdapter(list){
                        startActivity<MatchActivity>(EXTRA_MATCH to it)
                    }
                }
            }
        })
    }


}
