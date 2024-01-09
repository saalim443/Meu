package codeflies.com.meunavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import codeflies.com.meunavigation.R
import com.skydoves.elasticviews.ElasticButton


class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        // Access the ElasticButton in your layout and set a click listener
        val elasticButton: ElasticButton = view.findViewById(R.id.elasticButton)
        elasticButton.setOnClickListener {
            // Perform some action when the button is clicked
            showToast("Elastic Button Clicked")
        }

        return view
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
