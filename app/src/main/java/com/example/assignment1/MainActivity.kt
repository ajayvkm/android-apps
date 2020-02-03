package com.example.assignment1

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val NAME : String = "name"
    val AGE : String = "age"
    val ORIGIN : String = "origin"
    val OS : String = "os"
    val ZERO : Int = 0

    // Var created to hold Origin Spinner selected position
    var originSelectedPosition : Int = ZERO

    // Var created to hold OS Spinner selected position
    var osSelectedPosition: Int = ZERO

    /**
     * Default function to initialize
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the saved user information from shared preference
        val pref = getPreferences(Context.MODE_PRIVATE)
        val name = pref.getString(NAME, "")
        val age = pref.getString(AGE, "")
        val originPosition = pref.getInt(ORIGIN, ZERO)
        val osPosition = pref.getInt(OS, ZERO)

        // Read Origin Option from String XML
        val originList = resources.getStringArray(R.array.Origin)

        // Read OS Option from String XML
        val osList = resources.getStringArray(R.array.OS)

        /**
         * Fill all the saved preferences to controls
         */
        val originAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, originList)
        originSpinner.adapter = originAdapter
        // Set selected option for Origin Spinner
        originSpinner.setSelection(originPosition)


        val osAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, osList)
        osSpinner.adapter = osAdapter
        // Set selected option for OS Spinner
        osSpinner.setSelection(osPosition)

        nameText.setText(name.toString())
        ageText.setText(age.toString())

        // Listener update the selected value to class var originSelectedPosition
        originSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                /**
                 * Find selected Item position to store and to show selected while reload
                 */
                originSelectedPosition = position//parent.getItemAtPosition(position).toString().toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        // Listener update the selected value to class var osSelectedPosition
        osSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                /**
                 * Find selected Item position to store and to show selected while reload
                 */
                osSelectedPosition = position//parent.getItemAtPosition(position).toString().toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        // Updating the saved Instance state to UI fields
        if (savedInstanceState != null) {
            if(null != savedInstanceState.get(NAME))
                nameText.setText(savedInstanceState.get(NAME).toString())

            if(null != savedInstanceState.get(AGE))
                ageText.setText(savedInstanceState.get(AGE).toString())

            if(null != savedInstanceState.getInt(ORIGIN) && ZERO != savedInstanceState.getInt(ORIGIN))
                originSpinner.setSelection(savedInstanceState.getInt(ORIGIN))

            if(null != savedInstanceState.getInt(OS) && ZERO != savedInstanceState.getInt(OS))
                osSpinner.setSelection(savedInstanceState.getInt(OS))
        }
    }

    /**
     * Function created to save Instance State on change on layout | Configuration
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        if(null != nameText.text)
            outState.putString(NAME, nameText.text.toString())

        if(null != ageText.text)
            outState.putString(AGE, ageText.text.toString())

        if(null != originSelectedPosition)
            outState.putInt(ORIGIN, originSelectedPosition)

        if(null != osSelectedPosition)
            outState.putInt(OS, osSelectedPosition)
    }

    /**
     * Save user input to UserInfo Class - and will be retrieved from the Same UserInfo class
     * Note: For now storing in just UserInfo class - will use database in later assignment
     */
    fun onSave(view: View) {
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()
        // Save Name
        editor.putString(NAME, nameText.text.toString())
        // Save Age
        editor.putString(AGE, ageText.text.toString())
        // Save Origin position
        editor.putInt(ORIGIN, originSelectedPosition)
        // Save OS position
        editor.putInt(OS, osSelectedPosition)

        editor.commit()

        val toast = Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP, ZERO, 140)
        toast.show()
    }
}
