package mobile.muzaki.biodataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    val inputBiodataFragment = InputBiodataFragment()
    val manager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(inputBiodataFragment)
    }

    fun replaceFragment(fragment: Fragment){
        val backStateName: String = fragment::class.java.name
        val fragmentPopped: Boolean = manager.popBackStackImmediate(backStateName, 0)

        if (!fragmentPopped) { //fragment not in back stack, create it.
            val ft: FragmentTransaction = manager.beginTransaction()
            ft.replace(R.id.mainFrame, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    override fun onBackPressed() {
        Log.e("MainActivity","Jumlah Stack Fragment: "+manager.backStackEntryCount);
        if(manager.backStackEntryCount<=1){
           finish()
        }else{
            manager.popBackStack();
        }

    }

}