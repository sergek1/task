package com.example.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.adapters.ShoppingItemAdapter
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.AddDialogListener
import com.example.shoppinglist.ui.shoppinglist.AddShoppingItemDialog
import com.example.shoppinglist.ui.shoppinglist.ShoppingActivity
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.fragment_main.fab
import kotlinx.android.synthetic.main.fragment_main.rvShoppingItems
import kotlinx.coroutines.flow.launchIn

class MainFragment : Fragment() {

    private lateinit var viewModel: ShoppingViewModel
    private lateinit var shoppingItemsAdapter: ShoppingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as ShoppingActivity).viewModel
        shoppingItemsAdapter = ShoppingItemAdapter(listOf(), viewModel)
        fab.setOnClickListener {
            AddShoppingItemDialog(requireContext(),
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvShoppingItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shoppingItemsAdapter
        }
        viewModel.shoppingItems.launchIn(lifecycleScope)
    }
}