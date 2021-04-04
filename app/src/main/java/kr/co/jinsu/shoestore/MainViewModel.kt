package kr.co.jinsu.shoestore

import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.jinsu.shoestore.models.Shoe

class MainViewModel: ViewModel() {
    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    var email = ""
    var password = ""

    init {
        _shoeList.value = ArrayList()
    }

    fun addShoe(newShoe: Shoe){
        _shoeList.value?.add(newShoe)
    }
}