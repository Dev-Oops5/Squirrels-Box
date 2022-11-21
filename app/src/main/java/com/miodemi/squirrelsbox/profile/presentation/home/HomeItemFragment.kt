package com.miodemi.squirrelsbox.profile.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentHomeItemBinding
import com.miodemi.squirrelsbox.profile.presentation.AddDialogViewFab
import com.miodemi.squirrelsbox.inventory.presentation.item.HomeItemAdapter
import com.miodemi.squirrelsbox.inventory.application.item.HomeItemViewModel
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class HomeItemFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeItemBinding

    private val viewModel : HomeItemViewModel by activityViewModels()
    private val viewModelFAB : AddDialogViewFab by activityViewModels()

    lateinit var _addview : AddDialogViewFab

    //Export variables
    private lateinit var btnExportExcel : Button
    //lateinit var tvData : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFAB.setData("item")
        //init data binding in a fragment
        binding = FragmentHomeItemBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val itemItemAdapter = HomeItemAdapter()
        binding.allItemsRv.adapter = itemItemAdapter

        viewModel.fetchNewsFeed()

        viewModel.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        //Initializing export variables
        btnExportExcel = binding.btnExport
        //tvData = findViewById(R.id.tvData)

        btnExportExcel.setOnClickListener{
            guardar();
        // Inflate the layout for this fragment
        }
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

        sheet = wb.createSheet("items list ")
        var row: Row? = null
        row = sheet.createRow(0)

        cell = row.createCell(0)
        cell.setCellValue("Item Name")
        cell.cellStyle = cellStyle

        cell = row.createCell(1)
        cell.setCellValue("Section Color")
        cell.cellStyle = cellStyle

        cell = row.createCell(2)
        cell.setCellValue("Description")
        cell.cellStyle = cellStyle

        cell = row.createCell(3)
        cell.setCellValue("Amount")
        cell.cellStyle = cellStyle



        row = sheet.createRow(1)
        cell = row.createCell(0)
        cell.setCellValue("T-shirt")

        cell = row.createCell(1)
        cell.setCellValue("Gray")

        cell = row.createCell(2)
        cell.setCellValue("T-shirt with good style")

        cell = row.createCell(3)
        cell.setCellValue("2")


        row = sheet.createRow(2)
        cell = row.createCell(0)
        cell.setCellValue("Coat")

        cell = row.createCell(1)
        cell.setCellValue("Red")

        cell = row.createCell(2)
        cell.setCellValue("wing Coat")

        cell = row.createCell(3)
        cell.setCellValue("4")


        row = sheet.createRow(3)
        cell = row.createCell(0)
        cell.setCellValue("Jacket")

        cell = row.createCell(1)
        cell.setCellValue("Blue")

        cell = row.createCell(2)
        cell.setCellValue("Jacket for rain")

        cell = row.createCell(3)
        cell.setCellValue("1")

        val file = File(getExternalFilesDir(null), "items.xls")
        var outputStream: FileOutputStream? = null

        try {
            outputStream = FileOutputStream(file)
            wb.write(outputStream)
            Toast.makeText(applicationContext, "DataSheet Dowloaded", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "currency problem", Toast.LENGTH_LONG).show()
            try {
                outputStream!!.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }

    }
}
