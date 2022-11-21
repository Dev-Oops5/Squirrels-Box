package com.miodemi.squirrelsbox.inventory.infrastructure

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.session.domain.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ExcelRepository {
    fun addBox(b: BoxData, context: Context){

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
        cell.setCellValue("Id")
        cell.cellStyle = cellStyle

        cell = row.createCell(1)
        cell.setCellValue("Name")
        cell.cellStyle = cellStyle

        cell = row.createCell(2)
        cell.setCellValue("Date Created")
        cell.cellStyle = cellStyle

        cell = row.createCell(3)
        cell.setCellValue("Box Type")
        cell.cellStyle = cellStyle

        cell = row.createCell(4)
        cell.setCellValue("Private Link")
        cell.cellStyle = cellStyle

        cell = row.createCell(5)
        cell.setCellValue("Download")
        cell.cellStyle = cellStyle

        cell = row.createCell(6)
        cell.setCellValue("Favourite")
        cell.cellStyle = cellStyle

        cell = row.createCell(7)
        cell.setCellValue("Author")
        cell.cellStyle = cellStyle

        cell = row.createCell(2)
        cell.setCellValue(b.name)

        val file = File(context.getExternalFilesDir(null), "items.xls")
        var outputStream: FileOutputStream? = null

        try {
            outputStream = FileOutputStream(file)
            wb.write(outputStream)
            Toast.makeText(context.applicationContext, "DataSheet Dowloaded", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context.applicationContext, "currency problem", Toast.LENGTH_LONG).show()
            try {
                outputStream!!.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }



    }

    fun getBoxById(boxId: String): Flow<State<Any>> = flow<State<Any>> {
        emit(State.loading())

        val box = FirebaseDatabase.getInstance().getReference("boxes").child(boxId).get().await()

        box?.let {
            val data = box.getValue<BoxData>()
            emit(State.success(data!!))
        }
    }.catch {
        emit(State.failed(it.message!!))
    }.flowOn(Dispatchers.IO)

}