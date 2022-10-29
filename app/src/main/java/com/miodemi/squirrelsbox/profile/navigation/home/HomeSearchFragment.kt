package com.miodemi.squirrelsbox.profile.navigation.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentHomeItemBinding
import com.miodemi.squirrelsbox.databinding.FragmentHomeSearchBinding
import com.miodemi.squirrelsbox.inventory.navigation.home.HomeSearchViewModel
import com.miodemi.squirrelsbox.profile.navigation.AddDialogViewFab
import kotlinx.android.synthetic.main.fragment_home_search.*
import java.io.ByteArrayOutputStream

class HomeSearchFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeSearchBinding

    private val viewModel : HomeSearchViewModel by activityViewModels()

    lateinit var imageDetector : Unit

    private lateinit var imageLabeler : ImageLabeler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentHomeSearchBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        viewModel.data.observe(viewLifecycleOwner) {
            imageDetector = binding.image.setImageBitmap(it)

            binding.image.setImageBitmap(it)

            val imageUri: Uri = getImageUriFromBitmap(requireContext(),it)
            val bitmap2 = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)

            labelImage(bitmap2)
        }

        imageLabeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

//        val imageLabelerOptions = ImageLabelerOptions.Builder()
//            .setConfidenceThreshold(0.95f)
//            .build()
//        imageLabeler = ImageLabeling.getClient(imageLabelerOptions)

//        val bitmap1 = BitmapFactory.decodeResource(resources, imageDetector.toString().toInt())



//        val bitmapDrawable = binding.image.drawable as BitmapDrawable
//        val bitmap3 =   bitmapDrawable.bitmap



        // Inflate the layout for this fragment
        return  view
    }

    private fun labelImage(bitmap: Bitmap){
        val inputImage = InputImage.fromBitmap(bitmap, 0)
        imageLabeler.process(inputImage)
            .addOnSuccessListener { imageLabels ->
                for (imageLabel in imageLabels){
                    val text = imageLabel.text
                    val confidence = imageLabel.confidence
                    val index = imageLabel.index

                    binding.resultTv.append("Text: $text \n Confidence: $confidence \n Index: $index \n\n")
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this.requireContext(), "Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri{
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }
}