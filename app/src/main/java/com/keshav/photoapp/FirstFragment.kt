package com.keshav.photoapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.keshav.photoapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val TAG = "FirstFragment"
    private val selectedImages = mutableListOf<Uri>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pickImageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            resultLauncher.launch(intent)


        }
//
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }


    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                result.data?.let { intent ->
                    intent.data?.let { uri ->
                        selectedImages.add(uri)
                    }
                    if (intent.clipData != null) {
                        for (i in 0 until intent.clipData!!.itemCount) {
                            val uri = intent.clipData!!.getItemAt(i).uri
                            selectedImages.add(uri)
                        }
                    }
                }
                Log.d(TAG, "Image Size: ${selectedImages.size}")
                if (selectedImages.size >= 2) {
                    binding.pickImageBtn.visibility = View.GONE

                    indexAlgo()
//                    binding.apply {
//                        pickImageBtn.visibility = View.GONE
//                        imageView2.setImageURI(selectedImages[0])
//                        imageView5.setImageURI(selectedImages[1])
//                    }


                }


//            val data: Intent? = result.data
//            doSomeOperations()
            }
        }

    private fun indexAlgo() {
        val sizeOfArray = 50
        val result: ArrayList<Int> = ArrayList()
        for (i in 0..sizeOfArray) {
            if (result.isNotEmpty()) {
                result.add(result.last() + (i + 1))
            } else {
                result.add(i + 1)
            }
            if (result.last() >= sizeOfArray) {
                break
            }

        }




        drawTriangle(result)


    }

    private fun drawTriangle(result: ArrayList<Int>) {
        val rows = result.size - 1
        var k = 0
        var count = 0
        val itemList: ArrayList<Model> = ArrayList()

        for (i in 1..rows) {
            val model: Model = Model()
            for (space in 1..rows - i) {
                model.afterImageSpace.add(true)
//                print("  ")
            }

            while (k != 2 * i - 1) {
                count++
                if (result.contains(count)) {
                    model.imageUrl.add(selectedImages[0])
                    model.beforeImageSpace.add(true)


//                    print("Dog ")
                } else {
                    model.imageUrl.add(selectedImages[1])
                    model.afterImageSpace.add(true)
//                    print("Cat ")
                }
                ++k
            }

//            println()
            k = 0
            itemList.add(model)
        }

        val myAdapter = SingleItemAdapter(itemList)
        val myLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.apply {
            layoutManager = myLayoutManager
            adapter = myAdapter
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}