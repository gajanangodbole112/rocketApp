package com.gajanan.rocketapp.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gajanan.rocketapp.R
import com.gajanan.rocketapp.databinding.ActivityRocketDetailBinding
import com.gajanan.rocketapp.modalClass.RocketDetailResponse
import com.gajanan.rocketapp.viewModel.RocketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketDetailActivity : AppCompatActivity() {

    private val rocketViewModel by viewModels<RocketViewModel>()
    private var _binding: ActivityRocketDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRocketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        observers()
    }

    private fun observers() {
        val id = intent.getStringExtra("RocketId")
        rocketViewModel.getRocketDetail(id.toString()).observe(this) { rocketDetail ->


            setRocketDetails(rocketDetail.data)
            setFlickerImage(rocketDetail.data?.flickr_images)
        }

    }

    private fun setRocketDetails(data: RocketDetailResponse?) = with(binding) {

        tvName.text = data?.name
        tvDescription.text = data?.description
        tvCostPerLaunch.text = data?.cost_per_launch.toString()
        tvSuccessRatePer.text = data?.success_rate_pct.toString()
        tvWikiLink.text = data?.wikipedia

        tvStatus.apply {
            if (data?.active!!) {
                text = getString(R.string.active)
                setTextColor(Color.parseColor("#01910d"))
            } else {
                text = getString(R.string.inactive)
                setTextColor(Color.parseColor("#fc1c1c"))
            }

            lyDimen.apply {
                tvDimenFeet.text = data.diameter?.feet.toString()
                tvHeightFeet.text = data.height?.feet.toString()
            }
        }

    }

    private fun setFlickerImage(flickrImages: List<String?>?) {
        for (i in flickrImages!!) {
            val images = ImageView(this@RocketDetailActivity)
            images.load(i) {
                placeholder(R.color.light_grey)
                transformations(RoundedCornersTransformation(20f))
            }

            images.setPadding(10, 10, 10, 10)
            images.scaleType = ImageView.ScaleType.FIT_XY
            val params = LinearLayout.LayoutParams(500, 500)
            images.layoutParams = params
            binding.linearlayout.addView(images)
        }
    }

    fun goBack(view: View) {
        onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}