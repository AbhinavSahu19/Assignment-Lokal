package com.example.assignmentlokal.local.entity


class ContactPreferenceEmbedded(
    var preference: Int = 0,
    var whatsappLink: String = "",
    var preferredCallStartTime: String = "",
    var preferredCallEndTime: String = ""
)