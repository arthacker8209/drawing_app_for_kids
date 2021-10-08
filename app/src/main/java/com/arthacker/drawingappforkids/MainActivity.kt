package com.arthacker.drawingappforkids


import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.arthacker.drawingappforkids.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_brush_size.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private  var currentColor: ImageButton?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.canvas.setSizeForBrush(20.toFloat())
        binding.ibBrush.setOnClickListener {
            brushSizeDialog() }

        currentColor= binding.llPaintColors[1] as ImageButton
        currentColor!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pallet_pressed))


    }


    private fun brushSizeDialog()  {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size :")
        val smallBtn = brushDialog.ib_small_brush
        smallBtn.setOnClickListener(View.OnClickListener {
            binding.canvas.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        })
        val mediumBtn = brushDialog.ib_medium_brush
        mediumBtn.setOnClickListener(View.OnClickListener {
            binding.canvas.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        })

        val largeBtn = brushDialog.ib_large_brush
        largeBtn.setOnClickListener(View.OnClickListener {
            binding.canvas.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        })
        brushDialog.show()

        binding.chooseImage.setOnClickListener {
            if (isReadStorageAllowed()){

            }

            else{
                requestStoragePermission()
            }
        }

    }

    fun paintClicked(view:View){
        if(view!==currentColor){
            val imageButton= view as ImageButton
            val colorTag = imageButton.tag.toString()
            binding.canvas.setColor(colorTag)
            imageButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pallet_pressed))
            currentColor!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.pallet_normal
                )
            )

            currentColor= view
        }
    }


    private fun requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,  arrayOf( android.Manifest.permission.READ_EXTERNAL_STORAGE,
          android.Manifest.permission.WRITE_EXTERNAL_STORAGE  ).toString())){
            Toast.makeText(this, "Need Permission", Toast.LENGTH_SHORT).show()
        }

        ActivityCompat.requestPermissions(this, arrayOf( android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE  ), STORAGE_PERMISSION_CODE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode== STORAGE_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Snackbar.make(binding.root, "Permission Granted", Snackbar.LENGTH_SHORT).show()
            }
            else{
                Snackbar.make(binding.root, "You Denied Permission", Snackbar.LENGTH_SHORT).show()
            }
        }

    }


    private fun isReadStorageAllowed():Boolean{
        val result = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
        return result== PackageManager.PERMISSION_GRANTED
    }

    companion object{
        private const val STORAGE_PERMISSION_CODE=111

    }





    }




