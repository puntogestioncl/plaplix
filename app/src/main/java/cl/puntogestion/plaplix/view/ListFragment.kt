package cl.puntogestion.plaplix.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import cl.puntogestion.plaplix.R
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var adapter = ProductAdapter(mutableListOf())
        rListProducts.adapter = adapter

        viewModel.getAllProduct.observe(viewLifecycleOwner, Observer {
            adapter.updateItems(it)
        })

        adapter.product.observe(viewLifecycleOwner, Observer {
            viewModel.select(it)

            view?.findNavController()?.navigate(R.id.action_listFragment_to_detailActivity)
        })
    }

}