package com.pucuk.binar_challenge_ch_4.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pucuk.binar_challenge_ch_4.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): User?

}
