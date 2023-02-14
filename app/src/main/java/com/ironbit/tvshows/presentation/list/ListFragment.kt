package com.ironbit.tvshows.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironbit.tvshows.R
import com.ironbit.tvshows.databinding.FragmentListBinding
import com.ironbit.tvshows.domain.detail.DetailItem
import com.ironbit.tvshows.framework.common.SCHEDULE_COUNTRY
import com.ironbit.tvshows.framework.common.SCHEDULE_DATE_PATTERN
import com.ironbit.tvshows.framework.common.TOOLBAR_DATE_PATTERN
import com.ironbit.tvshows.framework.common.utils.ClickListenerAdapter
import com.ironbit.tvshows.framework.common.utils.getCurrentDate
import org.koin.androidx.viewmodel.ext.android.viewModel

// Fragment that shows the list screen
class ListFragment : Fragment(), ClickListenerAdapter<DetailItem> {

    // ViewModel instance associated to the current screen */
    private val viewModel: ListViewModel by viewModel()

    // ViewBinding associated to the current screen
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    // Fragment lifecycle functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        observeViewModel()
        initActions()
        setupToolbar()
        setupComponents()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Establish the observables for the current screen
    private fun observeViewModel() {
        viewModel.schedule.observe(viewLifecycleOwner, handleSchedule())
    }

    private fun handleSchedule(): (List<DetailItem>?) -> Unit = { scheduleList ->
        scheduleList?.let { items ->
            if (items.isNotEmpty()) {
                showScheduleList(scheduleList = items)
            }
        }
    }

    // Detonates init actions
    private fun initActions() {
        getSchedule()
    }

    private fun getSchedule() {
        binding.listSwipeRefreshLayout.isRefreshing = true
        viewModel.getSchedule(
            country = SCHEDULE_COUNTRY,
            date = getCurrentDate(pattern = SCHEDULE_DATE_PATTERN)
        )
    }

    private fun setupToolbar() {
        binding.listToolbar.let {
            // Set action bar title
            it.title = getCurrentDate(pattern = TOOLBAR_DATE_PATTERN)
            // Inflate action bar and configs the actions
            it.addMenuProvider(object : MenuProvider {
                override fun onPrepareMenu(menu: Menu) {
                    // Set search view fill max action bar space
                }

                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_list, menu)
                    val searchView =
                        menu.findItem(R.id.action_menu_list_search).actionView as SearchView?
                    searchView?.maxWidth = Int.MAX_VALUE

                    searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            viewModel.getSearch(newText ?: "")
                            if (newText?.isEmpty() == true) {
                                getSchedule()
                            }
                            return false
                        }
                    })
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    // Validate and handle the selected menu item
                    return true
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }
    }

    // Inits the current screen UI components
    private fun setupComponents() {
        binding.listSwipeRefreshLayout.setOnRefreshListener {
            getSchedule()
        }
    }

    private fun showScheduleList(scheduleList: List<DetailItem>) {
        binding.listRecyclerViewShows.let {
            binding.listSwipeRefreshLayout.isRefreshing = false
            it.layoutManager = LinearLayoutManager(activity)
            val adapter = ListAdapter(dataSet = scheduleList, clickListener = this)
            it.adapter = adapter
        }
    }

    override fun onItemClicked(position: Int, item: DetailItem) {
        navToDetailFragment(showId = item.id)
    }

    // Navigation associated to the current screen
    private fun navToDetailFragment(showId: Int) {
        val destination = ListFragmentDirections.navToDetailFragment(showId = showId)
        findNavController().navigate(destination)
    }

}