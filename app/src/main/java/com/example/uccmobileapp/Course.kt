package com.example.uccmobileapp

//code, name, credits, pre-requisites, description

data class Course(val id: Int,
                  val code: String,
                  val title: String,
                  val credits: Int,
                  val preReq: List<String>,
                  val description: String)
