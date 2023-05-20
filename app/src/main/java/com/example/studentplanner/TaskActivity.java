package com.example.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studentplanner.databinding.ActivityTaskBinding;

public class TaskActivity extends AppCompatActivity {

    ActivityTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  title = binding.editTextTitle.getText().toString();
                String  description = binding.editTextDescription.getText().toString();
                String  dueDate = binding.editTextDueDate.getText().toString();
                String  priority = binding.editTextPriority.getText().toString();
                String  subject = binding.editTextSubject.getText().toString();

                if (title.equals("") || description.equals("") || dueDate.equals("") || priority.equals("") || subject.equals("")) {
                    Toast.makeText(TaskActivity.this, "Provide All Information!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TaskActivity.this, "Task Added Successfully!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}