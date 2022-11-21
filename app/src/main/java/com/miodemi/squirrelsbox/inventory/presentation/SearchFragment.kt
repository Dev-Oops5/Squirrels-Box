package com.miodemi.squirrelsbox.inventory.presentation

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.*
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.miodemi.squirrelsbox.databinding.FragmentSearchBinding
import com.miodemi.squirrelsbox.inventory.application.HomeSearchViewModel
import com.miodemi.squirrelsbox.inventory.application.box.HomeBoxViewModel
import com.miodemi.squirrelsbox.inventory.application.item.HomeItemViewModel
import com.miodemi.squirrelsbox.inventory.application.item.ItemDialogViewModel
import com.miodemi.squirrelsbox.inventory.application.section.HomeSectionViewModel
import com.miodemi.squirrelsbox.inventory.application.section.SectionDialogViewModel
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.inventory.infrastructure.HomeBoxRepository
import com.miodemi.squirrelsbox.inventory.presentation.box.HomeBoxAdapter
import com.miodemi.squirrelsbox.inventory.presentation.item.HomeItemAdapter
import com.miodemi.squirrelsbox.inventory.presentation.section.HomeSectionAdapter
import java.io.ByteArrayOutputStream

class SearchFragment : Fragment() {

    private val repository = HomeBoxRepository()
    private val _boxDataLiveData = MutableLiveData<List<BoxData>>()
    val boxDataLiveData: LiveData<List<BoxData>> = _boxDataLiveData


    //Database
    private lateinit var database  : DatabaseReference

    //binding
    lateinit var binding: FragmentSearchBinding

    private val viewModel : HomeSearchViewModel by activityViewModels()
    private val viewModel1 : SectionDialogViewModel by activityViewModels()
    private val viewModel2 : ItemDialogViewModel by activityViewModels()
    private val viewModel3: HomeBoxViewModel by lazy {
        ViewModelProvider(this)[HomeBoxViewModel::class.java]
    }
    private val viewModel4: HomeSectionViewModel by lazy {
        ViewModelProvider(this)[HomeSectionViewModel::class.java]
    }
    private val viewModel5: HomeItemViewModel by lazy {
        ViewModelProvider(this)[HomeItemViewModel::class.java]
    }

    lateinit var imageDetector : Unit

    private lateinit var imageLabeler : ImageLabeler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //init data binding in a fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        //Adapter for show boxes
        val itemBoxAdapter = HomeBoxAdapter()
        val itemSectionAdapter = HomeSectionAdapter()
        val itemItemAdapter = HomeItemAdapter()
        binding.allBoxesRv.adapter = itemBoxAdapter

        //val itemSectionAdapter = HomeSectionAdapter()
        //binding.allBoxesRv.adapter = itemSectionAdapter
//
        //val itemItemAdapter = HomeItemAdapter()
        //binding.allBoxesRv.adapter = itemItemAdapter

        viewModel.data.observe(viewLifecycleOwner) {
            imageDetector = binding.image.setImageBitmap(it)

            binding.image.setImageBitmap(it)

            val imageUri: Uri = getImageUriFromBitmap(requireContext(),it)
            val bitmap2 = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)

            labelImage(bitmap2)
        }

        //Search Button
        binding.searchitm.setOnClickListener {
            val name : String = binding.etsearch.text.toString()
            if(name.isNotEmpty()){
                val lasegura = viewModel1.boxName.value.toString()
                val lasegura2 = viewModel2.sectionName.value.toString()
                searchItem(name, lasegura, lasegura2)


            }else{
                Toast.makeText(requireContext(), "Please enter a valid ID", Toast.LENGTH_SHORT).show()
            }

        }
        binding.searchbx.setOnClickListener {
            val name : String = binding.etsearch.text.toString()
            if(name.isNotEmpty()){

                searchBox(name)


            }else{
                Toast.makeText(requireContext(), "Please enter a valid ID", Toast.LENGTH_SHORT).show()
            }

        }
        binding.searchbsx.setOnClickListener {
            val name : String = binding.etsearch.text.toString()
            if(name.isNotEmpty()){
                val lasegura = viewModel1.boxName.value.toString()

                searchSection(name, lasegura)


            }else{
                Toast.makeText(requireContext(), "Please enter a valid ID", Toast.LENGTH_SHORT).show()
            }

        }

        imageLabeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)


        return  view
    }



    //Search implementation
    private fun searchItem(name: String, boxName: String, sectionName: String) {

        database = FirebaseDatabase.getInstance().getReference("boxes").child(boxName).child("sections").child(sectionName).child("items")
        //var listita = ArrayList<String>();
        //var section = ""
        database.child(name).get().addOnSuccessListener {
            if(it.exists()){
                // val section = it.child("sections").value
                // it.child("sections")
                binding.lifecycleOwner = this

                viewModel5.fetchNewsFeedSearch(name)

                Log.d("TAG", "$it")
                val boxName = it.child("name").value
                val boxId = it.child("id").value
                val itemName = it.child("sections").child("name").value
                Toast.makeText(requireContext(), "Box Found", Toast.LENGTH_SHORT).show()
                binding.etsearch.text.clear()
                binding.tvBoxName.text = boxName.toString()

            }else{
                Toast.makeText(requireContext(), "Box Not Found", Toast.LENGTH_SHORT).show()
            }
        }.addOnCanceledListener {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }

    }
    private fun searchSection(name: String, boxName: String) {


        database = FirebaseDatabase.getInstance().getReference("boxes").child(boxName).child("sections")

        database.child(name).get().addOnSuccessListener {
            if(it.exists()){


                binding.lifecycleOwner = this

                viewModel4.fetchNewsFeedSearch(name)

                Log.d("TAG", "$it")
                val boxName = it.child("name").value
                val boxId = it.child("id").value
                val itemName = it.child("sections").child("name").value
                Toast.makeText(requireContext(), "Box Found", Toast.LENGTH_SHORT).show()
                binding.etsearch.text.clear()
                binding.tvBoxName.text = boxName.toString()
            }else{
                Toast.makeText(requireContext(), "Box Not Found", Toast.LENGTH_SHORT).show()
            }
        }.addOnCanceledListener {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }

    }
    private fun searchBox(name: String) {


        database = FirebaseDatabase.getInstance().getReference("boxes")

        database.child(name).get().addOnSuccessListener {
            if(it.exists()){

                binding.lifecycleOwner = this
                binding.viewModel = viewModel3

                viewModel3.fetchNewsFeedSearch(name)
                Log.d("TAG", "$it")
                binding.etsearch.text.clear()
            }else{
                Toast.makeText(requireContext(), "Box Not Found", Toast.LENGTH_SHORT).show()
            }
        }.addOnCanceledListener {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }

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

    fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }



}


//        val imageLabelerOptions = ImageLabelerOptions.Builder()
//            .setConfidenceThreshold(0.95f)
//            .build()
//        imageLabeler = ImageLabeling.getClient(imageLabelerOptions)

//        val bitmap1 = BitmapFactory.decodeResource(resources, imageDetector.toString().toInt())



//        val bitmapDrawable = binding.image.drawable as BitmapDrawable
//        val bitmap3 =   bitmapDrawable.bitmap



// Inflate the layout for this fragment