package com.example.myapplication.ui.theme.imageSearchCodingInFLow.ui

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGalleryBinding
import com.example.myapplication.ui.theme.imageSearchCodingInFLow.viewmodel.ImageSearchViewModel
import com.example.myapplication.ui.theme.utils.MarginItemDecorator
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment :Fragment(R.layout.fragment_gallery)
{
        lateinit var binding: FragmentGalleryBinding

        val viewModel: ImageSearchViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGalleryBinding.bind(view)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolBar)

        binding.appBarLayout.addOnOffsetChangedListener(object :
            AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    binding.collapsBarLayout.title = "Hello world"
                    isShow = true
                } else if (isShow) {
                    binding.collapsBarLayout.title = " " //careful there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        })
        lifecycleScope.launch {

            while (true){
                binding.play.animate().scaleX(1.8f).scaleY(1.8f).alpha(0f).setDuration(1400)
                    .withEndAction {
                        binding.play.scaleX = 1f
                        binding.play.scaleY = 1f
                        binding.play.alpha = 1f
                    }
                delay(1500)
            }

        }
        /*Same Example with handle code*/
        /* var handlerAnimation = Handler()
        val runnable = object : Runnable {
            override fun run() {

                binding.play.animate().scaleX(1.8f).setDuration(1400)
                    .withEndAction {
                        binding.play.scaleX = 1f
                        binding.play.scaleY = 1f
                        binding.play.alpha = 1f
                    }
                handlerAnimation.postDelayed(this, 1500)
            }
        }
        runnable.run()*/


        val imagePagerAdapter = ImagePagerAdapter(){
            val action = GalleryFragmentDirections.actionGalleryFragment2ToImageDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.apply {
            recyclerView.itemAnimator = null

            // Create the ItemDecoration with a 10dp margin
            val itemDecoration = MarginItemDecorator(10)
            // Set the ItemDecoration to the RecyclerView
            recyclerView.addItemDecoration(itemDecoration)
            var layoutParsm = recyclerView.layoutParams as RelativeLayout.LayoutParams
            layoutParsm.marginStart = (10 * (context?.resources?.displayMetrics?.density!!)).toInt()
            layoutParsm.marginEnd = (10 * (context?.resources?.displayMetrics?.density!!)).toInt()

            recyclerView.adapter = imagePagerAdapter.withLoadStateHeaderAndFooter(
                header = ImageSearchHeaderFooterAdapter{
                    imagePagerAdapter.retry()
                },
                footer = ImageSearchHeaderFooterAdapter{
                    imagePagerAdapter.retry()
                }
            )
            recyclerView.setHasFixedSize(true)
        }


        binding.buttonRetry.setOnClickListener{
            imagePagerAdapter.retry()
        }

        viewModel.imageListLiveData.observe(viewLifecycleOwner){
            imagePagerAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


        imagePagerAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    imagePagerAdapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }

        viewModel.searchPhotos("cat")

        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        var searchView = menu.findItem(R.id.action_search)
        var actionItem = searchView.actionView as androidx.appcompat.widget.SearchView
         actionItem.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
            if(query != null)
            {
            binding.recyclerView.scrollToPosition(0)
                actionItem.clearFocus()
                viewModel.searchPhotos(query)
            }
            return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })



    }
}