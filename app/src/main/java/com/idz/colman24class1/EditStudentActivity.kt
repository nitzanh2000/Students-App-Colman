package com.idz.colman24class1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.colman24class1.model.Model
import com.idz.colman24class1.model.Student

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentId = intent.getIntExtra("studentId", 0)
        var currStudent : Student? = null

        studentId.let {
            currStudent = Model.shared.students.get(studentId)
        }


        val nameText = findViewById<EditText>(R.id.edit_student_name_text)
        val idText = findViewById<EditText>(R.id.edit_student_id_text)
        val phoneText = findViewById<EditText>(R.id.edit_student_phone_text)
        val addressText = findViewById<EditText>(R.id.edit_student_address_text)
        val checkbox = findViewById<CheckBox>(R.id.edit_student_checkbox)

        nameText.setText(currStudent?.name)
        idText.setText(currStudent?.id)
        phoneText.setText(currStudent?.phoneNumber)
        addressText.setText(currStudent?.address)
        checkbox.isChecked = currStudent?.isChecked ?: false

        val saveButton = findViewById<Button>(R.id.edit_student_save_button)
        val deleteButton = findViewById<Button>(R.id.edit_student_delete_button)
        val cancelButton = findViewById<Button>(R.id.edit_student_cancel_button)

        saveButton.setOnClickListener {
            val student: Student = Student(nameText.text.toString(), idText.text.toString(), "", phoneText.text.toString(), addressText.text.toString(), checkbox.isChecked)
            Model.shared.students[studentId] = student
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("studentId", studentId)
            startActivity(intent)
        }

        deleteButton.setOnClickListener{
            Model.shared.students.removeAt(studentId)
            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)        }

        cancelButton.setOnClickListener {
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("studentId", studentId)
            startActivity(intent)
        }
    }
}