package mobile.muzaki.biodataapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_input_biodata.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mobile.muzaki.myfriendapp.AppDatabase


class InputBiodataFragment : Fragment() {
    var TAG="InputBiodataFragment"
    var nim = ""
    var nama = ""
    var prodi = ""
    var alamat=""

    private var id_edit = 0
    private val db by lazy { AppDatabase(requireContext()) }

    companion object {
        fun newInstance(): InputBiodataFragment {
            return InputBiodataFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_input_biodata, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun initView() {
        btnSave.setOnClickListener { validasiInput() }
    }

    private fun loadData() {
        db.biodataDao().getBiodata().observe(viewLifecycleOwner, { r ->
            if(r.size>0){
                val data = r.get(0)
                id_edit = data.id!!
                Log.e(TAG,"data:$data")
                etNama.setText(data.nama)
                etNim.setText(data.nim)
                etProdi.setText(data.prodi)
                etAlamat.setText(data.alamat)
            }
        })
    }

    private fun validasiInput() {
        nim = etNim.text.toString()
        nama = etNama.text.toString()
        prodi = etProdi.text.toString()
        alamat = etAlamat.text.toString()
        when{
            nim.isEmpty() -> etNama.error = "NIM tidak boleh kosong"
            nama.isEmpty() -> etNama.error = "Nama tidak boleh kosong"
            prodi.isEmpty() -> etNama.error = "Prodi tidak boleh kosong"
            alamat.isEmpty() -> etNama.error = "Alamat tidak boleh kosong"
            else -> {
                if(id_edit==0){
                    val data = Biodata(nama = nama, nim = nim, prodi = prodi, alamat = alamat)
                    tambahData(data)
                }else{

                    val data = Biodata(id = id_edit,nama = nama, nim = nim, prodi = prodi, alamat = alamat)
                    updateData(data)
                }
                (activity as MainActivity).replaceFragment(BiodataFragment());
            }
        }

    }

    private fun tambahData(data: Biodata) : Job {

        return GlobalScope.launch {
            db.biodataDao().tambahData(data)
        }

    }

    private fun updateData(data: Biodata) : Job {

        return GlobalScope.launch {
            db.biodataDao().updateData(data)
        }

    }

    private fun tampilToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}