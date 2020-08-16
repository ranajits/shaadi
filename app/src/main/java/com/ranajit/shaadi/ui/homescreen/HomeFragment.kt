package com.ranajit.shaadi.ui.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranajit.shaadi.base.BaseFragment
import com.ranajit.shaadi.databinding.MainFragmentBinding
import com.ranajit.shaadi.model.MatchesModel
import com.ranajit.shaadi.ui.homescreen.adapter.ProfileListAdapter
import com.ranajit.shaadi.ui.homescreen.viewmodel.HomeViewModel
import com.ranajit.shaadi.utility.Status
import com.ranajit.shaadi.utility.Utility
import kotlinx.android.synthetic.main.main_fragment.*

class HomeFragment : BaseFragment(), ProfileListAdapter.RecyclerViewItemClickListener {

    private val TAG = HomeFragment::class.java.canonicalName

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: MainFragmentBinding
    private var bannerAdapter: ProfileListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            setupViewModel(this.requireActivity(), HomeViewModel::class.java) as HomeViewModel
        binding.vm = viewModel
        initialize()
        getData()
    }

    private fun initialize() {
        rv_profiles.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun getData() {
        viewModel.getMatches().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> binding.prgrs.visibility = View.VISIBLE
                Status.ERROR -> binding.prgrs.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding.prgrs.visibility = View.GONE
                    Log.d(TAG, "Success:${it.data}")
                    if (it.data!!) {
                        setupObserver()
                    }
                }
            }

        })
    }

    private fun setupObserver() {

        viewModel.getMatchesFromDB()
            .observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> binding.prgrs.visibility = View.VISIBLE
                    Status.ERROR -> binding.prgrs.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.prgrs.visibility = View.GONE

                        bannerAdapter = ProfileListAdapter(requireActivity(), it.data!!, this)
                        rv_profiles.adapter = bannerAdapter
                    }
                }
            })
    }

    private fun updateMatch(isAccepted: Int, position: Int) {
        val lastMatchModel = viewModel.matchesList.get(position)
        if (lastMatchModel.isAccepted != isAccepted) {
            lastMatchModel.isAccepted = isAccepted
            lastMatchModel.dateModified = Utility.getCurrentTimeStamp()
            viewModel.matchesList.get(position).isAccepted = isAccepted
            viewModel.updateMatch(lastMatchModel)
        }
    }

    override fun onAccept(data: MatchesModel, position: Int) {
        updateMatch(1, position)
    }

    override fun onReject(data: MatchesModel, position: Int) {
        updateMatch(0, position)
    }
}