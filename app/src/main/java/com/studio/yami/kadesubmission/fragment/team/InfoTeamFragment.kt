package com.studio.yami.kadesubmission.fragment.team


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_info_team.*

class InfoTeamFragment : Fragment() {

    companion object {
        private const val EXTRA_ID = "EXTRA ID"
        @JvmStatic
        fun newInstance(id: Int): InfoTeamFragment {
            return InfoTeamFragment().apply {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = activity?.application?.let { ViewModelFactory(it) }
        val model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        val id = arguments?.getInt(EXTRA_ID, 0)

        model.lookUpTeam(id.toString()).observe(this, Observer {

            if(it != null){
                pro_info_team.visibility = View.GONE
                scroll_info_team.visibility = View.VISIBLE

                if(it.teams != null){
                    val team = it.teams[0]
                    tv_desc_content_team.text = team.strDescriptionEN
                    tv_alternate_content.text = team.strAlternate
                    tv_formed_content_team.text = team.intFormedYear
                    tv_league_content_team.text = team.strLeague
                    tv_stadium_content_team.text = team.strStadium
                }
            }

        })
    }

}
