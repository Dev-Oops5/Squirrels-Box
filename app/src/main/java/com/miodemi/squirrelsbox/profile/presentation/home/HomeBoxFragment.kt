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
    //private lateinit var btnExportExcel : Button

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
        /*btnExportExcel = binding.btnExport
        //tvData = findViewById(R.id.tvData)

        btnExportExcel.setOnClickListener{
            guardar();
            // Inflate the layout for this fragment
        }*/
        // Inflate the layout for this fragment
        return view
    }

    private fun guardar() {
        //viewModelHomeItem.fetchNewsFeedName()
        val wb: Workbook = HSSFWorkbook()
        var cell: Cell? = null
        val cellStyle = wb.createCellStyle()
        cellStyle.fillForegroundColor = HSSFColor.AQUA.index
        cellStyle.fillPattern = HSSFCellStyle.SOLID_FOREGROUND
        cellStyle.alignment= CellStyle.ALIGN_CENTER

        var sheet: Sheet? = null


        sheet = wb.createSheet("items list")
        var row: Row? = null
        row = sheet.createRow(0)

        cell = row.createCell(0)
        cell.setCellValue("Box Name")
        cell.cellStyle = cellStyle

        cell = row.createCell(1)
        cell.setCellValue("Visibility")
        cell.cellStyle = cellStyle

        cell = row.createCell(2)
        cell.setCellValue("Shared members")
        cell.cellStyle = cellStyle


        row = sheet.createRow(1)
        cell = row.createCell(0)
        cell.setCellValue("a")

        cell = row.createCell(1)
        cell.setCellValue("´Private")

        cell = row.createCell(2)
        cell.setCellValue("null")


        row = sheet.createRow(2)
        cell = row.createCell(0)
        cell.setCellValue("e")

        cell = row.createCell(1)
        cell.setCellValue("Private")

        cell = row.createCell(2)
        cell.setCellValue("null")


        row = sheet.createRow(3)
        cell = row.createCell(0)
        cell.setCellValue("boxForm")

        cell = row.createCell(1)
        cell.setCellValue("Private")

        cell = row.createCell(2)
        cell.setCellValue("null")

        val file = File(context?.getExternalFilesDir(null), "items.xls")
        var outputStream: FileOutputStream? = null

        try {
            outputStream = FileOutputStream(file)
            wb.write(outputStream)
            Toast.makeText(context?.applicationContext, "DataSheet Dowloaded", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context?.applicationContext, "currency problem", Toast.LENGTH_LONG).show()
            try {
                outputStream!!.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }

    }

}