package com.example.uccmobileapp

//code, name, credits, pre-requisites, description

data class Course(val code: String,
                  val title: String,
                  val credits: Int,
                  val preReqs: List<String>,
                  val description: String)
