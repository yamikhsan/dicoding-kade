package com.studio.yami.kadesubmission.fragment.team

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.activity.PlayerActivity
import com.studio.yami.kadesubmission.activity.PlayerActivity.Companion.EXTRA_PLAYER
import com.studio.yami.kadesubmission.adapter.MyListAdapter
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.startActivity

class ListsPlayerFragment : Fragment() {

    companion object {
        private const val EXTRA_ID = "EXTRA ID"
        @JvmStatic
        fun newInstance(id: Int): ListsPlayerFragment {
            return ListsPlayerFragment().apply {
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

        model.lookUpAllPlayer(id.toString()).observe(this, Observer { list ->
            if (list != null) {
                pro_list.visibility = View.GONE

                if(list.players == null){
                    tv_list_null.visibility = View.VISIBLE
                }

                rv_list.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = list.getLists()?.let {
                        MyListAdapter(it){p ->
                            startActivity<PlayerActivity>( EXTRA_PLAYER to p.id.toInt())
                        }
                    }
                }

            }
        })
    }

}