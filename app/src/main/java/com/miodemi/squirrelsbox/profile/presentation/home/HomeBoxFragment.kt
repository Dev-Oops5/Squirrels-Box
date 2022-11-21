package com.miodemi.squirrelsbox.profile.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.miodemi.squirrelsbox.databinding.FragmentHomeBoxBinding
import com.miodemi.squirrelsbox.profile.presentation.AddDialogViewFab
import com.miodemi.squirrelsbox.inventory.application.box.HomeBoxViewModel
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import com.miodemi.squirrelsbox.inventory.infrastructure.ExcelRepository
import com.miodemi.squirrelsbox.inventory.presentation.box.HomeBoxAdapter
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class HomeBoxFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeBoxBinding

    private val viewModelFAB : AddDialogViewFab by activityViewModels()

    private val viewModel: HomeBoxViewModel by lazy {
        ViewModelProvider(this)[HomeBoxViewModel::class.java]
    }

    //lateinit var _addview : AddDialogViewFab

    //Export variables
    private lateinit var btnImportExcel : Button
    val import= ExcelRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFAB.setData("box")
        //init data binding in a fragment
        binding = FragmentHomeBoxBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val itemBoxAdapter = HomeBoxAdapter()
        binding.allBoxesRv.adapter = itemBoxAdapter

        viewModel.fetchNewsFeed()

        //Initializing export variables
        btnImportExcel = binding.btnImport
        //tvData = findViewById(R.id.tvData)

        btnImportExcel.setOnClickListener{
            import();
            // Inflate the layout for this fragment
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun import() {
        context?.let { import.importBox(it) }
    }

}