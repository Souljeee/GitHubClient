package com.soulje.githubclient.model.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

}