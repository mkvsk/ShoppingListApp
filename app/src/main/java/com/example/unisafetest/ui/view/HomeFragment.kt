package com.example.unisafetest.ui.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.unisafetest.R
import com.example.unisafetest.core.ShoppingList
import com.example.unisafetest.databinding.FragmentHomeBinding
import com.example.unisafetest.ui.adapters.ShopListItemAdapter
import com.example.unisafetest.ui.listeners.OnShoppingListClickListener
import com.example.unisafetest.ui.viewmodel.ShopListViewModel
import com.example.unisafetest.ui.viewmodel.AuthenticationViewModel
import com.example.unisafetest.util.obtainViewModel

class HomeFragment : Fragment(), OnShoppingListClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val allMyShopListsViewModel by lazy { obtainViewModel(ShopListViewModel::class.java) }
    private val authViewModel by lazy { obtainViewModel(AuthenticationViewModel::class.java) }

    private var rv: RecyclerView? = null
    private var shopListItemAdapter: ShopListItemAdapter? = null
    private var data: ArrayList<ShoppingList> = ArrayList()

    companion object {
        const val KEY_RECYCLER_STATE = "recycler_state"
        const val TAG = "HomeFragment"
    }

    private var mBundleRecyclerViewState: Bundle? = null
    private var mListState: Parcelable? = null
    private var mRecyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupAdapter()
        getData()
        initListeners()
        initObservers()
    }

    private fun getData() {
        authViewModel.userKey.value?.let {
            allMyShopListsViewModel.getAllMyShopLists(it)
        }
    }

    private fun initViews() {
    }

    private fun setupAdapter() {
        rv = binding.rvShoppingList
        mRecyclerView = rv
        shopListItemAdapter = ShopListItemAdapter()
        shopListItemAdapter!!.setClickListener(this)
        rv?.adapter = shopListItemAdapter
        rv?.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY


        data.add(0, ShoppingList("123456", "Aaaa", 1))
        data.add(1, ShoppingList("123456", "Bbbb", 2))
        data.add(2, ShoppingList("123456", "Cccc", 3))
        data.add(3, ShoppingList("123456", "Dddd", 4))
        shopListItemAdapter!!.setData(data)
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            navigateToList()
        }
    }

    private fun navigateToList() {
        findNavController().navigate(R.id.action_go_to_list)
    }

    private fun initObservers() {
        allMyShopListsViewModel.allMyShopLists.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                data.addAll(it)
                shopListItemAdapter!!.setData(data)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onResume() {
        super.onResume()
        if (mBundleRecyclerViewState != null) {
            Looper.myLooper()?.let {
                Handler(it).post {
                    mListState = mBundleRecyclerViewState?.getBundle(KEY_RECYCLER_STATE)
                    mRecyclerView!!.layoutManager?.onRestoreInstanceState(mListState)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mBundleRecyclerViewState = Bundle()
        mListState = mRecyclerView!!.layoutManager?.onSaveInstanceState()
        mBundleRecyclerViewState?.putParcelable(KEY_RECYCLER_STATE, mListState)
    }

    override fun onShopListClick(listId: Int) {
        Toast.makeText(requireContext(), "$listId EDIT", Toast.LENGTH_SHORT).show()
    }

    override fun onShopListRemove(listId: Int) {
        Toast.makeText(requireContext(), "$listId REMOVED", Toast.LENGTH_SHORT).show()

    }
}