package com.example.activiat3xml.imccalculator

import android.health.connect.datatypes.HeightRecord
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activiat3xml.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import org.w3c.dom.Text
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false
    private var currentWeight:Int = 60
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

    private lateinit var tvHeight: TextView
    private lateinit var rsHeight : RangeSlider

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusHeight: FloatingActionButton
    private lateinit var tvWeight: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun initComponents() {
            viewMale = findViewById(R.id.viewMale)
            viewFemale = findViewById(R.id.viewFemale)
            tvHeight = findViewById(R.id.tvHeight)
            rsHeight = findViewById(R.id.rsHeight)
            btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
            btnPlusHeight = findViewById(R.id.btnPlusWeight)
            tvWeight = findViewById(R.id.tvWeight)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor() }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor() }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            tvHeight.text = "~$result cm"
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnPlusHeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }
    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor((isMaleSelected)))
        viewFemale.setCardBackgroundColor(getBackgroundColor((isFemaleSelected)))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean) : Int{

        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }
    private fun initUI() {
        setGenderColor()
        setWeight()
    }
}