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
import com.studio.yami.kadesubmission.adapter.StandingsAdapter
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_standings.*
import kotlinx.android.synthetic.main.fragment_standings.view.*

class StandingsFragment : Fragment() {

    companion object {
        private const val EXTRA_ID = "EXTRA ID"
        @JvmStatic
        fun newInstance(id: Int): StandingsFragment {
            return StandingsFragment().apply {
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
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = activity?.application?.let { ViewModelFactory(it) }
        val model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        val id = arguments?.getInt(EXTRA_ID, 0)

        model.lookUpTable(id.toString()).observe(this, Observer {

            if(it != null){
                pro_standings.visibility = View.GONE
                view.rv_standings.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = StandingsAdapter(it.table)
                }
            }
        })



    }

}
