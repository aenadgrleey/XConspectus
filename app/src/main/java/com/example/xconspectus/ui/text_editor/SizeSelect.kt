package com.example.xconspectus.ui.text_editor

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselModel
import alirezat775.lib.carouselview.CarouselView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.xconspectus.databinding.TextEditorSizeSelectBinding

class SizeSelect : Fragment() {
    private val viewModel: TextEditorViewModel by viewModels()

    private lateinit var binding: TextEditorSizeSelectBinding
    private lateinit var carousel: Carousel
    private var adapter = SizeSelectAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = TextEditorSizeSelectBinding.inflate(inflater)
        carousel = Carousel(activity as AppCompatActivity, binding.carousel, adapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.scaleView(true)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var sequenceElement = 0
        val multipliers = mutableListOf<CarouselModel>().also {
            for (i in 0..8) it.add(MultiplierModel(i * 0.25F + 0.5F))
        }
        carousel.addAll(multipliers)
    }

    inner class MultiplierModel(val multiplier: Float) : CarouselModel()

}