package com.studio.yami.kadesubmission.fragment.league


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
import com.studio.yami.kadesubmission.activity.TeamActivity.Companion.EXTRA_TEAM
import com.studio.yami.kadesubmission.adapter.MyListAdapter
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.startActivity

class ListsTeamFragment: Fragment() {

    companion object {
        private const val EXTRA_ID = "EXTRA ID"
        @JvmStatic
        fun newInstance(id: Int): ListsTeamFragment {
            return ListsTeamFragment().apply {
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
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = activity?.application?.let { ViewModelFactory(it) }
        val model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        val id = arguments?.getInt(EXTRA_ID, 0)

        model.lookUpAllTeam(id.toString()).observe(this, Observer { list ->
            if(list != null){
                pro_list.visibility = View.GONE

                rv_list.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = MyListAdapter(list.getLists()){
                        startActivity<TeamActivity>(EXTRA_TEAM to it)
                    }
                }
            }
        })
    }

}
