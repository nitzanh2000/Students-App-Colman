package com.idz.colman24class1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.colman24class1.model.Model
import com.idz.colman24class1.model.Student
import com.idz.colman24class1.StudentsRecyclerViewActivity

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentId = intent.getIntExtra("studentId", 0)
        var currentStudent : Student? = null

        studentId.let {
            currentStudent = Model.shared.students.get(studentId)
        }

        print(currentStudent)

        val nameText = findViewById<TextView>(R.id.student_details_name_text)
        val idText = findViewById<TextView>(R.id.student_details_id_text)
        val phoneText = findViewById<TextView>(R.id.student_details_phone_text)
        val addressText = findViewById<TextView>(R.id.student_details_address_text)
        val checkbox = findViewById<CheckBox>(R.id.student_details_checkbox)

        nameText.text = currentStudent?.name
        idText.text = currentStudent?.id
        phoneText.text = currentStudent?.phoneNumber
        addressText.text = currentStudent?.address
        checkbox.isChecked = currentStudent?.isChecked ?: false

        val backButton = findViewById<Button>(R.id.student_details_back_button)
        val editButton = findViewById<Button>(R.id.student_details_edit_button)

        backButton.setOnClickListener {
            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)
        }

        editButton.setOnClickListener {
            // todo - add edit logic
        }

    }
}