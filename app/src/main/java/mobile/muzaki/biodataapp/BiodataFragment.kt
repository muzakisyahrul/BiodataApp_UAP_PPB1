package mobile.muzaki.biodataapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_biodata.*
import mobile.muzaki.myfriendapp.AppDatabase


class BiodataFragment : Fragment() {
    var TAG="InputBiodataFragment"
    private val db by lazy { AppDatabase(requireContext()) }

    companion object {
        fun newInstance(): BiodataFragment {
            return BiodataFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_biodata, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        db.biodataDao().getBiodata().observe(viewLifecycleOwner, { r ->
            if(r.size>0){
                val data = r.get(0)
                Log.e(TAG,"data:$data")
                tvNama.setText(data.nama)
                tvNim.setText(data.nim)
                tvProdi.setText(data.prodi)
                tvAlamat.setText(data.alamat)
            }
        })
    }

}