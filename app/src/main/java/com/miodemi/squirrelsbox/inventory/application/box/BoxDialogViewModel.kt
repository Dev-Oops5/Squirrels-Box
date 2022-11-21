package com.miodemi.squirrelsbox.inventory.application.box

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import com.miodemi.squirrelsbox.inventory.domain.SectionData
import com.miodemi.squirrelsbox.inventory.infrastructure.*
import com.miodemi.squirrelsbox.session.domain.State
import kotlinx.coroutines.flow.collect

class BoxDialogViewModel : ViewModel() {

//    internal val data = MutableLiveData<String>()
//
//    fun setData(newData: String) {
//        data.value = newData
//    }
    private val boxRepository = BoxDialogRepository()
    private val sectionRepository = SectionDialogRepository()
    private val itemRepository = ItemDialogRepository()
    lateinit var boxDbHelper: BoxOpenHelper
    private val excelRepository = ExcelRepository()

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    fun setResult(currentResult: String) {
        _result.value = currentResult
    }

    fun setId(currentId: String) {
        _id.value = currentId
    }

    fun setName(currentName: String) {
        _name.value = currentName
    }

    fun setDate(createdDate: String) {
        _date.value = createdDate
    }


    fun storeData(name: String, boxType: Boolean, privateLink: String, download: Boolean, favourite: Boolean){
        boxRepository.storeData(name, boxType, privateLink, download, favourite)
    }

    fun updateData(currentName: String){
        _id.value?.let { boxRepository.updateData(it,currentName) }
    }

    fun deleteData(){
        _id.value?.let { boxRepository.deleteData(it) }
    }

    suspend fun downloadBox(context: Context) {

        _id.value?.let {

            boxDbHelper = BoxOpenHelper(context)

            boxRepository.getBoxById(it).collect() { state ->
                when (state) {
                    is State.Loading -> {
                        setResult("Downloading")
                    }
                    is State.Success -> {
                        boxDbHelper.addBox(state.data as BoxData)

                        //Download sections
                        downloadSection(it)
                    }
                    is State.Failed -> {
                        setResult(state.message)
                    }
                }
            }
        }
    }

    suspend fun exportBox(context: Context){
        _id.value?.let {

           // boxDbHelper = BoxOpenHelper(context)

            excelRepository.getBoxById(it).collect(){ state ->
                when (state) {
                    is State.Loading -> {
                        setResult("Exporting")
                    }
                    is State.Success -> {
                        excelRepository.exportBox(state.data as BoxData, context)

                        //Download sections
                       // downloadSection(it)
                    }
                    is State.Failed -> {
                        setResult(state.message)
                    }
                }
            }

        }
    }

    private suspend fun downloadSection(boxId: String) {
        sectionRepository.getSectionsByBoxId(boxId).collect() { sectionState ->
            when (sectionState) {
                is State.Loading -> {
                    setResult("Downloading")
                }
                is State.Success -> {

                    for (section in sectionState.data as List<SectionData>) {
                        boxDbHelper.addSection(section)

                        //Download items
                        downloadItem(section.boxId!!, section.name!!)
                    }
                }
                is State.Failed -> {
                    setResult(sectionState.message)
                }
            }
        }
    }

    private suspend fun downloadItem(boxId: String, sectionId: String) {
        itemRepository.getItemsByBoxIdAndSectionId(boxId, sectionId).collect() { itemState ->
            when (itemState) {
                is State.Loading -> {
                    setResult("Downloading")
                }
                is State.Success -> {
                    for (item in itemState.data as List<ItemData>) {
                        boxDbHelper.addItem(item)
                    }
                }
                is State.Failed -> {
                    setResult(itemState.message)
                }
            }
        }
    }

}