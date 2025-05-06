package com.example.assignmentlokal.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignmentlokal.local.dao.BookmarkedJobsDao
import com.example.assignmentlokal.local.dao.JobDao
import com.example.assignmentlokal.local.dao.KeyDao
import com.example.assignmentlokal.local.entity.BookmarkJobEntity
import com.example.assignmentlokal.local.entity.JobEntity
import com.example.assignmentlokal.local.entity.KeyEntity

@Database(
    entities = [JobEntity::class, KeyEntity::class, BookmarkJobEntity::class],
    version = 1
)
abstract class JobDatabase: RoomDatabase() {
    abstract val jobDao: JobDao
    abstract val keyDao: KeyDao
    abstract val bookmarkDao: BookmarkedJobsDao

}