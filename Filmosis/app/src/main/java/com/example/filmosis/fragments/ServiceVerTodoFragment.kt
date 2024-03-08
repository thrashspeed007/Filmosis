//package com.example.filmosis.fragments
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.filmosis.R
//import com.example.filmosis.adapters.GridRecyclerViewAdapter
//import com.example.filmosis.adapters.ServicioAdapter
//import com.example.filmosis.data.access.tmdb.MoviesAccess
//
//class ServiceVerTodoFragment : Fragment() {
//
//    private lateinit var recyclerViewservice: RecyclerView
//    private var moviesAccess = MoviesAccess()
//
//    private var ids: ArrayList<Int> = ArrayList<Int>().apply {
//        addAll(listOf(49, 213, 2739,18,94,50,2740))
//    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_service_ver_todo, container, false)
//
//        recyclerViewservice = view.findViewById(R.id.recyclerViewVerTodoServicios)
//        recyclerViewservice.setHasFixedSize(true)
//        recyclerViewservice.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//        for (id in ids) {
//            moviesAccess.fetchNetworkDetails(id) {network ->
//                adapter = ServicioAdapter(network)
//
//
//            }
//        }
//        recyclerViewservice.adapter = adapter
//
//
//        return view
//    }
//
//
//}