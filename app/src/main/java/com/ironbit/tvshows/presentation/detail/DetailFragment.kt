package com.ironbit.tvshows.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ironbit.tvshows.R
import com.ironbit.tvshows.databinding.FragmentDetailBinding
import com.ironbit.tvshows.domain.cast.CastResponseItem
import com.ironbit.tvshows.domain.show.ShowResponse
import com.ironbit.tvshows.framework.common.DEFAULT_404
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

// Fragment that shows the detail screen
class DetailFragment : Fragment() {

    // Screen received arguments
    private val args: DetailFragmentArgs by navArgs()

    // ViewModel instance associated to the current screen */
    private val viewModel: DetailViewModel by viewModel()

    // ViewBinding associated to the current screen
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    // Fragment lifecycle functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        observerViewModel()
        initActions()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Establish the observables for the current screen
    private fun observerViewModel() {
        viewModel.show.observe(viewLifecycleOwner, handleShow())
        viewModel.cast.observe(viewLifecycleOwner, handleCast())
    }

    private fun handleShow(): (ShowResponse?) -> Unit = { scheduleList ->
        scheduleList?.let { items ->
            setupComponents(items)
        }
    }

    private fun handleCast(): (List<CastResponseItem>?) -> Unit = { scheduleList ->
        scheduleList?.let { items ->
            if (items.isNotEmpty()) {
                generateCast(items)
            }
        }
    }

    // Detonates init actions
    private fun initActions() {
        viewModel.getShow(args.showId)
        viewModel.getCast(args.showId)
    }

    // Generates the screen cast row
    private fun cleanCast() {
        binding.detailLinearLayoutCast.removeAllViews()
    }

    private fun generateCast(items: List<CastResponseItem>) {
        // Clean if exist any other element
        cleanCast()
        // Generate a cast item for each received item
        items.forEach { detailItem ->
            // Container
            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.gravity = Gravity.CENTER
            // Photo
            val imageView = ImageView(context)
            imageView.layoutParams = LinearLayout.LayoutParams(300, 340)
            // Name
            val textView = TextView(context)
            textView.gravity = Gravity.BOTTOM
            // Add elements to the Container
            linearLayout.addView(imageView)
            linearLayout.addView(textView)
            binding.detailLinearLayoutCast.addView(linearLayout)
            // Set the cast photo
            Picasso.get().load(detailItem.character?.image?.medium?.ifEmpty { null }).into(imageView)
            // Set the cast name
            textView.text = detailItem.person?.name
        }
    }

    // Inits the current screen UI components
    private fun setupComponents(show: ShowResponse) {
        // Appears the screen content
        binding.detailProgressBar?.visibility = View.INVISIBLE
        binding.detailScrollViewContainer?.visibility = View.VISIBLE
        // Sets the image show
        Picasso.get().load(show.image?.medium?.ifEmpty { null }).into(binding.detailIncludeRow.listRowImage)
        // Sets the show name
        binding.detailIncludeRow.listRowName.text = show.name
        // Sets the network name
        binding.detailIncludeRow.listRowNetworkName.text = show.network?.name
        // Sets the show rating
        binding.detailIncludeRow.listRowAirdate.text = context?.getString(R.string.detail_rating_title)
        binding.detailIncludeRow.listRowDateSeparator.visibility = View.GONE
        binding.detailIncludeRow.listRowAirtime.text = show.rating?.average.toString()
        // Sets the show site
        binding.detailIncludeRow.listRowSite.let {
            it.visibility = View.VISIBLE
            it.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(show.officialSite?.ifEmpty { DEFAULT_404 }))
                startActivity(browserIntent)
            }
        }
        // Sets the show summary
        binding.detailTextViewSummary.text = HtmlCompat.fromHtml(show.summary ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
        // Sets the show genres
        binding.detailTextViewGenres.text = show.genres.toString().replace("[", "").replace("]", "")
        // Sets the show dates
        val showTime = "${show.schedule?.time} | ${show.schedule?.days.toString().replace("[", "").replace("]", "")}"
        binding.detailTextViewTime.text = showTime
    }


}