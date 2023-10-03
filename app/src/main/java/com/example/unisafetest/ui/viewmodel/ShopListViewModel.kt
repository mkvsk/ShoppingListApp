package com.example.unisafetest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unisafetest.core.Item
import com.example.unisafetest.core.ShoppingList
import com.example.unisafetest.network.callback.ResultCallback
import com.example.unisafetest.network.response.AddToShoppingListResponse
import com.example.unisafetest.network.response.CreateShoppingListResponse
import com.example.unisafetest.network.response.GetAllMyShopListsResponse
import com.example.unisafetest.network.response.GetShoppingListResponse
import com.example.unisafetest.network.response.RemoveFromListResponse
import com.example.unisafetest.network.response.RemoveShoppingListResponse
import com.example.unisafetest.ui.repository.ShoppingItemRepository
import com.example.unisafetest.ui.repository.ShoppingListRepository

class ShopListViewModel : ViewModel() {
    private val shoppingListRepository by lazy { ShoppingListRepository() }
    private val shoppingItemRepository by lazy { ShoppingItemRepository() }

    private val _allMyShopLists = MutableLiveData(ArrayList<ShoppingList>())
    val allMyShopLists: LiveData<ArrayList<ShoppingList>> get() = _allMyShopLists

    private fun setAllMyShopLists(value: ArrayList<ShoppingList>) {
        _allMyShopLists.value = value
    }

    private val _listCreated = MutableLiveData(false)
    val listCreated: LiveData<Boolean> get() = _listCreated

    private fun setListCreated(value: Boolean) {
        _listCreated.value = value
    }

    private val _shoppingList = MutableLiveData(ArrayList<Item>())
    val shoppingList: LiveData<ArrayList<Item>> get() = _shoppingList

    private fun setShoppingList(value: ArrayList<Item>) {
        _shoppingList.value = value
    }

    fun getAllMyShopLists(key: String) {
        shoppingListRepository.getAllMyShopLists(
            key,
            object : ResultCallback<GetAllMyShopListsResponse> {
                override fun onResult(value: GetAllMyShopListsResponse?) {
                    value?.let {
                        if (it.success) {
                            setAllMyShopLists(it.allMyShopLists)
                        }
                    }
                }

                override fun onFailure(value: GetAllMyShopListsResponse?) {

                }
            })
    }

    fun createShoppingList(key: String, name: String) {
        shoppingListRepository.createShoppingList(
            key,
            name,
            object : ResultCallback<CreateShoppingListResponse> {
                override fun onResult(value: CreateShoppingListResponse?) {
                    setListCreated(true)
                }

                override fun onFailure(value: CreateShoppingListResponse?) {
                    setListCreated(false)
                }
            })
    }

    fun getShoppingList(id: Int) {
        shoppingListRepository.getShoppingList(
            id,
            object : ResultCallback<GetShoppingListResponse> {
                override fun onResult(value: GetShoppingListResponse?) {
                    value?.let {
                        if (it.success) {
                            setShoppingList(it.itemList)
                        }
                    }
                }

                override fun onFailure(value: GetShoppingListResponse?) {

                }
            })
    }

    //    TODO
    fun removeShoppingList(listId: Int) {
        shoppingListRepository.removeShoppingList(
            listId,
            object : ResultCallback<RemoveShoppingListResponse> {
                override fun onResult(value: RemoveShoppingListResponse?) {
                    value?.let {
                        if (it.success) {
                            it.newValue
                        }
                    }
                }

                override fun onFailure(value: RemoveShoppingListResponse?) {

                }
            })
    }


    private val _itemAdded = MutableLiveData(false)
    val itemAdded: LiveData<Boolean> get() = _itemAdded

    private fun setItemAdded(value: Boolean) {
        _itemAdded.value = value
    }

    private val _itemRemoved = MutableLiveData(false)
    val itemRemoved: LiveData<Boolean> get() = _itemRemoved

    private fun setItemRemoved(value: Boolean) {
        _itemRemoved.value = value
    }

    fun addToShoppingList(id: Int, name: String, qty: Int) {
        shoppingItemRepository.addToShoppingList(
            id,
            name,
            qty,
            object : ResultCallback<AddToShoppingListResponse> {
                override fun onResult(value: AddToShoppingListResponse?) {
                    value?.let {
                        setItemAdded(it.success)
                    }
                }

                override fun onFailure(value: AddToShoppingListResponse?) {

                }
            })
    }

    fun removeFromList(listId: Int, itemId: Int) {
        shoppingItemRepository.removeFromList(
            listId,
            itemId,
            object : ResultCallback<RemoveFromListResponse> {
                override fun onResult(value: RemoveFromListResponse?) {
                    value?.let {
                        setItemRemoved(it.success)
                    }
                }

                override fun onFailure(value: RemoveFromListResponse?) {

                }
            })
    }
}