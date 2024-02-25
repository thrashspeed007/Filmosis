package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R

class GenreSelectedFragment : Fragment() {

    companion object {
        private const val ARG_GENRE_ID = "genreId"
        private const val ARG_GENRE_NAME = "genreName"

        fun newInstance(genreId: Int, genreName: String): GenreSelectedFragment {
            val fragment = GenreSelectedFragment()
            val args = Bundle()
            args.putInt(ARG_GENRE_ID, genreId)
            args.putString(ARG_GENRE_NAME, genreName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val genreId = arguments?.getInt(ARG_GENRE_ID) ?: -1
        val genreName = arguments?.getString(ARG_GENRE_NAME) ?: ""

        view.findViewById<TextView>(R.id.genreSelected_genreTitleTextView).text = genreName

        val popularMoviesRv = view.findViewById<RecyclerView>(R.id.genreSelected_popularMoviesRecyclerView)


    }
}