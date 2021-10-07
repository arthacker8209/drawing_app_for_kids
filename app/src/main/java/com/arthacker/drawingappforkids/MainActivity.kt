package com.arthacker.drawingappforkids


import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.arthacker.drawingappforkids.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.dialog_brush_size.*

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

/*    @SuppressLint("CutPasteId")
    private fun brushSizeDialog(){
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush Size :")

        val layout = findViewById<ConstraintLayout>(R.id.layout)
        val small = layout?.findViewById<ImageButton>(R.id.ib_small_brush)
        val medium = layout?.findViewById<ImageButton>(R.id.ib_medium_brush)
        val large = layout?.findViewById<ImageButton>(R.id.ib_medium_brush)



        small?.setOnClickListener {
            binding.canvas.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        medium?.setOnClickListener {
            binding.canvas.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        large?.setOnClickListener {
            binding.canvas.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()*/
    }




/*
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.arthacker.drawingappforkids.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mImageButtonCurrentPaint: ImageButton? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawing_view.setSizeForBrush(20.toFloat()) // Setting the default brush size to drawing view.


        mImageButtonCurrentPaint = ll_paint_colors[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.pallet_pressed
            )
        )
        //END

        ib_brush.setOnClickListener {
            showBrushSizeChooserDialog()
        }
    }



}
*/
